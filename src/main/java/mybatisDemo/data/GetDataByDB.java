package mybatisDemo.data;

import jdbc.MyBatitsDao;
import mybatisDemo.entity.TableInfo;

import java.util.List;

/**
 * @ClassName:
 * @Description:
 * @author: QianTao
 * @date: 2019/07/18 10:34
 * @version: V1.0
 */
public class GetDataByDB {
    public static List<TableInfo> getData(List<String> tableNames,String dbSchema) {
        return MyBatitsDao.queryColumnInfoByTableName(tableNames,dbSchema);
    }

}
