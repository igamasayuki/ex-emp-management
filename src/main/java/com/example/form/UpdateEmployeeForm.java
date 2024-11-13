package com.example.form;

/**
 * 従業員情報を全権取得する.
 * 
 * @author M.aoki
 */
public class UpdateEmployeeForm {
  /** 従業員ID */
  private String id;

  /** 扶養人数 */
  private String dependentsCount;

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

}
