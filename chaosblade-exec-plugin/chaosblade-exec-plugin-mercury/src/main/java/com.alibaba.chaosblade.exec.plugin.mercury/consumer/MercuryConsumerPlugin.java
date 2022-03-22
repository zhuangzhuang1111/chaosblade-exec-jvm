package com.alibaba.chaosblade.exec.plugin.mercury.consumer;

import com.alibaba.chaosblade.exec.common.aop.Enhancer;
import com.alibaba.chaosblade.exec.common.aop.PointCut;
import com.alibaba.chaosblade.exec.plugin.mercury.MercuryPlugin;

/**
 * @author ljzhxx@gmail.com
 */
public class MercuryConsumerPlugin extends MercuryPlugin {
    @Override
    public String getName() {
        return "subscription";
    }

    @Override
    public PointCut getPointCut() {
        return new MercuryConsumerPointCut();
    }

    @Override
    public Enhancer getEnhancer() {
        return new MercuryConsumerEnhancer();
    }
}
