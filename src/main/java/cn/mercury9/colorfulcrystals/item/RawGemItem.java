package cn.mercury9.colorfulcrystals.item;

import dev.anvilcraft.lib.v2.util.nullness.NonNullFunction;
import dev.anvilcraft.lib.v2.util.nullness.NonNullSupplier;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.particles.ItemParticleOption;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.EntitySelector;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.ProjectileUtil;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.ItemUseAnimation;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.HitResult;
import net.minecraft.world.phys.Vec3;

public class RawGemItem extends Item {
    private final NonNullSupplier<Item> polished;

    public RawGemItem(NonNullSupplier<Item> polished, Properties properties) {
        super(properties);
        this.polished = polished;
    }

    public static NonNullFunction<Properties, RawGemItem> factory(NonNullSupplier<Item> polished) {
        return (properties) -> new RawGemItem(polished, properties);
    }

    @Override
    public int getUseDuration(ItemStack itemStack, LivingEntity user) {
        return 20;
    }

    @Override
    public ItemUseAnimation getUseAnimation(ItemStack itemStack) {
        return ItemUseAnimation.BOW;
    }

    private boolean isTargetValidGrindStone(Level level, BlockPos pos) {
        return level.getBlockState(pos).is(Blocks.GRINDSTONE);
    }

    @Override
    public InteractionResult useOn(UseOnContext context) {
        if (!isTargetValidGrindStone(context.getLevel(), context.getClickedPos())) {
            return InteractionResult.FAIL;
        }

        Player player = context.getPlayer();
        if (player != null && this.calcHit(player).getType() == HitResult.Type.BLOCK) {
            player.startUsingItem(context.getHand());
        }

        return InteractionResult.CONSUME;
    }

    @Override
    public void onUseTick(Level level, LivingEntity livingEntity, ItemStack itemStack, int ticksRemaining) {
        if (livingEntity instanceof Player player) {
            final var hitResult = this.calcHit(player);
            if (hitResult instanceof BlockHitResult blockHitResult && hitResult.getType() == HitResult.Type.BLOCK) {
                final var pos = blockHitResult.getBlockPos();

                if (ticksRemaining > 1) {
                    final var time = this.getUseDuration(itemStack, livingEntity) - ticksRemaining + 1;
                    if (time % 10 == 0) {
                        spawnDust(level, blockHitResult, player.getViewVector(0f));
                        playGrindSound(level, player, pos);
                    }
                } else {
                    playGrindSound(level, player, pos);
                    if (level.isClientSide()) return;
                    itemStack.shrink(1);
                    player.handleExtraItemsCreatedOnUse(polished.get().getDefaultInstance());
                }
            } else {
                livingEntity.releaseUsingItem();
            }
        } else {
            livingEntity.releaseUsingItem();
        }
    }

    private void spawnDust(
        Level level,
        BlockHitResult hit,
        Vec3 viewVec
    ) {
        final var scale = 3.0;
        final var number = level.getRandom().nextInt(7, 12);
        final var particle = new ItemParticleOption(ParticleTypes.ITEM, polished.get());
        final var hitDirection = hit.getDirection();
        final var delta = DustDelta.fromDirection(viewVec, hitDirection);
        final var hitLoc = hit.getLocation();

        for (var i = 0; i < number; i++) {
            level.addParticle(
                particle,
                hitLoc.x - (hitDirection == Direction.WEST  ? 1.0e-6f : 0f),
                hitLoc.y,
                hitLoc.z - (hitDirection == Direction.NORTH ? 1.0e-6f : 0f),
                delta.x * scale * level.getRandom().nextDouble(),
                delta.y,
                delta.z * scale * level.getRandom().nextDouble()
            );
        }
    }

    private void playGrindSound(Level level, Player player, BlockPos pos) {
        final var sound = SoundEvents.GRINDSTONE_USE;
        level.playSound(player, pos, sound, SoundSource.BLOCKS);
    }

    private HitResult calcHit(Player player) {
        return ProjectileUtil.getHitResultOnViewVector(player, EntitySelector.CAN_BE_PICKED, player.blockInteractionRange());
    }

    private record DustDelta(double x, double y, double z) {
        private static final double DELTA   = 1.0;
        private static final double OFFSET  = 0.1;
        private static final double Y       = 0.0;

        public static DustDelta fromDirection(Vec3 viewVec, Direction hitDirection) {
            return switch (hitDirection) {
                case NORTH  -> new DustDelta( DELTA     , Y, -OFFSET     );
                case SOUTH  -> new DustDelta(-DELTA     , Y,  OFFSET     );
                case WEST   -> new DustDelta(-OFFSET    , Y, -DELTA      );
                case EAST   -> new DustDelta( OFFSET    , Y,  DELTA      );
                default     -> new DustDelta(viewVec.z(), Y, -viewVec.x());
            };
        }
    }
}
