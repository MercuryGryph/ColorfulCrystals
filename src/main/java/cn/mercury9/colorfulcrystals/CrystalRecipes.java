package cn.mercury9.colorfulcrystals;

import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.item.crafting.RecipeType;
import net.neoforged.neoforge.registries.DeferredRegister;

public class CrystalRecipes {
    public static final DeferredRegister<RecipeType<?>> DR = DeferredRegister.create(BuiltInRegistries.RECIPE_TYPE, ColorfulCrystals.MOD_ID);
    public static final DeferredRegister<RecipeSerializer<?>> RECIPE_SERIALIZER_DR = DeferredRegister.create(BuiltInRegistries.RECIPE_SERIALIZER, ColorfulCrystals.MOD_ID);


}
