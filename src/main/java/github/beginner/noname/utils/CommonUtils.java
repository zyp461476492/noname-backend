package github.beginner.noname.utils;

import java.util.Collection;
import java.util.Map;

/**
 * 通用工具类
 *
 * @author zyp on 2018-12-6.
 */
public class CommonUtils {
    /**
     * 判断obj对象是否为空
     *
     * @param obj 待判断对象
     * @return true 如果为空
     */
    public static boolean isNullOrEmpty(Object obj) {
        if (obj == null) {
            return true;
        }

        if (obj instanceof String) {
            return ((String) obj).isEmpty();
        } else if (obj instanceof Collection) {
            return ((Collection) obj).isEmpty();
        } else if (obj instanceof Map) {
            return ((Map) obj).isEmpty();
        }

        return false;
    }
}
