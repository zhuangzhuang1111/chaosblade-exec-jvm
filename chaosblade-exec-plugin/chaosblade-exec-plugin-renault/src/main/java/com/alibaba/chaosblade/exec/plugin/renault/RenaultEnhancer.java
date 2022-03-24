package com.alibaba.chaosblade.exec.plugin.renault;

import com.alibaba.chaosblade.exec.common.aop.BeforeEnhancer;
import com.alibaba.chaosblade.exec.common.aop.EnhancerModel;
import com.alibaba.chaosblade.exec.common.model.matcher.MatcherModel;
import com.alibaba.chaosblade.exec.common.util.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Method;
import java.util.*;

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
        /**
         * MatcherModel
         * renault
         *    cmd: set\get                  key: xx
         *    set:set\setAsync get:getAsync  key:xx
         */
        MatcherModel matcherModel = new MatcherModel();
        List<String> keyList = new ArrayList<String>();
        String beforeArgument = "";
        if (method.getName().equals("get")) {
            beforeArgument = (String) methodArguments[0];
            matcherModel.add(CMD, "get");
        } else if (method.getName().equals("mget")) {
            Iterator it = (Iterator) methodArguments[0];
            Object next = it.next();
            if (next != null) {
                beforeArgument = (String) next;
            }
            matcherModel.add(CMD, "get");
        } else if (method.getName().equals("set")) {
            beforeArgument = (String) methodArguments[0];
            matcherModel.add(CMD, "set");
        } else if (method.getName().equals(SET_2)) {
            beforeArgument = (String) methodArguments[0];
            matcherModel.add(CMD, "set");
        } else if (method.getName().equals("mset")) {
            HashMap<Object, Object> setData = (HashMap<Object, Object>) methodArguments[0];
            beforeArgument = new ArrayList<String>((HashSet) setData.keySet()).get(0);
            matcherModel.add(CMD, "set");
        }
        if (StringUtils.isNotBlank(beforeArgument)) {
            int index = beforeArgument.indexOf(":");
            if (index != -1) { // 原生的可能没有
                String category = beforeArgument.substring(0, beforeArgument.indexOf(":"));
                keyList.add(category);
            }
        }
        EnhancerModel enhancerModel = new EnhancerModel(classLoader, matcherModel);
        enhancerModel.addCustomMatcher(KEY, keyList, RenaultParamsMatcher.getInstance());
        return enhancerModel;
    }
}
