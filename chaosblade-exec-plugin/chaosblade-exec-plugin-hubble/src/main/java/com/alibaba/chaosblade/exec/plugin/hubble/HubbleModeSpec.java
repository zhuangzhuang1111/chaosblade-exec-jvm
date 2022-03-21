package com.alibaba.chaosblade.exec.plugin.hubble;

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
public class HubbleModeSpec extends FrameworkModelSpec {

    public HubbleModeSpec() {
        //addUpdateActionSpec();
        addActionExample();
    }

    private void addActionExample() {
        List<ActionSpec> actions = getActions();
        for (ActionSpec action : actions) {
            if (action instanceof DelayActionSpec) {
                action.setLongDesc("Hubble client delay experiments");
                action.setExample("# Do a delay 2s experiment on Hubble `create key name lina` command\n" +
                        "blade create hubble delay --cmd hset --key name --time 2000\n\n" +

                        "#Do a delay 2s experiment on Jedis `key name lina` command\n" +
                        "blade create hubble delay --key name --time 2000");
            } else if (action instanceof ThrowCustomExceptionActionSpec) {
                action.setLongDesc("Hubble client throws custom exception experiments");
                action.setExample("# Do a throws custom exception experiment on Hubble `key name lina` command\n" +
                        "blade create hubble throwCustomException --exception java.lang.Exception --key name");
            } /*else if (action instanceof UpdateActionSpec) {
                action.setLongDesc("Renault client update value experiments");
                action.setExample("# Do a update value experiment on Renault `key name lina` command\n" +
                        "blade create renault update --value \"i'm hacker\" --key name");
            }*/
        }
    }

    @Override
    public List<MatcherSpec> createNewMatcherSpecs() {
        List<MatcherSpec> matchers = new ArrayList<MatcherSpec>();
        matchers.add(new HubbleKeyMatcherSpec());
        matchers.add(new HubbleCmdMatcherSpec());
        return matchers;
    }

    @Override
    public String getTarget() {
        return "hubble";
    }

    @Override
    public String getShortDesc() {
        return "hubble experiment";
    }

    @Override
    public String getLongDesc() {
        return "hubble experiment";
    }

}
