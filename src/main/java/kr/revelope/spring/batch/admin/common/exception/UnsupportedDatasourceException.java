package kr.revelope.spring.batch.admin.common.exception;

public class UnsupportedDatasourceException extends RuntimeException {
	public UnsupportedDatasourceException() {
	}

	public UnsupportedDatasourceException(String message) {
		super(message);
	}

	public UnsupportedDatasourceException(String message, Throwable cause) {
		super(message, cause);
	}

	public UnsupportedDatasourceException(Throwable cause) {
		super(cause);
	}

	public UnsupportedDatasourceException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}
}
