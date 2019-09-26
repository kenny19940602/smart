package com.kenny.smart.mapper;

import com.kenny.smart.model.SysRolePrivilege;
import com.kenny.smart.model.SysRolePrivilegeExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface SysRolePrivilegeMapper {
    long countByExample(SysRolePrivilegeExample example);

    int deleteByExample(SysRolePrivilegeExample example);

    int deleteByPrimaryKey(Long id);

    int insert(SysRolePrivilege record);

    int insertSelective(SysRolePrivilege record);

    List<SysRolePrivilege> selectByExample(SysRolePrivilegeExample example);

    SysRolePrivilege selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") SysRolePrivilege record, @Param("example") SysRolePrivilegeExample example);

    int updateByExample(@Param("record") SysRolePrivilege record, @Param("example") SysRolePrivilegeExample example);

    int updateByPrimaryKeySelective(SysRolePrivilege record);

    int updateByPrimaryKey(SysRolePrivilege record);

    int insertList(List<SysRolePrivilege> rolePrivilegeList);
}