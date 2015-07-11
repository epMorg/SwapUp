package ct869.project.controllers;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
@WebFilter("/*")
public class AuthenticationFilter implements Filter {

	private ServletContext context;
	
	@Override
	public void init(FilterConfig fConfig) throws ServletException {
		this.context = fConfig.getServletContext();
        this.context.log("AuthenticationFilter initialized");	
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		
		HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse res = (HttpServletResponse) response;
         
        String uri = req.getRequestURI();
        this.context.log("Requested Resource::"+uri);
         
        HttpSession session = req.getSession(false);
         
        if(session == null && (uri.endsWith("TrustServlet") || uri.endsWith("TransactionController") || 
        		uri.endsWith("profilePage.jsp") || uri.endsWith("PhotoServlet") || uri.endsWith("UploadServlet")
        		|| uri.endsWith("FeedbackServlet") || uri.endsWith("RegistrationController") 
        		|| uri.endsWith("LogoutServlet")|| uri.endsWith("TransactionController") || uri.endsWith("ListingServlet")
        		|| uri.endsWith("activeListingsList.jsp") || uri.endsWith("bartererInfo.jsp") || uri.endsWith("bondOfTrustSuccess.jsp")
        		|| uri.endsWith("closedListingsList.jsp") || uri.endsWith("feedbackForm.jsp") || uri.endsWith("loginSuccess.jsp")
        		|| uri.endsWith("retry.jsp") || uri.endsWith("seeFeedback.jsp") || uri.endsWith("submitFeedback.jsp")
        		|| uri.endsWith("uploadSuccess.jsp") || uri.endsWith("webOfTrust.jsp") || uri.endsWith("severingOfTrustSuccess.jsp")
        		|| uri.endsWith("biddingPage.jsp") || uri.endsWith("bidsForMyListings.jsp") || uri.endsWith("closedlisting.jsp") 
        		|| uri.endsWith("createListing.jsp") || uri.endsWith("listingSuccessfullyCreated") || uri.endsWith("successfulBid")
        		|| uri.endsWith("AcceptBidController") || uri.endsWith("BidsToMeController") || uri.endsWith("CreateListingController")
        		|| uri.endsWith("ReturnToProfilePageController"))){
            this.context.log("Unauthorized access request");
            res.sendRedirect("login.html");
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
