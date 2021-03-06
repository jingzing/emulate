package tk.jingzing.test;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPubSub;
import tk.jingzing.redis.util.RedisCacheManager;
import tk.jingzing.redis.util.RedisCachePool;

/**
 * @Description: 监控对redis的更新和删除
 * Created by Louis Wang on 2016/7/4.
 */
@Slf4j
public class MonitorSql {

    public static void main(String[] args) {
        ApplicationContext application = new ClassPathXmlApplicationContext("spring-context.xml");
        RedisCacheManager redisCacheManager = (RedisCacheManager) application.getBean("redisCacheManager");
        RedisCachePool pool = redisCacheManager.getRedisPoolMap().get(RedisDataBaseType.defaultType.toString());
        final Jedis jedis = pool.getResource();

        final JedisPubSub ndb = (JedisPubSub) application.getBean("notifyDataBase");
        // final Jedis jedis = RedisPool.getJedis();
        // final JedisPubSub ndb = new NotifyDataBase();
        new Thread() {
            public void run() {// 会广播形式打印log日志
                jedis.subscribe(ndb, "publog");
            }
        }.start();
    }
}
