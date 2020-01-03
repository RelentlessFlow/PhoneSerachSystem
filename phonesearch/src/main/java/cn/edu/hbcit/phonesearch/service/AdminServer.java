package cn.edu.hbcit.phonesearch.service;

import javax.servlet.http.HttpServletRequest;

public interface AdminServer {
    Boolean isAdmin(HttpServletRequest request);
}
