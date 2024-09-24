/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author ADMIN
 */
public class Account {

    private int account_id;
    private String full_name;
    private boolean gender;
    private String email;
    private String mobile;
    private String password;
    private String avatar;
    private int role_id;

    public Account() {
    }

    public Account(int account_id, String full_name, boolean gender, String email, String mobile) {
        this.account_id = account_id;
        this.full_name = full_name;
        this.gender = gender;
        this.email = email;
        this.mobile = mobile;
    }

    public Account(int account_id, String full_name, boolean gender, String email, String mobile, String password, String avatar, int role_id) {
        this.account_id = account_id;
        this.full_name = full_name;
        this.gender = gender;
        this.email = email;
        this.mobile = mobile;
        this.password = password;
        this.avatar = avatar;
        this.role_id = role_id;
    }

    public int getAccount_id() {
        return account_id;
    }

    public void setAccount_id(int account_id) {
        this.account_id = account_id;
    }

    public String getFull_name() {
        return full_name;
    }

    public void setFull_name(String full_name) {
        this.full_name = full_name;
    }

    public boolean isGender() {
        return gender;
    }

    public void setGender(boolean gender) {
        this.gender = gender;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public int getRole_id() {
        return role_id;
    }

    public void setRole_id(int role_id) {
        this.role_id = role_id;
    }

}
