package com.example.lumendepths.datagen;

import com.example.lumendepths.LumenBlocks;
import com.example.lumendepths.LumenItems;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricRecipeProvider;
import net.minecraft.data.server.recipe.RecipeExporter;
import net.minecraft.data.server.recipe.ShapedRecipeJsonBuilder;
import net.minecraft.data.server.recipe.ShapelessRecipeJsonBuilder;
import net.minecraft.item.Items;
import net.minecraft.recipe.book.RecipeCategory;
import net.minecraft.registry.RegistryWrapper;

import java.util.concurrent.CompletableFuture;

/** Generates crafting recipe JSONs into src/generated/resources at build time. */
public class LumenDatagen extends FabricRecipeProvider {

    public LumenDatagen(FabricDataOutput output, CompletableFuture<RegistryWrapper.WrapperLookup> registries) {
        super(output, registries);
    }

    @Override
    public void generate(RecipeExporter exporter) {
        ShapedRecipeJsonBuilder.create(RecipeCategory.BUILDING_BLOCKS, LumenBlocks.LUMEN_CRYSTAL_BLOCK)
                .pattern("##").pattern("##")
                .input('#', LumenItems.LUMEN_SHARD)
                .criterion("has_shard", conditionsFromItem(LumenItems.LUMEN_SHARD))
                .offerTo(exporter);

        ShapedRecipeJsonBuilder.create(RecipeCategory.BUILDING_BLOCKS, LumenBlocks.AURORA_CRYSTAL_BLOCK)
                .pattern("##").pattern("##")
                .input('#', LumenItems.AURORA_GEM)
                .criterion("has_gem", conditionsFromItem(LumenItems.AURORA_GEM))
                .offerTo(exporter);

        ShapedRecipeJsonBuilder.create(RecipeCategory.DECORATIONS, LumenBlocks.LUMEN_LANTERN)
                .pattern("/").pattern("#")
                .input('/', Items.IRON_NUGGET)
                .input('#', LumenItems.LUMEN_SHARD)
                .criterion("has_shard", conditionsFromItem(LumenItems.LUMEN_SHARD))
                .offerTo(exporter);

        ShapedRecipeJsonBuilder.create(RecipeCategory.BUILDING_BLOCKS, LumenBlocks.CRYSTAL_BRICKS, 4)
                .pattern("##").pattern("##")
                .input('#', LumenBlocks.LUMEN_CRYSTAL_BLOCK)
                .criterion("has_crystal_block", conditionsFromItem(LumenBlocks.LUMEN_CRYSTAL_BLOCK))
                .offerTo(exporter);

        ShapelessRecipeJsonBuilder.create(RecipeCategory.MISC, LumenItems.LUMEN_SHARD, 9)
                .input(LumenBlocks.LUMEN_CRYSTAL_BLOCK)
                .criterion("has_crystal_block", conditionsFromItem(LumenBlocks.LUMEN_CRYSTAL_BLOCK))
                .offerTo(exporter);

        ShapelessRecipeJsonBuilder.create(RecipeCategory.MISC, LumenItems.AURORA_GEM, 9)
                .input(LumenBlocks.AURORA_CRYSTAL_BLOCK)
                .criterion("has_aurora_block", conditionsFromItem(LumenBlocks.AURORA_CRYSTAL_BLOCK))
                .offerTo(exporter);
    }
}
