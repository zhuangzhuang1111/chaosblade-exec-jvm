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

        HashSet<String> topicKeySet = new HashSet<String>();
        if (KAFKA_CONSUME_METHOD.equals(method.getName())) {
            List<Object> messages = (List<Object>) methodArguments[0]; // List<KafkaMessage> messages
            for(Object obj : messages){
                //
                Object metadata = ReflectUtil.getFieldValue(obj, "metadata", false);
            }
            Object metadata = ReflectUtil.getFieldValue(object, "metadata", false);
            Object subscription = ReflectUtil.getFieldValue(metadata, "subscription", false);
            if (subscription != null) {
                Object subscriptionList = ReflectUtil.getFieldValue(subscription, "subscription", false);
                if (subscriptionList != null) {
                    topicKeySet = (HashSet<String>) subscriptionList;
                }
            }else{
                Map<String, Object> topics = (Map<String, Object>) ReflectUtil.getFieldValue(metadata, "topics", false);
                Iterator<String> iterator = topics.keySet().iterator();
                while (iterator.hasNext()){
                    topicKeySet.add(iterator.next());
                }
            }
        }

        MatcherModel matcherModel = new MatcherModel();

        for (String item : topicKeySet) {
            if (LOGGER.isDebugEnabled()) {
                LOGGER.debug("consumer topicKey: {}, matcherModel: {}", item, matcherModel.getMatchers());
            }
            matcherModel.add(TOPIC_KEY, item);
        }

        EnhancerModel enhancerModel = new EnhancerModel(classLoader, matcherModel);
        enhancerModel.addMatcher(CONSUMER_KEY, "true");
        return enhancerModel;
    }
}

