package com.alibaba.chaosblade.exec.plugin.mercury;

import com.alibaba.chaosblade.exec.common.aop.Plugin;
import com.alibaba.chaosblade.exec.common.model.ModelSpec;
import com.alibaba.chaosblade.exec.plugin.mercury.model.MercuryModelSpec;

/**
 * @author ljzhxx@gmail.com
 */
public abstract class MercuryPlugin implements Plugin, MercuryConstant {

    @Override
    public ModelSpec getModelSpec() {
        return new MercuryModelSpec();
    }

}
