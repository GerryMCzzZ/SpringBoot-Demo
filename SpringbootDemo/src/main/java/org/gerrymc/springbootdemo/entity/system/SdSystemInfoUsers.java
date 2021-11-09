package org.gerrymc.springbootdemo.entity.system;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * @author GerryMC
 * @Description 用户实体表
 * @date 2021/8/19
 */

@Data
@Entity
public class SdSystemInfoUsers {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    //账号
    private String username;

    //密码
    private String password;

    //真实姓名
    private String name;

    //公司id
    private Integer companyId;

    //权限角色id
    private Integer roleId;

    //登陆token
    private String tokenCode;

    //token过期时间
    private String tokenTimeout;
}
