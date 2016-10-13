package org.mapros.shiro.realm.service.impl;

import org.mapros.shiro.realm.entity.User;
import org.mapros.shiro.realm.service.UserService;

import java.util.Set;

/**
 * @author mapros
 */
public class UserServiceImpl implements UserService {
    @Override
    public User createUser(User user) {


        return null;
    }

    @Override
    public void modifyPassword(Long uid, String newPassword) {

    }

    @Override
    public void associateUserWithRoles(Long uid, Long... rids) {

    }

    @Override
    public void unAssociateUserWithRoles(Long uid, Long... rids) {

    }

    @Override
    public User findByUsername(String username) {
        return null;
    }

    @Override
    public Set<String> findRoles(String username) {
        return null;
    }

    @Override
    public Set<String> findPermissions(String username) {
        return null;
    }
}
