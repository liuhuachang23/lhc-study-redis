package com.pdx.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.core.RedisOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.SessionCallback;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.UUID;

/**
 * @author 派 大 星
 * @function
 * @date 2022/6/1 18:19
 * @website https://blog.csdn.net/Gaowumao
 */
@Controller
@RequestMapping("/grab")
public class doKillController {

    @Autowired
    private RedisTemplate redisTemplate;

    @RequestMapping("/")
    public String enterIndex(){
        return "index";
    }

    @RequestMapping("/doGrabZz")
    @ResponseBody
    public Boolean doGrabZz(HttpServletRequest request){
        String gid = request.getParameter("gid");
        String uid = UUID.randomUUID().toString().substring(1, 5);
        //1. 拼接key
        // 库存key
        String kcKey = "sk:"+gid+":qt";
        // 秒杀用户的key
        String userKey = "sk:"+gid+":user";

        //监视库存
        redisTemplate.watch(kcKey);

        //获取库存 如果库存为null，秒杀还未开始
        String kc = String.valueOf(redisTemplate.opsForValue().get(kcKey));
        if (kc == null){
            System.out.println("秒杀还未开始，请稍等...");
            return false;
        }

        //判断用户是否重复秒杀
        if (redisTemplate.opsForSet().isMember(userKey,uid)){
            System.out.println("已经秒杀成功，不能重复秒杀");
            return false;
        }

        //判断如果商品数量，库存数量小于1，秒杀结束
        if (Integer.parseInt(kc) <= 0){
            System.out.println("秒杀已经结束");
            return false;
        }

        List<Object> txRes = (List<Object>) redisTemplate.execute(new SessionCallback() {
            @Override
            public Object execute(RedisOperations operations) throws DataAccessException {
                //开启事务
                operations.multi();
                redisTemplate.opsForValue().decrement(kcKey);
                redisTemplate.opsForSet().add(userKey,uid);
                //执行
                return operations.exec();
            }
        });
        if (txRes == null || txRes.size() == 0){
            System.out.println("秒杀失败了");
            return false;
        }
        System.out.println("秒杀成功了");
        return true;
    }
}
