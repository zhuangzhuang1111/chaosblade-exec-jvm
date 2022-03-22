package com.alibaba.chaosblade.exec.plugin.mercury.model;

import com.alibaba.chaosblade.exec.common.model.matcher.BasePredicateMatcherSpec;
import com.alibaba.chaosblade.exec.plugin.mercury.MercuryConstant;

import static com.alibaba.chaosblade.exec.plugin.mercury.MercuryConstant.PRODUCER_KEY;

/**
 * @author ljzhxx@gmail.com
 */
public class MercuryProducerMatcherSpec extends BasePredicateMatcherSpec implements MercuryConstant {
    @Override
    public String getName() {
        return PRODUCER_KEY;
    }

    @Override
    public String getDesc() {
        return "";
    }

    @Override
    public boolean noArgs() {
        return true;
    }

    @Override
    public boolean required() {
        return false;
    }
}
