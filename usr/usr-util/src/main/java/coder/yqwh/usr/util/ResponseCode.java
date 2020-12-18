package coder.yqwh.usr.util;

/**
 * @author Administrator
 * @time 2020/12/2 21:58
 */
public abstract class ResponseCode {

    public ResponseCode() {}

    public ResponseCode(Integer code, String template) {
        this.code = code;
        this.template = template;
    }

    private Integer code;

    private String template;

    public abstract Integer prefixCode();

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getTemplate() {
        return template;
    }

    public void setTemplate(String template) {
        this.template = template;
    }

    public ResponseCode addArgs(String... args) {
        this.template = String.format(this.template, args);
        return this;
    }
}
