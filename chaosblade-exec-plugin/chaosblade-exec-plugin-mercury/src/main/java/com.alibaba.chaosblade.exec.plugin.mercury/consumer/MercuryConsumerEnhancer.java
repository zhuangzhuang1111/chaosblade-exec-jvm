package com.alibaba.chaosblade.exec.plugin.mercury.consumer;

import com.alibaba.chaosblade.exec.common.aop.BeforeEnhancer;
import com.alibaba.chaosblade.exec.common.aop.EnhancerModel;
import com.alibaba.chaosblade.exec.common.model.matcher.MatcherModel;
import com.alibaba.chaosblade.exec.common.util.ReflectUtil;
import com.alibaba.chaosblade.exec.plugin.mercury.MercuryConstant;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Method;
import java.util.*;

/**
 * @author ljzhxx@gmail.com
 */
public class MercuryConsumerEnhancer extends BeforeEnhancer implements MercuryConstant {

    private static final Logger LOGGER = LoggerFactory.getLogger(MercuryConsumerEnhancer.class);

    @Override
    public EnhancerModel doBeforeAdvice(ClassLoader classLoader, String className, Object object, Method method, Object[] methodArguments) throws Exception {

        if (methodArguments == null || object == null) {
            LOGGER.warn("The necessary parameter is null.");
            return null;
        }

        Object subscriptionName = ReflectUtil.getFieldValue(object, "subscription", false);
        MatcherModel matcherModel = new MatcherModel();
        if (subscriptionName != null && !subscriptionName.equals("")) {
            matcherModel.add(RESOURCE_NAME, subscriptionName);
        }

        EnhancerModel enhancerModel = new EnhancerModel(classLoader, matcherModel);
        enhancerModel.addMatcher(CONSUMER_KEY, "true");
        return enhancerModel;
    }
}

