package cn.uu710.filter;

import cn.uu710.domain.AdminInfo;

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

@WebFilter(filterName="adminFilter",urlPatterns={"/admin/*"})
public class AdminFilter implements Filter {

	public AdminFilter() {
	}

	public void destroy() {

	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		HttpServletRequest httpRequest=(HttpServletRequest) request;
		HttpServletResponse httpResponse=(HttpServletResponse) response;
		HttpSession session=httpRequest.getSession();
		AdminInfo adminInfo=(AdminInfo) session.getAttribute("login_admin");
		if(adminInfo==null){
			httpResponse.sendRedirect(httpRequest.getContextPath()+"/admin_login.jsp");
			return;
		}
		chain.doFilter(request, response);
	}


	public void init(FilterConfig fConfig) throws ServletException {
		
	}

}
