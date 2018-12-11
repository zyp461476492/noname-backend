package github.beginner.noname.util;

/**
 * @author wpt
 * @version 1.0
 * .
 * .                 .-~~~~~~~~~-._       _.-~~~~~~~~~-.
 * .             __.'              ~.   .~              `.__
 * .           .'//                  \./                  \\`.
 * .         .'//                     |                     \\`.
 * .       .'// .-~"""""""~~~~-._     |     _,-~~~~"""""""~-. \\`.
 * .     .'//.-"                 `-.  |  .-'                 "-.\\`.
 * .   .'//______.============-..   \ | /   ..-============.______\\`.
 * . .'______________________________\|/______________________________`.
 * .                    高山仰止,景行行止.虽不能至,心向往之
 * @date 18/12/11 23:46
 * <p>
 * 字符串工具类
 */

public class StringUtils {

    public static String byte2Hex(byte[] buff, boolean upper) {
        String hexStr = "";
        String tempStr = "";
        for (int n = 0; n < buff.length; n++) {
            tempStr = Integer.toHexString(buff[n] & 0xFF);
            if (tempStr.length() == 1) {
                hexStr = new StringBuffer().append(hexStr).append("0").append(tempStr).toString();
            } else {
                hexStr = new StringBuffer().append(hexStr).append(tempStr).toString();
            }
        }
        if (upper) {
            return hexStr.toUpperCase();
        }
        return hexStr.toLowerCase();
    }
}
