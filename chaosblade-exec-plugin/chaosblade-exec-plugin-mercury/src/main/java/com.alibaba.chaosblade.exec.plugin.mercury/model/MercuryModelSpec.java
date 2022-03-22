package com.alibaba.chaosblade.exec.plugin.mercury.model;

import com.alibaba.chaosblade.exec.common.aop.PredicateResult;
import com.alibaba.chaosblade.exec.common.model.FrameworkModelSpec;
import com.alibaba.chaosblade.exec.common.model.Model;
import com.alibaba.chaosblade.exec.common.model.action.ActionSpec;
import com.alibaba.chaosblade.exec.common.model.action.delay.DelayActionSpec;
import com.alibaba.chaosblade.exec.common.model.action.exception.ThrowCustomExceptionActionSpec;
import com.alibaba.chaosblade.exec.common.model.matcher.MatcherModel;
import com.alibaba.chaosblade.exec.common.model.matcher.MatcherSpec;
import com.alibaba.chaosblade.exec.plugin.mercury.MercuryConstant;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * @author ljzhxx@gmail.com
 */
public class MercuryModelSpec extends FrameworkModelSpec implements MercuryConstant {

    public MercuryModelSpec() {
        addActionExample();
    }

    private void addActionExample() {
        List<ActionSpec> actions = getActions();
        for (ActionSpec action : actions) {
            if (action instanceof DelayActionSpec) {
                action.setLongDesc("mercury delay experiment");
                action.setExample("# Delay when the producer sends the message\n" +
                        "blade create mercury delay --time 3000 --producer");
            } else if (action instanceof ThrowCustomExceptionActionSpec) {
                action.setLongDesc("mercury throws custom exception experiment");
                action.setExample("# Throw exception when the producer sends the message\n" +
                        "blade create mercury throwCustomException --exception java.lang.Exception --exception-message mock-beans-exception --producer");
            }
        }
    }

    @Override
    protected List<MatcherSpec> createNewMatcherSpecs() {
        ArrayList<MatcherSpec> arrayList = new ArrayList<MatcherSpec>();
        arrayList.add(new MercuryConsumerMatcherSpec());
        arrayList.add(new MercuryProducerMatcherSpec());
        arrayList.add(new ResourceNameMatcherSpec());
        return arrayList;
    }

    @Override
    public String getTarget() {
        return TARGET_NAME;
    }

    @Override
    public String getShortDesc() {
        return "mercury experiment";
    }

    @Override
    public String getLongDesc() {
        return "mercury experiment for testing service delay and exception.";
    }

    @Override
    protected PredicateResult preMatcherPredicate(Model model) {
        if (model == null) {
            return PredicateResult.fail("matcher not found for kafka");
        }
        MatcherModel matcher = model.getMatcher();
        Set<String> keySet = matcher.getMatchers().keySet();
        for (String key : keySet) {
            if (key.equals(CONSUMER_KEY) || key.equals(PRODUCER_KEY)) {
                return PredicateResult.success();
            }
        }
        return PredicateResult.fail("less necessary matcher is subscription or topic for mercury");
    }
}
