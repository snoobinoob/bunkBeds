package bunkBeds;

import necesse.engine.modLoader.annotations.ModEntry;
import necesse.engine.modLoader.annotations.ModMethodPatch;
import necesse.level.maps.levelData.settlementData.SettlementRoom;
import net.bytebuddy.asm.Advice;

@ModEntry
public class BunkBeds {
    @ModMethodPatch(target = SettlementRoom.class, name = "getOccupiedBeds", arguments = {})
    public static class GetOccupiedBedsPatch {
        @Advice.OnMethodExit
        public static void onExit(@Advice.Return(readOnly = false) int occupiedBeds) {
            occupiedBeds = Math.min(occupiedBeds, 1);
        }
    }
}
