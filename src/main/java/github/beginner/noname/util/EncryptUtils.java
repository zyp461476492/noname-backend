package github.beginner.noname.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

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
 * @date 18/12/11 23:35
 */

public class EncryptUtils {

    public static String generatePassword(String userName, String userPassword) throws NoSuchAlgorithmException {
        return encodeMD5ToString(userName + userPassword);
    }


    public static byte[] encodeMD5ToByte(String data) throws NoSuchAlgorithmException {
        MessageDigest messageDigest = MessageDigest.getInstance("MD5");
        return messageDigest.digest(data.getBytes());
    }

    public static String encodeMD5ToString(String data) throws NoSuchAlgorithmException {
        MessageDigest messageDigest = MessageDigest.getInstance("MD5");
        return StringUtils.byte2Hex(messageDigest.digest(data.getBytes()), true);
    }

    public static void main(String[] args) throws NoSuchAlgorithmException {
        System.out.println(generatePassword("vvv", "nonameuser"));
    }

}
