package cn.edu.hbcit.phonesearch.mapper;


import cn.edu.hbcit.phonesearch.model.Login;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Component;

import java.util.List;


@Mapper
public interface UserMapper {

    @Select("select * from login where username = #{username} and password = #{password};")
    List<Login> findByNumAndPass(String username, String password);

    @Select("select * from login where username = #{username}")
    List<Login> findByName(String username);

    @Select("select * from login where 1 = 1")
    List<Login> findAll();


    @Insert("insert into login (username, password, age, number, qx) VALUE (#{username},#{password},'','','0')")
    int insert(String username, String password);

    @Update("UPDATE mobile.login t SET t.username = #{username}, t.password = #{password}, t.age = #{age}, t.number = #{number} WHERE t.username LIKE #{username} ESCAPE '#'")
    int updateByUser(String username,String password,String age,String number);

    // XXX
    @Insert("INSERT INTO mobile.login (username, password, age, number, qx) VALUES (#{username}, #{password}, #{age}, #{number}, #{qx})")
    int insertByAdmin(String username,String password,String age,String number,String qx);


    @Delete("DELETE FROM mobile.login WHERE username LIKE #{username}")
    int delByUsrName(String username);

    // XXX
    @Update("UPDATE mobile.login t SET t.username = #{username}, t.password = #{password}, t.age = #{age}, t.number = #{number}, t.qx = #{qx} WHERE t.username LIKE #{username} ESCAPE '#'")
    int updateByAdmin(String username,String password,String age,String number,String qx);
}
