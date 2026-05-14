package cn.mercury9.colorfulcrystals;

import cn.mercury9.colorfulcrystals.block.CrystalBudBlock;
import com.mojang.math.Quadrant;
import dev.anvilcraft.lib.v2.registrum.providers.DataGenContext;
import dev.anvilcraft.lib.v2.registrum.providers.generators.RegistrumBlockModelGenerator;
import dev.anvilcraft.lib.v2.registrum.util.entry.BlockEntry;
import dev.anvilcraft.lib.v2.util.nullness.NonNullBiConsumer;
import dev.anvilcraft.lib.v2.util.nullness.NonNullSupplier;
import net.minecraft.client.data.models.MultiVariant;
import net.minecraft.client.data.models.blockstates.MultiVariantGenerator;
import net.minecraft.client.data.models.blockstates.PropertyDispatch;
import net.minecraft.client.renderer.block.dispatch.Variant;
import net.minecraft.client.renderer.block.dispatch.VariantMutator;
import net.minecraft.core.Direction;
import net.minecraft.data.recipes.RecipeCategory;
import net.minecraft.resources.Identifier;
import net.minecraft.util.random.Weighted;
import net.minecraft.util.random.WeightedList;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;

@SuppressWarnings("unused")
public class CrystalBlocks {
    static {
        ColorfulCrystals.REGISTRUM.defaultCreativeTab(CrystalCreativeTabs.TAB.getKey());
    }

    protected static void setupRegistration() {}

    public static final BlockEntry<CrystalBudBlock> TOPAZ_CLUSTER = ColorfulCrystals.REGISTRUM
        .object("topaz_cluster")
        .block(CrystalBudBlock.factory(8f, 8f))
        .initialProperties(() -> Blocks.AMETHYST_CLUSTER)
        .blockstate(clusterBlockState(identifier("topaz_cluster")))
        .lang("Topaz Cluster")
        .loot((tables, block) -> {
            tables.dropSelf(block);
            tables.createOreDrop(block, CrystalItems.TOPAZ_UNPOLISHED.asItem());
        })
        .tag(CrystalTags.Blocks.CLUSTERS, CrystalTags.Blocks.TOPAZ_CLUSTER)
        .item()
        .lang("Topaz Cluster")
        .tag(CrystalTags.Items.CLUSTERS, CrystalTags.Items.TOPAZ_CLUSTER)
        .build()
        .register();

    public static final BlockEntry<CrystalBudBlock> RUBY_CLUSTER = ColorfulCrystals.REGISTRUM
        .object("ruby_cluster")
        .block(CrystalBudBlock.factory(6f, 8f))
        .initialProperties(() -> Blocks.AMETHYST_CLUSTER)
        .blockstate(clusterBlockState(identifier("ruby_cluster")))
        .lang("Ruby Cluster")
        .loot((tables, block) -> {
            tables.dropSelf(block);
            tables.createOreDrop(block, CrystalItems.RUBY_UNPOLISHED.asItem());
        })
        .tag(CrystalTags.Blocks.CLUSTERS, CrystalTags.Blocks.RUBY_CLUSTER)
        .item()
        .lang("Ruby Cluster")
        .tag(CrystalTags.Items.CLUSTERS, CrystalTags.Items.RUBY_CLUSTER)
        .build()
        .register();

    public static final BlockEntry<CrystalBudBlock> SAPPHIRE_CLUSTER = ColorfulCrystals.REGISTRUM
        .object("sapphire_cluster")
        .block(CrystalBudBlock.factory(6f, 8f))
        .initialProperties(() -> Blocks.AMETHYST_CLUSTER)
        .blockstate(clusterBlockState(identifier("sapphire_cluster")))
        .lang("Sapphire Cluster")
        .loot((tables, block) -> {
            tables.dropSelf(block);
            tables.createOreDrop(block, CrystalItems.SAPPHIRE_UNPOLISHED.asItem());
        })
        .tag(CrystalTags.Blocks.CLUSTERS, CrystalTags.Blocks.RUBY_CLUSTER)
        .item()
        .lang("Sapphire Cluster")
        .tag(CrystalTags.Items.CLUSTERS, CrystalTags.Items.RUBY_CLUSTER)
        .build()
        .register();

    public static final BlockEntry<Block> UNPOLISHED_TOPAZ_BLOCK = ColorfulCrystals.REGISTRUM
        .object("unpolished_topaz_block")
        .block(Block::new)
        .initialProperties(() -> Blocks.AMETHYST_BLOCK)
        .defaultBlockstate()
        .lang("Unpolished Topaz Block")
        .tag(CrystalTags.Blocks.STORAGE, CrystalTags.Blocks.TOPAZ_BLOCK)
        .item()
        .lang("Unpolished Topaz Block")
        .tag(CrystalTags.Items.STORAGE, CrystalTags.Items.TOPAZ_BLOCK)
        .recipe((ctx, prov) -> prov.storage(
            () -> CrystalItems.TOPAZ_UNPOLISHED,
            RecipeCategory.BUILDING_BLOCKS,
            ctx::get
        ))
        .build()
        .register();

    public static final BlockEntry<Block> POLISHED_TOPAZ_BLOCK = ColorfulCrystals.REGISTRUM
        .object("polished_topaz_block")
        .block(Block::new)
        .initialProperties(() -> Blocks.AMETHYST_BLOCK)
        .defaultBlockstate()
        .lang("Polished Topaz Block")
        .tag(CrystalTags.Blocks.STORAGE, CrystalTags.Blocks.TOPAZ_BLOCK)
        .item()
        .lang("Polished Topaz Block")
        .tag(CrystalTags.Items.STORAGE, CrystalTags.Items.TOPAZ_BLOCK)
        .recipe((ctx, prov) -> prov.storage(
            () -> CrystalItems.TOPAZ_POLISHED,
            RecipeCategory.BUILDING_BLOCKS,
            ctx::get
        ))
        .build()
        .register();

    public static final BlockEntry<Block> UNPOLISHED_RUBY_BLOCK = ColorfulCrystals.REGISTRUM
        .object("unpolished_ruby_block")
        .block(Block::new)
        .initialProperties(() -> Blocks.AMETHYST_BLOCK)
        .defaultBlockstate()
        .lang("Unpolished Ruby Block")
        .tag(CrystalTags.Blocks.STORAGE, CrystalTags.Blocks.RUBY_BLOCK)
        .item()
        .lang("Unpolished Ruby Block")
        .tag(CrystalTags.Items.STORAGE, CrystalTags.Items.RUBY_BLOCK)
        .recipe((ctx, prov) -> prov.storage(
            () -> CrystalItems.RUBY_UNPOLISHED,
            RecipeCategory.BUILDING_BLOCKS,
            ctx::get
        ))
        .build()
        .register();

    public static final BlockEntry<Block> POLISHED_RUBY_BLOCK = ColorfulCrystals.REGISTRUM
        .object("polished_ruby_block")
        .block(Block::new)
        .initialProperties(() -> Blocks.AMETHYST_BLOCK)
        .defaultBlockstate()
        .lang("Polished Ruby Block")
        .tag(CrystalTags.Blocks.STORAGE, CrystalTags.Blocks.RUBY_BLOCK)
        .item()
        .lang("Polished Ruby Block")
        .tag(CrystalTags.Items.STORAGE, CrystalTags.Items.RUBY)
        .recipe((ctx, prov) -> prov.storage(
            () -> CrystalItems.RUBY_POLISHED,
            RecipeCategory.BUILDING_BLOCKS,
            ctx::get
        ))
        .build()
        .register();

    public static final BlockEntry<Block> Unpolished_SAPPHIRE_BLOCK = ColorfulCrystals.REGISTRUM
        .object("unpolished_sapphire_block")
        .block(Block::new)
        .initialProperties(() -> Blocks.AMETHYST_BLOCK)
        .defaultBlockstate()
        .lang("Unpolished Sapphire Block")
        .tag(CrystalTags.Blocks.STORAGE, CrystalTags.Blocks.SAPPHIRE_BLOCK)
        .item()
        .lang("Unpolished Sapphire Block")
        .tag(CrystalTags.Items.STORAGE, CrystalTags.Items.SAPPHIRE_BLOCK)
        .recipe((ctx, prov) -> prov.storage(
            () -> CrystalItems.SAPPHIRE_UNPOLISHED,
            RecipeCategory.BUILDING_BLOCKS,
            ctx::get
        ))
        .build()
        .register();

    public static final BlockEntry<Block> POLISHED_SAPPHIRE_BLOCK = ColorfulCrystals.REGISTRUM
        .object("polished_sapphire_block")
        .block(Block::new)
        .initialProperties(() -> Blocks.AMETHYST_BLOCK)
        .defaultBlockstate()
        .lang("Polished Sapphire Block")
        .tag(CrystalTags.Blocks.STORAGE, CrystalTags.Blocks.RUBY_BLOCK)
        .item()
        .lang("Polished Sapphire Block")
        .tag(CrystalTags.Items.STORAGE, CrystalTags.Items.RUBY)
        .recipe((ctx, prov) -> prov.storage(
            () -> CrystalItems.SAPPHIRE_POLISHED,
            RecipeCategory.BUILDING_BLOCKS,
            ctx::get
        ))
        .build()
        .register();

    //region utils
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
            .select(Direction.UP    , randomRotationMultiVariant(model, VariantMutator.Y_ROT).with(VariantMutator.X_ROT.withValue(Quadrant.R0  )))
            .select(Direction.DOWN  , randomRotationMultiVariant(model, VariantMutator.Y_ROT).with(VariantMutator.X_ROT.withValue(Quadrant.R180)))
            .select(Direction.NORTH , randomRotationMultiVariant(model, VariantMutator.Z_ROT).with(VariantMutator.X_ROT.withValue(Quadrant.R90 )))
            .select(Direction.SOUTH , randomRotationMultiVariant(model, VariantMutator.Z_ROT).with(VariantMutator.X_ROT.withValue(Quadrant.R270)))
            .select(Direction.EAST  , randomRotationMultiVariant(model, VariantMutator.Y_ROT).with(VariantMutator.Z_ROT.withValue(Quadrant.R90 )))
            .select(Direction.WEST  , randomRotationMultiVariant(model, VariantMutator.Y_ROT).with(VariantMutator.Z_ROT.withValue(Quadrant.R270)));
    }

    public static <T extends Block>
    NonNullSupplier<NonNullBiConsumer<DataGenContext<Block, T>, RegistrumBlockModelGenerator>>
    clusterBlockState(
        Identifier baseModel
    ) {
        //noinspection Convert2Lambda (have problem in dev server)
        return new NonNullSupplier<>() {
            @Override
            public NonNullBiConsumer<DataGenContext<Block, T>, RegistrumBlockModelGenerator> get() {
                return (ctx, gen) -> gen
                    .blockStateOutput.accept(MultiVariantGenerator.dispatch(ctx.get())
                        .with(propertyDispatchWithFacingAndRandomRotation(baseModel))
                    );
            }
        };
    }
    //endregion
}
