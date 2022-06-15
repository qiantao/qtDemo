package com.perfma.cmdb.xmarket.mapper;

import com.perfma.cmdb.xmarket.domain.MarketVars;
import org.apache.ibatis.annotations.Param;;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;


/**
 * @ClassName:  MarketVarsDao
 * @Description: MarketVarsDao
 * @author: QianTao
 * @date:  2022/05/18 10:20:22
 * @version: V1.0
 */
@Mapper
public interface MarketVarsDao {
     /**
     * 根据实体进行查询多个
     * @param marketVars 查询实体
     * @return
     */
    List<MarketVars> selectListByEntity(MarketVars marketVars);

     /**
     * 根据实体进行查询一个
     * @param marketVars 查询实体
     * @return
     */
    MarketVars selectByEntity(MarketVars marketVars);

    /**
     * 更新字段不为空的数据
     * @param marketVars
     * @return
     */
    int updateByEntitySelective(MarketVars marketVars);

    /**
     * 新增字段不为空的数据
     * @param marketVars
     * @return
     */
    int insertSelective(MarketVars marketVars);

    /**
     * 新增字段不为空的数据
     * @param marketVarsList
     * @return
     */
    int insertSelectiveList(List<MarketVars> marketVarsList);

}