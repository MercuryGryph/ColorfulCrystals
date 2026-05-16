package cn.mercury9.colorfulcrystals.block.builder;

import cn.mercury9.colorfulcrystals.CrystalTags;
import dev.anvilcraft.lib.v2.registrum.Registrum;
import dev.anvilcraft.lib.v2.registrum.util.entry.BlockEntry;
import dev.anvilcraft.lib.v2.registrum.util.entry.ItemEntry;
import net.minecraft.data.recipes.RecipeCategory;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;

public class GemStorageBuilder<T extends Block> extends CrystalBlockEntryBuilder<T, GemStorageBuilder<T>> {
    public GemStorageBuilder(Registrum registrum) {
        super(registrum);
    }

    protected ItemEntry<? extends Item> gem;

    @Override
    public GemStorageBuilder<T> reset() {
        this.gem = null;
        return super.reset();
    }

    //region setter

    public GemStorageBuilder<T> gem(ItemEntry<? extends Item> gem) {
        this.gem = gem;
        return this;
    }

    //endregion

    @Override
    protected void beforeBuildCheckNullValue() {
        super.beforeBuildCheckNullValue();
        if (this.gem == null) {
            throw new NullPointerException(this + ".gem is null");
        }
    }

    @Override
    protected BlockEntry<T> actualBuild() {
        TagKey<Block>[] blockTags = this.blockTag.toArray(new TagKey[1]);

        TagKey<Item>[] itemTags = this.itemTag.toArray(new TagKey[1]);

        final var gem = this.gem;

        return registrum
            .object(id)
            .block(block)
            .initialProperties(() -> Blocks.AMETHYST_BLOCK)
            .defaultBlockstate()
            .lang(name)
            .tag(CrystalTags.Blocks.STORAGE, BlockTags.MINEABLE_WITH_PICKAXE)
            .tag(blockTags)
            .item()
            .lang(name)
            .tag(CrystalTags.Items.STORAGE)
            .tag(itemTags)
            .recipe((ctx, prov) -> prov.storage(
                gem,
                RecipeCategory.BUILDING_BLOCKS,
                ctx::get
            ))
            .build()
            .register();
    }
}
