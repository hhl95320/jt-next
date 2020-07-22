package com.jt.exception;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpRequest;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.fasterxml.jackson.databind.util.JSONPObject;
import com.jt.vo.SysResult;

import lombok.extern.slf4j.Slf4j;

@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandle {
	/**
	 * 全局处理异常，多异常
	 * @param e
	 * @return
	 */
//	@ExceptionHandler({IOException.class})
//	public SysResult runtimeException(Exception e) {
//		e.printStackTrace();
//		log.info("exception:{}",e.getMessage());
//		return SysResult.fail(e.getMessage());
//	}
	@ExceptionHandler({RuntimeException.class,IOException.class,})
	public Object systemJsonp(Exception e,HttpServletRequest request) {
		e.printStackTrace();
		String callback = request.getParameter("callback");
		if(StringUtils.isEmpty(callback))
			return SysResult.fail(e.getMessage());
		if(!"GET".equals(request.getMethod()))
			return SysResult.fail(e.getMessage());
		
		log.info("exception:{}",e.getMessage());
		return new JSONPObject(callback, SysResult.fail(e.getMessage()));
	}
	
}
