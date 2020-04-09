package mx.com.teclo.siye.negocio.service.async;

import java.lang.reflect.Method;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.aop.interceptor.AsyncUncaughtExceptionHandler;

public class CustomAsyncExceptionHandler implements AsyncUncaughtExceptionHandler {
	private static final Logger LOGGER = LoggerFactory.getLogger(CustomAsyncExceptionHandler.class);

	@Override
	public void handleUncaughtException(Throwable ex, Method method, Object... params) {
		LOGGER.error("***********error "+ex.getMessage());

	}

}
