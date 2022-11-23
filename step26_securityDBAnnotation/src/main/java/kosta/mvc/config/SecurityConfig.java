package kosta.mvc.config;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import kosta.mvc.security.MemberAuthenticationFailureHandler;
import kosta.mvc.security.MemberAuthenticationProvider;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Configuration
@EnableWebSecurity //WebSecurityConfigurerAdapter�⺻Ŭ������ Ȯ��(���)�ϰ� �ʿ��� �޼ҵ带 �������ؼ� ���.
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	private final MemberAuthenticationFailureHandler memberAuthenticationFailureHandler;
	private final MemberAuthenticationProvider memberAuthenticationProvider;
	//�����߻�-���ӺҰ� -> Autowired�� ������ �Ϸ��->passwordEncoder����, �� �ؿ� static����
	
//	@Autowired
//	private MemberAuthenticationFailureHandler memberAuthenticationFailureHandler;
//	@Autowired
//	private MemberAuthenticationProvider memberAuthenticationProvider;
	
	
	
	@PostConstruct
	public void init() {
		System.out.println("SecurityConfig init()");
	}
	

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http 
		.authorizeRequests()
		.antMatchers("/member/main")
		.authenticated()
		.antMatchers("/admin/main")
		.hasRole("ADMIN") //ROLE_ADMIN
		//.antMatchers(~~) �� ������ �� ����
		.and() //�±� �ϳ� �����ٴ� ��
		
		.formLogin() //�Ʒ� ������ �������
		.loginPage("/member/loginForm")
		.loginProcessingUrl("/login")
		.usernameParameter("id")
		.passwordParameter("password")
		.defaultSuccessUrl("/")
		.failureHandler(memberAuthenticationFailureHandler)
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
	public static BCryptPasswordEncoder getPassWordEncoder() {
		BCryptPasswordEncoder passwordEncoder=new BCryptPasswordEncoder();
		return passwordEncoder;
	}
	
}
