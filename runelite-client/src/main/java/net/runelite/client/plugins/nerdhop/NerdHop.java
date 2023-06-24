package net.runelite.client.plugins.nerdhop;

import com.google.inject.Inject;
import com.google.inject.Provides;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.concurrent.ThreadLocalRandom;
import net.runelite.api.Client;
import net.runelite.api.GameState;
import net.runelite.api.World;
import net.runelite.api.events.GameStateChanged;
import net.runelite.api.events.GameTick;
import net.runelite.api.events.HitsplatApplied;
import net.runelite.client.config.ConfigManager;
import net.runelite.client.eventbus.Subscribe;
import net.runelite.client.plugins.Plugin;
import net.runelite.client.plugins.PluginDescriptor;
import net.unethicalite.api.game.Worlds;
import org.pf4j.Extension;

// This annotation is required in order for the client to detect it as a plugin/script.
@PluginDescriptor(name = "Nerd Hop", enabledByDefault = false)
@Extension
public class NerdHop extends Plugin
{
	@Inject
	private Client client;

	@Inject
	private NerdHopConfig config;

	private LocalDateTime login;

	private int hopAfterMinutes;

	private int lastHitsplatReceived;

	@Inject
	private ConfigManager configManager;



	@Provides
	NerdHopConfig provideConfig(ConfigManager configManager)
	{
		return configManager.getConfig(NerdHopConfig.class);
	}

	@Override
	protected void startUp(){
		login = LocalDateTime.now();
		hopAfterMinutes = ThreadLocalRandom.current().nextInt(config.minHopTime(),config.maxHopTime());
		lastHitsplatReceived = -1;
	}

	@Subscribe
	public void onGameStateChanged(GameStateChanged e){
		if ((e.getGameState() == GameState.LOGGING_IN) || (e.getGameState() == GameState.HOPPING)){
			login = LocalDateTime.now();
			client.getLogger().info("Logged in at "+login);
			hopAfterMinutes = ThreadLocalRandom.current().nextInt(config.minHopTime(),config.maxHopTime());
			client.getLogger().info("hop after minutes: "+hopAfterMinutes);
		}
	}

	@Subscribe
	public void onHitsplatApplied(HitsplatApplied e){
		if (e.getActor() != client.getLocalPlayer()) return;
		lastHitsplatReceived = client.getTickCount();
	}

	@Subscribe
	public void onGameTick(GameTick e){
		if (!LocalDateTime.now().isAfter(login.plusMinutes(hopAfterMinutes))) return;

		// only try every 5 ticks
		if (client.getTickCount() % 5 != 0) return;

		if (client.getTickCount() > lastHitsplatReceived + 10)
		{
			client.getLogger().info("Hopping due to 6 hr log.");
			while (true)
			{
				World hopWorld = Worlds.getRandom(w -> (!w.isAllPkWorld() && !w.isPvpArena() &&
					w.isMembers() == config.memberWorlds() &&
					w.isSkillTotal() == config.skillTotalWorlds()));

				if (hopWorld.getId() != client.getWorld()){
					Worlds.hopTo(hopWorld);
					return;
				}
			}
		}
	}
}
