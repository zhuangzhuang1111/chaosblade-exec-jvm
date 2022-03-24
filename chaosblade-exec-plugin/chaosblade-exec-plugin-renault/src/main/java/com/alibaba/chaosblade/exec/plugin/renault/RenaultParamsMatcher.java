/*
 * Copyright 2022 tuhu.cn All right reserved. This software is the
 * confidential and proprietary information of tuhu.cn ("Confidential
 * Information"). You shall not disclose such Confidential Information and shall
 * use it only in accordance with the terms of the license agreement you entered
 * into with Tuhu.cn
 */
package com.alibaba.chaosblade.exec.plugin.renault;

import com.alibaba.chaosblade.exec.common.aop.CustomMatcher;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.HashSet;

/**
 * @author lizhuangzhuang
 * @since 2022/3/20 9:54 下午
 */
public class RenaultParamsMatcher implements CustomMatcher {

    private static final Logger LOGGER = LoggerFactory.getLogger(RenaultParamsMatcher.class);

    private static final RenaultParamsMatcher INSTANCE = new RenaultParamsMatcher();

    private RenaultParamsMatcher() {
    }

    public static RenaultParamsMatcher getInstance() {
        return INSTANCE;
    }
    @Override
    public boolean match(String commandValue, Object originValue) {
        ArrayList<Object> keyList = (ArrayList<Object>) originValue;
        return keyList.contains(commandValue);
    }

    @Override
    public boolean regexMatch(String commandValue, Object originValue) {
        return false;
    }
}
