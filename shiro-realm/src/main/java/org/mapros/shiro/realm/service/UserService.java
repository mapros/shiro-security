package org.mapros.shiro.realm.service;

import org.mapros.shiro.realm.entity.User;

import java.util.Set;

/**
 * @author mapros
 */
public interface UserService {
    /**
     * create a new user
     *
     * @param user new user
     * @return new user
     */
    User createUser(User user);

    /**
     * modify old password to new password
     *
     * @param uid         user id
     * @param newPassword new password
     */
    void modifyPassword(Long uid, String newPassword);

    /**
     * associate roles with the user
     *
     * @param uid  user id
     * @param rids role ids
     */
    void associateUserWithRoles(Long uid, Long... rids);

    /**
     * un associate roles with the user
     *
     * @param uid  user id
     * @param rids role ids
     */
    void unAssociateUserWithRoles(Long uid, Long... rids);

    /**
     * find user by username
     *
     * @param username unique username
     * @return user
     */
    User findByUsername(String username);

    /**
     * find user roles
     *
     * @param username username
     * @return user roles
     */
    Set<String> findRoles(String username);

    /**
     * find user permissions
     *
     * @param username username
     * @return user permissions
     */
    Set<String> findPermissions(String username);
}
