package com.alibaba.chaosblade.exec.plugin.mercury.producer;

import com.alibaba.chaosblade.exec.common.aop.Enhancer;
import com.alibaba.chaosblade.exec.common.aop.PointCut;
import com.alibaba.chaosblade.exec.plugin.mercury.MercuryPlugin;

/**
 * @author ljzhxx@gmail.com
 */
public class MercuryProducerPlugin extends MercuryPlugin {
    @Override
    public String getName() {
        return "topic";
    }

    @Override
    public PointCut getPointCut() {
        return new MercuryProducerPointCut();
    }

    @Override
    public Enhancer getEnhancer() {
        return new MercuryProducerEnhancer();
    }
}
