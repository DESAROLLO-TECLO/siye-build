package mx.com.teclo;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@Component
@Order(Ordered.HIGHEST_PRECEDENCE)
public class TrailingSlashRedirectingFilter extends OncePerRequestFilter {
	
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		
		if (!request.getRequestURI().endsWith("/") && request.getRequestURI().equals(request.getServletContext().getContextPath())) {
			ServletUriComponentsBuilder builder = ServletUriComponentsBuilder.fromRequest(request);
			builder.replacePath(String.format("%s/", builder.build().getPath()));
			response.setStatus(HttpStatus.MOVED_PERMANENTLY.value());
			response.setHeader(HttpHeaders.LOCATION, builder.toUriString());
		} else {
			filterChain.doFilter(request, response);
		}
	}

}
