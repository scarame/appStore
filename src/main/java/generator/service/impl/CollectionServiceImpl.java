package generator.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import generator.entity.Collection;
import generator.mapper.CollectionMapper;
import generator.service.CollectionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 *
 */
@Service
public class CollectionServiceImpl extends ServiceImpl<CollectionMapper, Collection>
    implements CollectionService {

    @Autowired
    CollectionMapper collectionMapper;

    @Override
    public int collect( Collection collection) {
     if(collectionMapper.repeatedDownload(collection.getAppId())==null){
         collectionMapper.collect(collection);
            return 1;
     }else{
         return 2;
     }
    }

    @Override
    public List<Collection> collectedList(int uid) {
        return collectionMapper.collectedList(uid);
    }
}




