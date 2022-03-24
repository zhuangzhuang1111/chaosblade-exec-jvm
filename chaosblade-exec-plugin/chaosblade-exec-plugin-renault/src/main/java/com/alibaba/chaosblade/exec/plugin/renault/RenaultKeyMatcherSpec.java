package com.alibaba.chaosblade.exec.plugin.renault;

import com.alibaba.chaosblade.exec.common.model.matcher.BasePredicateMatcherSpec;

/**
 * @author yefei
 */
public class RenaultKeyMatcherSpec extends BasePredicateMatcherSpec {

    @Override
    public String getName() {
        return RenaultConstants.KEY;
    }

    @Override
    public String getDesc() {
        return "category matcher,same with renault category";
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
