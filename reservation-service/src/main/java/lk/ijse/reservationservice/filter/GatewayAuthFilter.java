package lk.ijse.reservationservice.filter;

import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class GatewayAuthFilter implements Filter {

    @Override
    public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain)
            throws IOException, ServletException {

        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) res;

        String userId = request.getHeader("x-user-id");

        if (userId == null || userId.isEmpty()) {
            response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Request must go through API Gateway");
            return;
        }

        chain.doFilter(req, res);
    }
}
