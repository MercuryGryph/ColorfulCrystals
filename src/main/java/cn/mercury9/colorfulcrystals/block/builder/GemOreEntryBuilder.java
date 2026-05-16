package cn.mercury9.colorfulcrystals.block.builder;

import cn.mercury9.colorfulcrystals.CrystalModelTemplates;
import cn.mercury9.colorfulcrystals.CrystalTags;
import dev.anvilcraft.lib.v2.registrum.Registrum;
import dev.anvilcraft.lib.v2.registrum.providers.DataGenContext;
import dev.anvilcraft.lib.v2.registrum.providers.generators.RegistrumBlockModelGenerator;
import dev.anvilcraft.lib.v2.registrum.util.entry.BlockEntry;
import dev.anvilcraft.lib.v2.registrum.util.entry.ItemEntry;
import dev.anvilcraft.lib.v2.util.nullness.NonNullBiConsumer;
import dev.anvilcraft.lib.v2.util.nullness.NonNullSupplier;
import net.minecraft.resources.Identifier;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import org.jspecify.annotations.NonNull;

public class GemOreEntryBuilder<T extends Block> extends CrystalBlockEntryBuilder<T, GemOreEntryBuilder<T>> {
    public GemOreEntryBuilder(Registrum registrum) {
        super(registrum);
    }

    protected Identifier texture;
    protected ItemEntry<? extends Item> drop;

    @Override
    public GemOreEntryBuilder<T> reset() {
        this.texture = null;
        this.drop = null;
        super.reset();
        return self();
    }

    //region setter

    public GemOreEntryBuilder<T> texture(Identifier texture) {
        this.texture = texture;
        return self();
    }

    public GemOreEntryBuilder<T> drop(ItemEntry<? extends Item> drop) {
        this.drop = drop;
        return self();
    }

    //endregion


    @Override
    protected void beforeBuildCheckNullValue() {
        super.beforeBuildCheckNullValue();
        if (this.texture == null) {
            throw new NullPointerException(this + ".texture is null");
        }
        if (this.drop == null) {
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
            .initialProperties(() -> Blocks.IRON_ORE)
            .blockstate(gemOreState(texture))
            .lang(name)
            .loot((tables, block) -> {
                tables.add(block, tables.createOreDrop(block, drop.asItem()));
            })
            .tag(blockTags)
            .tag(CrystalTags.Blocks.GEM_ORE, BlockTags.MINEABLE_WITH_PICKAXE)
            .item()
            .lang(name)
            .tag(CrystalTags.Items.GEM_ORE)
            .tag(itemTags)
            .build()
            .register();
    }

    public static <T extends Block>
    NonNullSupplier<NonNullBiConsumer<DataGenContext<Block, T>, RegistrumBlockModelGenerator>>
    gemOreState(
        Identifier oreTexture
    ) {
        return new NonNullSupplier<>() {
            @Override
            public @NonNull NonNullBiConsumer<DataGenContext<Block, T>, RegistrumBlockModelGenerator> get() {
                return (ctx, gen) -> gen.create(
                    ctx.get(),
                    gen.withParent(CrystalModelTemplates.TEMPLATE_GEM_ORE)
                        .texture(CrystalModelTemplates.GEM_ORE_GEM, oreTexture)
                        .build(ctx.get())
                );
            }
        };
    }
}
