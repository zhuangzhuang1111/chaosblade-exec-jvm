package com.alibaba.chaosblade.exec.plugin.renault;

import com.alibaba.chaosblade.exec.common.model.matcher.BasePredicateMatcherSpec;

import static com.alibaba.chaosblade.exec.plugin.renault.RenaultConstants.KEY;

/**
 * @author yefei
 */
public class RenaultKeyMatcherSpec extends BasePredicateMatcherSpec {

    @Override
    public String getName() {
        return KEY;
    }

    @Override
    public String getDesc() {
        return "key matcher";
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
