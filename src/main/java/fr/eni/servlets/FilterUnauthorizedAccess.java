package fr.eni.servlets;

import java.io.IOException;
import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebFilter(
        urlPatterns= {
            "/adminTools",
            "/deactivateAccount",
            "/reactivateAccount",
            "/deleteAccount",
            "/displayEditCategory",
            "/editCategory",
            "/deleteCategory",
            "/createNewCategory",
            "/editMy",
            "/creditShop",
            "/auctionDetails",
            "/createAuction",
            "/editMyAuction",
            "/cancelMyAuction"
        },
        dispatcherTypes= {
                DispatcherType.REQUEST,
                DispatcherType.INCLUDE,
                DispatcherType.FORWARD,
                DispatcherType.ERROR
        }
)
public class FilterUnauthorizedAccess implements Filter {

    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpSession laSession =((HttpServletRequest) request).getSession();
        if(laSession.getAttribute("isUserLoggedIn") != null) {
            chain.doFilter(request, response);
        }
        else {
            request.setAttribute("unauthorized", true);
            RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/login.jsp");
            dispatcher.forward(request, response);
        }
    }

    @Override
    public void destroy() {}

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {}

}
