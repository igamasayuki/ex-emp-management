package jp.co.sample.form;

public class UpdateEmployeeForm {
	/**
	 * フィールド属性
	 */
	private String id;
	private String dependentCount;
	
	@Override
	public String toString() {
		return "UpdateEmployeeForm [id=" + id + ", dependentCount=" + dependentCount + "]";
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getDependentCount() {
		return dependentCount;
	}

	public void setDependentCount(String dependentCount) {
		this.dependentCount = dependentCount;
	}
}