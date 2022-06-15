package com.perfma.cmdb.xmarket.mapper;

import com.perfma.cmdb.xmarket.domain.ComponentDataStack;
import org.apache.ibatis.annotations.Param;;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;


/**
 * @ClassName:  ComponentDataStackDao
 * @Description: ComponentDataStackDao
 * @author: QianTao
 * @date:  2022/05/18 10:20:22
 * @version: V1.0
 */
@Mapper
public interface ComponentDataStackDao {
     /**
     * 根据实体进行查询多个
     * @param componentDataStack 查询实体
     * @return
     */
    List<ComponentDataStack> selectListByEntity(ComponentDataStack componentDataStack);

     /**
     * 根据实体进行查询一个
     * @param componentDataStack 查询实体
     * @return
     */
    ComponentDataStack selectByEntity(ComponentDataStack componentDataStack);

    /**
     * 更新字段不为空的数据
     * @param componentDataStack
     * @return
     */
    int updateByEntitySelective(ComponentDataStack componentDataStack);

    /**
     * 新增字段不为空的数据
     * @param componentDataStack
     * @return
     */
    int insertSelective(ComponentDataStack componentDataStack);

    /**
     * 新增字段不为空的数据
     * @param componentDataStackList
     * @return
     */
    int insertSelectiveList(List<ComponentDataStack> componentDataStackList);

}