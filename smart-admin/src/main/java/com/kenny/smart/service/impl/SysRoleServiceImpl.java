package com.kenny.smart.service.impl;

import com.kenny.smart.mapper.SysPrivilegeMapper;
import com.kenny.smart.mapper.SysRoleMapper;
import com.kenny.smart.mapper.SysRolePrivilegeMapper;
import com.kenny.smart.model.*;
import com.kenny.smart.service.ISysRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * ClassName: SysRoleServiceImpl
 * Function:  用户角色业务层实现类
 * Date:      2019/9/25 17:24
 * @author Kenny
 * version    V1.0
 */
@Service
public class SysRoleServiceImpl implements ISysRoleService {
    @Autowired
    private SysRoleMapper sysRoleMapper;
    @Autowired
    private SysRolePrivilegeMapper sysRolePrivilegeMapper;
    @Autowired
    private SysPrivilegeMapper sysPrivilegeMapper;

    @Override
    public boolean create(SysRole role) {
        role.setCreateTime(new Date());
        role.setStatus(0);
        return 1 == sysRoleMapper.insert(role);
    }

    @Override
    public boolean delete(List<Long> ids) {
        SysRoleExample sysRoleExample = new SysRoleExample();
        sysRoleExample.createCriteria().andIdIn(ids);
        return 0 != sysRoleMapper.deleteByExample(sysRoleExample);
    }

    @Override
    public boolean update(SysRole role) {
        return 1 != sysRoleMapper.updateByPrimaryKeySelective(role);
    }

    @Override
    public boolean updatePrivilege(Long roleId, List<Long> privilegeIds) {
        int count = privilegeIds == null ? 0 : privilegeIds.size();
        //先删除原有关系
        SysRolePrivilegeExample rolePrivilegeExample = new SysRolePrivilegeExample();
        rolePrivilegeExample.createCriteria().andRoleIdEqualTo(roleId);
        sysRolePrivilegeMapper.deleteByExample(rolePrivilegeExample);
        //建立新关系
        List<SysRolePrivilege> list = new ArrayList<>();
        if (!CollectionUtils.isEmpty(privilegeIds)) {
            for (Long privilege : privilegeIds) {
                SysRolePrivilege rolePrivilege = new SysRolePrivilege();
                rolePrivilege.setRoleId(roleId);
                rolePrivilege.setPrivilegeId(privilege);
                list.add(rolePrivilege);
            }
        }
        return count == sysRolePrivilegeMapper.insertList(list);
    }

    @Override
    public List<SysPrivilege> getPrivilege(Long roleId) {
        return sysPrivilegeMapper.selectPrivilegeByRoleId(roleId);
    }

    @Override
    public List<SysRole> list() {
        return sysRoleMapper.selectByExample(new SysRoleExample());
    }

}
