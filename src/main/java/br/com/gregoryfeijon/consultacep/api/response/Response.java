package br.com.gregoryfeijon.consultacep.api.response;

import lombok.Data;

import java.util.List;

/**
 * 04/03/2021 às 22:01:19
 * 
 * @author gregory.feijon
 */

@Data
public class Response<T> {

	private T data;
	private List<String> errors;
}
