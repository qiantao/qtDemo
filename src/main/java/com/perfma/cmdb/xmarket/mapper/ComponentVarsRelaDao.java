package com.perfma.cmdb.xmarket.mapper;

import com.perfma.cmdb.xmarket.domain.ComponentVarsRela;
import org.apache.ibatis.annotations.Param;;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;


/**
 * @ClassName:  ComponentVarsRelaDao
 * @Description: ComponentVarsRelaDao
 * @author: QianTao
 * @date:  2022/05/18 10:20:22
 * @version: V1.0
 */
@Mapper
public interface ComponentVarsRelaDao {
     /**
     * 根据实体进行查询多个
     * @param componentVarsRela 查询实体
     * @return
     */
    List<ComponentVarsRela> selectListByEntity(ComponentVarsRela componentVarsRela);

     /**
     * 根据实体进行查询一个
     * @param componentVarsRela 查询实体
     * @return
     */
    ComponentVarsRela selectByEntity(ComponentVarsRela componentVarsRela);

    /**
     * 更新字段不为空的数据
     * @param componentVarsRela
     * @return
     */
    int updateByEntitySelective(ComponentVarsRela componentVarsRela);

    /**
     * 新增字段不为空的数据
     * @param componentVarsRela
     * @return
     */
    int insertSelective(ComponentVarsRela componentVarsRela);

    /**
     * 新增字段不为空的数据
     * @param componentVarsRelaList
     * @return
     */
    int insertSelectiveList(List<ComponentVarsRela> componentVarsRelaList);

}