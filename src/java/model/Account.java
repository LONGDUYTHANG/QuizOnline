

package model;

//import java.time.LocalDateTime;

public class Account  {
    
    private int user_id;
    private String full_name;
    private int gender;
    private String email;
    private String mobile;
    private String password;
    private Role role_id;
    private String avatar;

    public Account(int user_id, String full_name, int gender, String email, String mobile, String password, Role role_id, String avatar) {
        this.user_id = user_id;
        this.full_name = full_name;
        this.gender = gender;
        this.email = email;
        this.mobile = mobile;
        this.password = password;
        this.role_id = role_id;
        this.avatar = avatar;
    }

    public Account() {
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public String getFull_name() {
        return full_name;
    }

    public void setFull_name(String full_name) {
        this.full_name = full_name;
    }

    public int getGender() {
        return gender;
    }

    public void setGender(int gender) {
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

    public Role getRole_id() {
        return role_id;
    }

    public void setRole_id(Role role_id) {
        this.role_id = role_id;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    
    
    
}
