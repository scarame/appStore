package generator.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import generator.entity.Comments;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface CommentsMapper extends BaseMapper<Comments> {
    //添加一个评论
    @Insert("insert into comments (appId,userid,username,score,content,score_time,parent_comment_id) values(#{appid},#{userid},#{username},#{score},#{scoreContent},#{scoreTime},#{parentCommentId})")
    int insert(Comments gamescore);

    //查询父级评论
    @Select("select *\n" +
            "        from comments c\n" +
            "        where c.parent_comment_id = #{ParentId}\n" +
            "        and c.appId=#{appid}"+
            "        order by c.score_time desc")
    List<Comments> findByParentIdNull(@Param("ParentId") Long ParentId,@Param("appid") long appId);
    //查询一级回复
    @Select("select *\n" +
            "        from comments c\n" +
            "        where c.parent_comment_id = #{id}\n" +
            "        and c.appId=#{appid}  \n"+
            "        order by c.score_time desc")
    List<Comments> findParentIdNotNull(@Param("id") long id,@Param("appid") long appid);
    //查询二级以及所有子集回复
    @Select("select *\n" +
            "        from comments c\n" +
            "        where c.parent_comment_id = #{childId}\n" +
            "        order by c.score_time desc")
    List<Comments> findByReplayId(@Param("childId") int childId);


}




