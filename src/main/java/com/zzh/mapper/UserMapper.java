package com.zzh.mapper;

import com.zzh.pojo.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

/**
 * @author zzh
 * @version 1.0
 */
public interface UserMapper {
    /**
     * 根据用户名 和 用户密码，查询用户对象
     * @param username
     * @param password
     * @return
     */
    @Select("select * from tb_user where username=#{username} and password=#{password}")
    User select(@Param("username") String username, @Param("password") String password);

    /**
     * 根据用户名查询用户对象
     * @param username
     * @return
     */
    @Select("select * from tb_user where username=#{username}")
    User selectByUsername(@Param("username") String username);

    /**
     * 添加用户
     * @param user
     */
    @Insert("insert into tb_user (id, username, password) values (null, #{username}, #{password})")
    void add(User user);//id是主键自增长的，传null即可
}
