package jp.co.sample.form;

import javax.validation.constraints.Size;

public class UpdateEmployeeForm{
	//従業員ID
	String id;
	//扶養人数
	@Size(min=1,max=2)
	String dependentsCount;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getDependentsCount() {
		return dependentsCount;
	}
	public void setDependentsCount(String dependentsCount) {
		this.dependentsCount = dependentsCount;
	}
	@Override
	public String toString() {
		return "UpdateEmployeeForm [id=" + id + ", dependentsCount=" + dependentsCount + "]";
	}
	
	
}
