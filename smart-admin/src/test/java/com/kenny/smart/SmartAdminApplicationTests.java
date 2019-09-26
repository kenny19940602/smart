package com.kenny.smart;

import com.kenny.smart.mapper.SysPrivilegeMapper;
import com.kenny.smart.mapper.SysRoleMapper;
import com.kenny.smart.mapper.SysUserMapper;
import com.kenny.smart.model.SysPrivilege;
import com.kenny.smart.model.SysRole;
import com.kenny.smart.model.SysUser;
import com.kenny.smart.service.ISysUserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SmartAdminApplicationTests {
    @Autowired
    private SysPrivilegeMapper privilegeMapper;
    @Autowired
    private SysRoleMapper roleMapper;
    @Autowired
    private SysUserMapper sysUserMapper;
    @Autowired
    private ISysUserService adminService;

    @Test
    public void contextLoads() {
    }

    @Test
    public void selectPrivilegeByRoleId(){
        List<SysPrivilege> privilegeList = privilegeMapper.selectPrivilegeByRoleId(1L);
        System.out.println(privilegeList);

    }
    @Test
    public void selectRoleByUserId(){
        List<SysRole> roleList = roleMapper.selectRoleByUserId(1L);
        System.out.println(roleList);

    }

    @Test
    public void SelectAllUserAndRolesSelect(){
        SysUser sysUser = sysUserMapper.SelectAllUserAndRolesSelect(1L);

    }

    @Test
    public void update(){
        SysUser user = new SysUser();
        user.setId(4L);
        user.setUserNick("root5");
        int result = sysUserMapper.updateByPrimaryKeySelective(user);
    }

    @Test
    public void updateRole(){
        List<Long> list = new ArrayList<>();
        list.add(1L);
        list.add(2L);
        list.add(3L);
        list.add(4L);
        boolean result = adminService.updateRole(1L, list);
        System.out.println(result);
    }

    @Test
    public void updatePrivilege() {
        List<Long> list = new ArrayList<>();
        list.add(1L);
        list.add(2L);
        list.add(3L);
        list.add(4L);
//        boolean result = adminService.updatePrivilege(1L, list);
//        System.out.println(result);
    }

}
