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
public class CustomExceptionHandler {

	@ExceptionHandler(BusinessException.class)
	public ExceptionResponse handler(BusinessException ex) {
		return new ExceptionResponse(ex.messages());
	}

	@ExceptionHandler(DataIntegrityViolationException.class)
	public ExceptionResponse handler(DataIntegrityViolationException ex) {
		return new ExceptionResponse(ex.getMessage());
	}

	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ExceptionResponse handler(MethodArgumentNotValidException ex) {
		BindingResult bindingResult = ex.getBindingResult();
		List<FieldError> fieldErrors = bindingResult.getFieldErrors();

		String mensagensFormatadas = fieldErrors.stream().map(FieldError::getDefaultMessage)
				.collect(joining(System.lineSeparator()));
		return new ExceptionResponse(mensagensFormatadas);
	}

	@ExceptionHandler(UnauthorizedUserException.class)
	@ResponseStatus(HttpStatus.UNAUTHORIZED)
	public ExceptionResponse handler(UnauthorizedUserException ex) {
		return new ExceptionResponse(ex.getMessage());
	}

	@ExceptionHandler(AccessDeniedException.class)
	@ResponseStatus(HttpStatus.UNAUTHORIZED)
	public ExceptionResponse handler(AccessDeniedException ex) {
		return new ExceptionResponse(ex.getMessage());
	}

	@ExceptionHandler(Exception.class)
	@ResponseStatus(HttpStatus.SERVICE_UNAVAILABLE)
	public ExceptionResponse handler(Exception ex) {
		return new ExceptionResponse("Erro inesperado");
	}

	@ExceptionHandler(HttpRequestMethodNotSupportedException.class)
	public ExceptionResponse handler(HttpRequestMethodNotSupportedException ex) {
		return new ExceptionResponse(ex.getMessage());
	}

	@ExceptionHandler(HibernateOptimisticLockingFailureException.class)
	public ExceptionResponse handler(HibernateOptimisticLockingFailureException ex) {
		return new ExceptionResponse(ex.getMessage());
	}
	
	@ExceptionHandler(value = HttpClientErrorException.class)
    public ExceptionResponse httpClientErrorExceptionHandler(HttpClientErrorException ex) {
        return new ExceptionResponse(ex.getMessage());
    }

    @ExceptionHandler(value = HttpServerErrorException.class)
    public ExceptionResponse httpServerErrorExceptionHandler(HttpServerErrorException ex) {
        return new ExceptionResponse(ex.getMessage());
    }

    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
    public ExceptionResponse methodArgumentTypeMismatchException(MethodArgumentTypeMismatchException ex) {
        return new ExceptionResponse("Tipo inv�lido");
    }

    @ExceptionHandler(HttpStatusCodeException.class)
    private ExceptionResponse handler(HttpStatusCodeException ex) {
    	return new ExceptionResponse(ex.getStatusText());
    }

}
