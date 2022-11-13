package com.example.test.config;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

@Component
public class RequestInterceptor implements HandlerInterceptor {

	private static final String ERROR_MESSAGE = "Invalid Character found in request target, unacceptable chars: %s";
	private static final String UNACCEPTABLE_CHARACTERS = "[]{}|\\^<>~`%+=";

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {

		//Check path variables
		if (StringUtils.containsAny(request.getServletPath(), UNACCEPTABLE_CHARACTERS)) {
			throw new IllegalArgumentException(String.format(ERROR_MESSAGE, UNACCEPTABLE_CHARACTERS));
		}

		//Check request parameters
		List<String[]> paramValues = new ArrayList<>(request.getParameterMap().values());
		for (var paramValue : paramValues) {
			if (StringUtils.containsAny(paramValue[0], UNACCEPTABLE_CHARACTERS)) {
				throw new IllegalArgumentException(String.format(ERROR_MESSAGE, UNACCEPTABLE_CHARACTERS));
			}
		}

		return true;
	}
}
