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
    private String first_name;
    private String last_name;
    private boolean gender;
    private String email;
    private String mobile;
    private String password;
    private String avatar;
    private int role_id;
    private Role role_id1;
    private int gender1;

    public Account() {
    }

    public Account(int account_id, String first_name, String last_name, boolean gender, String email, String mobile, String password, String avatar, int role_id) {
        this.account_id = account_id;

        this.first_name = first_name;
        this.last_name = last_name;
        this.gender = gender;
        this.email = email;
        this.mobile = mobile;
        this.password = password;
        this.avatar = avatar;
        this.role_id = role_id;
    }

    public Account(String first_name, String last_name, boolean gender, String mobile) {
        this.first_name = first_name;
        this.last_name = last_name;
        this.gender = gender;
        this.mobile = mobile;
    }

    public Account(int account_id, String first_name, String last_name, boolean gender, String mobile) {
        this.account_id = account_id;
        this.first_name = first_name;
        this.last_name = last_name;
        this.gender = gender;
        this.mobile = mobile;
    }

    public int getAccount_id() {
        return account_id;
    }

    public void setAccount_id(int account_id) {
        this.account_id = account_id;
    }

    public String getFirst_name() {
        return first_name;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public String getLast_name() {
        return last_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
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

    public Role getRole_id1() {
        return role_id1;
    }

    public void setRole_id1(Role role_id1) {
        this.role_id1 = role_id1;
    }

    public int getGender1() {
        return gender1;
    }

    public void setGender1(int gender1) {
        this.gender1 = gender1;
    }

}
