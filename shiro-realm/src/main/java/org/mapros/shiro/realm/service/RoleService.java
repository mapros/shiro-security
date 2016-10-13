package org.mapros.shiro.realm.service;

import org.mapros.shiro.realm.entity.Role;

/**
 * @author mapros
 */
public interface RoleService {
    /**
     * create role
     *
     * @param role new role
     * @return new role
     */
    Role createRole(Role role);

    /**
     * delete role by role id
     *
     * @param rid role id
     */
    void deleteRole(Long rid);
}
