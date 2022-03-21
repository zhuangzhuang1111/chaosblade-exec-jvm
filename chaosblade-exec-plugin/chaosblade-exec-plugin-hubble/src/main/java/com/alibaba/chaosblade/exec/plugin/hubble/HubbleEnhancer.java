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
        if (methodName.equalsIgnoreCase("create")) {
            matcherModel.add(CMD, "create");
        } else if(methodName.equalsIgnoreCase("update")){
            matcherModel.add(CMD, "update");
        } else if(methodName.equalsIgnoreCase("query")){
            matcherModel.add(CMD, "query");
        }
        HashMap<String, String> clientParam = ReflectUtil.getFieldValue(methodArguments[0], "clientParam", false);
        HashSet<String> keySet = (HashSet<String>) clientParam.keySet();
        EnhancerModel enhancerModel = new EnhancerModel(classLoader, matcherModel);
        enhancerModel.addCustomMatcher(KEY, keySet, HubbleParamsMatcher.getInstance());
        return enhancerModel;
    }
}
