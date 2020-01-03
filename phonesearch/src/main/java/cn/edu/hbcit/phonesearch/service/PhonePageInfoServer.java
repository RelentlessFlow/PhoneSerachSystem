package cn.edu.hbcit.phonesearch.service;

import cn.edu.hbcit.phonesearch.dto.SetPageInfoDTO;
import cn.edu.hbcit.phonesearch.mapper.UserMapper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;

public interface PhonePageInfoServer {
    PageInfo findPhoneList(SetPageInfoDTO dto);
}
