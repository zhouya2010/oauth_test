package com.sidu.dao;

import com.sidu.domain.Permission;
import org.springframework.stereotype.Repository;

/**
 * Created by dell on 2017/2/3.
 */
@Repository
public interface PermissionMapper {
    public Permission createPermission(Permission permission);

    public void deletePermission(Long permissionId);
}
