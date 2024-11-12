package com.example.form;

/**
 * @Author: 金丸天
 *          リクエストパラメータを受け取るクラス
 * @param id:              ID
 * @param dependentsCount: 扶養⼈数
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
