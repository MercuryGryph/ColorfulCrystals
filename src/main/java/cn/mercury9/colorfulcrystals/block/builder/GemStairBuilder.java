package cn.mercury9.colorfulcrystals.block.builder;

import dev.anvilcraft.lib.v2.registrum.Registrum;
import dev.anvilcraft.lib.v2.registrum.providers.DataGenContext;
import dev.anvilcraft.lib.v2.registrum.providers.generators.RegistrumBlockModelGenerator;
import dev.anvilcraft.lib.v2.registrum.util.DataIngredient;
import dev.anvilcraft.lib.v2.registrum.util.entry.BlockEntry;
import dev.anvilcraft.lib.v2.util.nullness.NonNullBiConsumer;
import dev.anvilcraft.lib.v2.util.nullness.NonNullSupplier;
import net.minecraft.client.data.models.BlockModelGenerators;
import net.minecraft.client.data.models.model.ModelTemplates;
import net.minecraft.client.data.models.model.TextureSlot;
import net.minecraft.data.recipes.RecipeCategory;
import net.minecraft.resources.Identifier;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.StairBlock;
import org.jspecify.annotations.NonNull;

public class GemStairBuilder<T extends StairBlock> extends CrystalBlockEntryBuilder<T, GemStairBuilder<T>> {
    public GemStairBuilder(Registrum registrum) {
        super(registrum);
    }

    protected BlockEntry<? extends Block> sourceBlock;
    protected Identifier texture;

    @Override
    public GemStairBuilder<T> reset() {
        this.sourceBlock = null;
        return super.reset();
    }

    //region setter

    public GemStairBuilder<T> sourceBlock(BlockEntry<? extends Block> sourceBlock) {
        this.sourceBlock = sourceBlock;
        return self();
    }

    public GemStairBuilder<T> texture(Identifier texture) {
        this.texture = texture;
        return self();
    }

    //endregion

    @Override
    protected void beforeBuildCheckNullValue() {
        super.beforeBuildCheckNullValue();
        if (sourceBlock == null) {
            throw new NullPointerException(this + ".sourceBlock is null");
        }
    }

    @Override
    protected BlockEntry<T> actualBuild() {
        TagKey<Block>[] blockTags = this.blockTag.toArray(new TagKey[0]);
        TagKey<Item>[] itemTags = this.itemTag.toArray(new TagKey[0]);
        final var sourceBlock = this.sourceBlock;
        final var texture = this.texture;

        return registrum
            .object(id)
            .block(block)
            .initialProperties(sourceBlock)
            .blockstate(gemSlabState(texture))
            .lang(name)
            .tag(BlockTags.SLABS, BlockTags.MINEABLE_WITH_PICKAXE)
            .tag(blockTags)
            .item()
            .lang(name)
            .tag(ItemTags.SLABS)
            .tag(itemTags)
            .recipe((ctx, prov) -> {
                prov.stairs(
                    DataIngredient.items(sourceBlock.asItem()),
                    RecipeCategory.BUILDING_BLOCKS,
                    ctx,
                    null,
                    true
                );
            })
            .build()
            .register();
    }

    public static <T extends StairBlock>
    NonNullSupplier<NonNullBiConsumer<DataGenContext<Block, T>, RegistrumBlockModelGenerator>>
    gemSlabState(Identifier texture) {
        return new NonNullSupplier<>() {
            @Override
            @NonNull
            public NonNullBiConsumer<DataGenContext<Block, T>, RegistrumBlockModelGenerator> get() {
                return (ctx, gen) -> {
                    final var stair = ctx.get();
                    final var stairInnerModel = gen.withParent(ModelTemplates.STAIRS_INNER)
                        .texture(TextureSlot.TOP, texture)
                        .texture(TextureSlot.BOTTOM, texture)
                        .texture(TextureSlot.SIDE, texture)
                        .suffix("_inner")
                        .build(ctx.get());
                    final var stairOuterModel = gen.withParent(ModelTemplates.STAIRS_OUTER)
                        .texture(TextureSlot.TOP, texture)
                        .texture(TextureSlot.BOTTOM, texture)
                        .texture(TextureSlot.SIDE, texture)
                        .suffix("_outer")
                        .build(ctx.get());
                    final var stairModel = gen.withParent(ModelTemplates.STAIRS_STRAIGHT)
                        .texture(TextureSlot.TOP, texture)
                        .texture(TextureSlot.BOTTOM, texture)
                        .texture(TextureSlot.SIDE, texture)
                        .build(ctx.get());
                    gen.blockStateOutput.accept(BlockModelGenerators.createStairs(
                        stair,
                        BlockModelGenerators.plainVariant(stairInnerModel),
                        BlockModelGenerators.plainVariant(stairModel),
                        BlockModelGenerators.plainVariant(stairOuterModel)
                    ));
                    gen.registerSimpleItemModel(stair, stairModel);
                };
            }
        };
    }
}
