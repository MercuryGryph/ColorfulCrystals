package cn.mercury9.colorfulcrystals;

import net.minecraft.client.data.models.model.TextureSlot;
import net.neoforged.neoforge.client.model.generators.template.ExtendedModelTemplate;
import net.neoforged.neoforge.client.model.generators.template.ExtendedModelTemplateBuilder;

public class CrystalModelTemplates {
    public static final TextureSlot GEM_ORE_GEM = TextureSlot.create("gem");

    public static final ExtendedModelTemplate TEMPLATE_GEM_ORE = ExtendedModelTemplateBuilder.builder()
        .parent(CrystalBlocks.identifier("template_gem_ore"))
        .requiredTextureSlot(GEM_ORE_GEM)
        .build();
}
