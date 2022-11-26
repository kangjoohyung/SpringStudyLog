package kosta.mvc.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;



//@Configuration
//@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter{
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {

		http
		.authorizeRequests()  //  security-context  <security:intercept-url
		.antMatchers("/user/login") //     pattern="/member/main" 
		.hasRole("USER")            //      access="isAuthenticated()"
		.antMatchers("/member/**")
		.authenticated()
		.antMatchers("/admin/**")
		.hasRole("ADMIN")
		.and()
		//.csrf().disable() // <security:csrf disabled="true"/>
		.formLogin()
		.loginPage("/user/loginForm")
		.loginProcessingUrl("/loginCheck")
		.usernameParameter("id")
		.passwordParameter("pwd")
		.defaultSuccessUrl("/")
		.failureForwardUrl("/user/loginForm?err")
		.and()
		.logout()
		.logoutUrl("/logout")
		.logoutSuccessUrl("/")
		.invalidateHttpSession(true)
		.deleteCookies("JSESSIONID")
		.and();
	}

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		//in Memory 사용
		auth.inMemoryAuthentication()
		.withUser("jang").password("{noop}1234").authorities("ROLE_USER")
		.and()
		.withUser("admin").password("{noop}1234").authorities("ROLE_USER, USER_ADMIN")
		;
	}
    
}
