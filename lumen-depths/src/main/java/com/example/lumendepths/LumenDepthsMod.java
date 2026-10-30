package com.example.lumendepths;

import com.example.lumendepths.world.LumenWorldgen;
import net.fabricmc.api.ModInitializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LumenDepthsMod implements ModInitializer {

public static final String MOD_ID = "lumen_depths";
public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

@Override
public void onInitialize() {
// Order matters: blocks -> block items -> entities + spawn eggs ->
// plain items -> creative tab -> attributes -> worldgen hooks.
LumenBlocks.registerBlocks();
LumenBlockItems.registerBlockItems();
LumenEntities.registerEntities();
LumenItems.registerItems();
LumenCreativeTab.register();
LumenEntities.registerDefaultAttributes();
LumenWorldgen.register();
LOGGER.info("[Lumen Depths] v2 initialization complete.");
}
}
