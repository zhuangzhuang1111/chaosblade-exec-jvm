package com.alibaba.chaosblade.exec.plugin.renault;

import com.alibaba.chaosblade.exec.common.model.FrameworkModelSpec;
import com.alibaba.chaosblade.exec.common.model.action.ActionSpec;
import com.alibaba.chaosblade.exec.common.model.action.delay.DelayActionSpec;
import com.alibaba.chaosblade.exec.common.model.action.exception.ThrowCustomExceptionActionSpec;
import com.alibaba.chaosblade.exec.common.model.matcher.MatcherSpec;

import java.util.ArrayList;
import java.util.List;

/**
 * @author yefei
 */
public class RenaultModeSpec extends FrameworkModelSpec {

    public RenaultModeSpec() {
        addUpdateActionSpec();
        addActionExample();
    }

    private void addUpdateActionSpec() {
        UpdateActionSpec actionSpec = new UpdateActionSpec();
        actionSpec.addMatcherDesc(new RenaultKeyMatcherSpec());
        actionSpec.addMatcherDesc(new RenaultCmdMatcherSpec());
        addActionSpec(actionSpec);
    }

    private void addActionExample() {
        List<ActionSpec> actions = getActions();
        for (ActionSpec action : actions) {
            if (action instanceof DelayActionSpec) {
                action.setLongDesc("Renault client delay experiments");
                action.setExample("# Do a delay 2s experiment on Renault `hset key name lina` command\n" +
                        "blade create renault delay --cmd hset --key name --time 2000\n\n" +

                        "#Do a delay 2s experiment on Jedis `key name lina` command\n" +
                        "blade create renault delay --key name --time 2000");
            } else if (action instanceof ThrowCustomExceptionActionSpec) {
                action.setLongDesc("Renault client throws custom exception experiments");
                action.setExample("# Do a throws custom exception experiment on Renault `key name lina` command\n" +
                        "blade create renault throwCustomException --exception java.lang.Exception --key name");
            } else if (action instanceof UpdateActionSpec) {
                action.setLongDesc("Renault client update value experiments");
                action.setExample("# Do a update value experiment on Renault `key name lina` command\n" +
                        "blade create renault update --value \"i'm hacker\" --key name");
            }
        }
    }

    @Override
    public List<MatcherSpec> createNewMatcherSpecs() {
        List<MatcherSpec> matchers = new ArrayList<MatcherSpec>();
        matchers.add(new RenaultKeyMatcherSpec());
        matchers.add(new RenaultCmdMatcherSpec());
        return matchers;
    }

    @Override
    public String getTarget() {
        return "renault";
    }

    @Override
    public String getShortDesc() {
        return "redis client renault experiment";
    }

    @Override
    public String getLongDesc() {
        return "redis client renault experiment";
    }

}