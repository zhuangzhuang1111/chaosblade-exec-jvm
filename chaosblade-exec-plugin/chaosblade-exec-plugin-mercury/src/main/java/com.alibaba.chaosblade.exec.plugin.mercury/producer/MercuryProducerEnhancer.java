package com.alibaba.chaosblade.exec.plugin.mercury.producer;

import com.alibaba.chaosblade.exec.common.aop.BeforeEnhancer;
import com.alibaba.chaosblade.exec.common.aop.EnhancerModel;
import com.alibaba.chaosblade.exec.common.model.matcher.MatcherModel;
import com.alibaba.chaosblade.exec.common.util.ReflectUtil;
import com.alibaba.chaosblade.exec.plugin.mercury.MercuryConstant;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Method;

/**
 * @author ljzhxx@gmail.com
 */
public class MercuryProducerEnhancer extends BeforeEnhancer implements MercuryConstant {

    private static final Logger LOGGER = LoggerFactory.getLogger(MercuryProducerEnhancer.class);

    @Override
    public EnhancerModel doBeforeAdvice(ClassLoader classLoader, String className, Object object, Method method, Object[] methodArguments) throws Exception {

        if (methodArguments == null || object == null) {
            LOGGER.warn("The necessary parameter is null.");
            return null;
        }

        String topicKey = ReflectUtil.getFieldValue(object, "topicName", false);
        MatcherModel matcherModel = new MatcherModel();
        matcherModel.add(RESOURCE_NAME, topicKey);
        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("producer topicKey: {}, matcherModel: {}", topicKey, matcherModel);
        }
        EnhancerModel enhancerModel = new EnhancerModel(classLoader, matcherModel);
        enhancerModel.addMatcher(PRODUCER_KEY, "true");
        return enhancerModel;
    }
}
