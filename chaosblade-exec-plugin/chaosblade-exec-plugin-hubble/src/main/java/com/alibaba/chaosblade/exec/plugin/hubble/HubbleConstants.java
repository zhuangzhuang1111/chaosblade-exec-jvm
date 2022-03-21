package com.alibaba.chaosblade.exec.plugin.hubble;

/**
 * @author yefei
 * @create 2020-11-23 14:58
 */
public class HubbleConstants {

    public final static String KEY = "key";
    public final static String VALUE = "value";
    public final static String CMD = "cmd";

    //public final static String CLASS = "io.lettuce.core.protocol.CommandHandler";
    //public final static String METHOD = "write";

    /**
     * Renault 对读写进行拦截
     */
    public final static String HUBBLE_CLASS = "com.tuhu.hubble.remote.esclient.SnifferRestClient";
    //public final static String RENAULT_CLASS = "com.tuhu.renault.redis.lettuce.LettuceCache";
    public final static String HUBBLE_METHOD = "performRequest";
}
