package cn.itrip.dao.auth;


import cn.itrip.beans.pojo.ItripUser;

import java.util.List;
import java.util.Map;

public interface UserMapper {
    /**
     * 根据Map集合中的参数列表查询指定的用户对象信息
     * @param map
     * @return
     */
    List<ItripUser> queryUserByMap(Map<String, Object> map);

    /**
     * 注册邮箱
     * @param itripUser
     * @return
     * @throws Exception
     */
    public Integer insertItripUser(ItripUser itripUser)throws Exception;
    /**
     * 验证邮箱
     */
    public Integer updateItripUser(ItripUser itripUser)throws Exception;
    
}
