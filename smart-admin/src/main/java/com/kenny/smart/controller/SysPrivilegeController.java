package com.kenny.smart.controller;

import com.kenny.smart.common.api.CommonResult;
import com.kenny.smart.dto.SysPrivilegeNode;
import com.kenny.smart.model.SysPrivilege;
import com.kenny.smart.model.SysRole;
import com.kenny.smart.service.ISysPrivilegeService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * ClassName: SysPrivilegeController
 * Function:  后台用户权限管理
 * Date:      2019/9/26 11:03
 * @author Kenny
 * version    V1.0
 */
@Api(tags = "SysPrivilegeController",description = "后台用户权限管理")
@RestController
@RequestMapping("/privilege")
public class SysPrivilegeController {
    @Autowired
    private ISysPrivilegeService sysPrivilegeService;

    @ApiOperation("添加/修改权限")
    @PostMapping("/createorupdate")
    public CommonResult create(@RequestBody SysPrivilege privilege) {
        if (privilege.getId() != null) {
            if (sysPrivilegeService.create(privilege)) {
                return CommonResult.success("添加权限成功！");
            }
            return CommonResult.failed();
        }
        if (sysPrivilegeService.update(privilege)) {
            return CommonResult.success("修改权限成功！");
        }
        return CommonResult.failed();
    }

    @ApiOperation("根据id批量删除权限")
    @PostMapping("/delete")
    public CommonResult delete(@RequestParam("ids") List<Long> ids) {
        if (sysPrivilegeService.delete(ids)) {
            return CommonResult.success("删除权限成功！");
        }
        return CommonResult.failed();
    }

    @ApiOperation("获取所有权限列表")
    @GetMapping("/list")
    public CommonResult<List<SysPrivilege>> List(){
        return CommonResult.success(sysPrivilegeService.list());
    }

    @ApiOperation("以层级结构返回所有权限")
    @GetMapping("/treeList")
    public CommonResult<List<SysPrivilegeNode>> treeList(){
        return CommonResult.success(sysPrivilegeService.treeList());
    }

}
