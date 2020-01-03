package cn.edu.hbcit.phonesearch.dto;

import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class UserLoginDTO {
    private String username;
    private String result;
    private String qx;
    private Boolean success;

    public Boolean getSuccess() {
        return success;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }

    public UserLoginDTO() {
    }

    public UserLoginDTO(String username, String qx, String result, Boolean success) {
        this.username = username;
        this.result = result;
        this.qx = qx;
        this.success = success;
    }

    public UserLoginDTO(String result, Boolean success) {
        this.result = result;
        this.success = success;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public String getQx() {
        return qx;
    }

    public void setQx(String qx) {
        this.qx = qx;
    }
}