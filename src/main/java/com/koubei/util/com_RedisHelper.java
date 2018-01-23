package com.koubei.util;

import org.apache.commons.lang3.StringUtils;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

import java.util.List;

public class com_RedisHelper {
    // Redis服务器IP
    private static String IP = com_PropertiesFile.getInstance("redis").get("master.redis.ip");

    // Redis的端口号
    private static int PORT = com_PropertiesFile.getInstance("redis").getInt("master.redis.port");

    // 访问密码
    private static String PASSWORD = com_AESHelper.aesDecode(com_PropertiesFile.getInstance("redis").get("master.redis.password"));
    // 可用连接实例的最大数目，默认值为8；
    // 如果赋值为-1，则表示不限制；如果pool已经分配了maxActive个jedis实例，则此时pool的状态为exhausted(耗尽)。
    private static int MAX_ACTIVE = com_PropertiesFile.getInstance("redis").getInt("master.redis.max_active");

    // 控制一个pool最多有多少个状态为idle(空闲的)的jedis实例，默认值也是8。
    private static int MAX_IDLE = com_PropertiesFile.getInstance("redis").getInt("master.redis.max_idle");

    // 等待可用连接的最大时间，单位毫秒，默认值为-1，表示永不超时。如果超过等待时间，则直接抛出JedisConnectionException；
    private static int MAX_WAIT = com_PropertiesFile.getInstance("redis").getInt("master.redis.max_wait");

    // 超时时间
    private static int TIMEOUT = com_PropertiesFile.getInstance("redis").getInt("master.redis.timeout");

    // 在borrow一个jedis实例时，是否提前进行validate操作；如果为true，则得到的jedis实例均是可用的；
    private static boolean TEST_ON_BORROW = false;

    private static JedisPool jedisPool = null;

    /**
     * 初始化Redis连接池
     */
    private static void initialPool() {
        try {
            JedisPoolConfig config = new JedisPoolConfig();
            config.setMaxTotal(MAX_ACTIVE);
            config.setMaxIdle(MAX_IDLE);
            config.setMaxWaitMillis(MAX_WAIT);
            config.setTestOnBorrow(TEST_ON_BORROW);
            jedisPool = new JedisPool(config, IP, PORT, TIMEOUT);
        } catch (Exception e) {
            //LOGGER.error("First create JedisPool error : " + e);
        }
    }

    /**
     * 在多线程环境同步初始化
     */
    private static synchronized void poolInit() {
        if (null == jedisPool) {
            initialPool();
        }
    }

    /**
     * 同步获取Jedis实例
     *
     * @return Jedis
     */
    public synchronized static Jedis getJedis() {
        poolInit();
        Jedis jedis = null;
        try {
            if (null != jedisPool) {
                jedis = jedisPool.getResource();
                try {
                    jedis.auth(PASSWORD);
                } catch (Exception e) {

                }
            }
        } catch (Exception e) {
            //LOGGER.error("Get jedis error : " + e);
        }
        return jedis;
    }

    /**
     * 设置 String
     *
     * @param key
     * @param value
     */
    public synchronized static void set(String key, String value) {
        try {
            value = StringUtils.isBlank(value) ? "" : value;
            Jedis jedis = getJedis();
            jedis.set(key, value);
            jedis.close();
        } catch (Exception e) {
            //LOGGER.error("Set key error : " + e);
        }
    }

    /**
     * 设置 String 过期时间
     *
     * @param key
     * @param value
     * @param seconds 以秒为单位
     */
    public synchronized static void set(String key, String value, int seconds) {
        try {
            value = StringUtils.isBlank(value) ? "" : value;
            Jedis jedis = getJedis();
            jedis.setex(key, seconds, value);
            jedis.close();
        } catch (Exception e) {
            //LOGGER.error("Set keyex error : " + e);
        }
    }

    /**
     * 获取String值
     *
     * @param key
     * @return value
     */
    public synchronized static String get(String key) {
        Jedis jedis = getJedis();
        if (null == jedis) {
            return null;
        }
        String value = jedis.get(key);
        jedis.close();
        return value;
    }

    /**
     * 获取keys值
     *
     * @param keys
     * @return
     */
    public synchronized static List<String> mget(String... keys) {
        Jedis jedis = getJedis();
        if (null == jedis) {
            return null;
        }
        List<String> values = jedis.mget(keys);
        jedis.close();
        return values;
    }

}
