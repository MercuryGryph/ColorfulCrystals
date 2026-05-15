package cn.mercury9.colorfulcrystals.item;

import dev.anvilcraft.lib.v2.registrum.util.entry.ItemEntry;
import dev.anvilcraft.lib.v2.util.nullness.NonNullFunction;
import dev.anvilcraft.lib.v2.util.nullness.NonNullSupplier;
import net.minecraft.core.BlockPos;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;

public class RawGemItem extends HoldToTransformItem {
    public RawGemItem(NonNullSupplier<? extends Item> rawGem, Properties properties) {
        super(rawGem, properties);
    }

    public static NonNullFunction<Properties, RawGemItem> factory(ItemEntry<? extends Item> rawGem) {
        return (properties) -> new RawGemItem(rawGem, properties);
    }

    @Override
    protected boolean canUseOnBlock(Level level, BlockPos pos) {
        return level.getBlockState(pos).is(Blocks.STONECUTTER);
    }
}
