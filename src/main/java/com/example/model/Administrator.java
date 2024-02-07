package com.example.model;

public class Administrator {
    /** ID */
    private Integer id;

    /** name */
    private String name;

    /** mailAddress */
    private String mailAddress;

    /** password */
    private String password;

    public Administrator() {
    }

    public Administrator(Integer id, String name, String mailAddress, String password) {
        this.setId(id);
        this.setName(name);
        this.setMailAddress(mailAddress);
        this.setPassword(password);
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMailAddress() {
        return mailAddress;
    }

    public void setMailAddress(String mailAddress) {
        this.mailAddress = mailAddress;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "Administrator [id=" + id + ", name=" + name + ", mailAddress=" + mailAddress + ", password="
                + "*********"
                + "]";
    }

}
