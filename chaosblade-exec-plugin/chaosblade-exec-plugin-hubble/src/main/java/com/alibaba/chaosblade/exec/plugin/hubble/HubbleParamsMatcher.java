/*
 * Copyright 2022 tuhu.cn All right reserved. This software is the
 * confidential and proprietary information of tuhu.cn ("Confidential
 * Information"). You shall not disclose such Confidential Information and shall
 * use it only in accordance with the terms of the license agreement you entered
 * into with Tuhu.cn
 */
package com.alibaba.chaosblade.exec.plugin.hubble;

import com.alibaba.chaosblade.exec.common.aop.CustomMatcher;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashSet;

/**
 * @author lizhuangzhuang
 * @since 2022/3/20 9:54 下午
 */
public class HubbleParamsMatcher implements CustomMatcher {

    private static final Logger LOGGER = LoggerFactory.getLogger(HubbleParamsMatcher.class);

    private static final HubbleParamsMatcher INSTANCE = new HubbleParamsMatcher();

    private HubbleParamsMatcher() {
    }

    public static HubbleParamsMatcher getInstance() {
        return INSTANCE;
    }
    @Override
    public boolean match(String commandValue, Object originValue) {
        HashSet<Object> keySet = (HashSet<Object>) originValue;
        return keySet.contains(commandValue);
    }

    @Override
    public boolean regexMatch(String commandValue, Object originValue) {
        return false;
    }
}
