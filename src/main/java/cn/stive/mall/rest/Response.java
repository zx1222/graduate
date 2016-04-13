package cn.stive.mall.rest;


public class Response {

	/* 请求成功 */
	public final static int CODE_SUCCESS = 0;
	public final static String MSG_SUCCESS = "success";

	public final static int CODE_SERVER_ERROR = 500;
	public final static String MSG_SERVER_ERROR = "服务器异常！";

	public final static int CODE_USER_ERROR = 421;


	
	int status;
	String msg;
	long time;
	Object data;

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public long getTime() {
		return time;
	}

	public void setTime(long time) {
		this.time = time;
	}

	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}

}
