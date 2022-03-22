package com.alibaba.chaosblade.exec.plugin.mercury.model;

import com.alibaba.chaosblade.exec.common.model.matcher.BasePredicateMatcherSpec;
import com.alibaba.chaosblade.exec.plugin.mercury.MercuryConstant;

/**
 * @author ljzhxx@gmail.com
 */
public class ResourceNameMatcherSpec extends BasePredicateMatcherSpec implements MercuryConstant {
    @Override
    public String getName() {
        return RESOURCE_NAME;
    }

    @Override
    public String getDesc() {
        return "";
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
