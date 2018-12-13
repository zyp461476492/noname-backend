package github.beginner.noname.common.exception;

import github.beginner.noname.common.enums.ExceptionEnum;

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
 * @date 18/12/13 14:13
 */

public class NonameException extends RuntimeException {
    private Integer code;

    public NonameException(ExceptionEnum resultEnum) {
        super(resultEnum.getMsg());
        this.code = resultEnum.getCode();
    }

    public NonameException(Integer code, String message) {
        super(message);
        this.code = code;
    }
}
