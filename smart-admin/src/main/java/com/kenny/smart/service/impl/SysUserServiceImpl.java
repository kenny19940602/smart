package com.kenny.smart.service.impl;

import com.github.pagehelper.PageHelper;
import com.kenny.smart.mapper.SysRolePrivilegeMapper;
import com.kenny.smart.mapper.SysUserLoginLogMapper;
import com.kenny.smart.mapper.SysUserMapper;
import com.kenny.smart.mapper.SysUserRoleMapper;
import com.kenny.smart.model.*;
import com.kenny.smart.service.ISysUserService;
import com.kenny.smart.type.UserStatus;
import com.kenny.smart.util.JwtTokenUtil;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * ClassName: SysUserServiceImpl
 * Function:  后台管理业务层实现类
 * Date:      2019/9/20 8:45
 * @author Kenny
 * version    V1.0
 */
@Service
public class SysUserServiceImpl implements ISysUserService {
    private static final Logger LOGGER = LoggerFactory.getLogger(SysUserServiceImpl.class);
    @Autowired
    private SysUserMapper sysUserMapper;
    @Autowired
    private SysUserRoleMapper sysUserRoleMapper;
    @Autowired
    private SysRolePrivilegeMapper sysRolePrivilegeMapper;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private JwtTokenUtil jwtTokenUtil;
    @Autowired
    private UserDetailsService userDetailsService;
    @Autowired
    private SysUserLoginLogMapper userLoginLogMapper;
    @Value("${jwt.tokenHead}")
    private String tokenHead;


    @Override
    public SysUser getSysUserByUserName(String userName) {
        SysUserExample sysUserExample = new SysUserExample();
        sysUserExample.createCriteria().andUserNameEqualTo(userName);
        List<SysUser> sysUserList = sysUserMapper.selectByExample(sysUserExample);
        if (sysUserList != null && sysUserList.size() > 0) {
            return sysUserList.get(0);
        }
        return null;
    }

    @Override
    public SysUser register(SysUser sysUser) {
        //TODO 前台做简单验证（例：用户名密码不为空...
        //查询用户名是否已被占用
        if(getSysUserByUserName(sysUser.getUserName())!=null){
            return null;
        }
        //TODO 以后涉及到用户信息开发需增加用户相应的信息验证
        //密码加密
        String encodePassword = passwordEncoder.encode(sysUser.getUserPassword());
        sysUser.setUserPassword(encodePassword);
        //TODO 创建人
        sysUser.setStatus(UserStatus.enabled);
        sysUser.setCreateTime(new Date());
        return sysUserMapper.insert(sysUser)==1?sysUser:null;
    }

    @Override
    public String login(String userName, String password) {
        String token = null;
        //密码需要客户端加密后传输
        try {
            UserDetails userDetails = userDetailsService.loadUserByUsername(userName);
            if (!passwordEncoder.matches(password, userDetails.getPassword())) {
                throw new BadCredentialsException("密码不正确");
            }
            UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
            SecurityContextHolder.getContext().setAuthentication(authenticationToken);
            token = jwtTokenUtil.generateToken(userDetails);
            insertLoginLog(userName);
        } catch (AuthenticationException e) {
            LOGGER.warn("登录异常:{}"+e.getMessage());
        }
        return token;
    }

    /**
     * 添加登录记录
     * @param userName
     */
    private void insertLoginLog(String userName) {
        SysUser user = getSysUserByUserName(userName);
        SysUserLoginLog loginLog = new SysUserLoginLog();
        loginLog.setUserId(user.getId());
        loginLog.setCreateTime(new Date());
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();
        loginLog.setIp(request.getRemoteAddr());
        userLoginLogMapper.insert(loginLog);
    }

    @Override
    public SysUser getAllUserAndRolesSelect(Long userId) {
        return sysUserMapper.SelectAllUserAndRolesSelect(userId);
    }

    @Override
    public String refreshToken(String oldToken) {
        String token = oldToken.substring(tokenHead.length());
        if (jwtTokenUtil.canRefresh(token)) {
            return jwtTokenUtil.refreshToken(token);
        }
        return null;
    }

    @Override
    public List<SysUser> list(String name, Integer pageSize, Integer pageNum) {
        PageHelper.startPage(pageNum, pageSize);
        SysUserExample example = new SysUserExample();
        SysUserExample.Criteria criteria = example.createCriteria();
        if (!StringUtils.isEmpty(name)) {
            criteria.andUserNameLike("%" + name + "%");
            example.or(example.createCriteria().andUserNickLike("%" + name + "%"));
        }
        return sysUserMapper.selectByExample(example);
    }

    @Override
    public SysUser getUserByUserId(Long userId) {
        SysUser user = sysUserMapper.SelectAllUserAndRolesSelect(userId);
        user.equals("");
        user.setUserPassword(null);
        return user;
    }

    @Override
    public boolean update(SysUser user) {
        String userPassword = user.getUserPassword();
        if (!StringUtils.isEmpty(userPassword)) {
            String encodePassword = passwordEncoder.encode(userPassword);
            user.setUserPassword(encodePassword);
        }
        return 1 == sysUserMapper.updateByPrimaryKeySelective(user);
    }

    @Override
    public boolean delete(Long userId) {
        return 1 == sysUserMapper.deleteByPrimaryKey(userId);
    }

    @Override
    public boolean updateRole(Long userId, List<Long> roleIds) {
        int count = roleIds == null ? 0 : roleIds.size();
        //先删除原有关系
        SysUserRoleExample userRoleExample = new SysUserRoleExample();
        userRoleExample.createCriteria().andUserIdEqualTo(userId);
        sysUserRoleMapper.deleteByExample(userRoleExample);
        //建立新关系
        List<SysUserRole> list = new ArrayList<>();
        if (!CollectionUtils.isEmpty(roleIds)) {
            for (Long roleId : roleIds) {
                SysUserRole userRole = new SysUserRole();
                userRole.setUserId(userId);
                userRole.setRoleId(roleId);
                list.add(userRole);
            }
        }
        return count == sysUserRoleMapper.insertList(list);
    }


}
