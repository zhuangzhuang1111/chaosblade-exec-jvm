package com.alibaba.chaosblade.exec.plugin.hubble;

import com.alibaba.chaosblade.exec.common.aop.Enhancer;
import com.alibaba.chaosblade.exec.common.aop.Plugin;
import com.alibaba.chaosblade.exec.common.aop.PointCut;
import com.alibaba.chaosblade.exec.common.model.ModelSpec;

/**
 * @author yefei
 */
public class HubblePlugin implements Plugin {
    @Override
    public String getName() {
        return "hubble plugin";
    }

    @Override
    public ModelSpec getModelSpec() {
        return new HubbleModeSpec();
    }

    @Override
    public PointCut getPointCut() {
        return new HubblePointCut();
    }

    @Override
    public Enhancer getEnhancer() {
        return new HubbleEnhancer();
    }
}
