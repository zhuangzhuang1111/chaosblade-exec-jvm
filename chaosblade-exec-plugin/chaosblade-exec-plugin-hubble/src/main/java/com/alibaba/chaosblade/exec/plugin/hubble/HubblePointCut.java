package com.alibaba.chaosblade.exec.plugin.hubble;

import com.alibaba.chaosblade.exec.common.aop.PointCut;
import com.alibaba.chaosblade.exec.common.aop.matcher.clazz.*;
import com.alibaba.chaosblade.exec.common.aop.matcher.method.MethodMatcher;
import com.alibaba.chaosblade.exec.common.aop.matcher.method.NameMethodMatcher;
import com.alibaba.chaosblade.exec.common.aop.matcher.method.OrMethodMatcher;

import static com.alibaba.chaosblade.exec.plugin.hubble.HubbleConstants.*;

/**
 * @author yefei
 */
public class HubblePointCut implements PointCut {

    @Override
    public ClassMatcher getClassMatcher() {
        return new SuperClassMatcher(HUBBLE_CLASS);
    }

    /**
     * 对这些方法进行前置拦截
     * @return
     */
    @Override
    public MethodMatcher getMethodMatcher() {
        return new NameMethodMatcher(HUBBLE_METHOD);
    }
}
