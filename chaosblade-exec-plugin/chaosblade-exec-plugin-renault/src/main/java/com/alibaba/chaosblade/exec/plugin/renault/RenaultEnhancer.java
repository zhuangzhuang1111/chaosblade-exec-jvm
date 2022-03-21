package com.alibaba.chaosblade.exec.plugin.renault;

import com.alibaba.chaosblade.exec.common.aop.BeforeEnhancer;
import com.alibaba.chaosblade.exec.common.aop.EnhancerModel;
import com.alibaba.chaosblade.exec.common.model.matcher.MatcherModel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.HashSet;

import static com.alibaba.chaosblade.exec.plugin.renault.RenaultConstants.*;


/**
 * @author yefei
 */
public class RenaultEnhancer extends BeforeEnhancer {

    private static final Logger logger = LoggerFactory.getLogger(RenaultEnhancer.class);

    @Override
    public EnhancerModel doBeforeAdvice(ClassLoader classLoader,
                                        String className,
                                        Object object,
                                        Method method,
                                        Object[] methodArguments) throws Exception {

/*        Object command = methodArguments[1];
        Object args = ReflectUtil.getFieldValue(command, "command", false);
        Object commandType = ReflectUtil.getFieldValue(args, "type", false);

        Object commandArgs = ReflectUtil.getFieldValue(args, "args", false);
        List singularArguments = ReflectUtil.getFieldValue(commandArgs, "singularArguments", false);
        Object keyArgument = singularArguments.get(0);
        MatcherModel matcherModel = new MatcherModel();
        if (keyArgument == null) {
            return null;
        }
        Object key = ReflectUtil.getFieldValue(keyArgument, "key", false);
        matcherModel.add(KEY, key);
        matcherModel.add(CMD, commandType);
        logger.debug("lettuce matchers: {}", JsonUtil.writer().writeValueAsString(matcherModel));
        return new EnhancerModel(classLoader, matcherModel);
    }*/
        /**
         * MatcherModel
         * renault
         *    cmd: set\get                  key: xx
         *    set:set\setAsync get:getAsync  key:xx
         */
        MatcherModel matcherModel = new MatcherModel();
        HashSet<Object> keySet = new HashSet();
        if (method.getName().contains("GET")) {
            if (method.getName().equals(MULTI_GET_METHOD)) {
                keySet = (HashSet<Object>) methodArguments[0];
            } else {
                keySet.add(methodArguments[0]);
            }
            matcherModel.add(CMD, "GET");
        } else if (method.getName().contains("SET")) {
            if (method.getName().equals(MULTI_SET_METHOD)) {
                HashMap<Object, Object> setData = (HashMap<Object, Object>) methodArguments[0];
                keySet = (HashSet<Object>) setData.keySet();
            } else {
                keySet.add(methodArguments[0]);
            }
            matcherModel.add(CMD, "SET");
        }

        EnhancerModel enhancerModel = new EnhancerModel(classLoader, matcherModel);
        enhancerModel.addCustomMatcher(KEY, keySet, RenaultParamsMatcher.getInstance());
        return enhancerModel;
    }
}
