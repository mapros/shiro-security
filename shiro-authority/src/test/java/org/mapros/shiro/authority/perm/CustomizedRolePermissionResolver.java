package org.mapros.shiro.authority.perm;

import org.apache.shiro.authz.Permission;
import org.apache.shiro.authz.permission.RolePermissionResolver;
import org.apache.shiro.authz.permission.WildcardPermission;

import java.util.Collection;
import java.util.Collections;

/**
 * Created by HP on 2016/10/11.
 *
 * @author mapros
 */
public class CustomizedRolePermissionResolver implements RolePermissionResolver {
    @Override
    public Collection<Permission> resolvePermissionsInRole(String roleString) {
        if ("role1".equals(roleString)) {
            return Collections.singletonList((Permission) new WildcardPermission("menu:*"));
        }
        return null;
    }
}
