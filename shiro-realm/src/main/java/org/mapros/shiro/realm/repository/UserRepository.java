package org.mapros.shiro.realm.repository;

import org.mapros.shiro.realm.entity.User;

/**
 * @author mapros
 */
public interface UserRepository {
    User createUser(User user);

}
