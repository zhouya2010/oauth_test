package com.sidu.dao;

import com.sidu.domain.Role;

/**
 * Created by dell on 2017/2/3.
 */
public interface RoleMapper {
    public Role createRole(Role role);
    public void deleteRole(Long roleId);

    public void correlationPermissions(Long roleId, Long... permissionIds);
    public void uncorrelationPermissions(Long roleId, Long... permissionIds);
}
