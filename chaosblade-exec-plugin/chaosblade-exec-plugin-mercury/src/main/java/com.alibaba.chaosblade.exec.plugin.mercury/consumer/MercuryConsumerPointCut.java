package com.alibaba.chaosblade.exec.plugin.mercury.consumer;

import com.alibaba.chaosblade.exec.common.aop.PointCut;
import com.alibaba.chaosblade.exec.common.aop.matcher.MethodInfo;
import com.alibaba.chaosblade.exec.common.aop.matcher.clazz.ClassMatcher;
import com.alibaba.chaosblade.exec.common.aop.matcher.clazz.NameClassMatcher;
import com.alibaba.chaosblade.exec.common.aop.matcher.clazz.OrClassMatcher;
import com.alibaba.chaosblade.exec.common.aop.matcher.method.MethodMatcher;
import com.alibaba.chaosblade.exec.plugin.mercury.MercuryConstant;

/**
 * @author ljzhxx@gmail.com
 */
public class MercuryConsumerPointCut implements PointCut, MercuryConstant {

    @Override
    public ClassMatcher getClassMatcher() {

        OrClassMatcher orClassMatcher = new OrClassMatcher();
        orClassMatcher.or(new NameClassMatcher(KAFKA_CONSUMER_CLASS)).or(new NameClassMatcher(RABBITMQ_CONSUMER_CLASS));
        return orClassMatcher;
    }

    @Override
    public MethodMatcher getMethodMatcher() {
        return new MethodMatcher() {
            @Override
            public boolean isMatched(String methodName, MethodInfo methodInfo) {
                return methodName.equals(KAFKA_CONSUME_METHOD) ||
                methodName.equals(RMQ_CONSUME_METHOD);
            }
        };
    }
}
