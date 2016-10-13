package org.mapros.shiro.encrypt;

import org.apache.commons.beanutils.BeanUtilsBean;
import org.apache.commons.beanutils.converters.AbstractConverter;
import org.apache.shiro.authc.ExcessiveAttemptsException;
import org.apache.shiro.crypto.SecureRandomNumberGenerator;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.realm.jdbc.JdbcRealm;
import org.junit.Test;

/**
 * Created by HP on 2016/10/13.
 *
 * @author mapros
 */
public class PasswordTest extends BaseTest {
    @Test
    public void testPasswordServiceWithRealm() throws Exception {
        doLogin("classpath:shiro.passwordservice.ini", "mapros", "9841381");
    }

    @Test
    public void testPasswordServiceWithJdbcRealm() {
        doLogin("classpath:shiro.jdbc.passwordservice.ini", "wu", "123");
    }

    @Test
    public void testGeneratePassword() throws Exception {
        String algorithmName = "md5";
        String username = "mapros";
        String password = "984138";
        String randomHex = new SecureRandomNumberGenerator().nextBytes().toHex();
        int hashIterations = 2;
        SimpleHash hash = new SimpleHash(algorithmName, password, username + randomHex, hashIterations);
        String encodedPassword = hash.toHex();
        System.out.println("salt: " + randomHex);
        System.out.println("pwd: " + encodedPassword);
    }

    @Test
    public void testHashRealm() throws Exception {
        doLogin("classpath:shiro.passwordservice.ini", "mapros", "9841381");
    }

    @Test
    public void testHashedJdbcRealm() throws Exception {
        BeanUtilsBean.getInstance().getConvertUtils().register(new EnumConverter(), JdbcRealm.SaltStyle.class);
        doLogin("classpath:shiro.jdbc.hashed.ini", "mapros1", "984138");
    }

    private class EnumConverter extends AbstractConverter {
        @Override
        protected String convertToString(final Object value) throws Throwable {
            return ((Enum) value).name();
        }

        @Override
        protected Object convertToType(final Class type, final Object value) throws Throwable {
            return Enum.valueOf(type, value.toString());
        }

        @Override
        protected Class getDefaultType() {
            return null;
        }

    }

    @Test(expected = ExcessiveAttemptsException.class)
    public void testRetryLimitHashedCredentialsMatcherWithMyRealm() throws Exception {
        for (int i = 1; i <= 5; i++) {
            try {
                doLogin("classpath:shiro.retryLimitHashedCredentialsMatcher.ini", "mapros", "9841381");
            } catch (Exception e) {
                System.out.println(" error password ");
            }
        }
        doLogin("classpath:shiro.retryLimitHashedCredentialsMatcher.ini", "mapros", "9841381");
    }
}
