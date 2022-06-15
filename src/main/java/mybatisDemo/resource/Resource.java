package mybatisDemo.resource;

import java.util.List;

/**
 * @ClassName:
 * @Description:
 * @author: QianTao
 * @date: 2019/07/23 14:14
 * @version: V1.0
 */
public class Resource {
    public static List<SourceEntity> getResource () throws Exception{
        List<SourceEntity> sourceEntity = XmlResource.getResource();
        if(sourceEntity==null){
            sourceEntity = ProResource.getResource();
        }
        return sourceEntity;
    }
}
