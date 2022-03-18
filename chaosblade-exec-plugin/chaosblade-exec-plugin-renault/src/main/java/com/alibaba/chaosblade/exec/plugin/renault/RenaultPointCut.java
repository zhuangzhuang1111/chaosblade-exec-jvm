package com.alibaba.chaosblade.exec.plugin.renault;

import com.alibaba.chaosblade.exec.common.aop.PointCut;
import com.alibaba.chaosblade.exec.common.aop.matcher.MethodInfo;
import com.alibaba.chaosblade.exec.common.aop.matcher.clazz.*;
import com.alibaba.chaosblade.exec.common.aop.matcher.method.MethodMatcher;
import com.alibaba.chaosblade.exec.common.aop.matcher.method.NameMethodMatcher;
import com.alibaba.chaosblade.exec.common.aop.matcher.method.OrMethodMatcher;

import static com.alibaba.chaosblade.exec.plugin.renault.RenaultConstants.*;

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
        return new SuperClassMatcher(RENAULT_CLASS);
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
                .or(new NameMethodMatcher(GET_METHOD))
                .or(new NameMethodMatcher(MULTI_GET_METHOD))
                .or(new NameMethodMatcher(SET))
                .or(new NameMethodMatcher(MULTI_SET_METHOD));
    }
}
