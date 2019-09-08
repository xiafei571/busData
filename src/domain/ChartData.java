package domain;

public class ChartData {

	private String data;
	
	private Integer value;

	public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
	}

	public Integer getValue() {
		return value;
	}

	public void setValue(Integer value) {
		this.value = value;
	}

	@Override
	public String toString() {
		return "ChartData [data=" + data + ", value=" + value + "]";
	}
}
