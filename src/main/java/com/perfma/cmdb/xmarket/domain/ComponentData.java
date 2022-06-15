package com.perfma.cmdb.xmarket.domain;


import java.sql.Timestamp;
import java.io.Serializable;
import java.util.Date;
import lombok.Builder;
import lombok.Data;
/**   
 * @ClassName:  ComponentData   
 * @Description: 组件数据配置(t_component_data)实体类   
 * @author: QianTao 
 * @date:   2022/05/18 10:20:22
 * @version: V1.0   
 */
@Data
@Builder
public class ComponentData implements Serializable {

    /**
     * 
     */
    private Long id;

    /**
     * 组件id
     */
    private Long componentId;

    /**
     * x轴字段
     */
    private String xField;

    /**
     * y轴字段
     */
    private String yField;

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