package br.com.gregoryfeijon.consultacep.util;

/**
 * 06/03/2021 às 18:49:10
 * 
 * <p>
 * Classe com métodos úteis para manipulação do CEP a ser consultado.
 * <p>
 * 
 * @author gregory.feijon
 *
 */
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
