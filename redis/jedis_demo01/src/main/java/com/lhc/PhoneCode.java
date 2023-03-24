package com.lhc;

import redis.clients.jedis.Jedis;

import java.util.Random;

/**
 * @Author: lhc
 * @Date: 2023/2/2 17:05
 * @ClassName:
 */
public class PhoneCode {

    public static void main(String[] args) {

        //模拟 验证码发送 按钮
        verifyCode("19914655902");

        //模拟 用户输入验证码 登录
        getRedisCod("19914655902","637424");

    }


    /**
     * 生成6位随机数
     */
    public static String getCode() {
        StringBuilder stringBuilder = new StringBuilder();
        Random random = new Random();
        for (int i = 0; i < 6; i++) {
            int rand = random.nextInt(10);
            stringBuilder.append(rand);
        }
        return stringBuilder + "";
    }


    /**
     * 2. 保存验证码、设置手机发送次数
     *
     * @param phone 手机号
     */
    public static void verifyCode(String phone) {

        //1) 创建 Jedis对象
        Jedis jedis = new Jedis("192.168.200.130", 6379);

        //2) 拼接key（确保唯一性）
        //拼接手机发送次数的key
        String countKey = "VerifyCode" + phone + ":count";
        //拼接验证码key
        String codeKey = "VerifyCode" + phone + ":code";

        //3) 设置每个手机每天只能发送3次
        String count = jedis.get(codeKey);
        if (count == null) {
            //查不到表示当天第一次发送，设置发送次数为1，
            // 并且设置过期时间为1天（第二天 就又查不到了）
            jedis.setex(countKey, 24 * 60 * 60, "1");
        } else if (Integer.parseInt(count) <= 2) {
            //当天没有超过3次，值自增1
            jedis.incr(codeKey);
        } else if (Integer.parseInt(count) > 2) {
            //当天超过3次
            System.out.println("今天的发送次数已超过了3次");
            //关闭链接
            jedis.close();
            return;
        }

        //4) 发送验证码到redis中
        jedis.setex(codeKey, 120, getCode());
        jedis.close();
    }

    /**
     * 3.验证码校验
     *
     * @param phone 手机号
     * @param code  用户输入的验证码
     */
    public static void getRedisCod(String phone, String code) {

        //从redis中获取验证码
        Jedis jedis = new Jedis("192.168.200.130", 6379);
        //将用户输入的code 拼接成验证码key
        String codeKey = "VerifyCode" + phone + ":code";
        //获取
        String redisCode = jedis.get(codeKey);

        //判断
        if (code.equals(redisCode)) {
            System.out.println("成功");
        } else {
            System.out.println("失败");
        }

        jedis.close();
    }

}
