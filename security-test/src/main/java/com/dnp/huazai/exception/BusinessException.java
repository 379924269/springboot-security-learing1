package com.dnp.huazai.exception;

/**
 * <p> 业务异常继承自顶层异常
 * @author Xin.L
 */
public class BusinessException extends RuntimeException {

	private static final long serialVersionUID = 5634206236313900714L;

	public BusinessException() {
		super();
	}
	
	public BusinessException(String msg) {
		super(msg);
	}
	
	public BusinessException(Throwable th) {
		super(th);
	}
	
	public BusinessException(String msg, Throwable th) {
		super(msg, th);
	}
}
