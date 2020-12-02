package coder.yqwh.model;

import java.util.Date;
import javax.persistence.*;

@Table(name = "`usr_base_user`")
public class UsrBaseUser {
    @Id
    @Column(name = "`id`")
    @GeneratedValue(generator = "JDBC")
    private Long id;

    /**
     * 用户名
     */
    @Column(name = "`name`")
    private String name;

    /**
     * 用户编号
     */
    @Column(name = "`user_no`")
    private String userNo;

    /**
     * 租户编号
     */
    @Column(name = "`tenant_no`")
    private String tenantNo;

    /**
     * 删除(1), 正常(0)
     */
    @Column(name = "`del_flag`")
    private Byte delFlag;

    /**
     * 创建人
     */
    @Column(name = "`creator`")
    private String creator;

    /**
     * 创建时间
     */
    @Column(name = "`create_time`")
    private Date createTime;

    /**
     * 更新人
     */
    @Column(name = "`modifier`")
    private String modifier;

    /**
     * 更新时间
     */
    @Column(name = "`modify_time`")
    private Date modifyTime;

    /**
     * @return id
     */
    public Long getId() {
        return id;
    }

    /**
     * @param id
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * 获取用户名
     *
     * @return name - 用户名
     */
    public String getName() {
        return name;
    }

    /**
     * 设置用户名
     *
     * @param name 用户名
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * 获取用户编号
     *
     * @return user_no - 用户编号
     */
    public String getUserNo() {
        return userNo;
    }

    /**
     * 设置用户编号
     *
     * @param userNo 用户编号
     */
    public void setUserNo(String userNo) {
        this.userNo = userNo;
    }

    /**
     * 获取租户编号
     *
     * @return tenant_no - 租户编号
     */
    public String getTenantNo() {
        return tenantNo;
    }

    /**
     * 设置租户编号
     *
     * @param tenantNo 租户编号
     */
    public void setTenantNo(String tenantNo) {
        this.tenantNo = tenantNo;
    }

    /**
     * 获取删除(1), 正常(0)
     *
     * @return del_flag - 删除(1), 正常(0)
     */
    public Byte getDelFlag() {
        return delFlag;
    }

    /**
     * 设置删除(1), 正常(0)
     *
     * @param delFlag 删除(1), 正常(0)
     */
    public void setDelFlag(Byte delFlag) {
        this.delFlag = delFlag;
    }

    /**
     * 获取创建人
     *
     * @return creator - 创建人
     */
    public String getCreator() {
        return creator;
    }

    /**
     * 设置创建人
     *
     * @param creator 创建人
     */
    public void setCreator(String creator) {
        this.creator = creator;
    }

    /**
     * 获取创建时间
     *
     * @return create_time - 创建时间
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * 设置创建时间
     *
     * @param createTime 创建时间
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**
     * 获取更新人
     *
     * @return modifier - 更新人
     */
    public String getModifier() {
        return modifier;
    }

    /**
     * 设置更新人
     *
     * @param modifier 更新人
     */
    public void setModifier(String modifier) {
        this.modifier = modifier;
    }

    /**
     * 获取更新时间
     *
     * @return modify_time - 更新时间
     */
    public Date getModifyTime() {
        return modifyTime;
    }

    /**
     * 设置更新时间
     *
     * @param modifyTime 更新时间
     */
    public void setModifyTime(Date modifyTime) {
        this.modifyTime = modifyTime;
    }
}