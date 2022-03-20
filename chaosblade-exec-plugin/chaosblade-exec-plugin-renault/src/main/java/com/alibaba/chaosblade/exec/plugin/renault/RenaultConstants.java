package com.alibaba.chaosblade.exec.plugin.renault;

/**
 * @author yefei
 * @create 2020-11-23 14:58
 */
public class RenaultConstants {

    public final static String KEY = "key";
    public final static String VALUE = "value";
    public final static String CMD = "cmd";

    //public final static String CLASS = "io.lettuce.core.protocol.CommandHandler";
    //public final static String METHOD = "write";

    /**
     * Renault 对读写进行拦截
     */
    //public final static String RENAULT_CLASS = "com.tuhu.renault.AbstractCache";
    public final static String RENAULT_CLASS = "com.tuhu.renault.redis.lettuce.LettuceCache";
    public final static String GET_METHOD = "do_GET";
    public final static String MULTI_GET_METHOD = "do_MULTI_GET";
    public final static String SET = "do_SET";
    public final static String MULTI_SET_METHOD = "do_MULTI_SET";
}
