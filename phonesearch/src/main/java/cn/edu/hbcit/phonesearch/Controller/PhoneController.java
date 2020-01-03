package cn.edu.hbcit.phonesearch.Controller;

import cn.edu.hbcit.phonesearch.mapper.PhoneMapper;
import cn.edu.hbcit.phonesearch.model.Mobile;
import cn.edu.hbcit.phonesearch.service.AccountServer;
import cn.edu.hbcit.phonesearch.service.PhoneServer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

@Controller
public class PhoneController {


    @Autowired
    private AccountServer accountServer;

    @Autowired
    private PhoneServer phoneServer;

    @RequestMapping(value = "/phone/search",method = RequestMethod.POST)
    public String phoneSearch(@RequestParam(name = "num") String num, Model model,RedirectAttributes attributes)
    {
        if (num.equals("")){
            attributes.addFlashAttribute("result","您的输入为空，请重新输入");
            return "redirect:../index/search";
        }
        List mobiles = phoneServer.findPhoneInfoByNum(num);
        if (mobiles.size()>=1){
            model.addAttribute("lists", mobiles);
            return "search";
        }else {
            attributes.addFlashAttribute("result","查询结果为空");
            return "redirect:/phone/search";
        }
    }


    @RequestMapping(value = "/phone/search",method = RequestMethod.GET)
    public String phoneSearch(RedirectAttributes attributes,
                              HttpServletRequest request,Model model){
        if (accountServer.getIfLogin(request)) {
            List mobiles = phoneServer.findRecentPhoneInfo(20);
            model.addAttribute("lists", mobiles);
            return "search";
        }
        return "search";
    }


}
