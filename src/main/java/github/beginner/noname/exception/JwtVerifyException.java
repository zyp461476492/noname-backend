package github.beginner.noname.exception;

import lombok.Getter;
import lombok.Setter;

/**
 * @author zyp on 2019/2/19
 */
@Getter
@Setter
public class JwtVerifyException extends RuntimeException {
    private String msg;

    public JwtVerifyException(String msg) {
        super();
        this.msg = msg;
    }

}
