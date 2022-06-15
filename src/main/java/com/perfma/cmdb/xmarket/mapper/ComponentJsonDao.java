package com.perfma.cmdb.xmarket.mapper;

import com.perfma.cmdb.xmarket.domain.ComponentJson;
import org.apache.ibatis.annotations.Param;;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;


/**
 * @ClassName:  ComponentJsonDao
 * @Description: ComponentJsonDao
 * @author: QianTao
 * @date:  2022/05/18 10:20:22
 * @version: V1.0
 */
@Mapper
public interface ComponentJsonDao {
     /**
     * 根据实体进行查询多个
     * @param componentJson 查询实体
     * @return
     */
    List<ComponentJson> selectListByEntity(ComponentJson componentJson);

     /**
     * 根据实体进行查询一个
     * @param componentJson 查询实体
     * @return
     */
    ComponentJson selectByEntity(ComponentJson componentJson);

    /**
     * 更新字段不为空的数据
     * @param componentJson
     * @return
     */
    int updateByEntitySelective(ComponentJson componentJson);

    /**
     * 新增字段不为空的数据
     * @param componentJson
     * @return
     */
    int insertSelective(ComponentJson componentJson);

    /**
     * 新增字段不为空的数据
     * @param componentJsonList
     * @return
     */
    int insertSelectiveList(List<ComponentJson> componentJsonList);

}