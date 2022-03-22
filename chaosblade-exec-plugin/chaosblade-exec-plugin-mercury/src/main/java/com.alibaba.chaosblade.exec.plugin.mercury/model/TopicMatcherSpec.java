package com.alibaba.chaosblade.exec.plugin.mercury.model;

import com.alibaba.chaosblade.exec.common.model.matcher.BasePredicateMatcherSpec;
import com.alibaba.chaosblade.exec.plugin.mercury.MercuryConstant;

import static com.alibaba.chaosblade.exec.plugin.mercury.MercuryConstant.TOPIC_KEY;

/**
 * @author ljzhxx@gmail.com
 */
public class TopicMatcherSpec extends BasePredicateMatcherSpec implements MercuryConstant {
    @Override
    public String getName() {
        return TOPIC_KEY;
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
