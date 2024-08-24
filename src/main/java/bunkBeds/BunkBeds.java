package bunkBeds;

import necesse.engine.localization.Localization;
import necesse.engine.modLoader.annotations.ModEntry;
import necesse.engine.modLoader.annotations.ModMethodPatch;
import necesse.entity.mobs.friendly.human.HappinessModifier;
import necesse.entity.mobs.friendly.human.HumanMob;
import net.bytebuddy.asm.Advice;

import java.util.List;
import java.util.function.Predicate;

@ModEntry
public class BunkBeds {
    public static final Predicate<HappinessModifier> isSharedRoomModifier = (modifier) ->
        modifier.description.translate().startsWith(Localization.translate("settlement", "sharingroom"));

    @ModMethodPatch(target = HumanMob.class, name = "getHappinessModifiers", arguments = {})
    public static class GetHappinessModifiersPatch {
        @Advice.OnMethodExit
        public static void onExit(@Advice.Return(readOnly = false) List<HappinessModifier> modifiers) {
            modifiers.removeIf(isSharedRoomModifier);
        }
    }
}
