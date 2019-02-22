package github.beginner.noname.domain.dto.common;

import github.beginner.noname.constant.CodeConstant;
import lombok.Data;

/**
 * rest 接口返回响应数据
 *
 * @author zyp on 2018-12-6.
 */
@Data
public class ResponseMsg {
    private Object data;

    private int code;

    private String msg;

    public static ResponseMsg succMsg(String msg) {
        ResponseMsg responseMsg = new ResponseMsg();
        responseMsg.setSuccessResponse(msg);
        return responseMsg;
    }

    public static ResponseMsg failMsg(String msg) {
        ResponseMsg responseMsg = new ResponseMsg();
        responseMsg.setFailResponse(msg);
        return responseMsg;
    }

    public void setSuccessResponse(String msg) {
        this.code = CodeConstant.SUCCESS_CODE;
        this.msg = msg;
    }

    public void setFailResponse(String msg) {
        this.code = CodeConstant.FAIL_CODE;
        this.msg = msg;
    }

}
