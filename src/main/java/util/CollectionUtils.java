package util;

import cn.hutool.core.convert.Convert;

import java.util.*;

/**
 * @ClassName:
 * @Description:
 * @author: QianTao
 * @date: 2020/11/26 17:18
 * @version: V1.0
 */
public class CollectionUtils {

    public CollectionUtils() {
    }

    public static boolean isEmpty(Collection<?> collection) {
        return collection == null || collection.isEmpty();
    }

    public static boolean notEmpty(Collection<?> collection) {
        return !isEmpty(collection);
    }

    public static boolean isEmpty(Map<?, ?> map) {
        return map == null || map.isEmpty();
    }

    public static boolean notEmpty(Map<?, ?> map) {
        return !isEmpty(map);
    }

    public static List<Long> getCascadeLastDataValues(List<Object> valueMaps) {
        List<Long> values = new ArrayList();
        handleLastValues(valueMaps, values);
        return values;
    }

    private static void handleLastValues(List<Object> valueMaps, List<Long> values) {
        if (!isEmpty((Collection)valueMaps)) {
            if (valueMaps.size() == 1) {
                Object value = valueMaps.get(0);
                if (value instanceof Collection) {
                    List<Object> childs = new ArrayList((Collection)value);
                    handleLastValues(childs, values);
                } else {
                    values.add(Convert.convert(Long.class,value));
                }
            } else {
                Iterator<Object> entryIterator = valueMaps.iterator();
                int index = 0;

                while(entryIterator.hasNext()) {
                    ++index;
                    Object obj = entryIterator.next();
                    if (!(obj instanceof Collection)) {
                        if (index == valueMaps.size()) {
                            values.add(Convert.convert(Long.class,obj));
                        }
                    } else {
                        List<Object> childs = new ArrayList((Collection)obj);
                        handleLastValues(childs, values);
                    }
                }

            }
        }
    }

    public static List<Long> getAllCascadeDataValues(List<Object> valueMaps) {
        return getAllCascadeDataValues((Object)null, valueMaps);
    }

    public static List<Long> getAllCascadeDataValues(Object parentValue, List<Object> valueMaps) {
        List<Long> values = new ArrayList();
        handleCascadeDataValues(parentValue, valueMaps, values);
        return values;
    }

    private static void handleCascadeDataValues(Object parentValue, List<Object> valueMaps, List<Long> values) {
        if (!isEmpty((Collection)valueMaps)) {
            String parentStrValue = parentValue == null ? "" : parentValue.toString();
            Iterator entryIterator;
            Object obj;
            List entrys;
            if (StringUtil.isNullTrim(parentStrValue)) {
                entryIterator = valueMaps.iterator();

                while(entryIterator.hasNext()) {
                    obj = entryIterator.next();
                    if (!(obj instanceof Collection)) {
                        values.add(Convert.convert(Long.class,obj));
                    } else {
                        entrys = (List)obj;
                        values.add(Convert.convert(Long.class,entrys.get(0)));
                        List<Object> childs = new ArrayList(entrys);
                        childs.remove(0);
                        handleCascadeDataValues((Object)null, childs, values);
                    }
                }

            } else {
                entryIterator = valueMaps.iterator();

                while(entryIterator.hasNext()) {
                    obj = entryIterator.next();
                    if (!(obj instanceof Collection)) {
                        if (obj.toString().equalsIgnoreCase(parentStrValue)) {
                            values.add(Convert.convert(Long.class,obj));
                            ArrayList entryValue = new ArrayList();

                            while(entryIterator.hasNext()) {
                                entryValue.add(entryIterator.next());
                            }

                            handleCascadeDataValues((Object)null, entryValue, values);
                            return;
                        }
                    } else {
                        entrys = (List)obj;
                        Object entryKey = entrys.get(0);
                        List<Object> entryValue = new ArrayList(entrys);
                        entryValue.remove(0);
                        if (entryKey.toString().equalsIgnoreCase(parentStrValue)) {
                            values.add(Convert.convert(Long.class,entryKey));
                            handleCascadeDataValues((Object)null, entryValue, values);
                            return;
                        }

                        handleCascadeDataValues(parentValue, entryValue, values);
                    }
                }

            }
        }
    }

}
