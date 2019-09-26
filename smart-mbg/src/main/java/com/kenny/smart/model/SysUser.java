package com.kenny.smart.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.kenny.smart.type.UserStatus;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

@JsonIgnoreProperties(value = {"handler"})
public class SysUser implements Serializable {
    @ApiModelProperty(value = "用户ID")
    private Long id;

    @ApiModelProperty(value = "用户名")
    private String userName;

    @ApiModelProperty(value = "用户昵称")
    private String userNick;

    @ApiModelProperty(value = "用户密码")
    private String userPassword;

    @ApiModelProperty(value = "用户信息")
    private String userInfo;

    @ApiModelProperty(value = "用户头像")
    private String headImg;

    @ApiModelProperty(value = "用户状无效态（0正常，1无效，2删除）")
    private UserStatus status;

    @ApiModelProperty(value = "用户邮箱")
    private String email;

    @ApiModelProperty(value = "用户电话")
    private String telephone;

    @ApiModelProperty(value = "创建时间")
    private Date createTime;

    @ApiModelProperty(value = "创建人")
    private String createBy;

    @ApiModelProperty(value = "删除时间")
    private Date deleteTime;

    @ApiModelProperty(value = "删除人")
    private String deleteBy;

    @ApiModelProperty(value = "用户权限")
    private List<SysRole> roleList;

    public List<SysRole> getRoleList() {
        return roleList;
    }

    public void setRoleList(List<SysRole> roleList) {
        this.roleList = roleList;
    }

    private static final long serialVersionUID = 1L;

    public UserStatus getStatus() {
        return status;
    }

    public void setStatus(UserStatus status) {
        this.status = status;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserNick() {
        return userNick;
    }

    public void setUserNick(String userNick) {
        this.userNick = userNick;
    }

    public String getUserPassword() {
        return userPassword;
    }

    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }

    public String getUserInfo() {
        return userInfo;
    }

    public void setUserInfo(String userInfo) {
        this.userInfo = userInfo;
    }

    public String getHeadImg() {
        return headImg;
    }

    public void setHeadImg(String headImg) {
        this.headImg = headImg;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getCreateBy() {
        return createBy;
    }

    public void setCreateBy(String createBy) {
        this.createBy = createBy;
    }

    public Date getDeleteTime() {
        return deleteTime;
    }

    public void setDeleteTime(Date deleteTime) {
        this.deleteTime = deleteTime;
    }

    public String getDeleteBy() {
        return deleteBy;
    }

    public void setDeleteBy(String deleteBy) {
        this.deleteBy = deleteBy;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", userName=").append(userName);
        sb.append(", userNick=").append(userNick);
        sb.append(", userPassword=").append(userPassword);
        sb.append(", userInfo=").append(userInfo);
        sb.append(", headImg=").append(headImg);
        sb.append(", status=").append(status);
        sb.append(", email=").append(email);
        sb.append(", telephone=").append(telephone);
        sb.append(", createTime=").append(createTime);
        sb.append(", createBy=").append(createBy);
        sb.append(", deleteTime=").append(deleteTime);
        sb.append(", deleteBy=").append(deleteBy);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}