package coder.yqwh.usr.api.model;

import lombok.Data;

import java.util.Date;

/**
 * @author Administrator
 * @time 2020/12/2 21:43
 */
@Data
public class UsrBaseUserDTO {

    private Long id;

    /**
     * 用户名
     */
    private String name;

    /**
     * 用户编号
     */
    private String userNo;

    /**
     * 租户编号
     */
    private String tenantNo;

    /**
     * 删除(1), 正常(0)
     */
    private Byte delFlag;

    /**
     * 创建人
     */
    private String creator;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 更新人
     */
    private String modifier;

    /**
     * 更新时间
     */
    private Date modifyTime;
}
