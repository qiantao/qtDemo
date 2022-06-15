package com.perfma.cmdb.xmarket.domain;


import java.sql.Timestamp;
import java.io.Serializable;
import java.util.Date;
import lombok.Builder;
import lombok.Data;
/**   
 * @ClassName:  ComponentDataFilter   
 * @Description: 组件数据配置过滤字段(t_component_data_filter)实体类   
 * @author: QianTao 
 * @date:   2022/05/18 10:20:22
 * @version: V1.0   
 */
@Data
@Builder
public class ComponentDataFilter implements Serializable {

    /**
     * 
     */
    private Long id;

    /**
     * 组件配置id
     */
    private Long componentDataId;

    /**
     * 字段名
     */
    private String key;

    /**
     * 字段条件
     */
    private String condition;

    /**
     * 值
     */
    private String value;

    /**
     * 创建人
     */
    private Long createUser;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 最终修改人
     */
    private Long updateUser;

    /**
     * 最终修改时间
     */
    private Date updateTime;

    /**
     * 状态
     */
    private Integer status;

    /**
     * 关联当前表id
     */
    private Long relaId;






}