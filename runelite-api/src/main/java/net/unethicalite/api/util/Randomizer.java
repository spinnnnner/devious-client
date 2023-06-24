package net.unethicalite.api.util;

import net.runelite.api.Point;

import java.awt.Rectangle;
import java.util.concurrent.ThreadLocalRandom;

public class Randomizer
{

    public static Point getRandomPointIn(Rectangle rect)
    {
        if (rect == null)
        {
            return new Point(82, 74);
        }

        int xDeviation = (int) Math.log(rect.getWidth() * Math.PI);
        int yDeviation = (int) Math.log(rect.getHeight() * Math.PI);
        return getRandomPointIn(rect, xDeviation, yDeviation);
    }

    public static Point getRandomPointIn(Rectangle rect, int xDeviation, int yDeviation)
    {
        double centerX = rect.getCenterX();
        double centerY = rect.getCenterY();

		double scaleFactor = ThreadLocalRandom.current().nextDouble(0.9,1.1);

        double randX = Math.max(
                Math.min(centerX + xDeviation * ThreadLocalRandom.current().nextGaussian(), rect.getMaxX() * scaleFactor),
                rect.getMinX() * scaleFactor);

        double randY = Math.max(
                Math.min(centerY + yDeviation * ThreadLocalRandom.current().nextGaussian(), rect.getMaxY() * scaleFactor),
                rect.getMinY() * scaleFactor);

		if (randX < 0) randX = 7;
		if (randY < 0) randY = 4;

        return new Point((int) randX, (int) randY);
    }

	public static Point getUniformPointIn(Rectangle rect)
	{
		int randX = ThreadLocalRandom.current().nextInt((int) rect.getMinX(), (int) rect.getMaxX());
		int randY = ThreadLocalRandom.current().nextInt((int) rect.getMinY(), (int) rect.getMaxY());

		if (randX < 0) randX = 12;
		if (randY < 0) randY = 6;
		return new Point(randX, randY);
	}
}
