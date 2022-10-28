package generator.service;

import com.baomidou.mybatisplus.extension.service.IService;
import generator.entity.Collection;

import java.util.List;

/**
 *
 */
public interface CollectionService extends IService<Collection> {


    //插入历史
    int collect(int userId,int appId);
    //显示下载历史
    List<Collection> collectedList(int uid);
}
