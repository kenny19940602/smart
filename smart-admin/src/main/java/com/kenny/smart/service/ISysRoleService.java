package com.kenny.smart.service;

import com.kenny.smart.model.SysPrivilege;
import com.kenny.smart.model.SysRole;

import java.util.List;

/**
 * ClassName: ISysRoleService
 * Function:  用户角色业务层接口
 * Date:      2019/9/25 17:20
 * @author Kenny
 * version    V1.0
 */
public interface ISysRoleService {

    /**
     * 添加角色
     * @param role
     * @return
     */
    boolean create(SysRole role);

    /**
     * 修改角色信息
     * @param role
     * @return
     */
    boolean update(SysRole role);

    /**
     * 批量删除角色
     * @param ids
     * @return
     */
    boolean delete(List<Long> ids);

    /**
     * 给角色赋值
     * @param roleId
     * @param privilegeIds
     * @return
     */
    boolean updatePrivilege(Long roleId, List<Long> privilegeIds);

    /**
     * 获取指定角色权限
     * @param roleId
     * @return
     */
    List<SysPrivilege> getPrivilege(Long roleId);

    /**
     * 获取角色列表
     * @return
     */
    List<SysRole> list();
}
