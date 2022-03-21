package com.alibaba.chaosblade.exec.plugin.renault;

import com.alibaba.chaosblade.exec.common.aop.EnhancerModel;
import com.alibaba.chaosblade.exec.common.model.action.ActionExecutor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

/**
 * @author yefei
 */
public class RenaultActionExecutor implements ActionExecutor {

    private static final Logger logger = LoggerFactory.getLogger(RenaultActionExecutor.class);

    private static final List<String> SUPPORTS_COMMANDS = new ArrayList<String>();

    static {
        //SUPPORTS_COMMANDS.add("SET");
        //SUPPORTS_COMMANDS.add("SETNX");
        //SUPPORTS_COMMANDS.add("HSET");
    }

    private RenaultValueFlagSpec renaultValueFlagSpec;

    public RenaultActionExecutor(RenaultValueFlagSpec renaultValueFlagSpec) {
        this.renaultValueFlagSpec = renaultValueFlagSpec;
    }

    /**
     * 拦截后进行逻辑处理
     * Renault 不区分redis或hbase 根据categoryId做区分
     * 如categoryIdA set请求做delay实验
     * @param enhancerModel
     * @throws Exception
     */
    @Override
    public void run(EnhancerModel enhancerModel) throws Exception {

        Method method = enhancerModel.getMethod();
        if (method.getName().equals("SET")) {

        }

        /*Object command = enhancerModel.getMethodArguments()[1];
        Object args = ReflectUtil.getFieldValue(command, "command", false);
        Object commandType = ReflectUtil.getFieldValue(args, "type", false);
        if (!SUPPORTS_COMMANDS.contains(String.valueOf(commandType))) {
            logger.info("can not support commandType: {}", commandType);
            return;
        }

        Object commandArgs = ReflectUtil.getFieldValue(args, "args", false);
        List singularArguments = ReflectUtil.getFieldValue(commandArgs, "singularArguments", false);

        Object valArgument = singularArguments.get(1);
        Object originVal = ReflectUtil.getFieldValue(valArgument, "val", false);
        if (!(originVal instanceof String)) {
            logger.info("can not support value, value type: {}", originVal.getClass());
            return;
        }

        Object codec = ReflectUtil.getFieldValue(valArgument, "codec", false);
        String value = enhancerModel.getActionFlag(renaultValueFlagSpec.getName());
        Object[] arguments = new Object[]{value, codec};
        Object valueArgument = ReflectUtil.invokeStaticMethod(valArgument.getClass(), "of", arguments, false);
        if (valueArgument != null) {
            logger.info("update value success. origin value: {}, update value: {}", originVal, value);
            // 更新原来的值
            singularArguments.set(1, valueArgument);
        }*/
    }
}
