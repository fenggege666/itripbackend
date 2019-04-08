package cn.itrip.auth.service;

import cn.itrip.beans.pojo.ItripUser;

public interface TokenService {
    /**
     * 按指定的格式生成Token
     * @Param userAgent 用户的唯一标识
     */
    public String generateToken(String userAgent, ItripUser user);
    /**
     * 将Token保存至Redis缓存数据库中
     * @Param token Token对象，
     */
    public String save(String token, ItripUser user);

    /**
     * 验证Token
     * @param userAgent 用来区分是否是用一个设备访问服务器 y
     * @param token  token字符串 ，保存到请求头文件
     * @return
     */
    public boolean validate (String userAgent,String token);

}
