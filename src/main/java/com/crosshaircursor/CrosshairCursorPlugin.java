package com.crosshaircursor;

import com.google.inject.Provides;
import javax.inject.Inject;
import lombok.extern.slf4j.Slf4j;
import net.runelite.api.ChatMessageType;
import net.runelite.api.Client;
import net.runelite.api.GameState;
import net.runelite.api.events.GameStateChanged;
import net.runelite.client.config.ConfigManager;
import net.runelite.client.eventbus.Subscribe;
import net.runelite.client.plugins.Plugin;
import net.runelite.client.plugins.PluginDescriptor;
import net.runelite.client.ui.ClientUI;

import java.awt.Cursor;

@Slf4j
@PluginDescriptor(
	name = "Crosshair Cursor", description = "Changes your cursor to a crosshair. Also supports animated cursors (see help page)"
)
public class CrosshairCursorPlugin extends Plugin
{
	@Inject
	private Client client;

	@Inject
	private ClientUI clientUI;

	@Inject
	private CrosshairCursorConfig config;

	@Override
	protected void startUp() throws Exception
	{
		updateCursor();
	}

	private void updateCursor() {
		clientUI.setCursor(Cursor.getPredefinedCursor(Cursor.CROSSHAIR_CURSOR));
	}

	@Override
	protected void shutDown() throws Exception
	{
		clientUI.resetCursor();
	}


	@Provides
	CrosshairCursorConfig provideConfig(ConfigManager configManager)
	{
		return configManager.getConfig(CrosshairCursorConfig.class);
	}
}
