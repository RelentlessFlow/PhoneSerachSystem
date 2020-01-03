package cn.edu.hbcit.phonesearch.dto;

public class UserSettingDTO {
    private String username;
    private String password;
    private String age;
    private String number;

    public String getUsername() {
        return username;
    }

    public UserSettingDTO(String username, String password, String age, String number) {
        this.username = username;
        this.password = password;
        this.age = age;
        this.number = number;
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
}
