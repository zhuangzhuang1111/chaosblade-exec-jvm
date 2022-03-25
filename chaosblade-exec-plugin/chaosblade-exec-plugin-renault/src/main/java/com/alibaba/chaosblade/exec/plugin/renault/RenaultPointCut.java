package com.alibaba.chaosblade.exec.plugin.renault;

import com.alibaba.chaosblade.exec.common.aop.PointCut;
import com.alibaba.chaosblade.exec.common.aop.matcher.MethodInfo;
import com.alibaba.chaosblade.exec.common.aop.matcher.clazz.*;
import com.alibaba.chaosblade.exec.common.aop.matcher.method.MethodMatcher;
import com.alibaba.chaosblade.exec.common.aop.matcher.method.NameMethodMatcher;
import com.alibaba.chaosblade.exec.common.aop.matcher.method.OrMethodMatcher;

/**
 * @author yefei
 */
public class RenaultPointCut implements PointCut {

    @Override
    public ClassMatcher getClassMatcher() {
        return new OrClassMatcher().or(new NameClassMatcher(RenaultConstants.RENAULT_CLASS)).
            or(new NameClassMatcher(RenaultConstants.RENAULT_HBASE_CLASS));
        //return new NameClassMatcher(RenaultConstants.RENAULT_CLASS);
    }

    /**
     * 对这些方法进行前置拦截
     * @return
     */
    @Override
    public MethodMatcher getMethodMatcher() {
        return new MethodMatcher() {
            @Override
            public boolean isMatched(String methodName, MethodInfo methodInfo) {
                return
                        (methodName.equals(RenaultConstants.GET_METHOD)) ||
                                /*
                                * mget mget(K... keys)
                                *
                                * */
                                (methodName.equals(RenaultConstants.MULTI_GET_METHOD)
                                && methodInfo.getParameterTypes().length == 1
                                && methodInfo.getParameterTypes()[0].equals("java.lang.Iterable")) ||
                                (methodName.equals(RenaultConstants.SET)) ||
                                (methodName.equals(RenaultConstants.SET_2))||
                                (methodName.equals(RenaultConstants.MULTI_SET_METHOD) ||
                                // hbase method below
                                (methodName.equals(RenaultConstants.HBASE_GET)) ||
                                (methodName.equals(RenaultConstants.HBASE_GET_COLUMN)) ||
                                (methodName.equals(RenaultConstants.HBASE_MULTI_GET)) ||
                                (methodName.equals(RenaultConstants.HBASE_PUT)) ||
                                (methodName.equals(RenaultConstants.HBASE_PUT_COLUMN)) ||
                                (methodName.equals(RenaultConstants.HBASE_MULTI_PUT))
                                );
            }
        };
    }


}
