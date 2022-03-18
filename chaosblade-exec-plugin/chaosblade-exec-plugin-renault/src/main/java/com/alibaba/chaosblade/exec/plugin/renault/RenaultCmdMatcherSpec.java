package com.alibaba.chaosblade.exec.plugin.renault;

import com.alibaba.chaosblade.exec.common.model.matcher.BasePredicateMatcherSpec;

import static com.alibaba.chaosblade.exec.plugin.renault.RenaultConstants.CMD;

/**
 * @author yefei
 * @create 2020-11-23 14:53
 */
public class RenaultCmdMatcherSpec extends BasePredicateMatcherSpec {

    @Override
    public String getName() {
        return CMD;
    }

    @Override
    public String getDesc() {
        return "cmd matcher";
    }

    @Override
    public boolean noArgs() {
        return false;
    }

    @Override
    public boolean required() {
        return false;
    }
}
