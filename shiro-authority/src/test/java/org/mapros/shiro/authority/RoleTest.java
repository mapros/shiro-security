package org.mapros.shiro.authority;

import org.apache.shiro.authz.UnauthorizedException;
import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;

/**
 * Created by HP on 2016/10/11.
 *
 * @author mapros
 */
public class RoleTest extends BaseTest {
    @Test
    public void testHasRole() throws Exception {
        doLogin("classpath:shiro.role.ini", "system", "123456");
        Assert.assertTrue(subject().hasRole("role2"));
        Assert.assertTrue(subject().hasAllRoles(Arrays.asList("role1", "role2")));
        boolean[] re = subject().hasRoles(Arrays.asList("role1", "role2", "role3"));
        Assert.assertTrue(re[0]);
        Assert.assertTrue(re[1]);
        Assert.assertFalse(re[2]);
    }

    /**
     * check role or checkRoles method will throw UnauthenticatedException if subject does not have
     *
     * @throws Exception ex
     */
    @Test(expected = UnauthorizedException.class)
    public void testCheckRole() throws Exception {
        doLogin("classpath:shiro.role.ini", "mapros", "984138");
        subject().checkRole("role1");
        subject().checkRoles("role1,role2");
    }
}
