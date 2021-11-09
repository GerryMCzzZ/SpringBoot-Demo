package org.gerrymc.springbootdemo.utils.daily;

/**
 * @Author GerryMC
 * @Date: 2020/7/23 0023 15:02
 *
 * token码相关操作函数
 */
public class  TokenUtil {

    //Token码生成
    public static String getToken(String username) {
        return MD5Util.getMD5(username,System.currentTimeMillis()+"");
    }


    //刷新token码过期时间
    public static String flushTokenTimeout() {
        return System.currentTimeMillis() + 604800000 + "";
    }

}
