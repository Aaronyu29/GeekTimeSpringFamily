package aaron.spring.data.mybatisdemo.mapper;

import aaron.spring.data.mybatisdemo.model.Coffee;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface CoffeeMapper {
    @Insert("insert into t_coffee(name,price,create_time,update_time)" +
            "values (#{name},#{price},now(),now())")
    @Options(useGeneratedKeys = true,keyColumn = "id",keyProperty = "id")
//    @Options(useGeneratedKeys = true)
    int save(Coffee coffee);

    @Select("select * from t_coffee where id = #{id}")
    @Results({
            @Result(id = true,column = "id",property = "id"),
            @Result(column = "create_time",property = "createTime"),
            @Result(column = "update_time",property = "updateTime")
    })
    Coffee findById(@Param("id") Long id);
}
