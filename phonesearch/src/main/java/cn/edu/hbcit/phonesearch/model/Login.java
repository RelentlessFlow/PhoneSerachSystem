package cn.edu.hbcit.phonesearch.model;

import org.springframework.stereotype.Component;

@Component
public class Login {
    private String username;
    private String password;
    private String age;
    private String number;
    private String qx;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getQx() {
        return qx;
    }

    public void setQx(String qx) {
        this.qx = qx;
    }
}
