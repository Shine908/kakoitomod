package com.example.lumendepths;

import net.fabricmc.api.ModInitializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LumenDepthsMod implements ModInitializer {
	public static final String MOD_ID = "lumen_depths";
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	@Override
	public void onInitialize() {
		// Order matters: entities + spawn eggs first (the creative tab references
		// them), then items, then the creative tab and finally attributes.
		LumenEntities.registerEntities();
		LumenItems.registerItems();
		LumenCreativeTab.register();
		LumenEntities.registerDefaultAttributes();
		LOGGER.info("[Lumen Depths] Initialization complete: {} items, {} entities registered.", 6, 2);
	}
}
