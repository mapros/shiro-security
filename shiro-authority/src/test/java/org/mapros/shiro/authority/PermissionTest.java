package org.mapros.shiro.authority;

import org.apache.shiro.authz.UnauthorizedException;
import org.junit.Assert;
import org.junit.Test;

/**
 * Created by HP on 2016/10/11.
 *
 * @author mapros
 */
public class PermissionTest extends BaseTest {
    @Test
    public void testIsPermitted() throws Exception {
        doLogin("classpath:shiro.permission.ini", "system", "123456");
        Assert.assertTrue(subject().isPermitted("user:create"));
        Assert.assertTrue(subject().isPermittedAll("user:create", "user:update"));
        Assert.assertTrue(subject().isPermittedAll("user:delete", "user:create"));
        Assert.assertFalse(subject().isPermitted("user:view"));
    }

    @Test(expected = UnauthorizedException.class)
    public void testCheckPermission() throws Exception {
        doLogin("classpath:shiro.permission.ini", "mapros", "984138");
        subject().checkPermission("user:create");
        subject().checkPermissions("user:create", "user:update");
        //mapros do not have user:delete permission,it will throw UnauthorizedException
        subject().checkPermission("user:delete");
    }
}
