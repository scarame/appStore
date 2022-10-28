package generator.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import generator.entity.Collection;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface CollectionMapper extends BaseMapper<Collection> {
    @Insert("insert into collection (user_id,app_id) values(#{userId},#{appId})")
    int collect(int userId,int appId);

    @Select("select * from collection where app_id=#{appId}")
    Collection repeatedDownload(int appId);

    @Select("select app_id from collection where user_id=#{userId}")
    List<Collection> collectedList( int userId);

}




