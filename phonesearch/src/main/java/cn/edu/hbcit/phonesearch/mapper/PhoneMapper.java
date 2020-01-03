package cn.edu.hbcit.phonesearch.mapper;

import cn.edu.hbcit.phonesearch.model.Mobile;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;

@Component
@Mapper
public interface PhoneMapper {
    @Select("select * from list where num = #{num}")
    List<Mobile> findByNum(@Param("num") String num);

    @Select("select * from list where 1=1 ORDER BY id")
    List<Mobile> findAll();

    @Select("SELECT t.* FROM mobile.list t ORDER BY id LIMIT #{num}")
    List<Mobile> findRecent(@Param("num") int num);

    @Insert("INSERT INTO mobile.list (num, city, cardtype) VALUES (#{num}, #{city}, #{cardtype})")
    int insertMobile(@Param("num") String num, @Param("city") String city, @Param("cardtype") String cardtype);

    @Delete("DELETE FROM mobile.list WHERE num = #{num}")
    int delMobileByNum(@Param("num") String num);

    @Update("UPDATE mobile.list t SET t.num = #{num}, t.city = #{city}, t.cardtype = #{cardtype} WHERE t.id = #{id}")
    int updateMobile(@Param("id") int id,@Param("num") String num, @Param("city") String city, @Param("cardtype") String cardtype);
}
