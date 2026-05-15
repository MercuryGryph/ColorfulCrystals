package cn.mercury9.colorfulcrystals;

import cn.mercury9.colorfulcrystals.block.CrystalBudBlock;
import com.mojang.math.Quadrant;
import dev.anvilcraft.lib.v2.registrum.Registrum;
import dev.anvilcraft.lib.v2.registrum.providers.DataGenContext;
import dev.anvilcraft.lib.v2.registrum.providers.generators.RegistrumBlockModelGenerator;
import dev.anvilcraft.lib.v2.registrum.util.entry.BlockEntry;
import dev.anvilcraft.lib.v2.registrum.util.entry.ItemEntry;
import dev.anvilcraft.lib.v2.util.nullness.NonNullBiConsumer;
import dev.anvilcraft.lib.v2.util.nullness.NonNullFunction;
import dev.anvilcraft.lib.v2.util.nullness.NonNullSupplier;
import net.minecraft.client.data.models.MultiVariant;
import net.minecraft.client.data.models.blockstates.MultiVariantGenerator;
import net.minecraft.client.data.models.blockstates.PropertyDispatch;
import net.minecraft.client.renderer.block.dispatch.Variant;
import net.minecraft.client.renderer.block.dispatch.VariantMutator;
import net.minecraft.core.Direction;
import net.minecraft.data.recipes.RecipeCategory;
import net.minecraft.resources.Identifier;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.TagKey;
import net.minecraft.util.random.Weighted;
import net.minecraft.util.random.WeightedList;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;

@SuppressWarnings("unused")
public class CrystalBlocks {
    static {
        ColorfulCrystals.REGISTRUM.defaultCreativeTab(CrystalCreativeTabs.TAB.getKey());
    }

    protected static void setupRegistration() {}

    //region cluster

    public static final BlockEntry<CrystalBudBlock> TOPAZ_CLUSTER = createGemCluster(
        ColorfulCrystals.REGISTRUM,
        CrystalBudBlock.factory(8f ,8f),
        "topaz_cluster",
        "Topaz Cluster",
        identifier("topaz_cluster"),
        CrystalItems.TOPAZ_UNPOLISHED,
        new TagKey[]{CrystalTags.Blocks.TOPAZ_CLUSTER},
        new TagKey[]{CrystalTags.Items.TOPAZ_CLUSTER}
    );

    public static final BlockEntry<CrystalBudBlock> RUBY_CLUSTER = createGemCluster(
        ColorfulCrystals.REGISTRUM,
        CrystalBudBlock.factory(6f, 8f),
        "ruby_cluster",
        "Ruby Cluster",
        identifier("ruby_cluster"),
        CrystalItems.RUBY_UNPOLISHED,
        new TagKey[]{CrystalTags.Blocks.RUBY_CLUSTER},
        new TagKey[]{CrystalTags.Items.RUBY_CLUSTER}
    );

    public static final BlockEntry<CrystalBudBlock> SAPPHIRE_CLUSTER = createGemCluster(
        ColorfulCrystals.REGISTRUM,
        CrystalBudBlock.factory(6f, 8f),
        "sapphire_cluster",
        "Sapphire Cluster",
        identifier("sapphire_cluster"),
        CrystalItems.SAPPHIRE_UNPOLISHED,
        new TagKey[]{CrystalTags.Blocks.RUBY_CLUSTER},
        new TagKey[]{CrystalTags.Items.RUBY_CLUSTER}
    );

    //endregion

    //region gem ore

    public static final BlockEntry<Block> TOPAZ_ORE = createGemOre(
        ColorfulCrystals.REGISTRUM,
        Block::new,
        "topaz_ore",
        "Topaz Ore",
        identifier("topaz_ore"),
        CrystalItems.TOPAZ_RAW,
        new TagKey[]{CrystalTags.Blocks.TOPAZ_ORE},
        new TagKey[]{CrystalTags.Items.TOPAZ_ORE}
    );

    public static final BlockEntry<Block> RUBY_ORE = createGemOre(
        ColorfulCrystals.REGISTRUM,
        Block::new,
        "ruby_ore",
        "Ruby Ore",
        identifier("ruby_ore"),
        CrystalItems.RUBY_RAW,
        new TagKey[]{CrystalTags.Blocks.RUBY_ORE},
        new TagKey[]{CrystalTags.Items.RUBY_ORE}
    );

    public static final BlockEntry<Block> SAPPHIRE_ORE = createGemOre(
        ColorfulCrystals.REGISTRUM,
        Block::new,
        "sapphire_ore",
        "Sapphire Ore",
        identifier("sapphire_ore"),
        CrystalItems.SAPPHIRE_RAW,
        new TagKey[]{CrystalTags.Blocks.SAPPHIRE_ORE},
        new TagKey[]{CrystalTags.Items.SAPPHIRE_ORE}
    );

    //endregion

    //region gem block

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
        .tag(CrystalTags.Items.STORAGE, CrystalTags.Items.RUBY_BLOCK)
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
        .tag(CrystalTags.Blocks.STORAGE, CrystalTags.Blocks.SAPPHIRE_BLOCK)
        .item()
        .lang("Polished Sapphire Block")
        .tag(CrystalTags.Items.STORAGE, CrystalTags.Items.SAPPHIRE_BLOCK)
        .recipe((ctx, prov) -> prov.storage(
            () -> CrystalItems.SAPPHIRE_POLISHED,
            RecipeCategory.BUILDING_BLOCKS,
            ctx::get
        ))
        .build()
        .register();

    //endregion

    //region utils
    public static Identifier identifier(String id) {
        return ColorfulCrystals.identifier("block/" + id);
    }

    public static <T extends Block> BlockEntry<T> createGemOre(
        Registrum registrum,
        NonNullFunction<BlockBehaviour.Properties, T> factory,
        String id,
        String lang,
        Identifier texture,
        ItemEntry<? extends Item> drop,
        TagKey<Block>[] additionalTags,
        TagKey<Item>[] additionalItemTags
    ) {
        return registrum
            .object(id)
            .block(factory)
            .initialProperties(() -> Blocks.IRON_ORE)
            .blockstate(gemOreState(texture))
            .lang(lang)
            .loot((tables, block) -> {
                tables.add(block, tables.createOreDrop(block, drop.asItem()));
            })
            .tag(additionalTags)
            .tag(CrystalTags.Blocks.GEM_ORE, BlockTags.MINEABLE_WITH_PICKAXE)
            .item()
            .lang(lang)
            .tag(CrystalTags.Items.GEM_ORE)
            .tag(additionalItemTags)
            .build()
            .register();
    }

    public static <T extends Block> BlockEntry<T> createGemCluster(
        Registrum registrum,
        NonNullFunction<BlockBehaviour.Properties, T> factory,
        String id,
        String lang,
        Identifier model,
        ItemEntry<? extends Item> drop,
        TagKey<Block>[] additionalTags,
        TagKey<Item>[] additionalItemTags
    ) {
        return registrum
            .object(id)
            .block(factory)
            .initialProperties(() -> Blocks.AMETHYST_CLUSTER)
            .blockstate(clusterBlockState(model))
            .lang(lang)
            .loot((tables, block) -> {
                tables.dropSelf(block);
                tables.createOreDrop(block, drop.asItem());
            })
            .tag(CrystalTags.Blocks.CLUSTERS)
            .tag(additionalTags)
            .item()
            .lang(lang)
            .tag(CrystalTags.Items.CLUSTERS)
            .tag(additionalItemTags)
            .build()
            .register();
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
    gemOreState(
        Identifier oreTexture
    ) {
        //noinspection Convert2Lambda (have problem in dev server)
        return new NonNullSupplier<>() {
            @Override
            public NonNullBiConsumer<DataGenContext<Block, T>, RegistrumBlockModelGenerator> get() {
                return (ctx, gen) -> gen.create(
                    ctx.get(),
                    gen.withParent(CrystalModelTemplates.TEMPLATE_GEM_ORE)
                        .texture(CrystalModelTemplates.GEM_ORE_GEM, oreTexture)
                        .build(ctx.get())
                );
            }
        };
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
