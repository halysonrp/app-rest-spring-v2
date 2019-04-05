package br.com.api.restful.responses;
/**
 * 
 */

import java.util.ArrayList;
import java.util.List;

/**
 * @author Halyson
 *
 */
public class Response<T>  {
	
	private T data;
	private List<String> mensagem = new ArrayList<String>();
	
	
	public Response() {
		
	}


	public T getData() {
		return data;
	}


	public void setData(T data) {
		this.data = data;
	}


	public List<String> getMensagem() {
		return mensagem;
	}


	public void setMensagem(List<String> mensagem) {
		this.mensagem = mensagem;
	}

}