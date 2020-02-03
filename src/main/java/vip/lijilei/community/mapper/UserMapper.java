package vip.lijilei.community.mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import vip.lijilei.community.model.User;

/**
 * @author 李吉磊
 * @Company http://www.lijilei.vip
 * @Date: Create in 2020/2/3
 */
@Mapper
public interface UserMapper {

    @Insert("insert into user(id, account_id, name, token, gmt_create, gmt_modified )" +
            " values(#{id}, #{accountId}, #{name}, #{token}, #{gmtCreate}, #{gmtModified})")
    void insert(User user);

    @Select("select * from user where token = #{token}")
    User selectBytoken(@Param("token") String token);
}
