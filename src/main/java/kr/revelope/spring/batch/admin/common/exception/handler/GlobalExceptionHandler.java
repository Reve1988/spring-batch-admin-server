package kr.revelope.spring.batch.admin.common.exception.handler;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import kr.revelope.spring.batch.admin.common.exception.InvalidParameterException;

@ControllerAdvice
public class GlobalExceptionHandler {
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler(InvalidParameterException.class)
	@ResponseBody
	public String handleInvalidParameterException(InvalidParameterException exception) {
		return exception.getMessage();
	}
}
