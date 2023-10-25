package com.qt.export;

import lombok.Data;
/**
 * @Author MuYang
 * @Date 2023/10/25 10:28
 * @version: V1.0
 */
@Data
public class TableData {




        /**
         * 标题
         */
        private String title;

        /**
         * 表格
         */
//        private TableRenderData table;

        private String[][] tableList;

        /**
         * 总价
         */
        private String totalPrice;



}
