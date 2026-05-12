package cn.mercury9.colorfulcrystals;

import cn.mercury9.colorfulcrystals.block.CrystalBudBlock;
import com.mojang.math.Quadrant;
import dev.anvilcraft.lib.v2.registrum.util.entry.BlockEntry;
import net.minecraft.client.data.models.BlockModelGenerators;
import net.minecraft.client.data.models.MultiVariant;
import net.minecraft.client.data.models.blockstates.MultiVariantGenerator;
import net.minecraft.client.data.models.blockstates.PropertyDispatch;
import net.minecraft.client.renderer.block.dispatch.Variant;
import net.minecraft.client.renderer.block.dispatch.VariantMutator;
import net.minecraft.core.Direction;
import net.minecraft.resources.Identifier;
import net.minecraft.util.random.Weighted;
import net.minecraft.util.random.WeightedList;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;

public class CrystalBlocks {
    static {
        ColorfulCrystals.REGISTRUM.defaultCreativeTab(CrystalCreativeTabs.TAB, "colorful_crystals");
    }

    public static void setupRegistration() {}

    public static final BlockEntry<CrystalBudBlock> TOPAZ_CLUSTER = ColorfulCrystals.REGISTRUM
        .object("topaz_cluster")
        .block(CrystalBudBlock.constructWith(8f, 8f))
        .initialProperties(() -> Blocks.AMETHYST_CLUSTER)
        .blockstate(() -> (ctx, gen) -> gen
            .blockStateOutput.accept(MultiVariantGenerator.dispatch(ctx.get())
                .with(propertyDispatchWithFacingAndRandomRotation(identifier("topaz_cluster")))
            )
        )
        .lang("Topaz Cluster")
        .item()
        .build()
        .register();

    public static final BlockEntry<CrystalBudBlock> RUBY_CLUSTER = ColorfulCrystals.REGISTRUM
        .object("ruby_cluster")
        .block(CrystalBudBlock.constructWith(8f, 8f))
        .initialProperties(() -> Blocks.AMETHYST_CLUSTER)
        .lang("Ruby Cluster")
        .blockstate(() -> (ctx, gen) -> gen
            .blockStateOutput.accept(MultiVariantGenerator.dispatch(ctx.get())
                .with(propertyDispatchWithFacingAndRandomRotation(identifier("ruby_cluster")))
            )
        )
        .item()
        .build()
        .register();

    public static Identifier identifier(String id) {
        return ColorfulCrystals.identifier("block/" + id);
    }

    public static MultiVariant randomRotationMultiVariant(
        Identifier model,
        VariantMutator.VariantProperty<Quadrant> rot
    ) {
        return new MultiVariant(
            WeightedList.of(
                new Weighted<>(new Variant(model).with(rot.withValue(Quadrant.R0  )), 1),
                new Weighted<>(new Variant(model).with(rot.withValue(Quadrant.R90 )), 1),
                new Weighted<>(new Variant(model).with(rot.withValue(Quadrant.R180)), 1),
                new Weighted<>(new Variant(model).with(rot.withValue(Quadrant.R270)), 1)
            )
        );
    }

    public static PropertyDispatch<MultiVariant> propertyDispatchWithFacingAndRandomRotation(Identifier model) {
        return PropertyDispatch.initial(BlockStateProperties.FACING)
            .select(Direction.UP    , randomRotationMultiVariant(model, VariantMutator.Y_ROT))
            .select(Direction.DOWN  , randomRotationMultiVariant(model, VariantMutator.Y_ROT).with(VariantMutator.X_ROT.withValue(Quadrant.R180)))
            .select(Direction.NORTH , randomRotationMultiVariant(model, VariantMutator.Z_ROT).with(VariantMutator.X_ROT.withValue(Quadrant.R90 )))
            .select(Direction.SOUTH , randomRotationMultiVariant(model, VariantMutator.Z_ROT).with(VariantMutator.X_ROT.withValue(Quadrant.R270)))
            .select(Direction.EAST  , randomRotationMultiVariant(model, VariantMutator.Y_ROT).with(VariantMutator.Z_ROT.withValue(Quadrant.R90 )))
            .select(Direction.WEST  , randomRotationMultiVariant(model, VariantMutator.Y_ROT).with(VariantMutator.Z_ROT.withValue(Quadrant.R270)));
    }

}
