package com.itwill.security.config;

import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.session.DisableEncodeUrlFilter;

import com.itwill.security.domain.PrincipalDetails;


@EnableWebSecurity(debug = true)
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	
	
	   @Override
	    protected void configure(HttpSecurity http) throws Exception {
		   http
		   .csrf().disable()
		   .authorizeRequests()
		 
		   .antMatchers("/join/**").permitAll()  
           .antMatchers("/user/**").authenticated()     
           .antMatchers("/manager/**").access("hasRole('MANAGER') or hasRole('ADMIN')")	
           .antMatchers("/admin/**").hasRole("ADMIN")	
           .anyRequest().permitAll()
 	  .and()					//추가
       .formLogin()				// form기반의 로그인인 경우
           .loginPage("/loginForm")		// 인증이 필요한 URL에 접근하면 /loginForm으로 이동
           .usernameParameter("username")		// 로그인 시 form에서 가져올 값(id, email 등이 해당)
           .passwordParameter("password")		// 로그인 시 form에서 가져올 값
           .loginProcessingUrl("/login")		// 로그인을 처리할 URL 입력
           .defaultSuccessUrl("/")			// 로그인 성공하면 "/" 으로 이동
           .failureUrl("/loginForm")		//로그인 실패 시 /loginForm으로 이동
       .and()
       .logout()					// logout할 경우
       	.logoutUrl("/logout")			// 로그아웃을 처리할 URL 입력
           .logoutSuccessUrl("/");			// 로그아웃 성공 시 "/"으로 이동
	    }

	  @Override
	  public void configure(WebSecurity web) throws Exception {
	      web.ignoring()
	              .requestMatchers(
	                      PathRequest.toStaticResources().atCommonLocations()
	              )
	              ;
	  }

	/*
	authorizeRequests()	Security처리에 HttpServletRequest를 이용한다
	
	ExpressionUrlAuthorizationConfigurer을 불러온다.
	.antMatchers("경로")				특정 경로 지정
	.anyRequest()					설정한 경로 외의 모든 경로
	.authenticated()					인증된 사용자만 접근할 수 있다
	.permitAll()						인증없이 접근할 수 있다
	.denyAll()						인증없이는 접근할 수 없다
	.access(String str)				SpEL표현식의 결과가 true이면 접근할 수 있다
	.hasRole(String role)			사용자가 해당되는 Role이 있다면 접근할 수 있다
	.hasAnyRole(String role)			사용자가 가진 Role 중 해당되는 Role이 하나라도 존재한다면 접근할 수 있다
	.anonymous()						익명사용자가 접근할 수 있다
	.rememberMe()					rememberMe인증 사용자가 접근할 수 있다.
	
	-------------------------------------------------------
	formLogin()	form기반으로 로그인 할 경우의 설정을 추가할 수 있다
	
	FormLoginConfigurer를 불러온다.
	.loginPage(String url)			로그인 페이지 경로를 호출한다. default값은 "/login" 이다.
	.usernameParameter(String str)	스프링 시큐리티에서 사용자를 구분할 수 있는 값을 가져온다.
									default값은 "username"으로, <form>의 <input name="username">이면 작성하지 않아도 된다.
									<input name="id">인 경우에 작성해준다.
	.passwordParameter(String str)	스프링 시큐리티에서 사용자를 인증할 수 잆는 값을 가져온다.
									default값은 "password"로, <form>의 <input name="password">이면 작성하지 않아도 된다.
									<input name="pw">인 경우에 작성해준다.
	.loginProcessingUrl(String url)	로그인을 처리할 url을 설정한다. default값은 "/login" 이다.
									<form> 태그의 action속성과 맞추어준다.
	.defaultSuccessUrl(String url)	로그인 성공 시 url로 이동한다
	.failureUrl(String url)			로그인 실패 시 url로 이동한다. default값은 "/login?error" 이다.
	.logout()						로그아웃 시 설정을 추가할 수 있다.
	
	LogoutConfigurer을 불러온다.
	.logoutUrl(String url)			로그아웃을 처리할 url을 설정한다. default값은 "/logout" 이다.
									로그아웃 버튼의 href속성과 맞추어준다.
	.logoutSuccessUrl(String url)	로그아웃 성공 시 url로 이동한다
	 */
}