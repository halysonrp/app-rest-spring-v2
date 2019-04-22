package br.com.api.restful.securitys.handlers;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.http.MediaType;

import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

@Component
public class JwtUnauthorizedEntryPoint implements AuthenticationEntryPoint {

	@Override
	public void commence(HttpServletRequest request, HttpServletResponse response,
			AuthenticationException authException) throws IOException {
		// response.sendError(HttpServletResponse.SC_UNAUTHORIZED,
		// "Acesso negado. Você deve estar autenticado no sistema para acessar a URL
		// solicitada.");
		response.getStatus();
		authException.getMessage();
		authException.getCause();
		response.setContentType(MediaType.APPLICATION_JSON_UTF8_VALUE);
		response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
	}
}
