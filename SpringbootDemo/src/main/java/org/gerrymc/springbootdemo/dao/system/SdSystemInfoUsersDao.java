package org.gerrymc.springbootdemo.dao.system;

import org.gerrymc.springbootdemo.entity.system.SdSystemInfoUsers;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * @author GerryMC
 * @Description 用户信息数据库操作
 * @date 2021/8/19
 */

public interface SdSystemInfoUsersDao extends JpaRepository<SdSystemInfoUsers,Integer> {
    /**
     *  根据账户名查询用户
     * @param username
     * @return
     */
    SdSystemInfoUsers findByUsername(String username);


    /**
     *  根据token码查询用户
     * @param tokenCode
     * @return
     */
    SdSystemInfoUsers findByTokenCode(String tokenCode);

    /**
     * 根据公司id查询所有
     * @param companyId
     * @return
     */
    List<SdSystemInfoUsers> findAllByCompanyId(Integer companyId);

    /**
     * 根据真实姓名查询用户
     * @param name
     * @return
     */
    SdSystemInfoUsers findByName(String name);

}
