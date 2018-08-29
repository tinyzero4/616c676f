package w2;

import edu.princeton.cs.algs4.Picture;

import java.awt.*;

public class SeamCarver
{
	private static final int MAX_ENERGY = 1000;

	private final Picture picture;

	public SeamCarver(Picture picture)
	{
		if (picture == null)
		{
			throw new IllegalArgumentException();
		}
		this.picture = new Picture(picture);
	}

	public Picture picture()
	{
		return new Picture(picture);
	}

	public int width()
	{
		return picture.width();
	}

	public int height()
	{
		return picture.height();
	}

	public double energy(int x, int y)
	{
		checkBounds(x, y);

		if ((x == 0 || x == width() - 1) || (y == 0 || y == height() - 1))
		{
			return MAX_ENERGY;
		}

		Color x1 = picture.get(x - 1, y);
		Color x2 = picture.get(x + 1, y);

		double xGradient = Math.pow(x1.getRed() - x2.getRed(), 2) + Math.pow(x1.getGreen() - x2.getGreen(), 2) + Math.pow(x1.getBlue() - x2.getBlue(), 2);

		Color y1 = picture.get(x, y - 1);
		Color y2 = picture.get(x, y + 1);

		double yGradient = Math.pow(y1.getRed() - y2.getRed(), 2) + Math.pow(y1.getGreen() - y2.getGreen(), 2) + Math.pow(y1.getBlue() - y2.getBlue(), 2);

		return Math.sqrt(xGradient + yGradient);
	}

	private void checkBounds(int x, int y)
	{
		if ((x < 0 || x >= width()) || (y < 0 || y >= height()))
		{
			throw new IllegalArgumentException();
		}
	}


	public int[] findHorizontalSeam()
	{
		return null;
	}

	public int[] findVerticalSeam()
	{
		return null;
	}

	public void removeHorizontalSeam(int[] seam)
	{

	}

	public void removeVerticalSeam(int[] seam)
	{

	}
}
