package com.dvtt.demo.coredemo.filter;

import com.dvtt.demo.coredemo.service.LoggingServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpMethod;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.constraints.NotNull;
import java.io.IOException;
import java.util.UUID;

/**
 * Created by linhtn on 1/3/2022.
 */
@Slf4j
public class CustomURLFilter extends OncePerRequestFilter {

    private final LoggingServiceImpl loggingService;

    public CustomURLFilter() {
        loggingService = new LoggingServiceImpl();
    }

    @Override
    protected boolean isAsyncDispatch(@NotNull final HttpServletRequest request) {
        return false;
    }

    @Override
    protected boolean shouldNotFilterErrorDispatch() {
        return false;
    }

    @Override
    public void doFilterInternal(HttpServletRequest request, @NotNull HttpServletResponse response,
                                 @NotNull FilterChain filterChain) throws IOException, ServletException {

        String requestId = UUID.randomUUID().toString();
        request.setAttribute(LoggingServiceImpl.REQUEST_ID, requestId);
        if (DispatcherType.REQUEST.name().equals(request.getDispatcherType().name())
                && request.getMethod().equals(HttpMethod.GET.name())
                || DispatcherType.REQUEST.name().equals(request.getDispatcherType().name())
                && request.getMethod().equals(HttpMethod.PATCH.name())) {
            loggingService.logRequest(request, null);
        }
        filterChain.doFilter(request, response);
    }

}
