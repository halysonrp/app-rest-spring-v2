package br.com.api.restful.securitys.exceptions;

import static java.util.stream.Collectors.joining;

import java.util.List;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.orm.hibernate5.HibernateOptimisticLockingFailureException;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.oauth2.common.exceptions.UnauthorizedUserException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.HttpStatusCodeException;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

@RestControllerAdvice
@ResponseStatus(HttpStatus.BAD_REQUEST)
public class HandlerException {

	@ExceptionHandler(BusinessException.class)
	public responseException handler(BusinessException ex) {
		return new responseException(ex.messages());
	}
	
	@ExceptionHandler(DataIntegrityViolationException.class)
	public responseException handler(DataIntegrityViolationException ex) {
		return new responseException(ex.getMessage());
	}

	@ExceptionHandler(MethodArgumentNotValidException.class)
	public responseException handler(MethodArgumentNotValidException ex) {
		BindingResult bindingResult = ex.getBindingResult();
		List<FieldError> fieldErrors = bindingResult.getFieldErrors();

		String mensagensFormatadas = fieldErrors.stream().map(FieldError::getDefaultMessage)
				.collect(joining(System.lineSeparator()));
		return new responseException(mensagensFormatadas);
	}

	@ExceptionHandler(UnauthorizedUserException.class)
	@ResponseStatus(HttpStatus.UNAUTHORIZED)
	public responseException handler(UnauthorizedUserException ex) {
		return new responseException(ex.getMessage());
	}

	@ExceptionHandler(AccessDeniedException.class)
	@ResponseStatus(HttpStatus.UNAUTHORIZED)
	public responseException handler(AccessDeniedException ex) {
		return new responseException(ex.getMessage());
	}

	@ExceptionHandler(Exception.class)
	@ResponseStatus(HttpStatus.SERVICE_UNAVAILABLE)
	public responseException handler(Exception ex) {
		return new responseException("Erro inesperado");
	}

	@ExceptionHandler(HttpRequestMethodNotSupportedException.class)
	public responseException handler(HttpRequestMethodNotSupportedException ex) {
		return new responseException(ex.getMessage());
	}

	@ExceptionHandler(HibernateOptimisticLockingFailureException.class)
	public responseException handler(HibernateOptimisticLockingFailureException ex) {
		return new responseException(ex.getMessage());
	}
	
	@ExceptionHandler(value = HttpClientErrorException.class)
    public responseException httpClientErrorExceptionHandler(HttpClientErrorException ex) {
        return new responseException(ex.getMessage());
    }

    @ExceptionHandler(value = HttpServerErrorException.class)
    public responseException httpServerErrorExceptionHandler(HttpServerErrorException ex) {
        return new responseException(ex.getMessage());
    }

    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
    public responseException methodArgumentTypeMismatchException(MethodArgumentTypeMismatchException ex) {
        return new responseException("Tipo inválido");
    }

    @ExceptionHandler(HttpStatusCodeException.class)
    private responseException handler(HttpStatusCodeException ex) {
    	return new responseException(ex.getStatusText());
    }

}
