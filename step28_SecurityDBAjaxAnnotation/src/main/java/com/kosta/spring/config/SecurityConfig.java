package com.kosta.spring.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.kosta.spring.security.MemberAuthenticationFailureHandler;
import com.kosta.spring.security.MemberAuthenticationProvider;



@Configuration
@EnableWebSecurity 
public class SecurityConfig extends WebSecurityConfigurerAdapter{

	@Autowired
	MemberAuthenticationFailureHandler memberAuthenticationFailurHandler;
	
	@Autowired
	MemberAuthenticationProvider memberAuthenticationProvider;
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {

		http
		.authorizeRequests()  //  security-context  <security:intercept-url
		.antMatchers("/admin/**") //     pattern="/member/main" 
		.hasRole("ADMIN")            //      access="isAuthenticated()"
		.antMatchers("/member/**")
		.authenticated()
		.and()
		//.csrf().disable() // <security:csrf disabled="true"/>
		.formLogin()
		.loginPage("/loginForm")
		.loginProcessingUrl("/j_spring_security_check")
		.usernameParameter("id")
		.passwordParameter("password")
		.defaultSuccessUrl("/")
		.failureHandler(memberAuthenticationFailurHandler)
		.and()
		.logout()
		.logoutUrl("/member/logout")
		.logoutSuccessUrl("/")
		.invalidateHttpSession(true)
		.deleteCookies("JSESSIONID")
		.and();
	}

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.authenticationProvider(memberAuthenticationProvider);
	}
	
	
	@Bean
	public static BCryptPasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
    
}
