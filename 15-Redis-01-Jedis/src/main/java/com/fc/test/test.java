package com.fc.test;

import org.junit.Test;
import redis.clients.jedis.Jedis;

public class test {
    @Test
    public void testConnect() {
        Jedis jedis = new Jedis("192.168.232.128", 6379);

        jedis.auth("123456");

        String ping = jedis.ping("连接");

        System.out.println(ping);
    }
}
