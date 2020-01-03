package cn.edu.hbcit.phonesearch.service;


import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

@Component
public class AdminServerImpl implements AdminServer {
    @Override
    public Boolean isAdmin(HttpServletRequest request) {
        String qx = (String)request.getSession().getAttribute("qx");
        if (qx==null||qx.equals("0")){
            return false;
        }else {
            return true;
        }
    }
}
