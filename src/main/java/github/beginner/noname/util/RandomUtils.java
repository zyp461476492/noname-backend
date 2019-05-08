package github.beginner.noname.util;

import java.util.UUID;

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
 * @date 18/12/9 16:01
 */

public final class RandomUtils {

    /**
     * 生成uuid
     * 例如：adab4f0b-e0e7-4013-bc0e-fba7983f212c
     *
     * @return uuid
     */
    public String uuid() {
        return UUID.randomUUID().toString();
    }

    /**
     * 短格式uuid，不含"-"
     * 例如：4a376c21b34043efbceba78a0d47c364
     *
     * @return shortuuid
     */
    public String uuidShort() {
        return UUID.randomUUID().toString().replace("-", "");
    }

    public static void main(String[] args) {
        RandomUtils randomUtils = new RandomUtils();
        for (int i = 0; i < 20; i++) {
            System.out.println(randomUtils.uuidShort());
        }

    }
}
