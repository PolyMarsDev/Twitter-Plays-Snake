import twitter4j.Status;
import java.util.TimerTask;
public class ScheduledTweet extends TimerTask {

    @Override
    public void run() {
        Status tweet = Bot.getLatestTweet(Bot.twitter);
        String move = "";
        if (tweet.getRetweetCount() > tweet.getFavoriteCount() - (tweet.getFavoriteCount()/20) && tweet.getRetweetCount() < tweet.getFavoriteCount() + (tweet.getFavoriteCount()/20))
        {
            move = "";
        }
        else if (tweet.getRetweetCount() > tweet.getFavoriteCount())
        {
            move = "rt";
        }
        else if (tweet.getFavoriteCount() > tweet.getRetweetCount())
        {
            move = "like";
        }
        Bot.sendTweet(Bot.game.run(move), Bot.twitter);
        Bot.save();
    }

}
