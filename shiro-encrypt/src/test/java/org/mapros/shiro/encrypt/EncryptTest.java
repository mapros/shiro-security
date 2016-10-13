package org.mapros.shiro.encrypt;

import org.apache.shiro.codec.Base64;
import org.apache.shiro.codec.CodecSupport;
import org.apache.shiro.codec.Hex;
import org.apache.shiro.crypto.AesCipherService;
import org.apache.shiro.crypto.BlowfishCipherService;
import org.apache.shiro.crypto.DefaultBlockCipherService;
import org.apache.shiro.crypto.SecureRandomNumberGenerator;
import org.apache.shiro.crypto.hash.*;
import org.apache.shiro.util.ByteSource;
import org.apache.shiro.util.SimpleByteSource;
import org.junit.Assert;
import org.junit.Test;

import java.security.Key;

/**
 * Created by HP on 2016/10/12.
 *
 * @author mapros
 */
public class EncryptTest {
    private String pwd = "sayhello";
    private String salt = "984138";

    @Test
    public void testBase64() throws Exception {
        String encodeStr = Base64.encodeToString(pwd.getBytes());
        String decodeStr = Base64.decodeToString(encodeStr);
        Assert.assertEquals(pwd, decodeStr);
    }

    @Test
    public void testHex() throws Exception {
        String encodeStr = Hex.encodeToString(pwd.getBytes());
        String decodeStr = new String(Hex.decode(encodeStr.getBytes()));
        Assert.assertEquals(pwd, decodeStr);
    }

    @Test
    public void testCodecSupport() throws Exception {
        byte[] bytes = CodecSupport.toBytes(pwd, "utf-8");
        Assert.assertEquals(pwd, CodecSupport.toString(bytes, "utf-8"));
    }

    @Test
    public void testRandom() throws Exception {
        SecureRandomNumberGenerator randomNumberGenerator = new SecureRandomNumberGenerator();
        randomNumberGenerator.setSeed("984138".getBytes());
        System.out.println(randomNumberGenerator.nextBytes().toHex());
    }

    @Test
    public void testMD5() throws Exception {
        String hex = new Md5Hash(pwd, salt).toHex();
        String base64 = new Md5Hash(pwd, salt).toBase64();
        String md5 = new Md5Hash(pwd, salt).toString();
        System.out.println(hex);
        System.out.println(base64);
        System.out.println(md5);
    }

    @Test
    public void testSha1() throws Exception {
        String base64 = new Sha1Hash(pwd, salt).toBase64();
        String hex = new Sha1Hash(pwd, salt).toHex();
        String sha1 = new Sha1Hash(pwd, salt).toString();
        System.out.println(hex);
        System.out.println(base64);
        System.out.println(sha1);
    }

    @Test
    public void testSha256() throws Exception {
        String sha256 = new Sha256Hash(pwd, salt).toString();
        String hex = new Sha256Hash(pwd, salt).toHex();
        String base64 = new Sha256Hash(pwd, salt).toBase64();
        System.out.println(hex);
        System.out.println(base64);
        System.out.println(sha256);
    }

    @Test
    public void testSha384() throws Exception {
        String sha384 = new Sha384Hash(pwd, salt).toString();
        String hex = new Sha384Hash(pwd, salt).toHex();
        String base64 = new Sha384Hash(pwd, salt).toBase64();
        System.out.println(sha384);
        System.out.println(base64);
        System.out.println(hex);
    }

    @Test
    public void testSha512() throws Exception {
        String sha512 = new Sha512Hash(pwd, salt).toString();
        String hex = new Sha512Hash(pwd, salt).toHex();
        String base64 = new Sha512Hash(pwd, salt).toBase64();
        System.out.println(sha512);
        System.out.println(hex);
        System.out.println(base64);
    }

    @Test
    public void testSimpleHash() throws Exception {
        String simpleHash = new SimpleHash("SHA-1", pwd, salt).toString();
        String hex = new SimpleHash("SHA-1", pwd, salt).toHex();
        String base64 = new SimpleHash("SHA-1", pwd, salt).toBase64();
        System.out.println(simpleHash);
        System.out.println(hex);
        System.out.println(base64);
    }

    @Test
    public void testHashService() throws Exception {
        //default hash service
        DefaultHashService hashService = new DefaultHashService();
        //set hash algorithm
        hashService.setHashAlgorithmName("SHA-512");
        //private salt
        hashService.setPrivateSalt(new SimpleByteSource("984138"));
        //generate public salt
        hashService.setGeneratePublicSalt(true);
        //set random number generator
        hashService.setRandomNumberGenerator(new SecureRandomNumberGenerator());
        //set hash iterations value 1
        hashService.setHashIterations(1);
        HashRequest request = new HashRequest.Builder()
                .setAlgorithmName("MD5").setSource(ByteSource.Util.bytes(pwd))
                .setSalt(ByteSource.Util.bytes(salt)).setIterations(2).build();
        String hex = hashService.computeHash(request).toHex();
        System.out.println(hex);
    }

    @Test
    public void testAesCipherService() throws Exception {
        AesCipherService aesCipherService = new AesCipherService();
        aesCipherService.setKeySize(128);
        Key key = aesCipherService.generateNewKey();
        String encryptStr = aesCipherService.encrypt(pwd.getBytes(), key.getEncoded()).toHex();
        String decryptStr = new String(aesCipherService.decrypt(Hex.decode(encryptStr), key.getEncoded()).getBytes());
        Assert.assertEquals(pwd, decryptStr);
    }

    @Test
    public void testBlowfishCipherService() {
        BlowfishCipherService blowfishCipherService = new BlowfishCipherService();
        blowfishCipherService.setKeySize(128);
        Key key = blowfishCipherService.generateNewKey();
        String encryptStr = blowfishCipherService.encrypt(pwd.getBytes(), key.getEncoded()).toHex();
        String decryptStr = new String(blowfishCipherService.decrypt(Hex.decode(encryptStr), key.getEncoded()).getBytes());
        Assert.assertEquals(pwd, decryptStr);
    }

    @Test
    public void testDefaultBlockCipherService() throws Exception {
        DefaultBlockCipherService cipherService = new DefaultBlockCipherService("AES");
        cipherService.setKeySize(128);
        Key key = cipherService.generateNewKey();
        String encryptStr = cipherService.encrypt(pwd.getBytes(), key.getEncoded()).toHex();
        String decryptStr = new String(cipherService.decrypt(Hex.decode(encryptStr), key.getEncoded()).getBytes());
        Assert.assertEquals(pwd, decryptStr);
    }
}
