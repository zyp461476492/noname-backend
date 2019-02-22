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
        StringBuilder hexStr = new StringBuilder();
        String tempStr = "";
        for (byte b : buff) {
            tempStr = Integer.toHexString(b & 0xFF);
            if (tempStr.length() == 1) {
                hexStr.append("0").append(tempStr);
            } else {
                hexStr.append(tempStr);
            }
        }
        if (upper) {
            return hexStr.toString().toUpperCase();
        }
        return hexStr.toString().toLowerCase();
    }
}
