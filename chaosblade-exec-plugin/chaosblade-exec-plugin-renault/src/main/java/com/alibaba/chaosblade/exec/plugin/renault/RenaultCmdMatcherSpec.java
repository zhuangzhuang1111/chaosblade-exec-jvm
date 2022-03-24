package com.alibaba.chaosblade.exec.plugin.renault;

import com.alibaba.chaosblade.exec.common.model.matcher.BasePredicateMatcherSpec;

/**
 * @author yefei
 * @create 2020-11-23 14:53
 */
public class RenaultCmdMatcherSpec extends BasePredicateMatcherSpec {

    @Override
    public String getName() {
        return RenaultConstants.CMD;
    }

    @Override
    public String getDesc() {
        return "cmd matcher,support set(set,mset)/get(get，mget)";
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
