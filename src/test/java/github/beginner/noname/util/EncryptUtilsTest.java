package github.beginner.noname.util;

import org.junit.Test;

import java.security.NoSuchAlgorithmException;
import java.util.Arrays;

import static org.junit.Assert.*;

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
 * @date 18/12/12 0:03
 */

public class EncryptUtilsTest {

    @Test
    public void generatePassword() throws NoSuchAlgorithmException {
        System.out.println(EncryptUtils.generatePassword("wang", "pt"));
    }

    @Test
    public void encodeMD5ToByte() throws NoSuchAlgorithmException {
        System.out.println(Arrays.toString(EncryptUtils.encodeMD5ToByte("wangpt")));
    }

    @Test
    public void encodeMD5ToString() throws NoSuchAlgorithmException {
        System.out.println(EncryptUtils.encodeMD5ToString("wangpt"));
    }
}