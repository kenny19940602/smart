package com.kenny.smart.dto;

import com.kenny.smart.model.SysPrivilege;
import lombok.Getter;
import lombok.Setter;

import java.util.List;


/**
 * ClassName: SysPrivilegeNode
 * Function:  权限树实体类
 * Date:      2019/9/26 12:41
 * @author Kenny
 * version    V1.0
 */
public class SysPrivilegeNode extends SysPrivilege {

    @Getter
    @Setter
    private List<SysPrivilegeNode> children;
}
