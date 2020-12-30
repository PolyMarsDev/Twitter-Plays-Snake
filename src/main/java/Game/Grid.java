package Game;

import java.io.Serializable;
import java.util.*;

public class Grid implements Serializable {
    Randomizer rand = new Randomizer();
    //snake modes
    final int CLASSIC = 0;
    final int GLUTTED = 1;
    final int CHARGED = 2;
    final int DEVIL = 3;
    //apple modes
    final int SINGLE = 0;
    final int PORTAL = 1;
    //directions
    final int N = 0;
    final int E = 1;
    final int S = 2;
    final int W = 3;
    final int PORTALAPPLE = 9;
    final int DEADSEGMENT = 8;
    final int DEADENDSEGMENT = 7;
    final int ENDSEGMENT = 6;
    final int YUMHEAD = 5;
    final int DEADHEAD = 4;
    final int HEAD = 3;
    final int APPLE = 2;
    final int SEGMENT = 1;
    final int GROUND = 0;
    int width;
    int height;
    Tile[][] grid;
    Snake snake;
    ArrayList<Apple> apples = new ArrayList<Apple>();
    public Grid (int width, int height)
    {
        this.width = width;
        this.height = height;
        snake = new Snake(1, this);
        randomizeApples(snake.getX(), snake.getY());
        grid = new Tile[width][height];
    }
    public boolean hasHead(int x, int y)
    {
        return snake.getSegment(0).getX() == x && snake.getSegment(0).getY() == y;
    }
    public boolean hasSegment(int x, int y, boolean includeHead, boolean includeTail)
    {
        int startingIndex = includeHead ? 0 : 1;
        int endingIndex = snake.getSize() - (includeTail ? 0 : 1);
        for (int i = startingIndex; i < endingIndex; i++)
        {
            if (snake.getSegment(i).getX() == x && snake.getSegment(i).getY() == y)
            {
                return true;
            }
        }
        return false;
    }
    public boolean hasApple(int x, int y)
    {
        for (Apple apple : apples)
        {
            if (x == apple.getX() && y == apple.getY())
            {
                return true;
            }
        }
        return false;
    }
    public Snake getSnake()
    {
        return snake;
    }
    public Apple getApple(int index)
    {
        return apples.get(index);
    }
    public Apple getApple(int x, int y)
    {
        for (Apple apple : apples)
        {
            if (x == apple.getX() && y == apple.getY())
            {
                return apple;
            }
        }
        return null;
    }
    public void updateGrid()
    {
        for (int i = 0; i < height; i++)
        {
            for (int j = 0; j < width; j++)
            {
                if (hasSegment(j, i, false, false))
                {
                    if (getSnake().isDead())
                    {
                        grid[j][i] = new Tile(DEADSEGMENT, snake.getSnakeMode());
                    }
                    else
                    {
                        grid[j][i] = new Tile(SEGMENT, snake.getSnakeMode());
                    }
                }
                else if (hasSegment(j, i, false, true))
                {
                    if (getSnake().isDead())
                    {
                        grid[j][i] = new Tile(DEADENDSEGMENT, snake.getSnakeMode());
                    }
                    else
                    {
                        grid[j][i] = new Tile(ENDSEGMENT, snake.getSnakeMode());
                    }
                }
                else if (hasHead(j, i))
                {
                    if (getSnake().isDead())
                    {
                        grid[j][i] = new Tile(DEADHEAD, snake.getSnakeMode());
                    }
                    else if (getSnake().isYummed())
                    {
                        getSnake().setYummed(false);
                        grid[j][i] = new Tile(YUMHEAD, snake.getSnakeMode());
                    }
                    else
                    {
                        grid[j][i] = new Tile(HEAD, snake.getSnakeMode());
                    }
                }
                else if (hasApple(j, i))
                {
                    if (getApple(j, i).getType() == PORTAL)
                    {
                        grid[j][i] = new Tile(PORTALAPPLE, snake.getSnakeMode());
                    }
                    else
                    {
                        grid[j][i] = new Tile(APPLE, snake.getSnakeMode());
                    }
                }
                else
                {
                    grid[j][i] = new Tile(GROUND, snake.getSnakeMode());
                }

            }


        }
    }
    void changeAppleModes()
    {
        if (snake.getLength() > (width * height) * 0.8)
        {
            snake.setAppleMode(SINGLE);
        }
        else
        {
            int random = rand.nextInt(2);
            if (random == 0)
            {
                snake.setAppleMode(SINGLE);
            }
            else if (random == 1)
            {
                snake.setAppleMode(PORTAL);
            }
        }
    }
    public void randomizeApples(int excludeX, int excludeY)
    {
        changeAppleModes();
        apples.clear();
        if (snake.getAppleMode() == SINGLE)
        {
            apples.add(new Apple(this, SINGLE, 0, excludeX, excludeY));
        }
        else if (snake.getAppleMode() == PORTAL)
        {
            apples.add(new Apple(this, PORTAL, 0, excludeX, excludeY));
            apples.add(new Apple(this, PORTAL, 1, excludeX, excludeY));
        }
    }
    public int getWidth()
    {
        return width;
    }
    public int getHeight()
    {
        return height;
    }
    public String toString()
    {
        updateGrid();
        String result = "";
        for (int i = 0; i < height; i++)
        {
            for (int j = 0; j < width; j++)
            {
                result += grid[j][i];
            }
            result += "\n";
        }
        return result;
    }
}