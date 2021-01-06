package org.example;

import org.apache.shiro.crypto.hash.Md5Hash;
import org.junit.Test;

/**
 * MD5
 * 作用: 一般用来加密或者签名
 * 特点: MD算法不可逆, 内容相同无论执行多少次MD5生成的结果始终一致
 * 结果: 始终是一个16进制的32位长度的字符串
 */
public class AppTest {
    /**
     * MD5
     */
    @Test
    public void MD5() {
        Md5Hash md5Hash = new Md5Hash("123456");
        System.out.println(md5Hash.toHex());
    }

    /**
     * MD5 + Salt
     */
    @Test
    public void MD5WithSalt() {
        Md5Hash md5Hash = new Md5Hash("123456", "x0*7ps");
        System.out.println(md5Hash.toHex());
    }

    /**
     * MD5 + Salt + Hash
     */
    @Test
    public void MD5WithSaltAndHash() {
        Md5Hash md5Hash = new Md5Hash("123456", "x0*7ps", 1024);
        System.out.println(md5Hash.toHex());
    }
}
