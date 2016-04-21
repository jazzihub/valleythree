package at.kfiw.valley3.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebFilter("/sign/*")
public class LoginFilter implements Filter
{

	public static final String LOGIN_PAGE = "login.xhtml";
	
	@Override
	public void destroy()
	{
				
	}

	public void doFilter(ServletRequest servletRequest,
			ServletResponse servletResponse, FilterChain filterChain)
			throws IOException, ServletException
	{
		HttpServletRequest httpServletRequest = (HttpServletRequest) servletRequest;
		HttpServletResponse httpServletResponse = (HttpServletResponse) servletResponse;
		HttpSession session = httpServletRequest.getSession(true);

		if (session.getAttribute("currentUser") != null)
		{
			filterChain.doFilter(servletRequest, servletResponse);
		} 
		else
		{
			// User ist nicht eingeloggt, Redirect zu Loginseite
			httpServletResponse.sendRedirect("../login.xhtml");
		}
	}
	@Override
	public void init(FilterConfig arg0) throws ServletException
	{
		
		
	}

}
