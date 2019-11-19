package com.tianque.commons.util;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * @see 响应实体
 * @author Administrator
 * @since 2018年8月8日 12:53:36
 */
@ApiModel("返回响应数据")
public class ResponseData {
	@ApiModelProperty(value = "操作成功与否")
	protected Boolean success = true;
	@ApiModelProperty(value = "响应状态码，0表示成功")
	protected int code = 0;
	@ApiModelProperty(value = "返回响应消息内容")
	protected String msg;
	@ApiModelProperty(value = "返回的响应数据")
	protected Object data;

	@ApiModelProperty(value = "返回的数据数量")
	protected int rows = 0;

	@ApiModelProperty(value = "返回的数据，云效用")
	protected Object result;

	@ApiModelProperty(value = "返回的错误信息，云效用")
	protected String errorMsg;

	public Boolean getSuccess() {
		return success;
	}

	public void setSuccess(Boolean success) {
		this.success = success;
	}

	public int getCode() {
		return code;
	}

	public int getRows() {
		return rows;
	}

	public void setRows(int rows) {
		this.rows = rows;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	/**
	 * @return the result
	 */
	public Object getResult() {
		return result;
	}

	/**
	 * @param result
	 *            the result to set
	 */
	public void setResult(Object result) {
		this.result = result;
	}

	/**
	 * @return the errorMsg
	 */
	public String getErrorMsg() {
		return errorMsg;
	}

	/**
	 * @param errorMsg
	 *            the errorMsg to set
	 */
	public void setErrorMsg(String errorMsg) {
		this.errorMsg = errorMsg;
	}

	public ResponseData() {

	}

	/**
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "ResponseData [success=" + success + ", code=" + code + ", msg="
				+ msg + ", data=" + data + ", rows=" + rows + ", result="
				+ result + ", errorMsg=" + errorMsg + "]";
	}

	protected ResponseData(Boolean success, int code, String msg, Object data,
			int rows) {
		super();
		this.success = success;
		this.code = code;
		this.msg = msg;
		this.data = data;
		this.rows = rows;
		this.result = data;
		this.errorMsg = msg;
	}

	public static ResponseData getSuccessResult() {
		return new ResponseData(Boolean.TRUE, 0, "", null, 0);
	}

	public static ResponseData getSuccessResult(Object data) {
		return new ResponseData(Boolean.TRUE, 0, "", data, 0);
	}

	public static ResponseData getSuccessResult(Object data, String message) {
		return new ResponseData(Boolean.TRUE, 0, message, data, 0);
	}

	public static ResponseData getSuccessResult(String message) {
		return new ResponseData(Boolean.TRUE, 0, message, null, 0);
	}

	public static ResponseData getSuccessResult(Object data, int size) {
		return new ResponseData(Boolean.TRUE, 0, "", data, size);
	}
}
