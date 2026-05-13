package cn.mercury9.colorfulcrystals;

import net.minecraft.core.registries.Registries;
import net.minecraft.resources.Identifier;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;

@SuppressWarnings("unused")
public class CrystalTags {


    public static class Blocks {
        public static final TagKey<Block> STORAGE = c("storage_blocks");
        public static final TagKey<Block> CLUSTERS = c("clusters");
        public static final TagKey<Block> BUDS = c("buds");
        public static final TagKey<Block> BUDDING_BLOCKS = c("budding_blocks");

        public static final TagKey<Block> TOPAZ_BLOCK = storage("topaz");
        public static final TagKey<Block> TOPAZ_CLUSTER = cluster("topaz");

        public static final TagKey<Block> RUBY_BLOCK = storage("ruby");
        public static final TagKey<Block> RUBY_CLUSTER = cluster("ruby");

        public static final TagKey<Block> SAPPHIRE_BLOCK = storage("sapphire");
        public static final TagKey<Block> SAPPHIRE_CLUSTER = cluster("sapphire");

        //region utils
        public static TagKey<Block> c(String id) {
            return TagKey.create(
                Registries.BLOCK,
                Identifier.fromNamespaceAndPath("c", id)
            );
        }

        public static TagKey<Block> mod(String id) {
            return TagKey.create(
                Registries.BLOCK,
                Identifier.fromNamespaceAndPath(ColorfulCrystals.MOD_ID, id)
            );
        }

        public static TagKey<Block> storage(String material) {
            return c("storage_blocks/" + material);
        }

        public static TagKey<Block> cluster(String type) {
            return c("clusters/" + type);
        }

        public static TagKey<Block> buds(String id) {
            return c("buds/" + id);
        }

        public static TagKey<Block> budding(String type) {
            return c("budding_blocks/" + type);
        }
        //endregion
    }


    public static class Items {
        public static final TagKey<Item> STORAGE = c("storage_blocks");
        public static final TagKey<Item> GEMS = c("gems");
        public static final TagKey<Item> CLUSTERS = c("clusters");
        public static final TagKey<Item> BUDS = c("buds");
        public static final TagKey<Item> BUDDING_BLOCKS = c("budding_blocks");

        public static final TagKey<Item> TOPAZ = gem("topaz");
        public static final TagKey<Item> TOPAZ_BLOCK = storage("topaz");
        public static final TagKey<Item> TOPAZ_CLUSTER = cluster("topaz");

        public static final TagKey<Item> RUBY = gem("ruby");
        public static final TagKey<Item> RUBY_BLOCK = storage("ruby");
        public static final TagKey<Item> RUBY_CLUSTER = cluster("ruby");

        public static final TagKey<Item> SAPPHIRE = gem("sapphire");
        public static final TagKey<Item> SAPPHIRE_BLOCK = storage("sapphire");
        public static final TagKey<Item> SAPPHIRE_CLUSTER = cluster("sapphire");

        //region utils
        public static TagKey<Item> c(String id) {
            return TagKey.create(
                Registries.ITEM,
                Identifier.fromNamespaceAndPath("c", id)
            );
        }

        public static TagKey<Item> mod(String id) {
            return TagKey.create(
                Registries.ITEM,
                Identifier.fromNamespaceAndPath(ColorfulCrystals.MOD_ID, id)
            );
        }

        public static TagKey<Item> storage(String material) {
            return c("storage_blocks/" + material);
        }

        public static TagKey<Item> gem(String type) {
            return c("gems/" + type);
        }

        public static TagKey<Item> cluster(String type) {
            return c("clusters/" + type);
        }

        public static TagKey<Item> buds(String type) {
            return c("buds/" + type);
        }

        public static TagKey<Item> budding(String type) {
            return c("budding_blocks/" + type);
        }
        //endregion
    }

}
