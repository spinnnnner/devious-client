package net.unethicalite.api.packets;

import java.awt.Polygon;
import java.awt.Rectangle;
import net.runelite.api.Perspective;
import net.runelite.api.Point;
import net.runelite.api.Tile;
import net.runelite.api.TileObject;
import net.runelite.api.coords.LocalPoint;
import net.runelite.api.coords.WorldPoint;
import net.runelite.api.packets.PacketBufferNode;
import net.runelite.api.widgets.Widget;
import net.unethicalite.api.Interactable;
import net.unethicalite.api.game.Game;
import net.unethicalite.api.game.GameThread;
import net.unethicalite.api.util.Randomizer;
import net.unethicalite.client.Static;

public class MousePackets
{
	public static void queueClickPacket(int x, int y)
	{
		Static.getClient().setMouseLastPressedMillis(System.currentTimeMillis());
		int mousePressedTime = ((int) (Static.getClient().getMouseLastPressedMillis() - Static.getClient().getClientMouseLastPressedMillis()));
		if (mousePressedTime < 0)
		{
			mousePressedTime = 0;
		}
		if (mousePressedTime > 32767)
		{
			mousePressedTime = 32767;
		}
		Static.getClient().setClientMouseLastPressedMillis(Static.getClient().getMouseLastPressedMillis());
		int mouseInfo = (mousePressedTime << 1) + 1;
		MousePackets.queueClickPacket(mouseInfo, x, y);
	}

	public static void queueClickPacket(int mouseInfo, int x, int y)
	{
		GameThread.invoke(() -> createClickPacket(mouseInfo, x, y).send());
	}

	public static void queueClickPacket()
	{
		Point clickpoint = Randomizer.getUniformPointIn(Static.getClient().getCanvas().getBounds());
		queueClickPacket(clickpoint.getX(), clickpoint.getY());
	}

	public static void queueClickPacket(Widget widget){
		Point clickpoint = Randomizer.getRandomPointIn(widget.getBounds());
		queueClickPacket(clickpoint.getX(), clickpoint.getY());
	}

	public static void queueClickPacket(Interactable o){
		Point clickpoint = o.getClickPoint();
		queueClickPacket(clickpoint.getX(), clickpoint.getY());
	}

	public static void queueClickPacket(Point p){
		queueClickPacket(p.getX(), p.getY());
	}

	public static PacketBufferNode createClickPacket(int mouseInfo, int x, int y)
	{
		var client = Static.getClient();
		var clientPacket = Game.getClientPacket();
		var packetBufferNode = Static.getClient().preparePacket(clientPacket.EVENT_MOUSE_CLICK(), client.getPacketWriter().getIsaacCipher());
		packetBufferNode.getPacketBuffer().writeShort(mouseInfo);
		packetBufferNode.getPacketBuffer().writeShort(x);
		packetBufferNode.getPacketBuffer().writeShort(y);
		return packetBufferNode;
	}
}