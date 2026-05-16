package cn.mercury9.colorfulcrystals;

import cn.mercury9.colorfulcrystals.block.CrystalBudBlock;
import cn.mercury9.colorfulcrystals.block.builder.ClusterEntryBuilder;
import cn.mercury9.colorfulcrystals.block.builder.GemOreEntryBuilder;
import cn.mercury9.colorfulcrystals.block.builder.GemStorageBuilder;
import dev.anvilcraft.lib.v2.registrum.util.entry.BlockEntry;
import net.minecraft.resources.Identifier;
import net.minecraft.world.level.block.Block;

@SuppressWarnings("unused")
public class CrystalBlocks {
    static {
        ColorfulCrystals.REGISTRUM.defaultCreativeTab(CrystalCreativeTabs.TAB.getKey());
    }

    protected static void setupRegistration() {}

    private static final ClusterEntryBuilder<CrystalBudBlock> CLUSTER_BUILDER = new ClusterEntryBuilder<>(ColorfulCrystals.REGISTRUM);

    private static final GemOreEntryBuilder<Block> GEM_ORE_BUILDER = new GemOreEntryBuilder<>(ColorfulCrystals.REGISTRUM);

    private static final GemStorageBuilder<Block> GEM_STORAGE_BUILDER = new GemStorageBuilder<>(ColorfulCrystals.REGISTRUM);

    //region cluster

    public static final BlockEntry<CrystalBudBlock> TOPAZ_CLUSTER = CLUSTER_BUILDER
        .block(CrystalBudBlock.factory(8f, 8f))
        .id("topaz_cluster")
        .name("Topaz Cluster")
        .model(identifier("topaz_cluster"))
        .drop(CrystalItems.TOPAZ_UNPOLISHED)
        .addBlockTag(CrystalTags.Blocks.TOPAZ_CLUSTER)
        .addItemTag(CrystalTags.Items.TOPAZ_CLUSTER)
        .build();

    public static final BlockEntry<CrystalBudBlock> RUBY_CLUSTER = CLUSTER_BUILDER
        .block(CrystalBudBlock.factory(6f, 8f))
        .id("ruby_cluster")
        .name("Ruby Cluster")
        .model(identifier("ruby_cluster"))
        .drop(CrystalItems.RUBY_UNPOLISHED)
        .addBlockTag(CrystalTags.Blocks.RUBY_CLUSTER)
        .addItemTag(CrystalTags.Items.RUBY_CLUSTER)
        .build();

    public static final BlockEntry<CrystalBudBlock> SAPPHIRE_CLUSTER = CLUSTER_BUILDER
        .block(CrystalBudBlock.factory(6f, 8f))
        .id("sapphire_cluster")
        .name("Sapphire Cluster")
        .model(identifier("sapphire_cluster"))
        .drop(CrystalItems.SAPPHIRE_UNPOLISHED)
        .addBlockTag(CrystalTags.Blocks.SAPPHIRE_CLUSTER)
        .addItemTag(CrystalTags.Items.SAPPHIRE_CLUSTER)
        .build();

    //endregion

    //region gem ore

    public static final BlockEntry<Block> TOPAZ_ORE = GEM_ORE_BUILDER
        .block(Block::new)
        .id("topaz_ore")
        .name("Topaz Ore")
        .texture(identifier("topaz_ore"))
        .drop(CrystalItems.TOPAZ_RAW)
        .addBlockTag(CrystalTags.Blocks.TOPAZ_ORE)
        .addItemTag(CrystalTags.Items.TOPAZ_ORE)
        .build();

    public static final BlockEntry<Block> RUBY_ORE = GEM_ORE_BUILDER
        .block(Block::new)
        .id("ruby_ore")
        .name("Ruby Ore")
        .texture(identifier("ruby_ore"))
        .drop(CrystalItems.RUBY_RAW)
        .addBlockTag(CrystalTags.Blocks.RUBY_ORE)
        .addItemTag(CrystalTags.Items.RUBY_ORE)
        .build();

    public static final BlockEntry<Block> SAPPHIRE_ORE = GEM_ORE_BUILDER
        .block(Block::new)
        .id("sapphire_ore")
        .name("Sapphire Ore")
        .texture(identifier("sapphire_ore"))
        .drop(CrystalItems.SAPPHIRE_RAW)
        .addBlockTag(CrystalTags.Blocks.SAPPHIRE_ORE)
        .addItemTag(CrystalTags.Items.SAPPHIRE_ORE)
        .build();

    //endregion

    //region gem block

    public static final BlockEntry<Block> UNPOLISHED_TOPAZ_BLOCK = GEM_STORAGE_BUILDER
        .block(Block::new)
        .id("unpolished_topaz_block")
        .name("Unpolished Topaz Block")
        .gem(CrystalItems.TOPAZ_UNPOLISHED)
        .addBlockTag(CrystalTags.Blocks.TOPAZ_BLOCK)
        .addItemTag(CrystalTags.Items.TOPAZ_BLOCK)
        .build();

    public static final BlockEntry<Block> POLISHED_TOPAZ_BLOCK = GEM_STORAGE_BUILDER
        .block(Block::new)
        .id("polished_topaz_block")
        .name("Polished Topaz Block")
        .gem(CrystalItems.TOPAZ_POLISHED)
        .addBlockTag(CrystalTags.Blocks.TOPAZ_BLOCK)
        .addItemTag(CrystalTags.Items.TOPAZ_BLOCK)
        .build();

    public static final BlockEntry<Block> UNPOLISHED_RUBY_BLOCK = GEM_STORAGE_BUILDER
        .block(Block::new)
        .id("unpolished_ruby_block")
        .name("Unpolished Ruby Block")
        .gem(CrystalItems.RUBY_UNPOLISHED)
        .addBlockTag(CrystalTags.Blocks.RUBY_BLOCK)
        .addItemTag(CrystalTags.Items.RUBY_BLOCK)
        .build();

    public static final BlockEntry<Block> POLISHED_RUBY_BLOCK = GEM_STORAGE_BUILDER
        .block(Block::new)
        .id("polished_ruby_block")
        .name("Polished Ruby Block")
        .gem(CrystalItems.RUBY_POLISHED)
        .addBlockTag(CrystalTags.Blocks.RUBY_BLOCK)
        .addItemTag(CrystalTags.Items.RUBY_BLOCK)
        .build();

    public static final BlockEntry<Block> Unpolished_SAPPHIRE_BLOCK = GEM_STORAGE_BUILDER
        .block(Block::new)
        .id("unpolished_sapphire_block")
        .name("Unpolished Sapphire Block")
        .gem(CrystalItems.SAPPHIRE_UNPOLISHED)
        .addBlockTag(CrystalTags.Blocks.SAPPHIRE_BLOCK)
        .addItemTag(CrystalTags.Items.SAPPHIRE_BLOCK)
        .build();

    public static final BlockEntry<Block> POLISHED_SAPPHIRE_BLOCK = GEM_STORAGE_BUILDER
        .block(Block::new)
        .id("polished_sapphire_block")
        .name("Polished Sapphire Block")
        .gem(CrystalItems.SAPPHIRE_POLISHED)
        .addBlockTag(CrystalTags.Blocks.SAPPHIRE_BLOCK)
        .addItemTag(CrystalTags.Items.SAPPHIRE_BLOCK)
        .build();

    //endregion

    //region utils

    public static Identifier identifier(String id) {
        return ColorfulCrystals.identifier("block/" + id);
    }

    //endregion
}
