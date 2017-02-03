package com.sidu.dao;

import com.sidu.domain.Permission;

/**
 * Created by dell on 2017/2/3.
 */
public interface PermissionMapper {
    public Permission createPermission(Permission permission);

    public void deletePermission(Long permissionId);
}
