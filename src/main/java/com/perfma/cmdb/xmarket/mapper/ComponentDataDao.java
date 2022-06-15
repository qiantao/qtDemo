package com.perfma.cmdb.xmarket.mapper;

import com.perfma.cmdb.xmarket.domain.ComponentData;
import org.apache.ibatis.annotations.Param;;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;


/**
 * @ClassName:  ComponentDataDao
 * @Description: ComponentDataDao
 * @author: QianTao
 * @date:  2022/05/18 10:20:22
 * @version: V1.0
 */
@Mapper
public interface ComponentDataDao {
     /**
     * 根据实体进行查询多个
     * @param componentData 查询实体
     * @return
     */
    List<ComponentData> selectListByEntity(ComponentData componentData);

     /**
     * 根据实体进行查询一个
     * @param componentData 查询实体
     * @return
     */
    ComponentData selectByEntity(ComponentData componentData);

    /**
     * 更新字段不为空的数据
     * @param componentData
     * @return
     */
    int updateByEntitySelective(ComponentData componentData);

    /**
     * 新增字段不为空的数据
     * @param componentData
     * @return
     */
    int insertSelective(ComponentData componentData);

    /**
     * 新增字段不为空的数据
     * @param componentDataList
     * @return
     */
    int insertSelectiveList(List<ComponentData> componentDataList);

}