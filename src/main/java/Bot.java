import Game.Game;
import org.apache.commons.io.input.ReaderInputStream;
import twitter4j.Status;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;
import twitter4j.conf.ConfigurationBuilder;

import java.io.*;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Scanner;
import java.util.Timer;
import java.util.TimerTask;

public class Bot {
    final static boolean DEBUG = false; //in debug mode, console messages are sent instead of tweets
    final static String USERNAME = "REPLACE_WITH_USERNAME";
    final static String SAVEPATH = "C:/REPLACE/WITH/PATH/TO/SAVE.txt";
    final static String CONSUMER_KEY = "REPLACE_WITH_CONSUMER_KEY";
    final static String CONSUMER_SECRET = "REPLACE_WITH_OAUTH_CONSUMER_SECRET";
    final static String ACCESS_TOKEN = "REPLACE_WITH_OATH_ACCESS_TOKEN";
    final static String ACCESS_TOKEN_SECRET = "REPLACE_WITH_OATH_ACCESS_TOKEN_SECRET";
    public static Game game;
    public static Twitter twitter = getTwitterInstance();

    public static void main(String[] args)
    {
      game = new Game(10, 10);
      load();
      if (DEBUG)
      {
          System.out.println(game.run("like"));
          Scanner scan = new Scanner(System.in);
          while (true)
          {
              System.out.println(game.run(scan.nextLine()));
              save();
          }
      }
      else
      {
          sendTweet(game.run("like"), twitter);
          Timer timer = new Timer();
          timer.scheduleAtFixedRate(new ScheduledTweet(), 1800000, 1800000);
      }
    }
    static void save()
    {
        try
        {
            FileOutputStream fos = new FileOutputStream(SAVEPATH);
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(game);
            oos.close();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }
    static void load()
    {
       if (new File(SAVEPATH).exists())
       {
            try
            {
                FileInputStream fis = new FileInputStream(SAVEPATH);
                ObjectInputStream ois = new ObjectInputStream(fis);
                game = (Game) ois.readObject();
                ois.close();
            }
            catch (Exception e)
            {
                e.printStackTrace();
            }
        }
    }
    private static Twitter getTwitterInstance()
    {
        ConfigurationBuilder cb = new ConfigurationBuilder();
        cb.setDebugEnabled(true)
                .setOAuthConsumerKey(CONSUMER_KEY)
                .setOAuthConsumerSecret(CONSUMER_SECRET)
                .setOAuthAccessToken(ACCESS_TOKEN)
                .setOAuthAccessTokenSecret(ACCESS_TOKEN_SECRET);
        TwitterFactory tf = new TwitterFactory(cb.build());
        return tf.getInstance();
    }
    public static void sendTweet(String line, Twitter twitter) {
        Status status;
        try {
            status = twitter.updateStatus(line);
        } catch (TwitterException e) {;
            e.printStackTrace();
        }
    }
    public static Status getLatestTweet(Twitter twitter) {
        List<Status> statusList = null;

        try {
            statusList = twitter.getUserTimeline("@" + USERNAME);
        } catch (TwitterException e) {
            e.printStackTrace();
        }
       return statusList.get(0);
    }
}