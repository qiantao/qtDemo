package util;

import java.util.Objects;

/**
 * @ClassName:
 * @Description:
 * @author: QianTao
 * @date: 2021/06/21 9:57
 * @version: V1.0
 */
public class StringUtil {
    public static boolean isNullTrim(String module) {
        return null == module || "".equals(module);
    }
    public static boolean isNotNullTrim(String module) {
        return null != module && !"".equals(module);
    }
}
