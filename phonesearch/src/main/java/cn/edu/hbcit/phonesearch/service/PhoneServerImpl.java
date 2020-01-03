package cn.edu.hbcit.phonesearch.service;

import cn.edu.hbcit.phonesearch.mapper.PhoneMapper;
import cn.edu.hbcit.phonesearch.model.Mobile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class PhoneServerImpl implements PhoneServer {

    @Autowired
    private PhoneMapper phoneMapper;

    @Override
    public List findPhoneInfoByNum(String num) {
        List<Mobile> mobiles = phoneMapper.findByNum(num);
        return mobiles;
    }

    @Override
    public List findRecentPhoneInfo(int size) {
        List<Mobile> mobiles = phoneMapper.findRecent(size);
        return mobiles;
    }

    @Override
    public boolean addPhone(String num, String city, String cardtype) {
        if (findPhoneInfoByNum(num).size() >= 1) {
            return false;
        } else {
            int re = phoneMapper.insertMobile(num, city, cardtype);
            return re >= 1;
        }
    }

    @Override
    public boolean delPhone(String num) {
        if (findPhoneInfoByNum(num).size() >= 1) {
            int re = phoneMapper.delMobileByNum(num);
            return re >= 1;
        } else {
            return false;
        }
    }

    @Override
    public boolean updatePhone(int id, String num, String city, String cardtype) {
        if (findPhoneInfoByNum(num).size() >= 1) {
            int re = phoneMapper.updateMobile(id, num, city, cardtype);
            return re >= 1;
        } else {
            return false;
        }
    }


}
