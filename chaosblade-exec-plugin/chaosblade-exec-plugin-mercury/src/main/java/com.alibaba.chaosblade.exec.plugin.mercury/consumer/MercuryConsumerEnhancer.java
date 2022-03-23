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
 * @author lizhaunzhuang@tuhu.cn
 */
public class MercuryConsumerEnhancer extends BeforeEnhancer implements MercuryConstant {

    private static final Logger LOGGER = LoggerFactory.getLogger(MercuryConsumerEnhancer.class);

    @Override
    public EnhancerModel doBeforeAdvice(ClassLoader classLoader, String className, Object object, Method method, Object[] methodArguments) throws Exception {

        if (methodArguments == null || object == null) {
            LOGGER.warn("The necessary parameter is null.");
            return null;
        }

        MatcherModel matcherModel = new MatcherModel();
        // kafka differ with rmq
        Object subscriptionName = null;
        if (method.getName().equals(KAFKA_CONSUME_METHOD)) {
            // truth-south "subscription"
            subscriptionName = ReflectUtil.getFieldValue(object, "subscription", false);
        } else if(method.getName().equals(RMQ_CONSUME_METHOD)){
            Object metaData = ReflectUtil.getFieldValue(object, "metaData", false);
            subscriptionName = ReflectUtil.getFieldValue(metaData, "resourceName", false);
        }
        if (subscriptionName != null && !subscriptionName.equals("")) {
            matcherModel.add(RESOURCE_NAME, subscriptionName);
        }
        EnhancerModel enhancerModel = new EnhancerModel(classLoader, matcherModel);
        enhancerModel.addMatcher(CONSUMER_KEY, "true");
        return enhancerModel;
    }
}

