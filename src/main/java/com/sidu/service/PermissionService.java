package com.sidu.service;


import com.sidu.domain.Permission;

/**
 * Created by dell on 2017/2/3.
 */
public interface PermissionService {
    public Permission createPermission(Permission permission);
    public void deletePermission(Long permissionId);
}
