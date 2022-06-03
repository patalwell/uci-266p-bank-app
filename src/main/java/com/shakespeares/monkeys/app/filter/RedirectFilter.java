package com.shakespeares.monkeys.app.filter;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class RedirectFilter implements Filter {

  @Override
  public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
    HttpServletRequest request = (HttpServletRequest) servletRequest;
    HttpServletResponse response = (HttpServletResponse) servletResponse;
    String url = request.getParameter("url");

    if (url != null && isSameDomain(url)) {
      response.sendRedirect(url);
      return;
    }

    if (url != null && !isSameDomain(url)) {
      response.setStatus(HttpStatus.BAD_REQUEST.value());
      response.getOutputStream().flush();
      response.getOutputStream().println("Redirect Blocked");
    }

    filterChain.doFilter(request, response);
  }

  private boolean isSameDomain(String url) {
    return url.matches("/[^/\\\\]?.*");
  }
}
