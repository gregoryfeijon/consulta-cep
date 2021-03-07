package br.com.gregoryfeijon.consultacep.util;

public final class CepUtil {

	private CepUtil() {}

	public static boolean validaCep(String cep) {
		if (StringUtil.isNull(cep)) {
			return false;
		}
		String aux = StringUtil.somenteNumeros(cep);
		return aux.length() == 8;
	}

	public static String trocaUltimoNumeroPorZero(String cep) {
		char[] aux = cep.toCharArray();
		for (int i = cep.length() - 1; i >= 0; i--) {
			char character = cep.charAt(i);
			if (character != '0') {
				aux[i] = '0';
				return new String(aux);
			}
		}
		return cep;
	}
}
