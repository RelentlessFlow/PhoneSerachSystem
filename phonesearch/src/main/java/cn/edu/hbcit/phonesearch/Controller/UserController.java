package cn.edu.hbcit.phonesearch.Controller;

import cn.edu.hbcit.phonesearch.dto.UserLoginDTO;
import cn.edu.hbcit.phonesearch.dto.UserSettingDTO;
import cn.edu.hbcit.phonesearch.mapper.UserMapper;
import cn.edu.hbcit.phonesearch.model.Login;
import cn.edu.hbcit.phonesearch.service.AccountServer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

@Controller
public class UserController {

    @Autowired
    UserMapper userMapper;

    @Autowired
    AccountServer accountServer;



    @RequestMapping(value = "/user/login",method = RequestMethod.GET)
    public String userLogin(){
        return "user_login";
    }

    @RequestMapping(value = "/user/login",method = RequestMethod.POST)
    public String userLogin(@RequestParam(name = "username") String username,
                            @RequestParam(name = "password") String password,
                            HttpServletRequest httpServletRequest,
                            RedirectAttributes attributes){
        UserLoginDTO userLoginDTO = accountServer.UserRegister(username, password, httpServletRequest);

        if (userLoginDTO.getSuccess()) {
            attributes.addFlashAttribute("result",userLoginDTO.getResult());
            return "redirect:/index";
        }else {
            attributes.addFlashAttribute("result",userLoginDTO.getResult());
            return "redirect:/user/login";
        }
    }

    @RequestMapping(value = "/user/quit")
    public String userQuit(HttpServletRequest httpServletRequest)
    {
        accountServer.userQuit(httpServletRequest);
        return "index";
    }

    @RequestMapping(value = "user/setting",method = RequestMethod.GET)
    public String userSetting(@RequestParam(name = "password",defaultValue = "",required = false)String password, HttpServletRequest request, Model model){
        if (!password.equals("")){
            List li = accountServer.userGetInfo(accountServer.getUserName(request),password);
            model.addAttribute("users",li.get(0));
            return "userSetting";
        }
        return "userSetting";
    }

    @RequestMapping(value = "user/setting",method = RequestMethod.POST)
    public String userSetting(@RequestParam(name = "password") String password,
                              @RequestParam(name = "age") String age,
                              @RequestParam(name = "number") String number,
                              HttpServletRequest request,
                              RedirectAttributes attributes) {
        if (password.equals("") || age.equals("") || number.equals("") || accountServer.getUserName(request) == null) {
            attributes.addFlashAttribute("result", "您的输入的值为空,请重新输入！");
        } else {
            boolean ifSuss = accountServer.userSetting(password, age, number, request);
            if (ifSuss) {
                attributes.addFlashAttribute("result", "更改成功");
                return "redirect:/user/setting?" + "password=" + password;
            } else {
                attributes.addFlashAttribute("result", "您的输入有误,请重新输入！");
                return "redirect:/user/setting";
            }
        }
        return "redirect:/user/setting";
    }


//    updateuser

}
