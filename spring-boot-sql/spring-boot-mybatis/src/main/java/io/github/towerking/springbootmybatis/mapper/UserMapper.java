package io.github.towerking.springbootmybatis.mapper;

import io.github.towerking.springbootmybatis.model.User;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Component;

@Mapper
@Component
public interface UserMapper {

    @Insert("insert into t_user (name, create_time, update_time) values (#{name}, now(), now()) ")
    @Options(useGeneratedKeys = true)
    int save(User user);

    @Select("select * from t_user where id = #{id}")
    @Results({
            @Result(id = true, column = "id", property = "id"),
            @Result(column = "create_time", property = "createTime"),
            @Result(column = "update_time", property = "updateTime")
    })
    User findById(@Param("id") Integer id);
}
