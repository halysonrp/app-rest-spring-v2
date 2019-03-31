package br.com.api.restful.responses;
/**
 * 
 */

import java.util.List;

/**
 * @author Halyson
 *
 */
public class Response<T>  {
	
	private T data;
	private List<String> erros;
	
	
	public Response() {
		
	}


	public T getData() {
		return data;
	}


	public void setData(T data) {
		this.data = data;
	}


	public List<String> getErros() {
		return erros;
	}


	public void setErros(List<String> erros) {
		this.erros = erros;
	}

}