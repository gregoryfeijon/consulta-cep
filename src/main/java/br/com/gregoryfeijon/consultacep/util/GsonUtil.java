package br.com.gregoryfeijon.consultacep.util;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

/**
 * 04/03/2021 às 22:28:00
 * 
 * @author gregory.feijon
 */

public final class GsonUtil {

	private GsonUtil() {}

	private static final GsonBuilder gsonBuilder = new GsonBuilder().setPrettyPrinting();

	public static Gson getGson() {
		return gsonBuilder.create();
	}
}
