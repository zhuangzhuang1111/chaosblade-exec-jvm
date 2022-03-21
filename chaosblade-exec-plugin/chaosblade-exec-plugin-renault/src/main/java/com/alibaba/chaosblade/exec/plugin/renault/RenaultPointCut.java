package com.alibaba.chaosblade.exec.plugin.renault;

import com.alibaba.chaosblade.exec.common.aop.PointCut;
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
        /*return new OrClassMatcher().or(new InterfaceClassMatcher("com.ning.http.client.AsyncHandler"))
                .or(new NameClassMatcher("com.ning.http.client.providers.netty.request.NettyRequestSender"))
                .or(new NameClassMatcher("com.ning.http.client.providers.netty.handler.HttpProtocol"));*/
        //NameClassMatcher nameClassMatcher = new NameClassMatcher(RENAULT_CLASS);
        return new SuperClassMatcher(RenaultConstants.RENAULT_CLASS);
        //return new NameClassMatcher(RENAULT_CLASS);
        //return nameClassMatcher;
    }

    /**
     * 对这些方法进行前置拦截
     * @return
     */
    @Override
    public MethodMatcher getMethodMatcher() {
        //NameMethodMatcher nameMethodMatcher = new NameMethodMatcher();
        OrMethodMatcher orMethodMatcher = new OrMethodMatcher();
        return orMethodMatcher
                .or(new NameMethodMatcher(RenaultConstants.GET_METHOD))
                .or(new NameMethodMatcher(RenaultConstants.MULTI_GET_METHOD))
                .or(new NameMethodMatcher(RenaultConstants.SET))
                .or(new NameMethodMatcher(RenaultConstants.MULTI_SET_METHOD));
    }
}
