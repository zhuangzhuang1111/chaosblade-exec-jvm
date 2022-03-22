package com.alibaba.chaosblade.exec.plugin.mercury.producer;

import com.alibaba.chaosblade.exec.common.aop.PointCut;
import com.alibaba.chaosblade.exec.common.aop.matcher.MethodInfo;
import com.alibaba.chaosblade.exec.common.aop.matcher.clazz.ClassMatcher;
import com.alibaba.chaosblade.exec.common.aop.matcher.clazz.NameClassMatcher;
import com.alibaba.chaosblade.exec.common.aop.matcher.clazz.OrClassMatcher;
import com.alibaba.chaosblade.exec.common.aop.matcher.method.MethodMatcher;
import com.alibaba.chaosblade.exec.plugin.mercury.MercuryConstant;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**
 * @author ljzhxx@gmail.com
 */
public class MercuryProducerPointCut implements PointCut, MercuryConstant {

    private static final Logger LOGGER = LoggerFactory.getLogger(MercuryProducerPointCut.class);

    @Override
    public ClassMatcher getClassMatcher() {
        OrClassMatcher orClassMatcher = new OrClassMatcher();
        orClassMatcher.or(new NameClassMatcher(KAFKA_PRODUCER_CLASS))
                .or(new NameClassMatcher(RABBITMQ_PRODUCER_CLASS));
        return orClassMatcher;
    }

    @Override
    public MethodMatcher getMethodMatcher() {
        return new MethodMatcher() {
            @Override
            public boolean isMatched(String methodName, MethodInfo methodInfo) {
                return methodName.equals(SEND)
                        || methodName.equals(SEND_BATCH);
            }
        };
    }
}
