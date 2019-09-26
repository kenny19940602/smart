package com.kenny.smart.service;

import com.kenny.smart.dto.SysPrivilegeNode;
import com.kenny.smart.model.SysPrivilege;

import java.util.List;

/**
 * ClassName: ISysPrivilegeService
 * Function:  用户权限业务层接口
 * Date:      2019/9/26 11:27
 * @author Kenny
 * version    V1.0
 */
public interface ISysPrivilegeService {
    /**
     * 添加权限
     * @param privilege
     * @return
     */
    boolean create(SysPrivilege privilege);

    /**
     * 修改权限
     * @param privilege
     * @return
     */
    boolean update(SysPrivilege privilege);

    /**
     * 批量删除权限
     * @param ids
     * @return
     */
    boolean delete(List<Long> ids);

    /**
     * 获取所有权限
     * @return
     */
    List<SysPrivilege> list();

    /**
     * 以层级结构返回所有权限
     * @return
     */
    List<SysPrivilegeNode> treeList();
}
