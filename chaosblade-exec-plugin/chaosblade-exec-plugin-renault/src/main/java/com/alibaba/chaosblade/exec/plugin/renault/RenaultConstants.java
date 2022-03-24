package com.alibaba.chaosblade.exec.plugin.renault;

/**
 * @author yefei
 * @create 2020-11-23 14:58
 */
public class RenaultConstants {

    public final static String KEY = "category";
    public final static String CMD = "cmd";

    /**
     * Renault 对读写进行拦截
     */
    public final static String RENAULT_CLASS = "io.lettuce.core.AbstractRedisAsyncCommands";
    public final static String GET_METHOD = "get";
    public final static String MULTI_GET_METHOD = "mget";
    public final static String SET = "set";
    public final static String MULTI_SET_METHOD = "mset";
}
