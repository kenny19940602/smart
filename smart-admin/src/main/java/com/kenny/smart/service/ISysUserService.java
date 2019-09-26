package com.kenny.smart.service;

import com.kenny.smart.model.SysUser;

import java.util.List;

/**
 * ClassName: ISysUserService
 * Function:  后台管理业务层接口
 * Date:      2019/9/20 8:44
 * @author Kenny
 * version    V1.0
 */
public interface ISysUserService {

    /**
     * 根据用户名获取用户信息
     * @param userName 要获取信息的用户名
     * @return 获取的用户信息
     */
    SysUser getSysUserByUserName(String userName);

    /**
     * 用户注册
     * @param sysUser 要注册的用户信息
     * @return 用户信息
     */
    SysUser register(SysUser sysUser);

    /**
     * 用户登录
     * @param userName 用户名
     * @param password 密码
     * @return token信息
     */
    String login(String userName,String password);

    /**
     * 查询用户的所有信息（角色，权限） 注：按需加载
     * @param userId 用户ID
     * @return 用户的所有信息
     */
    SysUser getAllUserAndRolesSelect(Long userId);

    /**
     * 刷新token的功能
     * @param oldToken
     * @return
     */
    String refreshToken(String oldToken);

    /**
     * 根据用户名或昵称分页查询用户
     * @param name
     * @param pageSize
     * @param pageNum
     * @return
     */
    List<SysUser> list(String name, Integer pageSize, Integer pageNum);

    /**
     * 根据用户Id获取用户信息
     * @param userId
     * @return
     */
    SysUser getUserByUserId(Long userId);

    /**
     * 修改用户信息
     * @param user
     * @return
     */
    boolean update(SysUser user);

    /**
     * 删除指定用户
     * @param userId
     * @return
     */
    boolean delete(Long userId);

    /**
     * 修改用户角色关系
     * @param userId
     * @param roleIds
     * @return
     */
    boolean updateRole(Long userId, List<Long> roleIds);


}
