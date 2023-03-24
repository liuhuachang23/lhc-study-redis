package com.lhc;

import org.junit.Test;
import redis.clients.jedis.Jedis;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * @Author: lhc
 * @Date: 2023/2/2 16:19
 * @ClassName:
 */
public class JedisDemo01 {
    public static void main(String[] args) {
        Jedis jedis = new Jedis("192.168.200.130", 6379);
        String pong = jedis.ping();
        System.out.println("连接成功：" + pong);
        jedis.close();
    }

    @Test
    public void demo01() {
        //创建 Jedis对象
        Jedis jedis = new Jedis("192.168.200.130", 6379);
        jedis.set("k1", "v1");
        jedis.set("k2", "v2");
        jedis.set("k3", "v3");
        Set<String> keys = jedis.keys("*");
        System.out.println(keys.size());
        for (String key : keys) {
            System.out.println(key);
        }
        System.out.println(jedis.exists("k1"));
        System.out.println(jedis.ttl("k1"));
        System.out.println(jedis.get("k1"));
        //关闭链接
        jedis.close();
    }


    @Test
    public void demo04() {
        //创建 Jedis对象
        Jedis jedis = new Jedis("192.168.200.130", 6379);

        //添加数据
        jedis.sadd("orders", "order01");
        jedis.sadd("orders", "order02");
        jedis.sadd("orders", "order03");
        jedis.sadd("orders", "order04");

        Set<String> smembers = jedis.smembers("orders");
        for (String order : smembers) {
            System.out.println(order);
        }
        jedis.srem("orders", "order02");

    }

    @Test
    public void demo05() {
        //创建 Jedis对象
        Jedis jedis = new Jedis("192.168.200.130", 6379);

        jedis.hset("hash1", "userName", "lisi");
        System.out.println(jedis.hget("hash1", "userName"));
        Map<String, String> map = new HashMap<String, String>();
        map.put("telphone", "13810169999");
        map.put("address", "atguigu");
        map.put("email", "abc@163.com");
        jedis.hmset("hash2", map);
        List<String> result = jedis.hmget("hash2", "telphone", "email");
        for (String element : result) {
            System.out.println(element);
        }

        //关闭链接
        jedis.close();
    }
}
