package com.kenny.smart.service.impl;

import com.kenny.smart.dto.SysPrivilegeNode;
import com.kenny.smart.mapper.SysPrivilegeMapper;
import com.kenny.smart.model.SysPrivilege;
import com.kenny.smart.model.SysPrivilegeExample;
import com.kenny.smart.service.ISysPrivilegeService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

/**
 * ClassName: SysPrivilegeServiceImpl
 * Function:  用户权限业务层实现类
 * Date:      2019/9/26 11:30
 * @author Kenny
 * version    V1.0
 */
@Service
public class SysPrivilegeServiceImpl implements ISysPrivilegeService {
    @Autowired
    private SysPrivilegeMapper sysPrivilegeMapper;
    @Override
    public boolean create(SysPrivilege privilege) {
        SysPrivilege privilege1 = new SysPrivilege();
        privilege.setStatus(0);
        privilege.setCreateTime(new Date());
        return 1 == sysPrivilegeMapper.insert(privilege);
    }

    @Override
    public boolean update(SysPrivilege privilege) {
        return 1 == sysPrivilegeMapper.updateByPrimaryKeySelective(privilege);
    }

    @Override
    public boolean delete(List<Long> ids) {
        int count = ids == null ? 0 : ids.size();
        SysPrivilegeExample privilegeExample = new SysPrivilegeExample();
        privilegeExample.createCriteria().andIdIn(ids);
        return count == sysPrivilegeMapper.deleteByExample(privilegeExample);
    }

    @Override
    public List<SysPrivilege> list() {
        return sysPrivilegeMapper.selectByExample(new SysPrivilegeExample());
    }

    @Override
    public List<SysPrivilegeNode> treeList() {
        List<SysPrivilege> privilegeList = sysPrivilegeMapper.selectByExample(new SysPrivilegeExample());
        return privilegeList.stream()
                .filter(privilege -> privilege.getId().equals(0))
                .map(privilege -> covert(privilege, privilegeList)).collect(Collectors.toList());
    }

    /**
     *将权限转换为带有子级的权限对象
     * @param privilege
     * @param privilegeList
     * @return
     */
    private SysPrivilegeNode covert(SysPrivilege privilege, List<SysPrivilege> privilegeList) {
        SysPrivilegeNode node = new SysPrivilegeNode();
        BeanUtils.copyProperties(privilege, node);
        List<SysPrivilegeNode> children = privilegeList.stream()
                .filter(subPrivilege -> subPrivilege.getPid().equals(privilege.getId()))
                .map(subPrivilege -> covert(subPrivilege,privilegeList)).collect(Collectors.toList());
        node.setChildren(children);
        return node;
    }
}
