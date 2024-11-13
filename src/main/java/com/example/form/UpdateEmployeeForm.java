package com.example.form;

/**
 * 従業員情報の更新を行うクラス
 * 
 * @author Yutaka Nishizawa
 * @param id              従業員ID
 * @param dependentsCount 扶養人数
 */
public class UpdateEmployeeForm {
    private String id;

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

    @Override
    public String toString() {
        return "UpdateEmployeeForm [id=" + id + ", dependentsCount=" + dependentsCount + "]";
    }

}
