package cn.mercury9.colorfulcrystals.item;

import dev.anvilcraft.lib.v2.util.nullness.NonNullFunction;
import dev.anvilcraft.lib.v2.util.nullness.NonNullSupplier;
import net.minecraft.core.BlockPos;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;

public class UnpolishedGemItem extends HoldToTransformItem {
    public UnpolishedGemItem(NonNullSupplier<Item> polished, Properties properties) {
        super(polished, properties);
    }

    public static NonNullFunction<Properties, UnpolishedGemItem> factory(NonNullSupplier<Item> polished) {
        return (properties) -> new UnpolishedGemItem(polished, properties);
    }

    @Override
    protected boolean canUseOnBlock(Level level, BlockPos pos) {
        return level.getBlockState(pos).is(Blocks.GRINDSTONE);
    }
}
