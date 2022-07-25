package com.example.demo.security;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class JwtAuthenticationFilter extends OncePerRequestFilter {

	@Autowired
	private TokenProvider tokenProvider;
	
	@Override
	protected void doFilterInternal(HttpServletRequest request,HttpServletResponse response,
			FilterChain filterchain)throws ServletException,IOException{
		//요청에서 토큰 가져 오기
		try {
			String token = parseBearerToken(request);
			log.info("filter is running");
			//토큰 검사, 인가서버에 요청하지 않고도 검증 가능
			if (token !=null && !token.equalsIgnoreCase("null")) {
				//userid가져오기 , 위조시 예외처리
				String userId = tokenProvider.validateAndGetUserId(token);
				log.info("user ID"+userId);
				//인증 완료
				AbstractAuthenticationToken authentication = new UsernamePasswordAuthenticationToken
						(userId //인증된 사용자의 정보, 아무거나 넣을 수 있다 보통 userdetail이라는 오브젝트를 넣지만 여기선 그러지 않았음
								, null,
								AuthorityUtils.NO_AUTHORITIES);
				authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
				SecurityContext securityContext = SecurityContextHolder.createEmptyContext();
				securityContext.setAuthentication(authentication);
				SecurityContextHolder.setContext(securityContext);			
			 }
			}catch(Exception ex) {
				logger.error("blablabla " ,ex);
			}
		filterchain.doFilter(request, response);		
	}
	
	private String parseBearerToken(HttpServletRequest request) {
		String bearerToken = request.getHeader("authorization");
		
		if(StringUtils.hasText(bearerToken)&&bearerToken.startsWith("Bearer")) {
			return bearerToken.substring(7);			
		}		
		return null;
	}
	
}
