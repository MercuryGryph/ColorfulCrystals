package cn.mercury9.colorfulcrystals.recipe;

import cn.mercury9.colorfulcrystals.ColorfulCrystals;
import com.mojang.serialization.Codec;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import dev.anvilcraft.lib.v2.registrum.providers.generators.RegistrumRecipeProvider;
import net.minecraft.advancements.Advancement;
import net.minecraft.advancements.AdvancementRequirements;
import net.minecraft.advancements.AdvancementRewards;
import net.minecraft.advancements.criterion.RecipeUnlockedTrigger;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.ItemStackTemplate;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.PlacementInfo;
import net.minecraft.world.item.crafting.Recipe;
import net.minecraft.world.item.crafting.RecipeBookCategories;
import net.minecraft.world.item.crafting.RecipeBookCategory;
import net.minecraft.world.item.crafting.RecipeInput;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;


public record ItemBlockInteractionRecipe(
    Ingredient in,
    ItemStackTemplate out,
    int time,
    Block block
) implements Recipe<ItemBlockInteractionRecipe.Input> {
    public static final RecipeType<ItemBlockInteractionRecipe> TYPE = RecipeType.simple(ColorfulCrystals.identifier("item_block_interaction"));

    public static final MapCodec<ItemBlockInteractionRecipe> MAP_CODEC = RecordCodecBuilder.mapCodec(it -> it.group(
        Ingredient.CODEC.fieldOf("ingredient").forGetter(ItemBlockInteractionRecipe::in),
        ItemStackTemplate.CODEC.fieldOf("output").forGetter(ItemBlockInteractionRecipe::out),
        Codec.INT.fieldOf("time").forGetter(ItemBlockInteractionRecipe::time),
        Block.CODEC.fieldOf("block").forGetter(ItemBlockInteractionRecipe::block)
    ).apply(it, ItemBlockInteractionRecipe::new));

    public static final StreamCodec<RegistryFriendlyByteBuf, ItemBlockInteractionRecipe> STREAM_CODEC = StreamCodec.composite(
        Ingredient.CONTENTS_STREAM_CODEC,
        ItemBlockInteractionRecipe::in,
        ItemStackTemplate.STREAM_CODEC,
        ItemBlockInteractionRecipe::out,
        ByteBufCodecs.VAR_INT,
        ItemBlockInteractionRecipe::time,
        ByteBufCodecs.registry(Registries.BLOCK),
        ItemBlockInteractionRecipe::block,
        ItemBlockInteractionRecipe::new
    );

    public static final RecipeSerializer<ItemBlockInteractionRecipe> RECIPE_SERIALIZER = new RecipeSerializer<>(MAP_CODEC, STREAM_CODEC);

    @Override
    public boolean matches(Input input, Level level) {
        return in.test(input.in) && block == input.block;
    }

    @Override
    public ItemStack assemble(Input input) {
        return out.create();
    }

    @Override
    public boolean showNotification() {
        return false;
    }

    @Override
    public String group() {
        return "";
    }

    @Override
    public RecipeSerializer<? extends Recipe<Input>> getSerializer() {
        return RECIPE_SERIALIZER;
    }

    @Override
    public RecipeType<? extends Recipe<Input>> getType() {
        return TYPE;
    }

    @Override
    public PlacementInfo placementInfo() {
        return PlacementInfo.NOT_PLACEABLE;
    }

    @Override
    public RecipeBookCategory recipeBookCategory() {
        return RecipeBookCategories.CRAFTING_MISC;
    }

    public void save(Identifier id, RegistrumRecipeProvider output) {
        ResourceKey<Recipe<?>> resourceKey = ResourceKey.create(Registries.RECIPE, id);
        Advancement.Builder builder = output.advancement()
            .addCriterion("has_the_recipe", RecipeUnlockedTrigger.unlocked(resourceKey))
            .rewards(AdvancementRewards.Builder.recipe(resourceKey))
            .requirements(AdvancementRequirements.Strategy.OR);

        output.accept(resourceKey, this, builder.build(id));
    }

    public record Input(
        ItemStack in,
        Block block
    ) implements RecipeInput {
        @Override
        public ItemStack getItem(int index) {
            return in;
        }

        @Override
        public int size() {
            return 0;
        }
    }
}
