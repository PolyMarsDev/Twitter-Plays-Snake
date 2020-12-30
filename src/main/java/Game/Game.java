package Game;

import java.io.Serializable;

public class Game implements Serializable {
    final int N = 0;
    final int E = 1;
    final int S = 2;
    final int W = 3;
    Grid grid;
    public Game(int width, int height)
    {
        grid = new Grid(width, height);
    }
    public Grid getGrid()
    {
        return grid;
    }
    public String run(String direction)
    {
        if (grid.getSnake().isDead())
        {
            grid.getSnake().reset();
        }
        if (direction.equals("rt") || direction.equals("a") || direction.equals("w"))
        {
            if (grid.getSnake().getDirection() == N || grid.getSnake().getDirection() == S)
            {
                grid.getSnake().setDirection(W);
            }
            else
            {
                grid.getSnake().setDirection(N);
            }
        }
        else if (direction.equals("like") || direction.equals("d") || direction.equals("s"))
        {
            if (grid.getSnake().getDirection() == N || grid.getSnake().getDirection() == S)
            {
                grid.getSnake().setDirection(E);
            }
            else
            {
                grid.getSnake().setDirection(S);
            }
        }
        grid.getSnake().move();
        if (grid.getSnake().isDead())
        {
            return getScore() + grid.toString() + "\noops";
        }
        grid.getSnake().changeModes();
        return getScore() + grid.toString() + getInstructions();
    }
    private String getInstructions()
    {
        if (grid.getSnake().getDirection() == N || grid.getSnake().getDirection() == S)
        {
            return "\n⬅️\uD83D\uDD01                             ❤️➡️";
        }
        return "\n⬆️\uD83D\uDD01                             ❤️⬇️";
    }
    private String getScore()
    {
        return "Score: " + grid.getSnake().getScore() + "\nBest: " + grid.getSnake().getBestScore() + "\n\n";
    }
}
