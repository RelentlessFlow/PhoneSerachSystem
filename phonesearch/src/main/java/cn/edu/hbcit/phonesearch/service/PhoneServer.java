package cn.edu.hbcit.phonesearch.service;

import java.util.List;

public interface PhoneServer {
    List findPhoneInfoByNum(String num);

    List findRecentPhoneInfo(int size);

    boolean addPhone(String num,String city,String cardtype);

    boolean delPhone(String num);

    boolean updatePhone(int id,String num,String city,String cardtype);
}
