package coder.yqwh.usr.util;

/**
 * @author Administrator
 * @time 2020/12/2 22:02
 */
public class UsrResponseCode extends ResponseCode {

    public UsrResponseCode(Integer code, String template) {
        super(code, template);
    }

    @Override
    public Integer prefixCode() {
        return 1001;
    }

    public static final ResponseCode SUCCESS = new UsrResponseCode(0, "成功");
    public static final ResponseCode FAIL = new UsrResponseCode(1, "失败");
}
