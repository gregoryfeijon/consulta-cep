package br.com.gregoryfeijon.consultacep.model;

import br.com.gregoryfeijon.consultacep.util.LoggerUtil;
import org.objenesis.instantiator.util.ClassUtils;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;
import java.util.stream.Collectors;

/**
 * 07/03/2021 às 18:39:32
 * 
 * @author gregory.feijon
 */

public abstract class Builder<T> {

	private static final LoggerUtil LOG = LoggerUtil.getLog(Builder.class);

	public abstract T build();

	@SuppressWarnings("unchecked")
	protected T clone(T t) {
		T newT = null;
		Field lastField = null;
		Object lastValue = null;
		try {
			Class<T> clazz = (Class<T>) t.getClass();
			newT = ClassUtils.newInstance(clazz);
			Method[] methodsArray = clazz.getDeclaredMethods();

			List<Method> methods = Arrays.asList(methodsArray);
			List<Method> getters = methods.stream().filter(predicateGetterMethods()).collect(Collectors.toList());
			List<Method> setters = methods.stream().filter(predicateSetterMethods()).collect(Collectors.toList());

			Field[] fields = clazz.getDeclaredFields();
			for (Field field : fields) {
				lastField = field;
				int mod = field.getModifiers();
				if (!Modifier.isStatic(mod) && !Modifier.isFinal(mod)) {
					Optional<Method> opGetter = etter(field.getName(), getters);
					Optional<Method> opSetter = etter(field.getName(), setters);
					if (opGetter.isPresent() && opSetter.isPresent()) {
						Method setter = opSetter.get();
						Method getter = opGetter.get();
						Object value = getter.invoke(t);
						lastValue = value;
						String setterName = setter.getName();
						String getterName = getter.getName();
						LOG.info("setter: {0}; getter: {1}; value: {2}", setterName, getterName, value);
						if (value != null) {
							setter.invoke(newT, value);
						}
					}
				}
			}
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			LOG.severe("último campo: {0}; último valor: {1}", lastField, lastValue);
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}
		return newT;
	}

	private Predicate<Method> predicateSetterMethods() {
		return m -> m.getName().toLowerCase().startsWith("set");
	}

	private Predicate<Method> predicateGetterMethods() {
		return m -> m.getName().toLowerCase().startsWith("get") || m.getName().toLowerCase().startsWith("is");
	}

	private Optional<Method> etter(String name, List<Method> etters) {
		Optional<Method> first = etters.stream().filter(predicateNameEtter(name)).findFirst();
		return first;
	}

	private Predicate<Method> predicateNameEtter(String name) {
		return e -> e.getName().toLowerCase().endsWith(name.toLowerCase());
	}
}
