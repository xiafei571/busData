package util;

public enum ErrorCodeEnum {
	// 部门相关
	DEPT_NOT_EXIST(1001, "部门不存在"),
	// 员工相关
	EMP_NOT_EXIST(2001, "员工不存在"),
	// 通用
	INVALID_PARAMETER(9000, "非法参数"), CONTENT_NOT_NULL(9001, "内容不能为空"), ALREADY_EXIST(9002, "数据已存在"),
	MULTI_UPDATE(9003, "重复请求"), SYSTEM_ERROR(9999, "系统异常");

	private int status;

	private String msg;

	private ErrorCodeEnum(int status, String msg) {
		this.status = status;
		this.msg = msg;
	}

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

}
