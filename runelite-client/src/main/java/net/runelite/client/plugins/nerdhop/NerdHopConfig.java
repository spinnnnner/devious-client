package net.runelite.client.plugins.nerdhop;

import net.runelite.client.config.Config;
import net.runelite.client.config.ConfigGroup;
import net.runelite.client.config.ConfigItem;
import net.runelite.client.config.Range;
import net.runelite.client.config.Units;

@ConfigGroup("nerdHop")
public interface NerdHopConfig extends Config
{
	@ConfigItem(
		keyName = "memberWorlds",
		name = "Member Worlds",
		description = "Hop to members-only worlds",
		position = 1
	)
	default boolean memberWorlds()
	{
		return false;
	}

	@ConfigItem(
		keyName = "totalWorlds",
		name = "Skill Total Worlds",
		description = "Hop to skill total worlds",
		position = 2
	)
	default boolean skillTotalWorlds()
	{
		return false;
	}

	@ConfigItem(
		keyName = "minHopTime",
		name = "Minimum hop time (minutes)",
		description = "Minimum time to wait before hopping",
		position = 3
	)
	default int minHopTime()
	{
		return 240;
	}

	@ConfigItem(
		keyName = "maxHopTime",
		name = "Maximum hop time (minutes)",
		description = "Maximum time to wait before hopping",
		position = 4
	)
	default int maxHopTime()
	{
		return 330;
	}
}
