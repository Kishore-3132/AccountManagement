package com.kcv.account.management.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.MethodParameter;
import org.springframework.http.HttpInputMessage;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.RequestBodyAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

import java.io.IOException;
import java.lang.reflect.Type;

@ControllerAdvice
@Slf4j
// This class logs incoming request bodies and outgoing response bodies in JSON format
// GET and DELETE with query parameters logging cannot be done here due to framework limitations
public class LoggingAdvice implements RequestBodyAdvice, ResponseBodyAdvice<Object> {

    private final ObjectMapper objectMapper = new ObjectMapper();

    // ---------------- Request Logging ----------------
    @Override
    public boolean supports(MethodParameter methodParameter, Type targetType,
                            Class<? extends HttpMessageConverter<?>> converterType) {
        return true;
    }

    @Override
    public HttpInputMessage beforeBodyRead(HttpInputMessage inputMessage, MethodParameter parameter,
                                           Type targetType,
                                           Class<? extends HttpMessageConverter<?>> converterType) throws IOException {
        return inputMessage;
    }

    @Override
    public Object afterBodyRead(Object body, HttpInputMessage inputMessage, MethodParameter parameter,
                                Type targetType, Class<? extends HttpMessageConverter<?>> converterType) {
        try {
            String json = objectMapper.writeValueAsString(body);
            log.info("JSON REQUEST: {}", json);
        } catch (Exception e) {
            log.warn("Failed to log request body as JSON", e);
        }
        return body;
    }

    @Override
    public Object handleEmptyBody(Object body, HttpInputMessage inputMessage, MethodParameter parameter,
                                  Type targetType, Class<? extends HttpMessageConverter<?>> converterType) {
        return body;
    }

    // ---------------- Response Logging ----------------
    @Override
    public boolean supports(MethodParameter returnType, Class<? extends HttpMessageConverter<?>> converterType) {
        return true;
    }

    @Override
    public Object beforeBodyWrite(Object body, MethodParameter returnType, MediaType selectedContentType,
                                  Class<? extends HttpMessageConverter<?>> selectedConverterType,
                                  ServerHttpRequest request, ServerHttpResponse response) {
        try {
            String json = objectMapper.writeValueAsString(body);
            log.info("JSON RESPONSE: {}",json);
        } catch (Exception e) {
            log.warn("Failed to log response body as JSON", e);
        }
        return body;
    }

    // ---------------- GET/DELETE Query Params Logging ----------------
//    public void logQueryParams(HttpServletRequest request) {
//        if (request.getParameterMap().isEmpty()) return;
//
//        Map<String, String[]> paramMap = request.getParameterMap();
//        try {
//            String jsonParams = objectMapper.writeValueAsString(paramMap);
//            log.info("Incoming Request (Query Params JSON): method={}, URI={}, params={}",
//                    request.getMethod(), request.getRequestURI(), jsonParams);
//        } catch (Exception e) {
//            log.warn("Failed to log query parameters as JSON", e);
//        }
//    }
}
