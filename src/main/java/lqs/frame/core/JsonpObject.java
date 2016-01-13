package lqs.frame.core;

/**
 * fastjson的jsonp消息对象
 * 
 * @author liqingshan 2016-01-12
 *
 */
public class JsonpObject {
	private String function;
	private Object json;

	/**
	 * 构造函数
	 * 
	 * @param function
	 *            回调方法
	 * @param json
	 *            返回对象
	 */
	public JsonpObject(String function, Object json) {
		this.function = function;
		this.json = json;
	}

	public String getFunction() {
		return function;
	}

	public void setFunction(String function) {
		this.function = function;
	}

	public Object getJson() {
		return json;
	}

	public void setJson(Object json) {
		this.json = json;
	}
}
