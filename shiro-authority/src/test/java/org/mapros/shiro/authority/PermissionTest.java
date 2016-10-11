package org.mapros.shiro.authority;

import org.apache.shiro.authz.UnauthorizedException;
import org.apache.shiro.authz.permission.WildcardPermission;
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

    @Test
    public void testWildcardPermission_1() throws Exception {
        doLogin("classpath:shiro.permission.ini", "admin", "12345");
        subject().checkPermission("system:user:update");
        subject().checkPermissions("system:user:update", "system:user:delete");
        subject().checkPermissions("system:user:update,delete");
    }

    @Test
    public void testWildcardPermission_2() throws Exception{
        doLogin("classpath:shiro.permission.ini", "admin", "12345");
        subject().checkPermissions("system:user:create,delete,update:view");
        subject().checkPermissions("system:user:*");
        subject().checkPermissions("system:user");
    }

    @Test
    public void testWildcardPermission_3() throws Exception{
        doLogin("classpath:shiro.permission.ini", "admin", "12345");
        subject().checkPermissions("user:view");
        subject().checkPermissions("system:user:view");
    }

    @Test
    public void testWildcardPermission_4() throws Exception{
        doLogin("classpath:shiro.permission.ini", "admin", "12345");
        subject().checkPermissions("user:view:1");
        subject().checkPermissions("user:delete,update:1");
        subject().checkPermissions("user:update:1", "user:delete:1");
        subject().checkPermissions("user:update:1", "user:delete:1", "user:view:1");
        subject().checkPermissions("user:auth:1", "user:auth:2");
        subject().checkPermissions("user:view:1", "user:auth:2");
    }

    @Test
    public void testWildcardPermission_5() throws Exception{
        doLogin("classpath:shiro.permission.ini", "admin", "12345");
        subject().checkPermissions("menu:view:1");
        subject().checkPermissions("organization");
        subject().checkPermissions("organization:view");
        subject().checkPermissions("organization:view:1");
    }


    @Test
    public void testWildcardPermission6() throws Exception{
        doLogin("classpath:shiro.permission.ini", "admin", "12345");
        subject().checkPermission("menu:view:1");
        subject().checkPermission(new WildcardPermission("menu:view:1"));
    }

}
