package cn.edu.hbcit.phonesearch.service;

import cn.edu.hbcit.phonesearch.dto.SetPageInfoDTO;
import cn.edu.hbcit.phonesearch.mapper.PhoneMapper;
import cn.edu.hbcit.phonesearch.model.Mobile;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class PhonePageInfoServerImpl implements PhonePageInfoServer {
    @Autowired
    private PhoneMapper phoneMapper;

    @Override
    public PageInfo findPhoneList(SetPageInfoDTO dto) {
        PageHelper.startPage(dto.getPageNum(),dto.getPageSize());
        List<Mobile> phoneMobile = phoneMapper.findAll();
        PageInfo pageInfo = new PageInfo(phoneMobile);
        return  pageInfo;
    }
}
