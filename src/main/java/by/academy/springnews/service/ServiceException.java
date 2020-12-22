package by.academy.springnews.service;

public class ServiceException extends Exception{

	
	private static final long serialVersionUID = 2555608895996779826L;

	public ServiceException() {
		
	}

	public ServiceException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public ServiceException(String message, Throwable cause) {
		super(message, cause);
	}

	public ServiceException(String message) {
		super(message);
	}

	public ServiceException(Throwable cause) {
		super(cause);
	}
	

}
