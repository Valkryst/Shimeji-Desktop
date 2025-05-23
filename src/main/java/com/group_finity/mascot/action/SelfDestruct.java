package com.group_finity.mascot.action;

import com.group_finity.mascot.animation.Animation;
import com.group_finity.mascot.exception.LostGroundException;
import com.group_finity.mascot.exception.VariableException;
import com.group_finity.mascot.script.VariableMap;

import java.util.List;
import java.util.ResourceBundle;

/**
 * @author Kilkakon
 * @since 1.0.13
 */
public class SelfDestruct extends Animate {
    public SelfDestruct(ResourceBundle schema, final List<Animation> animations, final VariableMap context) {
        super(schema, animations, context);
    }

    @Override
    protected void tick() throws LostGroundException, VariableException {
        super.tick();

        if (getTime() == getAnimation().getDuration() - 1) {
            getMascot().dispose();
        }
    }
}
