package com.group_finity.mascot.action;

import com.group_finity.mascot.Main;
import com.group_finity.mascot.animation.Animation;
import com.group_finity.mascot.exception.BehaviorInstantiationException;
import com.group_finity.mascot.exception.CantBeAliveException;
import com.group_finity.mascot.exception.LostGroundException;
import com.group_finity.mascot.exception.VariableException;
import com.group_finity.mascot.script.VariableMap;
import lombok.extern.java.Log;

import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;

/**
 * @author Kilkakon
 * @since 1.0.14
 */
@Log
public class Interact extends Animate {
    public static final String PARAMETER_BEHAVIOUR = "Behaviour";

    private static final String DEFAULT_BEHAVIOUR = "";

    public Interact(ResourceBundle schema, final List<Animation> animations, final VariableMap context) {
        super(schema, animations, context);
    }

    @Override
    public boolean hasNext() throws VariableException {
        return super.hasNext() && getMascot().getManager().hasOverlappingMascotsAtPoint(getMascot().getAnchor());
    }

    @Override
    protected void tick() throws LostGroundException, VariableException {
        super.tick();

        if (getTime() == getAnimation().getDuration() - 1 && !getBehavior().trim().isEmpty()) {
            try {
                getMascot().setBehavior(Main.getInstance().getConfiguration(getMascot().getImageSet()).buildBehavior(getBehavior(), getMascot()));
            } catch (final BehaviorInstantiationException | CantBeAliveException e) {
                log.log(Level.SEVERE, "Failed to set behavior to \"" + getBehavior() + "\" for mascot \"" + getMascot() + "\"", e);
                Main.showError(Main.getInstance().getLanguageBundle().getString("FailedSetBehaviourErrorMessage") + "\n" + e.getMessage() + "\n" + Main.getInstance().getLanguageBundle().getString("SeeLogForDetails"));
            }
        }
    }

    private String getBehavior() throws VariableException {
        return eval(getSchema().getString(PARAMETER_BEHAVIOUR), String.class, DEFAULT_BEHAVIOUR);
    }
}
