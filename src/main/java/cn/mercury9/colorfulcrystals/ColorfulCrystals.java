package cn.mercury9.colorfulcrystals;

import com.mojang.logging.LogUtils;
import dev.anvilcraft.lib.v2.registrum.Registrum;
import net.minecraft.resources.Identifier;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.common.Mod;
import org.slf4j.Logger;

@Mod(ColorfulCrystals.MOD_ID)
public class ColorfulCrystals {
    public static final String MOD_ID = "colorful_crystals";
    public static final Logger LOGGER = LogUtils.getLogger();
    public static final Registrum REGISTRUM = Registrum.create(MOD_ID);

    public ColorfulCrystals(IEventBus modEventBus, ModContainer modContainer) {
        setupRegistration(modEventBus);
    }

    private void setupRegistration(IEventBus modEventBus) {
        CrystalBlocks.setupRegistration();
    }

    public static Identifier identifier(String path) {
        return Identifier.fromNamespaceAndPath(MOD_ID, path);
    }
}
