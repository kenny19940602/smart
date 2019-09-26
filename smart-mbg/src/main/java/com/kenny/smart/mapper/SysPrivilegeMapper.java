package com.kenny.smart.mapper;

import com.kenny.smart.model.SysPrivilege;
import com.kenny.smart.model.SysPrivilegeExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface SysPrivilegeMapper {
    long countByExample(SysPrivilegeExample example);

    int deleteByExample(SysPrivilegeExample example);

    int deleteByPrimaryKey(Long id);

    int insert(SysPrivilege record);

    int insertSelective(SysPrivilege record);

    List<SysPrivilege> selectByExample(SysPrivilegeExample example);

    SysPrivilege selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") SysPrivilege record, @Param("example") SysPrivilegeExample example);

    int updateByExample(@Param("record") SysPrivilege record, @Param("example") SysPrivilegeExample example);

    int updateByPrimaryKeySelective(SysPrivilege record);

    int updateByPrimaryKey(SysPrivilege record);

    List<SysPrivilege>selectPrivilegeByRoleId(Long roleId);
}