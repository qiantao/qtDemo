package com.perfma.cmdb.xmarket.domain;


import java.sql.Timestamp;
import java.io.Serializable;
import java.util.Date;
import lombok.Builder;
import lombok.Data;
/**   
 * @ClassName:  ComponentJson   
 * @Description: 组件json表(t_component_json)实体类   
 * @author: QianTao 
 * @date:   2022/05/18 10:20:22
 * @version: V1.0   
 */
@Data
@Builder
public class ComponentJson implements Serializable {

    /**
     * 
     */
    private Long id;

    /**
     * 组件id
     */
    private Long componentId;

    /**
     * 组件json
     */
    private String componentJson;

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