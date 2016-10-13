package org.mapros.shiro.realm.service;

import org.mapros.shiro.realm.entity.Permission;

/**
 * @author mapros
 */
public interface PermissionService {
    /**
     * create permission
     *
     * @param permission new permission instance
     * @return permission
     */
    Permission createPermission(Permission permission);

    /**
     * delete permission by permission id
     *
     * @param pid permission id
     */
    void deletePermission(Long pid);

    /**
     * associate permissions with the role
     *
     * @param rid  role id
     * @param pids permission ids
     */
    void associatePermissionsWithRole(Long rid, Long... pids);

    /**
     * un associate permissions with the role
     *
     * @param rid  role id
     * @param pids permission ids
     */

    public void uncorrelationPermissions(Long rid, Long... pids);
}
