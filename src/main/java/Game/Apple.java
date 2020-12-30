package Game;

import java.io.Serializable;

public class Apple implements Serializable {//apple modes
    final int SINGLE = 0;
    final int PORTAL = 1;
    int type;
    int index;
    int x = 0;
    int y = 0;
    Grid grid;
    Randomizer rand = new Randomizer();
    public Apple(Grid grid, int type, int index, int excludeX, int excludeY)
    {
        this.index = index;
        this.type = type;
        this.grid = grid;
        randomize(excludeX, excludeY);
    }
    public int getIndex()
    {
        return index;
    }
    public int getType()
    {
        return type;
    }
    public void setPos(int x, int y)
    {
        this.x = x;
        this.y = y;
    }
    public void randomize(int excludeX, int excludeY)
    {
        int tempX = rand.nextInt(grid.getWidth());
        int tempY = rand.nextInt(grid.getHeight());
        while (grid.hasSegment(tempX, tempY, true, true) || (tempX == excludeX && tempY == excludeY))
        {
            tempX = rand.nextInt(grid.getWidth());
            tempY = rand.nextInt(grid.getHeight());
        }
        x = tempX;
        y = tempY;
    }
    int getX()
    {
        return x;
    }
    int getY()
    {
        return y;
    }


}