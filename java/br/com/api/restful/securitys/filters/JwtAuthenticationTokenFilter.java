package br.com.api.restful.securitys.filters;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.oauth2.common.exceptions.UnauthorizedUserException;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.web.filter.OncePerRequestFilter;

import br.com.api.restful.dtos.LoginDTO;
import br.com.api.restful.entities.User;
import br.com.api.restful.securitys.utils.JwtTokenUtil;
import br.com.api.restful.services.IAuthService;
import br.com.api.restful.utils.PasswordUtils;



public class JwtAuthenticationTokenFilter extends OncePerRequestFilter {

	private static final String AUTH_HEADER = "Authorization";
	private static final String BEARER_PREFIX = "Bearer ";
	
	@Autowired
	private IAuthService loginService;

	@Autowired
	private JwtTokenUtil jwtTokenUtil;
		
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
			throws ServletException, IOException {
		String token = request.getHeader(AUTH_HEADER);
		
		if (token != null && token.startsWith(BEARER_PREFIX)) {
			token.replace(BEARER_PREFIX, "");
			
		}
		
		
		String email = jwtTokenUtil.getEmailFromToken(token);

		if (email != null && SecurityContextHolder.getContext().getAuthentication() == null) {
			User user = loginService.findByEmail(email);

			if (jwtTokenUtil.tokenValid(token)) {
				UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(
						user.getEmail(), user.getPassword());
				authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
				SecurityContextHolder.getContext().setAuthentication(authentication);
			}
		}

		chain.doFilter(request, response);
	}

}
