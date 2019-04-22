package br.com.api.restful.securitys.exceptions;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class BusinessException extends RuntimeException {

	private static final long serialVersionUID = 571793320849663039L;
	
	List<String> messages = new ArrayList<>();

    public void addMessages(String message) {
        messages.add(message);
    }

    public boolean hasError() {
        return !messages.isEmpty();
    }

    public String messages() {
        return messages.stream().collect(Collectors.joining(System.lineSeparator()));
    }
}
