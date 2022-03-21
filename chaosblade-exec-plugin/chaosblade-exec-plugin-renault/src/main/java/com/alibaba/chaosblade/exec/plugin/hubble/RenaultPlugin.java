package com.alibaba.chaosblade.exec.plugin.hubble;

import com.alibaba.chaosblade.exec.common.aop.Enhancer;
import com.alibaba.chaosblade.exec.common.aop.Plugin;
import com.alibaba.chaosblade.exec.common.aop.PointCut;
import com.alibaba.chaosblade.exec.common.model.ModelSpec;

/**
 * @author yefei
 */
public class RenaultPlugin implements Plugin {
    @Override
    public String getName() {
        return "renault plugin";
    }

    @Override
    public ModelSpec getModelSpec() {
        return new RenaultModeSpec();
    }

    @Override
    public PointCut getPointCut() {
        return new RenaultPointCut();
    }

    @Override
    public Enhancer getEnhancer() {
        return new RenaultEnhancer();
    }
}
