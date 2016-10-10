package org.mapros.shiro.example.realm;

import org.apache.shiro.authc.*;
import org.apache.shiro.realm.Realm;

/**
 * Created by HP on 2016/10/10.
 *
 * @author mapros
 */
public class SystemAccountRealm implements Realm {
    public String getName() {
        return getClass().getSimpleName();
    }

    public boolean supports(AuthenticationToken token) {
        return token instanceof UsernamePasswordToken;
    }

    public AuthenticationInfo getAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        String username = (String) token.getPrincipal();
        String password = new String((char[]) token.getCredentials());
        if (!"system".equals(username)) {
            throw new UnknownAccountException();
        }
        if (!"123456".equals(password)) {
            throw new IncorrectCredentialsException();
        }
        return new SimpleAuthenticationInfo(username, password, getName());
    }
}
