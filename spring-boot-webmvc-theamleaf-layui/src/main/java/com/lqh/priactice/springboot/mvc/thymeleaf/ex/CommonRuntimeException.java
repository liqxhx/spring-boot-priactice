/**
 * Copyright (c) 2016, qhlee. All rights reserved.
 * qhlee版权所有.
 *
 * 审核人:qhlee
 */
package com.lqh.priactice.springboot.mvc.thymeleaf.ex;


import org.springframework.util.StringUtils;
import com.lqh.priactice.springboot.mvc.thymeleaf.utils.I18n;
import java.util.List;
import java.util.Locale;

public class CommonRuntimeException extends RuntimeException implements IndexedMessage {

	private static final long serialVersionUID = 6326450420472654286L; 

	private String code;
	private Object[] params;
	private List<Locale> locales;

	public CommonRuntimeException(String code, Throwable e, List<Locale> locales, Object... params) {
		super(e);
		this.code = code;
		this.params = params;
		this.locales = locales;		
	}

	public CommonRuntimeException(String code, Throwable e, Object... params) {
		this(code, e, null, params);
	}

	public CommonRuntimeException(String code, Object... params) {
		this(code, null, params);
	}

	public CommonRuntimeException(Throwable e) {
		this(null, null, e);
	}

	public CommonRuntimeException() {
	}

	@Override
	public String getMessage() {
		return assembleMessage(getLocalizedMessage());
	}

	@Override
	public String getLocalizedMessage() {
		if (locales == null || locales.isEmpty()) {
			return getLocalizedExceptionMessage(Locale.getDefault());
		}

		String exceptionMessage = null;
		for (Locale locale : locales) {
			if (locale != null) {
				exceptionMessage = getLocalizedExceptionMessage(locale);
				if (exceptionMessage != null) {
					return assembleMessage(exceptionMessage);
				}
			}
		}

		return getMessage();
	}

	/**
	 * 根据语言国家信息获取本地异常信息
	 * <p>
	 * 
	 * @param locale
	 *            语言国家信息
	 * @return 本地异常信息
	 */
	public String getLocalizedExceptionMessage(Locale locale) {
		if (StringUtils.isEmpty(getCode())) {
			return null;
		}
		return I18n.getMessage(getCode(), getParameters(), getCode(), locale);
	}

	/**
	 * 获取异常信息字符长度
	 * 
	 * @return 字符长度
	 */
	public int getMessageLength() {
		if (StringUtils.isEmpty(getCode())) {
			return 0;
		}

		if (null != getLocalizedMessage()) {
			return getLocalizedMessage().length();
		} else {
			return 0;
		}

	}

	// [ERRORCODE=20161107lqh0000][this is a test!!!]
	private String assembleMessage(String exceptionMessage) {
		return new StringBuilder("[ERRORCODE=")
				.append(getCode()).append("] [")
				.append(StringUtils.isEmpty(exceptionMessage) ? "":exceptionMessage).append(']').toString();
	}

	@Override
	public String getCode() {
		return code;
	}

	@Override
	public Object[] getParameters() {
		return params;
	}

	public List<Locale> getLocales() {
		return locales;
	}

	public void setLocales(List<Locale> locales) {
		this.locales = locales;
	}

	@Override
	public String toString() {

		String s = getClass().getName();
		String message = getLocalizedMessage();

		StringBuilder sb = new StringBuilder(s).append("[ERRORCODE=")
				.append(getCode()).append("] [")
				.append(message == null ? "" : message).append("]");

		return sb.toString();
	}

}