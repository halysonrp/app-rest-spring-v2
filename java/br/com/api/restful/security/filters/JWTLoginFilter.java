package br.com.api.restful.security.filters;

import java.io.IOException;
import java.util.Collections;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import com.fasterxml.jackson.databind.ObjectMapper;

import br.com.api.restful.dtos.LoginDTO;
import br.com.api.restful.security.services.TokenAuthenticationService;

public class JWTLoginFilter extends AbstractAuthenticationProcessingFilter {

	public JWTLoginFilter(String url, AuthenticationManager authManager) {
		super(new AntPathRequestMatcher(url));
		setAuthenticationManager(authManager);
	}

	/*
	 * attemptAuthentication é quem lida com a tentativa de autenticação. Pegamos
	 * o usernamee password da requisição, e utilizamos o AuthenticationManager
	 * para verificar se os dados são correspondentes aos dados do nosso usuário
	 * existente.
	 */
	@Override
	public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
			throws AuthenticationException, IOException, ServletException {
		// Objeto Responsável pela credencial de autenticação ou seja o objeto login
		LoginDTO credentials = new ObjectMapper().readValue(request.getInputStream(), LoginDTO.class);

		return getAuthenticationManager().authenticate(new UsernamePasswordAuthenticationToken(credentials.getEmail(),
				credentials.getPassword(), Collections.emptyList()));
	}

	@Override
	protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response,
			FilterChain filterChain, Authentication auth) throws IOException, ServletException {

		TokenAuthenticationService.addAuthentication(response, auth.getName());
	}

}
