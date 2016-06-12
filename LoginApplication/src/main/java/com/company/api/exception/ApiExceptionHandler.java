package com.company.api.exception;

import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.MalformedJwtException;

@ControllerAdvice
@Order(Ordered.LOWEST_PRECEDENCE)
public class ApiExceptionHandler {

	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler({ NotAValidEmailAddressException.class })
	@ResponseBody
	public JsonError handleBadRequestException(Exception exception) {
		return getJsonMessage(exception);
	}

	@ResponseStatus(HttpStatus.UNAUTHORIZED)
	@ExceptionHandler({ AccessDeniedException.class, UnsuccessfulLoginException.class  })
	@ResponseBody
	public JsonError handleUnauthorizedException(Exception exception) {
		return getJsonMessage(exception);
	}

	@ResponseStatus(HttpStatus.FORBIDDEN)
	@ExceptionHandler({ TokenExpiredException.class, ExpiredJwtException.class, MalformedJwtException.class })
	@ResponseBody
	public JsonError handleTokenExpirationExceptions(Exception exception) {
		return getJsonMessage(exception);
	}

	private JsonError getJsonMessage(Exception exception) {
		if (ApiException.class.isInstance(exception)) {
			return new JsonError(((ApiException) exception).getCustomMessgae());
		}
		return new JsonError(exception.getMessage());
	}
}
