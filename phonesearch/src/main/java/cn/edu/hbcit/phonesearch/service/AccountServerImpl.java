package cn.edu.hbcit.phonesearch.service;

import cn.edu.hbcit.phonesearch.dto.SetPageInfoDTO;
import cn.edu.hbcit.phonesearch.dto.UserLoginDTO;
import cn.edu.hbcit.phonesearch.mapper.UserMapper;
import cn.edu.hbcit.phonesearch.model.Login;
import cn.edu.hbcit.phonesearch.model.Mobile;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Component
public class AccountServerImpl implements AccountServer {

    @Autowired
    UserMapper userMapper;

    @Override
    public UserLoginDTO UserRegister(String username, String password, HttpServletRequest request) {
        UserLoginDTO dto = new UserLoginDTO();
        if (username.equals("") || password.equals("")) {
            dto.setResult("输入值为空，请重新输入");
            dto.setSuccess(false);
        } else {
            List<Login> ifExist = userMapper.findByName(username);
            if (ifExist.size() == 0) {
                System.out.println("数据库不存在该账号");
                userMapper.insert(username, password);
                System.out.println("用户账号创建成功");
                request.getSession().setAttribute("qx", 0);
                request.getSession().setAttribute("username", username);
                dto.setSuccess(true);
                dto.setResult("新建用户数据已记录浏览器");
            } else {
                dto = UserLogin(username, password, request);
                request.getSession().setAttribute("qx", dto.getQx());
                request.getSession().setAttribute("username", dto.getUsername());
                dto.setResult("登陆成功");
            }
            return dto;
        }
        return dto;
    }

    @Override
    public UserLoginDTO UserLogin(String username, String password, HttpServletRequest request) {
        UserLoginDTO dto;
        List<Login> loginList = userMapper.findByNumAndPass(username, password);
        if (loginList.size() > 0) {
            dto = new UserLoginDTO(
                    loginList.get(0).getUsername(),
                    loginList.get(0).getQx(),
                    "用户已找到",
                    true);
        } else {
            dto = new UserLoginDTO(
                    "用户名或者密码错误",
                    false);
        }
        return dto;
    }

    @Override
    public Boolean userQuit(HttpServletRequest httpServletRequest) {
        httpServletRequest.getSession().setAttribute("qx", null);
        httpServletRequest.getSession().setAttribute("username", null);
        return true;
    }


    @Override
    public Boolean userSetting(String password, String age, String number, HttpServletRequest request) {
        return userMapper.updateByUser(getUserName(request), password, age, number) >= 1;
    }

    @Override
    public List<Login> userGetInfo(String username, String password) {
        return userMapper.findByNumAndPass(username, password);
    }

    // 管理员功能
    @Override
    public boolean insert(String username, String password, String age, String number, String qx) {
        if (userMapper.findByName(username).size() >= 1) {
            return false;
        } else {
            return userMapper.insertByAdmin(username, password, age, number, qx) >= 1;
        }
    }

    @Override
    public boolean del(String username) {
        if (userMapper.findByName(username).size() >= 1) {
            return userMapper.delByUsrName(username) >= 1;
        } else {
            return false;
        }
    }

    @Override
    public boolean updateByAdmin(String username, String password, String age, String number, String qx) {
        if (userMapper.findByName(username).size() >= 1) {
            return userMapper.updateByAdmin(username, password, age, number, qx) >= 1;
        } else {
            return false;
        }
    }

    @Override
    public PageInfo findUserPageList(SetPageInfoDTO dto) {
        PageHelper.startPage(dto.getPageNum(),dto.getPageSize());
        List ls = userMapper.findAll();
        PageInfo pageInfo = new PageInfo(ls);
        return pageInfo;
    }

    @Override
    public List<Login> findUserInfoByName(String username) {
        List<Login> ls = userMapper.findByName(username);
        if (ls.size()>=1){
            return ls;
        }else {
            return null;
        }
    }


    @Override
    public Boolean getIfLogin(HttpServletRequest httpServletRequest) {
        return httpServletRequest.getSession().getAttribute("username") != null;
    }

    @Override
    public String getUserName(HttpServletRequest httpServletRequest) {
        return (String) httpServletRequest.getSession().getAttribute("username");
    }

    /**
     * 1 ： 管理员
     * 0 ： 用户
     *
     * @param httpServletRequest
     * @return
     */
    @Override
    public String getPower(HttpServletRequest httpServletRequest) {
        return (String) httpServletRequest.getSession().getAttribute("qx");
    }


}
