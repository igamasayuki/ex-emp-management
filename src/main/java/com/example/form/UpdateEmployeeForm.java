package com.example.form;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

/**
 * 従業員情報を全権取得する.
 * 
 * @author M.aoki
 */
public class UpdateEmployeeForm {
  /** 従業員ID */
  private String id;

  /** 扶養人数 */
  @NotNull(message = "扶養人数を入力してください")
  @Pattern(regexp = "^\\d+$", message = "半角数値で入力してください")
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
