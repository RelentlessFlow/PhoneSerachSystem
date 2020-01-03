package cn.edu.hbcit.phonesearch.Controller;

import cn.edu.hbcit.phonesearch.dto.SetPageInfoDTO;
import cn.edu.hbcit.phonesearch.model.Login;
import cn.edu.hbcit.phonesearch.model.Mobile;
import cn.edu.hbcit.phonesearch.service.AccountServer;
import cn.edu.hbcit.phonesearch.service.AdminServer;
import cn.edu.hbcit.phonesearch.service.PhonePageInfoServer;
import cn.edu.hbcit.phonesearch.service.PhoneServer;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
public class ManagerController {
    @Autowired
    private PhonePageInfoServer phonePageInfoServer;

    @Autowired
    private PhoneServer phoneServer;

    @Autowired
    private AdminServer adminServer;

    @Autowired
    private AccountServer accountServer;


    @RequestMapping(value = "/manager/phone",method = RequestMethod.GET)
    public String managerPhone(@RequestParam(name = "pagesize",defaultValue = "20",required = false) String pageSize,
                               @RequestParam(name = "pagenum",defaultValue = "1",required = false) String pageNum,
                               Model model, HttpServletRequest request, RedirectAttributes attributes){

        if (!adminServer.isAdmin(request)){
            attributes.addFlashAttribute("result","监测到您不是管理员账户或请您登陆后再使用");
            return "redirect:/user/login";
        }

        PageInfo pageInfo =phonePageInfoServer.findPhoneList(new SetPageInfoDTO(Integer.parseInt(pageSize),Integer.parseInt(pageNum)));
        model.addAttribute(pageInfo);
        return "search";
    }

    @RequestMapping(value = "/manager/addphone",method = RequestMethod.POST)
    public String managerAddPhone(@RequestParam(name = "num") String num,
                                  @RequestParam(name = "city") String city,
                                  @RequestParam(name = "cardtype") String cardtype,RedirectAttributes attributes,HttpServletRequest request){

        if (!adminServer.isAdmin(request)){
            attributes.addFlashAttribute("result","监测到您不是管理员账户或请您登陆后再使用");
            return "redirect:/user/login";
        }

        if (num==null||city==null||cardtype==null||num.equals("")||city.equals("")||cardtype.equals("")){
            attributes.addFlashAttribute("result","输入值为空");
            return "redirect:/manager/phone";
        }else {
            boolean result = phoneServer.addPhone(num,city,cardtype);
            if (result){
                attributes.addFlashAttribute("result","添加成功");
                return "redirect:/manager/phone";
            }else {
                attributes.addFlashAttribute("result","添加失败，手机号已存在");
                return "redirect:/manager/phone";
            }
        }
    }

    @RequestMapping(value = "/manager/delphone",method = RequestMethod.GET)
    public String managerAddPhone(@RequestParam(name = "num") String num,
                                  RedirectAttributes attributes,HttpServletRequest request){

        if (!adminServer.isAdmin(request)){
            attributes.addFlashAttribute("result","监测到您不是管理员账户或请您登陆后再使用");
            return "redirect:/user/login";
        }

        if (num==null||num.equals("")){
            attributes.addFlashAttribute("result","输入值为空");
            return "redirect:/manager/phone";
        }else {
            boolean result = phoneServer.delPhone(num);
            if (result){
                attributes.addFlashAttribute("result","删除成功");
                return "redirect:/manager/phone";
            }else {
                attributes.addFlashAttribute("result","删除失败，手机号不存在");
                return "redirect:/manager/phone";
            }
        }
    }

    @RequestMapping(value = "/manager/updatephone",method = RequestMethod.GET)
    public String managerUpdatePhone(@RequestParam(name = "num") String num,RedirectAttributes attributes,Model model,HttpServletRequest request){

        if (!adminServer.isAdmin(request)){
            attributes.addFlashAttribute("result","监测到您不是管理员账户或请您登陆后再使用");
            return "redirect:/user/login";
        }

        if (num==null){
            attributes.addFlashAttribute("result","输入值为空");
            return "redirect:/manager/phone";
        }else {
            List ls = phoneServer.findPhoneInfoByNum(num);
            model.addAttribute("lists",ls);
            return "update_phone";
        }
    }


    @RequestMapping(value = "/manager/updatephone",method = RequestMethod.POST)
    public String managerUpdatePhone(@RequestParam(name = "id") int id,
                                     @RequestParam(name = "num") String num,
                                     @RequestParam(name = "city") String city,
                                     @RequestParam(name = "cardtype") String cardtype,RedirectAttributes attributes,
                                     HttpServletRequest request){

        if (!adminServer.isAdmin(request)){
            attributes.addFlashAttribute("result","监测到您不是管理员账户或请您登陆后再使用");
            return "redirect:/user/login";
        }

        if (num==null||city==null||cardtype==null||num.equals("")||city.equals("")||cardtype.equals("")){
            attributes.addFlashAttribute("result","输入值为空");
            return "redirect:/manager/phone";
        }else {
            boolean result = phoneServer.updatePhone(id,num,city,cardtype);
            if (result){
                attributes.addFlashAttribute("result","更新成功");
                return "redirect:/manager/phone";
            }else {
                attributes.addFlashAttribute("result","更新失败");
                return "redirect:/manager/phone";
            }
        }
    }


    @RequestMapping(value = "/manager/user",method = RequestMethod.GET)
    public String managerUser(@RequestParam(name = "pagesize",defaultValue = "20",required = false) String pageSize,
                               @RequestParam(name = "pagenum",defaultValue = "1",required = false) String pageNum,
                               Model model, HttpServletRequest request, RedirectAttributes attributes){

        if (!adminServer.isAdmin(request)){
            attributes.addFlashAttribute("result","监测到您不是管理员账户或请您登陆后再使用");
            return "redirect:/user/login";
        }

        PageInfo pageInfo =accountServer.findUserPageList(new SetPageInfoDTO(Integer.parseInt(pageSize),Integer.parseInt(pageNum)));
        model.addAttribute(pageInfo);
        return "search_user";
    }


    @PostMapping(value = "/manager/adduser")
    public String managerAddUser(@RequestParam(name = "username") String username,
                              @RequestParam(name = "password") String password,
                              @RequestParam(name = "age") String age,
                              @RequestParam(name = "number") String number,
                              @RequestParam(name = "qx") String qx,
                              HttpServletRequest request,
                              RedirectAttributes attributes){

        if (!adminServer.isAdmin(request)){
            attributes.addFlashAttribute("result","监测到您不是管理员账户或请您登陆后再使用");
            return "redirect:/user/login";
        }

        boolean ifSucc = accountServer.insert(username, password, age, number, qx);
        if (ifSucc){
            attributes.addFlashAttribute("result","添加成功");
        }else {
            attributes.addFlashAttribute("result","添加失败成功");
        }
        return "redirect:/manager/user";
    }


    @GetMapping(value = "/manager/deluser")
    public String managerDelUser(@RequestParam(name = "username") String username,
                                 HttpServletRequest request,
                                 RedirectAttributes attributes){
        if (!adminServer.isAdmin(request)){
            attributes.addFlashAttribute("result","监测到您不是管理员账户或请您登陆后再使用");
            return "redirect:/user/login";
        }

        boolean ifSucc = accountServer.del(username);
        if (ifSucc){
            attributes.addFlashAttribute("result","删除成功");
        }else {
            attributes.addFlashAttribute("result","删除失败");
        }
        return "redirect:/manager/user";
    }




    @RequestMapping(value = "manager/updateuser",method = RequestMethod.GET)
    public String userSetting(@RequestParam(name = "username")String username, HttpServletRequest request, RedirectAttributes attributes,Model model){

        if (!adminServer.isAdmin(request)){
            attributes.addFlashAttribute("result","监测到您不是管理员账户或请您登陆后再使用");
            return "redirect:/user/login";
        }

        List<Login> ls = accountServer.findUserInfoByName(username);
        if (ls.size()>0){
            model.addAttribute("users",ls.get(0));
            return "update_user";
        }else {
            model.addAttribute("result","用户未找到");
            return "update_user";
        }
    }

    @RequestMapping(value = "manager/updateuser",method = RequestMethod.POST)
    public String userSetting(@RequestParam(name = "username") String username,
                              @RequestParam(name = "password") String password,
                              @RequestParam(name = "age") String age,
                              @RequestParam(name = "number") String number,
                              @RequestParam(name = "qx") String qx,
                              HttpServletRequest request,
                              RedirectAttributes attributes) {

        if (!adminServer.isAdmin(request)){
            attributes.addFlashAttribute("result","监测到您不是管理员账户或请您登陆后再使用");
            return "redirect:/user/login";
        }

        if (username.equals("") || password.equals("") || age.equals("") || number.equals("") || qx.equals("") || username== null|| password== null|| age== null|| number== null|| qx== null) {
            attributes.addFlashAttribute("result", "您的输入的值为空,请重新输入！");
        } else {
            boolean ifSuss = accountServer.updateByAdmin(username, password, age, number, qx);
            if (ifSuss) {
                attributes.addFlashAttribute("result", "更改成功");
                return "redirect:/manager/user";
            } else {
                attributes.addFlashAttribute("result", "您的输入有误,请重新输入！");
                return "redirect:/manager/user";
            }
        }
        return "redirect:/manager/user";
    }
}
