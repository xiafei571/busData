package util;

public interface Const {

	String WELL_NUMBER_SIGN = "#";

	/**
	 * 表单状态
	 */
	public interface FormStatus {
		/** 添加 */
		Integer ADD = 0;
		/** 更新 */
		Integer UPDATE = 1;
		/** 展示 */
		Integer SHOW = 2;
	}

	enum GenderType {
		// 未知
		UNKNOWN(0, "未知"),
		// 男
		MAN(1, "男"),
		// 女
		WOMAN(2, "女");

		private int sex;

		private String gender;

		GenderType(int sex, String gender) {
			this.sex = sex;
			this.gender = gender;
		}

		public int getSex() {
			return sex;
		}

		public void setSex(int sex) {
			this.sex = sex;
		}

		public String getGender() {
			return gender;
		}

		public void setGender(String gender) {
			this.gender = gender;
		}
	}
	
	public interface DateFilter {
		/** 年 */
		Integer Year = 0;
		/** 月 */
		Integer Month = 1;
		/** 日 */
		Integer Day = 2;
	}

}
