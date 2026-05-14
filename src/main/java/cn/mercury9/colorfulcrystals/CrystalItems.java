package cn.mercury9.colorfulcrystals;

import cn.mercury9.colorfulcrystals.item.RawGemItem;
import dev.anvilcraft.lib.v2.registrum.util.entry.ItemEntry;
import net.minecraft.resources.Identifier;
import net.minecraft.world.item.Item;

@SuppressWarnings("unused")
public class CrystalItems {
    static {
        ColorfulCrystals.REGISTRUM.defaultCreativeTab(CrystalCreativeTabs.TAB.getKey());
    }

    protected static void setupRegistration() {}


    public static final ItemEntry<Item> RUBY_POLISHED = ColorfulCrystals.REGISTRUM
        .object("polished_ruby")
        .item(Item::new)
        .lang("Polished Ruby")
        .defaultModel()
        .tag(CrystalTags.Items.GEMS, CrystalTags.Items.RUBY)
        .register();

    public static final ItemEntry<RawGemItem> RUBY_RAW = ColorfulCrystals.REGISTRUM
        .object("raw_ruby")
        .item(RawGemItem.factory(RUBY_POLISHED))
        .lang("Raw Ruby")
        .defaultModel()
        .tag(CrystalTags.Items.GEMS, CrystalTags.Items.RUBY)
        .register();


    public static final ItemEntry<Item> TOPAZ_POLISHED = ColorfulCrystals.REGISTRUM
        .object("polished_topaz")
        .item(Item::new)
        .lang("Polished Topaz")
        .defaultModel()
        .tag(CrystalTags.Items.GEMS, CrystalTags.Items.TOPAZ)
        .register();

    public static final ItemEntry<RawGemItem> TOPAZ_RAW = ColorfulCrystals.REGISTRUM
        .object("raw_topaz")
        .item(RawGemItem.factory(TOPAZ_POLISHED))
        .lang("Raw Topaz")
        .defaultModel()
        .tag(CrystalTags.Items.GEMS, CrystalTags.Items.TOPAZ)
        .register();


    public static final ItemEntry<Item> SAPPHIRE_POLISHED = ColorfulCrystals.REGISTRUM
        .object("polished_sapphire")
        .item(Item::new)
        .lang("Polished Sapphire")
        .defaultModel()
        .tag(CrystalTags.Items.GEMS, CrystalTags.Items.SAPPHIRE)
        .register();

    public static final ItemEntry<RawGemItem> SAPPHIRE_RAW = ColorfulCrystals.REGISTRUM
        .object("raw_sapphire")
        .item(RawGemItem.factory(SAPPHIRE_POLISHED))
        .lang("Sapphire Topaz")
        .defaultModel()
        .tag(CrystalTags.Items.GEMS, CrystalTags.Items.SAPPHIRE)
        .register();


    public static Identifier identifier(String id) {
        return ColorfulCrystals.identifier("item/" + id);
    }
}
