package cn.edu.hbcit.phonesearch.service;

import cn.edu.hbcit.phonesearch.dto.SetPageInfoDTO;
import cn.edu.hbcit.phonesearch.dto.UserLoginDTO;
import cn.edu.hbcit.phonesearch.model.Login;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.List;


public interface AccountServer {


    UserLoginDTO UserRegister(String username, String password, HttpServletRequest request);


    UserLoginDTO UserLogin(String username, String password, HttpServletRequest request);


    Boolean userQuit(HttpServletRequest httpServletRequest);

    Boolean userSetting(String password,String age,String number,HttpServletRequest request);

    List<Login> userGetInfo(String username, String password);


    // 管理员操作
    boolean insert(String username,String password,String age,String number,String qx);

    boolean del(String username);

    boolean updateByAdmin(String username,String password,String age,String number,String qx);

    PageInfo findUserPageList(SetPageInfoDTO dto);

    List<Login> findUserInfoByName(String username);

    // 获取用户当前状态
    Boolean getIfLogin(HttpServletRequest httpServletRequest);
    String getUserName(HttpServletRequest httpServletRequest);
    String getPower(HttpServletRequest httpServletRequest);


}
