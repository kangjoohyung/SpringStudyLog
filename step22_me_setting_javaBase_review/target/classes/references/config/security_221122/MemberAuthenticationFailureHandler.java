package references.config.security_221122;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.stereotype.Service;

/**
 * 인증에 실패하면 호출되어지는 메소드로,
 * 인증에 실패했을때 해야 할 일을 작성한다.
 *
 */
@Service //id="memberAuthenticationFailureHandler"
public class MemberAuthenticationFailureHandler implements AuthenticationFailureHandler {

	@Override
	public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response,
			AuthenticationException exception) throws IOException, ServletException {
		System.out.println("MemberAuthenticationFailureHandler");
		
		request.setAttribute("errorMessage", exception.getMessage());
		request.getRequestDispatcher("/WEB-INF/views/member/loginForm.jsp").forward(request, response);

	}

}
