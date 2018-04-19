package com.zhang.common.listener;

import redis.clients.jedis.JedisPubSub;

/**
 * @author Edison
 * @ClassName:
 * @Desc:
 * @date 2017/9/1
 * @history
 */
public class RedisMsgPubSubListener extends JedisPubSub {

    @Override
    public void onMessage(String channel, String message) {
        System.out.println("channel:" + channel + "receives message :" + message);
        this.unsubscribe();
    }

    @Override
    public void onSubscribe(String channel, int subscribedChannels) {
        System.out.println("channel:" + channel + "is been subscribed:" + subscribedChannels);
    }

    @Override
    public void onUnsubscribe(String channel, int subscribedChannels) {
        System.out.println("channel:" + channel + "is been unsubscribed:" + subscribedChannels);
    }

}
