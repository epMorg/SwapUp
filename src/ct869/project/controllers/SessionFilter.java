package ct869.project.controllers;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class SessionFilter implements Filter {
	
	private ServletContext context;
	
	@Override
	public void init(FilterConfig fConfig) throws ServletException {
		this.context = fConfig.getServletContext();
        this.context.log("SessionFilter initialized");	
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse res = (HttpServletResponse) response;
   
        HttpSession session = req.getSession(false);
         
        if(session == null) {
        	res.sendRedirect("sessionExpired.html");
        }else{
            // pass the request along the filter chain
            chain.doFilter(request, response);
        }
      }
	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		
	}
}
