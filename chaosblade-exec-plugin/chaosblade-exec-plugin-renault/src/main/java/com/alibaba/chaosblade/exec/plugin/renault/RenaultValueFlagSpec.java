package com.alibaba.chaosblade.exec.plugin.renault;

import com.alibaba.chaosblade.exec.common.model.FlagSpec;

/**
 * @author yefei
 */
public class RenaultValueFlagSpec implements FlagSpec {

    @Override
    public String getName() {
        return RenaultConstants.VALUE;
    }

    @Override
    public String getDesc() {
        return "value set";
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
