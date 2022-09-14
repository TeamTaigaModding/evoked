package com.teamtaigamodding.evoked;

import net.minecraftforge.common.ForgeConfigSpec;
import org.apache.commons.lang3.tuple.Pair;

public class EvokedConfig {

    public static class Common {
        public ForgeConfigSpec.ConfigValue<Boolean> TotemsWorkInVoid;

        Common(ForgeConfigSpec.Builder builder) {
            builder.push("items");
            builder.push("totems");
            TotemsWorkInVoid = builder.comment("If Totems can save you in the void.").define("Void Totems", true);
            builder.pop();
        }

        public static final ForgeConfigSpec COMMON_SPEC;
        public static final Common COMMON;

        static {
            final Pair<Common, ForgeConfigSpec> specPair = new ForgeConfigSpec.Builder().configure(Common::new);
            COMMON_SPEC = specPair.getRight();
            COMMON = specPair.getLeft();
        }
    }
}
