package com.kenny.smart.controller;

import com.kenny.smart.common.api.CommonResult;
import com.kenny.smart.model.SysPrivilege;
import com.kenny.smart.model.SysRole;
import com.kenny.smart.service.ISysRoleService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

/**
 * ClassName: SysRoleController
 * Function:  用户角色控制器
 * Date:      2019/9/25 17:18
 * @author Kenny
 * version    V1.0
 */
@RestController
@RequestMapping("/role")
@Api(tags = "SysRoleController", description = "用户角色管理")
public class SysRoleController {
    @Autowired
    private ISysRoleService sysRoleService;

    @ApiOperation("添加/修改角色")
    @PostMapping("/createorupdate")
    public CommonResult create(@RequestBody SysRole role, Principal principal) {
        if (role.getId() != null) {
            String userName = principal.getName();
            role.setCreateBy(userName);
            if (sysRoleService.create(role)) {
                return CommonResult.success("添加角色成功！");
            }
            return CommonResult.failed();
        }
        if (sysRoleService.update(role)) {
            return CommonResult.success("修改角色成功！");
        }
        return CommonResult.failed();
    }

    @ApiOperation("批量删除角色")
    @PostMapping("/delete")
    public CommonResult delete(@RequestParam("ids") List<Long> ids) {
        if (sysRoleService.delete(ids)) {
            return CommonResult.success("删除角色成功！");
        }
        return CommonResult.failed();
    }

    @ApiOperation("给角色赋权")
    @PostMapping("/privilege/update")
    public CommonResult updatePrivilege(@RequestParam("roleId") long roleId,
                                        @RequestParam("privilegeIds") List<Long> privilegeIds) {
        if (sysRoleService.updatePrivilege(roleId, privilegeIds)) {
            return CommonResult.success("修改成功");
        }
        return CommonResult.failed();
    }

    @ApiOperation("获取相应角色权限")
    @GetMapping("/privilege/{roleId}")
    public CommonResult<List<SysPrivilege>> getPrivilege(@PathVariable Long roleId) {
        return CommonResult.success(sysRoleService.getPrivilege(roleId));
    }

    @ApiOperation("获取所有角色")
    @GetMapping("/list")
    public List<SysRole> getRoleList(){
        return sysRoleService.list();
    }

}
