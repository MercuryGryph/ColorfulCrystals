package cn.mercury9.colorfulcrystals.block.builder;

import cn.mercury9.colorfulcrystals.CrystalTags;
import dev.anvilcraft.lib.v2.registrum.Registrum;
import dev.anvilcraft.lib.v2.registrum.providers.DataGenContext;
import dev.anvilcraft.lib.v2.registrum.providers.generators.RegistrumBlockModelGenerator;
import dev.anvilcraft.lib.v2.registrum.util.entry.BlockEntry;
import dev.anvilcraft.lib.v2.registrum.util.entry.ItemEntry;
import dev.anvilcraft.lib.v2.util.nullness.NonNullBiConsumer;
import dev.anvilcraft.lib.v2.util.nullness.NonNullSupplier;
import net.minecraft.client.data.models.blockstates.MultiVariantGenerator;
import net.minecraft.resources.Identifier;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import org.jspecify.annotations.NonNull;

public class ClusterEntryBuilder<T extends Block> extends CrystalBlockEntryBuilder<T, ClusterEntryBuilder<T>> {
    public ClusterEntryBuilder(Registrum registrum) {
        super(registrum);
    }

    protected Identifier model;
    protected ItemEntry<? extends Item> drop;

    @Override
    public ClusterEntryBuilder<T> reset() {
        this.model = null;
        this.drop = null;
        return super.reset();
    }

    //region setter

    public ClusterEntryBuilder<T> model(Identifier model) {
        this.model = model;
        return self();
    }

    public ClusterEntryBuilder<T> drop(ItemEntry<? extends Item> drop) {
        this.drop = drop;
        return self();
    }

    //endregion

    @Override
    protected void beforeBuildCheckNullValue() {
        super.beforeBuildCheckNullValue();
        if (model == null) {
            throw new NullPointerException(this + ".model is null");
        }
        if (drop == null) {
            throw new NullPointerException(this + ".drop is null");
        }
    }

    @Override
    protected BlockEntry<T> actualBuild() {
        TagKey<Block>[] blockTags = this.blockTag.toArray(new TagKey[1]);

        TagKey<Item>[] itemTags = this.itemTag.toArray(new TagKey[1]);

        final var drop = this.drop;

        return registrum
            .object(id)
            .block(block)
            .initialProperties(() -> Blocks.AMETHYST_CLUSTER)
            .blockstate(clusterBlockState(model))
            .lang(name)
            .loot((tables, block) -> {
                tables.add(block, tables.createOreDrop(block, drop.asItem()));
            })
            .tag(CrystalTags.Blocks.CLUSTERS, BlockTags.MINEABLE_WITH_PICKAXE)
            .tag(blockTags)
            .item()
            .lang(name)
            .tag(CrystalTags.Items.CLUSTERS)
            .tag(itemTags)
            .build()
            .register();
    }

    public static <T extends Block>
    NonNullSupplier<NonNullBiConsumer<DataGenContext<Block, T>, RegistrumBlockModelGenerator>>
    clusterBlockState(
        Identifier baseModel
    ) {
        return new NonNullSupplier<>() {
            @Override
            public @NonNull NonNullBiConsumer<DataGenContext<Block, T>, RegistrumBlockModelGenerator> get() {
                return (ctx, gen) -> gen
                    .blockStateOutput.accept(MultiVariantGenerator.dispatch(ctx.get())
                        .with(propertyDispatchWithFacingAndRandomRotation(baseModel))
                    );
            }
        };
    }
}
