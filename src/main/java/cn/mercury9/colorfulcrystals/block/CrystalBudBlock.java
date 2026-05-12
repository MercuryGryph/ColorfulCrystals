package cn.mercury9.colorfulcrystals.block;

import dev.anvilcraft.lib.v2.util.nullness.NonNullFunction;
import net.minecraft.world.level.block.AmethystClusterBlock;

public class CrystalBudBlock extends AmethystClusterBlock {

    public CrystalBudBlock(float height, float width, Properties props) {
        super(height, width, props);
    }

    public static NonNullFunction<Properties, CrystalBudBlock> constructWith(
        float height,
        float width
    ) {
        return (properties) -> new CrystalBudBlock(height, width, properties);
    }

}
