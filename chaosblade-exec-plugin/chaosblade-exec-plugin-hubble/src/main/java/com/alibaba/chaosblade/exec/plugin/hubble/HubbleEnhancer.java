package com.alibaba.chaosblade.exec.plugin.hubble;

import com.alibaba.chaosblade.exec.common.aop.BeforeEnhancer;
import com.alibaba.chaosblade.exec.common.aop.EnhancerModel;
import com.alibaba.chaosblade.exec.common.model.matcher.MatcherModel;
import com.alibaba.chaosblade.exec.common.util.ReflectUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.HashSet;

import static com.alibaba.chaosblade.exec.plugin.hubble.HubbleConstants.CMD;
import static com.alibaba.chaosblade.exec.plugin.hubble.HubbleConstants.KEY;


/**
 * @author yefei
 */
public class HubbleEnhancer extends BeforeEnhancer {

    private static final Logger logger = LoggerFactory.getLogger(HubbleEnhancer.class);

    @Override
    public EnhancerModel doBeforeAdvice(ClassLoader classLoader,
                                        String className,
                                        Object object,
                                        Method method,
                                        Object[] methodArguments) throws Exception {
        MatcherModel matcherModel = new MatcherModel();
        String methodName = ReflectUtil.getFieldValue(methodArguments[0], "method", false);
        // hubble -> support create\get\delete
        String endPoint = ReflectUtil.getFieldValue(methodArguments[0], "endpoint", false);

        String[] split = endPoint.split("/");
        String index = split[1]; // common_test
        String type = split[2]; // _doc | _bulk  _health(健康检查)
        if (type.equalsIgnoreCase("_doc") && methodName.equalsIgnoreCase("POST")) {
            matcherModel.add(CMD, "create");
        } else if(methodName.equalsIgnoreCase("_bulk") && methodName.equalsIgnoreCase("POST") ) {
            matcherModel.add(CMD, "create");
        } else if (type.equalsIgnoreCase("_update")) {
            matcherModel.add(CMD, "update");
        } else if (methodName.equalsIgnoreCase("search")) {
            matcherModel.add(CMD, "query");
        } else if(methodName.equalsIgnoreCase("_doc") && methodName.equalsIgnoreCase("GET")){
            matcherModel.add(CMD, "query"); // not match in the end
        }
        matcherModel.add(KEY, index);
        EnhancerModel enhancerModel = new EnhancerModel(classLoader, matcherModel);
        return enhancerModel;
    }
}
