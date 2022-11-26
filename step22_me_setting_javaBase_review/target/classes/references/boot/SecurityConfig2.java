package kosta.mvc.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.ldap.EmbeddedLdapServerContextSourceFactoryBean;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity 
public class SecurityConfig2 {
	@Bean
	protected SecurityFilterChain  configure(HttpSecurity http) throws Exception {
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
		
		return http.build();
	}
	
	
	 
	@Bean
    public InMemoryUserDetailsManager userDetailsService() {
        UserDetails user = 
        		User.withUsername("jang").password("{noop}3333")
        		.authorities("ROLE_USER")
                .build();
        
        UserDetails admin = 
        		User.withUsername("admin").password("{noop}3333")
        		.authorities("ROLE_USER", "ROLE_ADMIN")
                .build();
        
        return new InMemoryUserDetailsManager(user,admin );
    }
	   

	
}




