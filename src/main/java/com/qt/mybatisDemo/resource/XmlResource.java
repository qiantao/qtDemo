package com.qt.mybatisDemo.resource;

import java.util.List;

/**
 * @ClassName:
 * @Description:
 * @author: QianTao
 * @date: 2019/07/23 11:16
 * @version: V1.0
 */
public class XmlResource {
    private static String path = XmlResource.class.getResource("/conf.xml").getPath();
//    private static Document doc = null;
//    static {
//        SAXReader reader = new SAXReader();
//        try {
//            doc = reader.read(new File(path));
//        } catch (DocumentException e) {
//            e.printStackTrace();
//        }
//    }

    public static List<SourceEntity> getResource () throws Exception{
//        List<SourceEntity> xmlEntityList = new ArrayList<>();
//        if(doc==null) throw new Exception("解析XML错误");
//        Element rootElement = doc.getRootElement();
//        List<Element> sourceElement = rootElement.elements("sourceEntity");
//        sourceElement.stream().forEach(element -> {
//            SourceEntity sourceEntity = new SourceEntity();
//            sourceEntity.setH3Module(element.element("h3module")==null?"":element.element("h3module").getStringValue());
//            sourceEntity.setModule(element.element("module")==null?"":element.element("module").getStringValue());
//
//            List<Element> tableNameElement = (List<Element>) element.element("tableNames").elements("table");
//            List<String> tableNames = new ArrayList<>();
//            tableNameElement.forEach(e->{
//
//                Map<String,Map<String,String>> tableNameHasMethod = sourceEntity.getTableNameHasMethod();
//                if(tableNameHasMethod==null){
//                    tableNameHasMethod = new HashMap<>();
//                    sourceEntity.setTableNameHasMethod(tableNameHasMethod);
//                }
//                Map<String,String> map = new HashMap<>();
//                MethodEnum[] enums = MethodEnum.values();
//                for (int i = 0; i < enums.length; i++) {
//                    String methodName=enums[i].getValue();
//                    map.put(methodName,e.element(methodName)==null?"true":e.element(methodName).getStringValue());
//                }
//                String tableName = e.element("tableName").getStringValue();
//                tableNameHasMethod.put(tableName,map);
//                tableNames.add(tableName);
//
//            });;
//            sourceEntity.setTableNames(tableNames);
//            xmlEntityList.add(sourceEntity);
//
//        });
//
//        return xmlEntityList;
          return null;
    }
}
