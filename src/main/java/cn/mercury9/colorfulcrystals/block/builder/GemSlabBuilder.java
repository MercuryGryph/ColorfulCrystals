package cn.mercury9.colorfulcrystals.block.builder;

import dev.anvilcraft.lib.v2.registrum.Registrum;
import dev.anvilcraft.lib.v2.registrum.providers.DataGenContext;
import dev.anvilcraft.lib.v2.registrum.providers.generators.RegistrumBlockModelGenerator;
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
import net.minecraft.world.level.block.SlabBlock;
import org.jspecify.annotations.NonNull;

public class GemSlabBuilder<T extends SlabBlock> extends CrystalBlockEntryBuilder<T, GemSlabBuilder<T>> {
    public GemSlabBuilder(Registrum registrum) {
        super(registrum);
    }

    protected BlockEntry<? extends Block> sourceBlock;
    protected Identifier texture;

    @Override
    public GemSlabBuilder<T> reset() {
        this.sourceBlock = null;
        return super.reset();
    }

    //region setter

    public GemSlabBuilder<T> sourceBlock(BlockEntry<? extends Block> sourceBlock) {
        this.sourceBlock = sourceBlock;
        return self();
    }

    public GemSlabBuilder<T> texture(Identifier texture) {
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
            .loot((tables, block) -> {
                tables.add(block, tables.createSlabItemTable(block));
            })
            .item()
            .lang(name)
            .tag(ItemTags.SLABS)
            .tag(itemTags)
            .recipe((ctx, prov) -> prov.slab(
                RecipeCategory.BUILDING_BLOCKS, ctx.get(), sourceBlock
            ))
            .build()
            .register();
    }

    public static <T extends SlabBlock>
    NonNullSupplier<NonNullBiConsumer<DataGenContext<Block, T>, RegistrumBlockModelGenerator>>
    gemSlabState(Identifier texture) {
        return new NonNullSupplier<>() {
            @Override
            @NonNull
            public NonNullBiConsumer<DataGenContext<Block, T>, RegistrumBlockModelGenerator> get() {
                return (ctx, gen) -> {
                    final var slab = ctx.get();
                    final var bottomModel = gen.withParent(ModelTemplates.SLAB_BOTTOM)
                        .texture(TextureSlot.TOP, texture)
                        .texture(TextureSlot.BOTTOM, texture)
                        .texture(TextureSlot.SIDE, texture)
                        .build(slab);
                    final var topModel = gen.withParent(ModelTemplates.SLAB_TOP)
                        .texture(TextureSlot.TOP, texture)
                        .texture(TextureSlot.BOTTOM, texture)
                        .texture(TextureSlot.SIDE, texture)
                        .suffix("_top")
                        .build(slab);

                    final var fullModel = ctx.getId().withPrefix("block/").withPath(path -> path.replace("_slab", "_block"));

                    gen.blockStateOutput.accept(BlockModelGenerators.createSlab(
                        slab,
                        BlockModelGenerators.plainVariant(bottomModel),
                        BlockModelGenerators.plainVariant(topModel),
                        BlockModelGenerators.plainVariant(fullModel)
                    ));
                    gen.registerSimpleItemModel(slab, bottomModel);
                };
            }
        };
    }
}
