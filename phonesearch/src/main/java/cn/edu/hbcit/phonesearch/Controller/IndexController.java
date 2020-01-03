package cn.edu.hbcit.phonesearch.Controller;

import cn.edu.hbcit.phonesearch.service.AccountServer;
import cn.edu.hbcit.phonesearch.service.PhoneServer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;


@Controller
public class IndexController {

    @Autowired
    AccountServer accountServer;

    @Autowired
    PhoneServer phoneServer;

    @RequestMapping("index")
    public String Index() {
        return "index";
    }

    @RequestMapping("index/search")
    public String phoneSearch(HttpServletRequest request, RedirectAttributes attributes, Model model) {
        if (accountServer.getIfLogin(request)) {
            List recentPhone = phoneServer.findRecentPhoneInfo(50);
            model.addAttribute("lists",recentPhone);
            return "search";
        } else {
            attributes.addFlashAttribute("result", "请您登陆后再使用");
            return "redirect:/user/login";
        }
    }




    @RequestMapping("hello")
    public ModelAndView hello(HttpServletResponse response) throws IOException {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("hello");
//        modelAndView.addObject("username", "libai");
        return modelAndView;
    }

}