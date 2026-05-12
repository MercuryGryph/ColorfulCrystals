package cn.mercury9.colorfulcrystals;

import net.minecraft.core.registries.Registries;
import net.minecraft.world.item.CreativeModeTab;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

public class CrystalCreativeTabs {
    public static final DeferredRegister<CreativeModeTab> DR = DeferredRegister.create(
        Registries.CREATIVE_MODE_TAB,
        ColorfulCrystals.MOD_ID
    );

    public static final DeferredHolder<CreativeModeTab, CreativeModeTab> TAB = DR.register(
        "tab",
        () -> CreativeModeTab.builder()
            .title(ColorfulCrystals.REGISTRUM.addLang("itemGroup", ColorfulCrystals.identifier("tab"), "Colorful Crystals"))
            .icon(CrystalBlocks.TOPAZ_CLUSTER.asItem()::getDefaultInstance)
            .build()
        );
}
