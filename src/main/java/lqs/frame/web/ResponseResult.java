package lqs.frame.web;

/**
 * 服务器端响应的结果
 * 
 * @author liqingshan
 *
 * @param <T>
 *            请求返回的对象类型
 */
public class ResponseResult<T> {
	private boolean success;
	private String message;
	private T result;

	/**
	 * 是否成功
	 * @return 请求是否成功
	 */
	public boolean isSuccess() {
		return success;
	}

	/**
	 * 设置请求执行是否成功
	 * @param success 是否执行成功
	 */
	public void setSuccess(boolean success) {
		this.success = success;
	}

	/**
	 * 请求执行返回的状态信息
	 * @return 状态信息。执行失败时返回错误信息
	 */
	public String getMessage() {
		return message;
	}

	/**
	 * 设置请求返回的状体信息
	 * @param message 状态信息。执行失败时填写错误信息
	 */
	public void setMessage(String message) {
		this.message = message;
	}

	/**
	 * 请求返回的结果
	 * @return 返回的结果
	 */
	public T getResult() {
		return result;
	}

	/**
	 * 设置请求返回的结果
	 * @param result 返回的结果
	 */
	public void setResult(T result) {
		this.result = result;
	}
}
