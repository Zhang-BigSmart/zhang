import com.zhang.common.listener.RedisMsgPubSubListener;
import com.zhang.common.util.RedisUtil;
import org.junit.Test;
import redis.clients.jedis.Jedis;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Edison
 * @ClassName:
 * @Desc:
 * @date 2017/9/1
 * @history
 */
public class RedisTest {

    @Test
    public void subscribe(){
        Jedis jedis = RedisUtil.getJedis();
        RedisMsgPubSubListener listener = new RedisMsgPubSubListener();
        jedis.subscribe(listener, "redisChatTest");
    }

    @Test
    public void subscribe2(){
        Jedis jedis = RedisUtil.getJedis();
        RedisMsgPubSubListener listener = new RedisMsgPubSubListener();
        jedis.subscribe(listener, "redisChatTest");
    }

    @Test
    public void publish() throws InterruptedException {
        Jedis jedis = RedisUtil.getJedis();
        jedis.publish("redisChatTest", "我是天才");
        Thread.sleep(5000);
        jedis.publish("redisChatTest", "我牛逼");
        Thread.sleep(5000);
        jedis.publish("redisChatTest", "哈哈");
    }

    @Test
    public void ArrayListTest(){
        List<String> a = new ArrayList<String>();
        a.add("test");
    }

}
