package com.perfma.cmdb.xmarket.mapper;

import com.perfma.cmdb.xmarket.domain.ComponentDataFilter;
import org.apache.ibatis.annotations.Param;;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;


/**
 * @ClassName:  ComponentDataFilterDao
 * @Description: ComponentDataFilterDao
 * @author: QianTao
 * @date:  2022/05/18 10:20:22
 * @version: V1.0
 */
@Mapper
public interface ComponentDataFilterDao {
     /**
     * 根据实体进行查询多个
     * @param componentDataFilter 查询实体
     * @return
     */
    List<ComponentDataFilter> selectListByEntity(ComponentDataFilter componentDataFilter);

     /**
     * 根据实体进行查询一个
     * @param componentDataFilter 查询实体
     * @return
     */
    ComponentDataFilter selectByEntity(ComponentDataFilter componentDataFilter);

    /**
     * 更新字段不为空的数据
     * @param componentDataFilter
     * @return
     */
    int updateByEntitySelective(ComponentDataFilter componentDataFilter);

    /**
     * 新增字段不为空的数据
     * @param componentDataFilter
     * @return
     */
    int insertSelective(ComponentDataFilter componentDataFilter);

    /**
     * 新增字段不为空的数据
     * @param componentDataFilterList
     * @return
     */
    int insertSelectiveList(List<ComponentDataFilter> componentDataFilterList);

}