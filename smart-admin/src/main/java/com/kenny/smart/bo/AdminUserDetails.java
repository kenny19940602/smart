package com.kenny.smart.bo;

import com.kenny.smart.model.SysPrivilege;
import com.kenny.smart.model.SysUser;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

/**
 * ClassName: AdminUserDetails
 * Function:  SpringSecurity需要的用户详情
 * Date:      2019/9/20 15:26
 * @author Kenny
 * version    V1.0
 */
public class AdminUserDetails  implements UserDetails {
    private SysUser sysUser;
    private List<SysPrivilege> privilegeList;

    public AdminUserDetails(SysUser sysUser, List<SysPrivilege> privilegeList) {
        this.sysUser = sysUser;
        this.privilegeList = privilegeList;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return privilegeList.stream().filter(permission -> permission.getValue() != null)
                .map(permission -> new SimpleGrantedAuthority(permission.getValue()))
                .collect(Collectors.toList());
    }

    @Override
    public String getPassword() {
        return sysUser.getUserPassword();
    }

    @Override
    public String getUsername() {
        return sysUser.getUserName();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return sysUser.getStatus().equals(0);
    }
}
