package cn.itrip.auth.service.impl;

import cn.itrip.auth.service.MailService;
import cn.itrip.auth.service.SmsService;
import cn.itrip.auth.service.UserService;
import cn.itrip.beans.pojo.ItripUser;
import cn.itrip.common.MD5;
import cn.itrip.common.RedisAPI;
import cn.itrip.dao.auth.UserMapper;
import org.apache.log4j.spi.ErrorCode;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service("userService")
public class UserServiceImpl implements UserService {

    @Resource
    private MailService mailService;
    @Resource
    private SmsService smsService;

    @Resource
    private RedisAPI redisAPI;

    @Resource
    private UserMapper userMapper;
         //根据Usercode查询用户名
       public ItripUser getUser(String userCode){
           Map<String,Object>map =new HashMap<String,Object>();
           map.put("name",userCode);
           List<ItripUser> userList = userMapper.queryUserByMap(map);
           ItripUser user = null;
           if(userList!=null&& userList.size()>0){
               user =  userList.get(0);
           }
              return  user;
       }
    @Override
    public ItripUser login(String userCode ,String password) throws Exception {
           ItripUser user = getUser(userCode);
      if(user !=null && user.getUserPassword().equals(password)){
          if(user.getActivated()!=1){
              throw new Exception("账号尚未激活");
          }
          return user;
      }
      return  null;
    }

    @Override
    public void insertUser(ItripUser user) throws Exception {
        //1、添加用户
        userMapper.insertItripUser(user);

        //2、生成激活码， new Date().toLocaleString()生成日期和时间，
        //格式如：2019-1-25     22:56:32，然后进行32位的MD5加密
        String activationCode = MD5.getMd5(new Date().toLocaleString(), 32);

        //3、发送邮件
        mailService.sendActivationMail(user.getUserCode(),activationCode);
        //4、激活码存入Redis中
        redisAPI.set("activation:" + user.getUserCode(),30 * 60, activationCode);
    }

    /**
     * 根据用户名查找用户
     * @param username
     */
    public ItripUser findByUsername(String username){
        Map<String, Object> param=new HashMap();
        param.put("name", username);
        List<ItripUser> list= userMapper.queryUserByMap(param);
        if(list.size()>0)
            return list.get(0);
        else
            return null;
    }
    /**
     * 邮箱激活
     * @param mail 邮箱地址
     * @param code 激活码(使用MD5进行32位的加密)
     * @return true/false，true表示验证成功
     */
    @Override
    public boolean activate(String mail, String code) throws Exception {
        //1、验证激活码
        String value = redisAPI.get("activation:" + mail);
        if (value.equals(code)) {
            ItripUser user = findByUsername(mail);
            //2、更新用户
            user.setActivated(1);  //激活账户
            user.setUserType(0); //自注册用户
            user.setFlatID(user.getId());
            userMapper.updateItripUser(user);
            return true;
        } else {
            return false;
        }
    }

    @Override
    public void insertUserByPhone(ItripUser user) throws Exception {
        //1、创建用户
        userMapper.insertItripUser(user);
        //2、生成验证码(1111-9999)
        int code = MD5.getRandomCode();
        //3、发送验证码，  “1”为默认模板id的值。
        smsService.sendMsg(user.getUserCode(), "1", new String[] {String.valueOf(code), "1"});
        //4、缓存验证码到Redis数据库中， 有效期为60秒，测试时可以设置大一点。
        redisAPI.set("activation:" + user.getUserCode(), 60, String.valueOf(code));
    }
    
    @Override
    public boolean validatePhone(String phoneNum, String code) throws Exception {
        //1、对比验证码
        String key = "activation:" + phoneNum;
        String value = redisAPI.get(key);
        if (value != null && value.equals(code)) {
            ItripUser user = findByUsername(phoneNum);
            if (user != null) {
                //2、更新用户激活状态
                user.setActivated(1);  //表示激活
                user.setFlatID(user.getId()); //设置平台ID
                user.setUserType(0);
                userMapper.updateItripUser(user);
                return true;
            }
        }
        return false;
    }

}
