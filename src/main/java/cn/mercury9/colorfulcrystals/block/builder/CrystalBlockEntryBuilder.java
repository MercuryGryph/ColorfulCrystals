package cn.mercury9.colorfulcrystals.block.builder;

import com.mojang.math.Quadrant;
import dev.anvilcraft.lib.v2.registrum.Registrum;
import dev.anvilcraft.lib.v2.registrum.util.entry.BlockEntry;
import dev.anvilcraft.lib.v2.util.nullness.NonNullFunction;
import net.minecraft.client.data.models.MultiVariant;
import net.minecraft.client.data.models.blockstates.PropertyDispatch;
import net.minecraft.client.renderer.block.dispatch.Variant;
import net.minecraft.client.renderer.block.dispatch.VariantMutator;
import net.minecraft.core.Direction;
import net.minecraft.resources.Identifier;
import net.minecraft.tags.TagKey;
import net.minecraft.util.random.Weighted;
import net.minecraft.util.random.WeightedList;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public abstract class CrystalBlockEntryBuilder<T extends Block, S extends CrystalBlockEntryBuilder<T, S>> {
    protected final Registrum registrum;

    protected NonNullFunction<BlockBehaviour.Properties, T> block;
    protected String id;
    protected String name;
    protected final List<TagKey<Block>> blockTag = new ArrayList<>();
    protected final List<TagKey<Item>> itemTag = new ArrayList<>();

    public CrystalBlockEntryBuilder(Registrum registrum) {
        this.registrum = registrum;
    }

    protected void beforeBuildCheckNullValue() {
        if (block == null) {
            throw new NullPointerException(this + ".factory is null");
        }
        if (id == null) {
            throw new NullPointerException(this + ".id is null");
        }
        if (name == null) {
            throw new NullPointerException(this + ".name is null");
        }
    }

    abstract protected BlockEntry<T> actualBuild();

    public BlockEntry<T> buildWithoutReset() {
        beforeBuildCheckNullValue();
        return actualBuild();
    }

    public BlockEntry<T> build() {
        var res = buildWithoutReset();
        reset();
        return res;
    }

    public S reset() {
        this.block = null;
        this.id = null;
        this.name = null;
        this.blockTag.clear();
        this.itemTag.clear();
        return self();
    }

    @SuppressWarnings("unchecked")
    protected final S self() {
        return (S) this;
    }

    //region setter

    public S block(NonNullFunction<BlockBehaviour.Properties, T> factory) {
        this.block = factory;
        return self();
    }

    public S id(String id) {
        this.id = id;
        return self();
    }

    public S name(String name) {
        this.name = name;
        return self();
    }

    public S addBlockTag(TagKey<Block> blockTag) {
        this.blockTag.add(blockTag);
        return self();
    }

    @SafeVarargs
    public final S addBlockTags(TagKey<Block> ...blockTag) {
        this.blockTag.addAll(Arrays.asList(blockTag));
        return self();
    }

    public S addItemTag(TagKey<Item> itemTag) {
        this.itemTag.add(itemTag);
        return self();
    }

    @SafeVarargs
    public final S addItemTags(TagKey<Item> ...itemTag) {
        this.itemTag.addAll(Arrays.asList(itemTag));
        return self();
    }

    //endregion

    public static MultiVariant randomRotationMultiVariant(
        Identifier model,
        VariantMutator.VariantProperty<Quadrant> rot
    ) {
        return new MultiVariant(
            WeightedList.of(
                new Weighted<>(new Variant(model).with(rot.withValue(Quadrant.R0  )), 1),
                new Weighted<>(new Variant(model).with(rot.withValue(Quadrant.R90 )), 1),
                new Weighted<>(new Variant(model).with(rot.withValue(Quadrant.R180)), 1),
                new Weighted<>(new Variant(model).with(rot.withValue(Quadrant.R270)), 1)
            )
        );
    }

    public static PropertyDispatch<MultiVariant> propertyDispatchWithFacingAndRandomRotation(Identifier model) {
        return PropertyDispatch.initial(BlockStateProperties.FACING)
            .select(Direction.UP    , randomRotationMultiVariant(model, VariantMutator.Y_ROT).with(VariantMutator.X_ROT.withValue(Quadrant.R0  )))
            .select(Direction.DOWN  , randomRotationMultiVariant(model, VariantMutator.Y_ROT).with(VariantMutator.X_ROT.withValue(Quadrant.R180)))
            .select(Direction.NORTH , randomRotationMultiVariant(model, VariantMutator.Z_ROT).with(VariantMutator.X_ROT.withValue(Quadrant.R90 )))
            .select(Direction.SOUTH , randomRotationMultiVariant(model, VariantMutator.Z_ROT).with(VariantMutator.X_ROT.withValue(Quadrant.R270)))
            .select(Direction.EAST  , randomRotationMultiVariant(model, VariantMutator.Y_ROT).with(VariantMutator.Z_ROT.withValue(Quadrant.R90 )))
            .select(Direction.WEST  , randomRotationMultiVariant(model, VariantMutator.Y_ROT).with(VariantMutator.Z_ROT.withValue(Quadrant.R270)));
    }
}
