package org.mapros.shiro.realm.service.impl;

import org.mapros.shiro.realm.entity.Permission;
import org.mapros.shiro.realm.service.PermissionService;

/**
 * @author mapros
 */
public class PermissionServiceImpl implements PermissionService {
    @Override
    public Permission createPermission(Permission permission) {
        return null;
    }

    @Override
    public void deletePermission(Long pid) {

    }

    @Override
    public void associatePermissionsWithRole(Long rid, Long... pids) {

    }

    @Override
    public void uncorrelationPermissions(Long rid, Long... pids) {

    }
}
