package common;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;

import util.Const;
import util.ErrorCodeEnum;

//@JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
@JsonInclude(content = JsonInclude.Include.NON_NULL)
public class Result<T> implements java.io.Serializable {

	/*
	 * @JsonIgnore 此注解用于属性上，作用是进行JSON操作时忽略该属性。
	 * 
	 * @JsonFormat 此注解用于属性上，作用是把Date类型直接转化为想要的格式，如@JsonFormat(pattern =
	 * "yyyy-MM-dd HH-mm-ss")。
	 * 
	 * @JsonProperty
	 * 此注解用于属性上，作用是把该属性的名称序列化为另外一个名称，如把trueName属性序列化为name，@JsonProperty("name")。
	 */

	private static final long serialVersionUID = 1L;

	// 是否成功标识
	private int status = 0;

	// 消息
	private String msg;

	// 返回的数据
	private T res;

	public Result() {
	}

	public Result(T res) {
		this.res = res;
	}

	public Result(ErrorCodeEnum errorCodeEnum, String extendMsg) {
		this.status = errorCodeEnum.getStatus();
		this.msg = errorCodeEnum.getMsg() + Const.WELL_NUMBER_SIGN + extendMsg;
	}

	public Result(ErrorCodeEnum errorCodeEnum) {
		this.status = errorCodeEnum.getStatus();
		this.msg = errorCodeEnum.getMsg();
	}

	public Result(int status, String msg) {
		this.status = status;
		this.msg = msg;
	}

	public Result(ErrorCodeEnum errorCodeEnum, T res) {
		this.status = errorCodeEnum.getStatus();
		this.msg = errorCodeEnum.getMsg();
		this.res = res;
	}

	@JsonIgnore
	public boolean isSuccess() {
		return status == 0 ? true : false;
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

	public T getRes() {
		return res;
	}

	public void setRes(T res) {
		this.res = res;
	}

	public String toString() {
		return ToStringBuilder.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE);
	}
}
