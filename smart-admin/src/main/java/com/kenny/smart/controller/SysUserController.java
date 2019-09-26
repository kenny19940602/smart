package com.kenny.smart.controller;

import com.kenny.smart.common.api.CommonPage;
import com.kenny.smart.common.api.CommonResult;
import com.kenny.smart.model.SysUser;
import com.kenny.smart.service.ISysUserService;
import com.kenny.smart.service.ISysUserService;
import com.kenny.smart.util.JwtTokenUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.security.Principal;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * ClassName: SysUserController
 * Function:  后台用户管理控制器层
 * Date:      2019/9/20 8:40
 * @author Kenny
 * version    V1.0
 */
@RestController
@RequestMapping("/user")
@Api(tags = "SysUserController",description = "后台用户管理")
public class SysUserController {
    private static final Logger LOGGER = LoggerFactory.getLogger(SysUserController.class);
    @Autowired
    private ISysUserService sysUserService;
    @Autowired
    private JwtTokenUtil jwtTokenUtil;
    @Value("${jwt.tokenHeader}")
    private String tokenHeader;
    @Value("${jwt.tokenHead}")
    private String tokenHead;

    @ApiOperation("用户注册")
    @PostMapping("/register")
    public CommonResult<SysUser> register(@RequestBody SysUser sysUser) {
        SysUser user = sysUserService.register(sysUser);
        if(user==null){
            LOGGER.info(sysUser.getUserName()+": 注册失败  "+new Date());
            return CommonResult.failed("用户名已存在！");
        }
        user.setUserPassword("");
        LOGGER.info(user.getUserName()+": 注册成功  "+new Date());
        return CommonResult.success(user);
    }

    @ApiOperation("登录以后返回token")
    @PostMapping("/login")
    public CommonResult login(String userName, String passWord) {
        String token = sysUserService.login(userName, passWord);
        if (token == null) {
            return CommonResult.validateFailed("用户名或密码错误，请重试");
        }
        Map<String, String> tokenMap = new HashMap<>(2);
        tokenMap.put("token", token);
        tokenMap.put("tokenHead", tokenHead);
        return CommonResult.success(tokenMap);
    }

    @ApiOperation("刷新token")
    @GetMapping("/token/refresh")
    public CommonResult refreshToken(HttpServletRequest request) {
        String token = request.getHeader(tokenHeader);
        String refreshToken = sysUserService.refreshToken(token);
        if (refreshToken == null) {
            return CommonResult.failed();
        }
        Map<String, String> tokenMap = new HashMap<>(2);
        tokenMap.put("token", refreshToken);
        tokenMap.put("tokenHead", tokenHead);
        return CommonResult.success(tokenMap);
    }

    @ApiOperation("获取当前登录用户信息")
    @GetMapping("/info")
    public CommonResult getUserInfo(Principal principal) {
        String userName = principal.getName();
        SysUser user = sysUserService.getSysUserByUserName(userName);
        Map<String, Object> data = new HashMap<>(3);
        data.put("userName", user.getUserName());
        data.put("icon", user.getHeadImg());
        data.put("nickName", user.getUserNick());
        return CommonResult.success(data);
    }

    @ApiOperation("登出功能")
    @GetMapping("logout")
    public CommonResult logout() {
        return CommonResult.success(null);
    }

    @ApiOperation("根据用户名分页获取用户列表")
    @GetMapping("list")
    public CommonResult<CommonPage<SysUser>> list(
            @RequestParam(value = "name", required = false) String name,
            @RequestParam(value = "pageSize", defaultValue = "5") Integer pageSize,
            @RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum) {
        List<SysUser> list = sysUserService.list(name, pageSize, pageNum);
        return CommonResult.success(CommonPage.restPage(list));
    }

    @ApiOperation("获取指定用户信息")
    @GetMapping("/{userId}")
    public CommonResult<SysUser> getUserByUserId(@PathVariable Long userId) {
        SysUser user = sysUserService.getUserByUserId(userId);
        return CommonResult.success(user);
    }

    @ApiOperation("修改指定用户信息")
    @PostMapping("/update")
    public CommonResult update(@RequestBody SysUser user) {
        if (sysUserService.update(user)){
            return CommonResult.success("修改成功！");
        }
        return CommonResult.failed("修改失败！请重试");
    }

    @ApiOperation("删除指定用户信息")
    @PostMapping("/delete/{userId}")
    public CommonResult delete(@PathVariable Long userId) {
        if (sysUserService.delete(userId)) {
            return CommonResult.success("删除成功！");
        }
        return CommonResult.failed();
    }

    @ApiOperation("给用户分配角色")
    @PostMapping("/role/update")
    public CommonResult updateRole(@RequestParam("userId") Long userId,
                                   @RequestParam("roleIds") List<Long> roleIds) {
        if (sysUserService.updateRole(userId, roleIds)) {
            return CommonResult.success("修改成功");
        }
        return CommonResult.failed();
    }



}

