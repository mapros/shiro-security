package org.mapros.shiro.encrypt.realm;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;

/**
 * Created by HP on 2016/10/13.
 *
 * @author mapros
 */
public class CustomizedHashRealm extends AuthorizingRealm {
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        return null;
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        String username = (String) token.getPrincipal();
        String randomHex = "74c17fc975a5fed00bccf27e8f5e8c01";
        SimpleAuthenticationInfo info = new SimpleAuthenticationInfo(username, "59e1dc9fbc9476e91f8483eb0aaeb060", getName());
        info.setCredentialsSalt(ByteSource.Util.bytes(username + randomHex));
        return info;
    }
}
