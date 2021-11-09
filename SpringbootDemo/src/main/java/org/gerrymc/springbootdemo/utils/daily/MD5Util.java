package org.gerrymc.springbootdemo.utils.daily;

import org.springframework.util.DigestUtils;

/**
 * @Author GerryMC
 * @Date: 2020/7/23
 *
 * 自定义MD5加密类
 */

public class MD5Util {

    public static String getMD5(String s1, String s2) {
        String base = s1 + "/springboot" + s2;
        String md5 = DigestUtils.md5DigestAsHex(base.getBytes());
        return md5;
    }

    //加密密码
    public static String pwdMD5(String password){
        return getMD5("mydemo",password);
    }

    //测试密码
    public static void main(String[] args) {
       System.out.println(pwdMD5("888888"));
    }
}