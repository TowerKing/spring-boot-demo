package io.github.towerking.springbootmybatis.mapper;

import io.github.towerking.springbootmybatis.model.PageRows;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.session.RowBounds;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Mapper
public interface PageRowsMapper {

    @Select("select * from t_page_rows order by id")
    List<PageRows> findAllWithRowBounds(RowBounds rowBounds);

    @Select("select * from t_page_rows order by id")
    List<PageRows> findAllWithParams(@Param("pageNum") int pageNum, @Param("pageSize") int pageSize);

    @Select("select * from t_page_rows order by id")
    List<PageRows> findAll();

}
