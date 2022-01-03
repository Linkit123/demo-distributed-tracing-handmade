package com.dvtt.demo.coredemo.service;

import com.google.gson.Gson;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by linhtn on 1/3/2022.
 */
@Service
@Slf4j
public class LoggingService {

    public static final String REQUEST_ID = "request_id";
    public final Gson gson;

    public LoggingService() {
        gson = new Gson();
    }

    public void logRequest(HttpServletRequest httpServletRequest, Object body) {
        Map<String, String> parameters = buildParametersMap(httpServletRequest);
        log.info("---------- BEGIN {} ----------", httpServletRequest.getAttribute(REQUEST_ID));
        log.info("method=[{}]", httpServletRequest.getMethod());
        log.info("path=[{}]", httpServletRequest.getRequestURI());

        if (!parameters.isEmpty()) {
            log.info("parameters=[{}]", parameters);
        }

        if (!ObjectUtils.isEmpty(body)) {
            log.info("body=[{}]", gson.toJson(body));
        }
        log.info("----------> processing...");
    }

    public void logResponse(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object body) {
        log.info("method=[{}]", httpServletRequest.getMethod());
        log.info("path=[{}]", httpServletRequest.getRequestURI());
        log.info("response_code=[{}]", httpServletResponse.getStatus());
        if (!ObjectUtils.isEmpty(body)) {
            log.info("response_body=[{}]", gson.toJson(body));
        }
        log.info("---------- END {} ----------", httpServletRequest.getAttribute(REQUEST_ID));
    }

    private Map<String, String> buildParametersMap(HttpServletRequest httpServletRequest) {
        var resultMap = new HashMap<String, String>();
        var parameterNames = httpServletRequest.getParameterNames();

        while (parameterNames.hasMoreElements()) {
            var key = parameterNames.nextElement();
            var value = httpServletRequest.getParameter(key);
            resultMap.put(key, value);
        }

        return resultMap;
    }

}