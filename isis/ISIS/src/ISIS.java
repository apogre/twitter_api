import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.datastax.driver.core.BoundStatement;
import com.datastax.driver.core.Cluster;
import com.datastax.driver.core.PreparedStatement;
import com.datastax.driver.core.Session;

import twitter4j.FilterQuery;
import twitter4j.HashtagEntity;
import twitter4j.MediaEntity;
import twitter4j.StallWarning;
import twitter4j.Status;
import twitter4j.StatusDeletionNotice;
import twitter4j.StatusListener;
import twitter4j.TwitterException;
import twitter4j.TwitterStream;
import twitter4j.TwitterStreamFactory;
import twitter4j.URLEntity;
import twitter4j.User;
import twitter4j.UserMentionEntity;
import twitter4j.conf.ConfigurationBuilder;


public final class ISIS {       

	private static final String username = "isis_twitter";
	private static final String password = "cipstweets";
	private static Session session;

	private static final String access_token = "1887750518-8AaNFMQ5rJGIF3Ol9R9HwkQqh7tRgp5SOFLYlWp";
	private static final String access_token_secret = "zZJlP3oXJHoopPUEOEBXb2CXqnaSjUFPyAJPlfUADMH2y";
	private static final String consumer_key = "5es2IDWv0YDmeqYgDWt8dnlow";
	private static final String consumer_secret = "NarkLyHm0QNGQKps5NuIuYUAsNLJj2AZq3KfaDZ1OLeXNv8P6j";

	public static void main(String[] args) throws TwitterException, IOException, SQLException { 

		connectToCluster();

		StatusListener listener = new StatusListener() {

			@Override    	
			public void onStatus(Status status) {
//				System.out.println("@" + status.getUser().getScreenName() + " : "  + status.getText()); 
				try
				{
					insertTweetsIntoDB(status);
					insertUserIntoDB(status);
				}
				catch(Exception e)
				{
					e.printStackTrace();
				}

			} // End of void onStatus           	

			@Override
			public void onDeletionNotice(StatusDeletionNotice statusDeletionNotice) {
			}

			@Override
			public void onTrackLimitationNotice(int numberOfLimitedStatuses) {
			}

			@Override
			public void onScrubGeo(long userId, long upToStatusId) {
			}

			@Override
			public void onStallWarning(StallWarning warning) {
			}

			@Override
			public void onException(Exception ex) {
			}

		}; // End of listener

		// ****************** Twitter Access Key configuration **********************
		ConfigurationBuilder cb = new ConfigurationBuilder();
		cb.setDebugEnabled(true)
		.setOAuthConsumerKey(consumer_key)
		.setOAuthConsumerSecret(consumer_secret)
		.setOAuthAccessToken(access_token)
		.setOAuthAccessTokenSecret(access_token_secret);
		
		TwitterStream twitterStream = new TwitterStreamFactory(cb.build()).getInstance();
		twitterStream.addListener(listener);
		
		FilterQuery fq = new FilterQuery();
		fq.track(IKeywords.words);
		
		twitterStream.filter(fq);
	}

	private static void connectToCluster() {
		//Creating Cluster object
		Cluster cluster = Cluster.builder().addContactPoint("10.107.24.90")
				.withCredentials(username, password).build();

		//Creating Session object
		session = cluster.connect("daesh");
	}
	
	
//	CREATE TABLE tweets (
//			  ti bigint,     // tweet id 
//			  ui bigint,     // user id
//			  t text,    // tweet text 
//			  h set<text>,    // hashtags
//			  tm timestamp, // timestamp
//			  rl set<text>, // urls 
//			  i set<text>,  // urls of images
//			  un text,    // user name
//			  ut timestamp, // user timestamp
//			  um set<text>, // user mentions
//
//			  l list<double>,     // (latitude, longitude)
//			  p text,       // place
//
//			  fc int,    // favorite count
//
//			  rc int,         // retweet count 
//			  rti bigint,    // retweet id  - id of the tweet of which this tweet is a retweet
//
//			  s text,         // source
//			  
//			  r2u bigint,    // reply to user id
//			  r2s bigint,    // reply to status id
//
//			  r2n text,    // reply to screen name
//			  co set<bigint>,    // contributors
//			  la text,    // language
//			  ps boolean,    // possibly sensitive
//			  qs bigint,    // quoted_status_id
//			  sc text,    // scopes
//			  rts boolean,    // retweeted or not
//			  rtn text,    // retweet - screenname of original poster
//			  tr boolean,    // truncated 
//			  wic set<text>,// witheld in countries
//			  
//			  PRIMARY KEY (ti, ui)
//			);
	private static void insertTweetsIntoDB(Status status) {

		User user = status.getUser();

		long tid = status.getId();    // tweet id 
		long uid = user.getId();    // user id
		String tweet = status.getText();    		// tweet text 

		Set<String> hashtags = new HashSet<String>();
		for(HashtagEntity he : status.getHashtagEntities())    // hashtags
		{
			hashtags.add(he.getText());
		}

		Date timestamp = status.getCreatedAt();   		// timestamp
		Set<String> urls = new HashSet<String>(); 	// urls
		for(URLEntity ue : status.getURLEntities())
		{
			urls.add(ue.getExpandedURL());
		}

		Set<String> images = new HashSet<String>();  	// urls of images
		for(MediaEntity me : status.getMediaEntities())
		{
			images.add(me.getExpandedURL());
		}

		String user_name = user.getScreenName();    	// user name
		Date user_timestamp = user.getCreatedAt(); 			// user timestamp

		Set<String> user_mentions = new HashSet<String>(); 	// user mentions
		for(UserMentionEntity ume : status.getUserMentionEntities())
		{
			user_mentions.add(ume.getScreenName());
		}

		List<Double> co_ord = new ArrayList<Double>();     // (latitude, longitude)
		if(status.getGeoLocation() != null)
		{
			co_ord.add(status.getGeoLocation().getLatitude());
			co_ord.add(status.getGeoLocation().getLongitude());
		}

		String place = "";
		if(status.getPlace()	!= null)
			place = status.getPlace().getName();          // place

		int fav_count = status.getFavoriteCount();            // favorite count

		int retweet_count = status.getRetweetCount();            // retweet count 
		
		long retweet_id = 0;
		if(status.getRetweetedStatus() != null)
		 retweet_id = status.getRetweetedStatus().getId();    // retweet id  - id of the tweet of which this tweet is a retweet

		String source = status.getSource();          // source

		long reply_2_user_id = status.getInReplyToUserId();    // reply to user id
		long reply_2_status_id = status.getInReplyToStatusId();    // reply to status id

		// Insert now
		PreparedStatement statement = session.prepare(IKeywords.insert_query_tweets);
		BoundStatement bound = statement.bind();
		
		bound.setLong("tid", tid);
		bound.setLong("uid", uid);
		bound.setString("tweet", tweet);
		if(hashtags != null)
		bound.setSet("hashtags", hashtags);
		
		bound.setDate("timestamp", timestamp);

		if(urls != null)
		bound.setSet("urls", urls);
		
		if(images != null)
		bound.setSet("images", images);
		
		bound.setString("user_name", user_name);
		bound.setDate("user_timestamp", user_timestamp);
		if(user_mentions != null)
		bound.setSet("user_mentions", user_mentions);
		if(co_ord != null)
		bound.setList("co_ord", co_ord);

		if(place != null)
		bound.setString("place", place);
		bound.setInt("fav_count", fav_count);
		bound.setInt("retweet_count", retweet_count);

		bound.setLong("retweet_id", retweet_id);
		if(source != null)
		bound.setString("source", source);

		bound.setLong("reply_2_user_id", reply_2_user_id);
		bound.setLong("reply_2_status_id", reply_2_status_id);
		
		String reply_2_scr_name = status.getInReplyToScreenName(); // reply to screen name
		Set<Long> contributors = null;    // contributors
		if( status.getContributors() != null)
		{
			contributors = new HashSet<Long>();
			for(long contr : status.getContributors())
				contributors.add(contr);
		}
		
		String language = status.getLang();    // language
		boolean possibly_sensitive = status.isPossiblySensitive();    // possibly sensitive
		
		long quoted_status_id = status.getQuotedStatusId();    // quoted_status_id
		
		String scopes = null;
		if(status.getScopes() != null)
		scopes = status.getScopes().toString();    // scopes
		
		boolean is_retweet = status.isRetweet();    // is this tweet a retweet or not
		boolean is_retweeted = status.isRetweet();    // is this retweeted or not
		
		String retweet_author_name = null;
		if(is_retweet)
		retweet_author_name = status.getRetweetedStatus().getUser().getScreenName();    // retweet - screenname of original poster
		
		boolean truncated = status.isTruncated();    // truncated 
		Set<String> witheld_in_countries = null;				  // witheld in countries
		
		if(status.getWithheldInCountries() != null)
		{
			for(String country : status.getWithheldInCountries() )
			{
				if(witheld_in_countries == null)
					witheld_in_countries = new HashSet<String>();
				witheld_in_countries.add(country);
			}
		}
		
		if(reply_2_scr_name != null)
		bound.setString("reply_2_scr_name", reply_2_scr_name);
		if(contributors != null)
		bound.setSet("contributors", contributors);
		bound.setString("language", language);
		bound.setBool("possibly_sensitive", possibly_sensitive);
		
		bound.setLong("quoted_status_id", quoted_status_id);
		if(scopes != null)
		bound.setString("scopes", scopes);
		bound.setBool("is_retweet", is_retweet);
		bound.setBool("is_retweeted", is_retweeted);
		bound.setString("retweet_author_name", retweet_author_name);
		bound.setBool("truncated", truncated);
		if(witheld_in_countries != null)
		bound.setSet("witheld_in_countries", witheld_in_countries);
		
		session.execute(bound);
	}

//	CREATE TABLE users (
//			  uid bigint,
//			  created_at timestamp,
//			  is_default_profile boolean,
//			  is_default_profile boolean,
//			  description text,
//			  favourites_count int,
//			  followers_count int,
//			  friends_count int,
//			  geo_enabled boolean,
//			  lang text,
//			  listed_count int,
//			  location text,
//			  name text,
//			  profile_background_color text,
//			  profile_background_image_url text,
//			  profile_banner_url text,
//			  profile_image_url text,
//			  protected boolean,
//			  screen_name text,
//			  time_zone text,
//			  tweets_count int,
//			  url text,
//			  urls set<text>,
//			  utc_offset int,
//			  withheld_in_countries set<text>,
//			  PRIMARY KEY (uid)
//			) 
	private static void insertUserIntoDB(Status status) {
		User user = status.getUser();
		long uid = user.getId();
		Date timestamp = user.getCreatedAt();
		boolean is_default_profile = user.isDefaultProfile();
		boolean is_default_profile_image = user.isDefaultProfileImage();
		String description = user.getDescription();
		
		int favourites_count = user.getFavouritesCount();
		int followers_count = user.getFollowersCount();
		int friends_count = user.getFriendsCount();
		
		boolean geo_enabled = user.isGeoEnabled();
		String lang = user.getLang();
		int listed_count = user.getListedCount();
		String location = user.getLocation();
		String name = user.getName();
		
		String profile_background_color = user.getProfileBackgroundColor();
		String profile_background_image_url = user.getProfileBackgroundImageURL();
		String profile_banner_url = user.getProfileBannerURL();
		String profile_image_url = user.getProfileImageURL();
		
		boolean isProtected = user.isProtected();
		String screen_name = user.getScreenName();
		String time_zone = user.getTimeZone();
		
		int tweets_count = user.getStatusesCount();
		String url = user.getURL();
		Set<String> urls = new HashSet<String>();
		if(user.getDescriptionURLEntities() != null)
		{
			for(URLEntity entity : user.getDescriptionURLEntities())
			{
				urls.add(entity.getExpandedURL());
			}
		}
		
		int utc_offset = user.getUtcOffset();
		Set<String> withheld_in_countries = new HashSet<String>();
		if(user.getWithheldInCountries() != null)
		{
			for(String country : user.getWithheldInCountries())
			{
				withheld_in_countries.add(country);
			}
		}

		// Insert now
		PreparedStatement statement = session.prepare(IKeywords.insert_query_users);
		BoundStatement bound = statement.bind();
		
		bound.setLong("uid", uid);
		bound.setDate("created_at", timestamp);
		bound.setBool("default_profile", is_default_profile);
		bound.setBool("default_profile_image", is_default_profile_image);
		bound.setString("description", description);
		bound.setInt("favourites_count", favourites_count);
		bound.setInt("followers_count", followers_count);
		bound.setInt("friends_count", friends_count);
		
		bound.setBool("geo_enabled", geo_enabled);
		bound.setString("lang", lang);
		bound.setInt("listed_count", listed_count);
		bound.setString("location", location);
		bound.setString("name", name);
		
		bound.setString("profile_background_color", profile_background_color);
		bound.setString("profile_background_image_url", profile_background_image_url);
		bound.setString("profile_banner_url", profile_banner_url);
		bound.setString("profile_image_url", profile_image_url);
		
		bound.setBool("protected", isProtected);
		bound.setString("screen_name", screen_name);
		bound.setString("time_zone", time_zone);
		bound.setInt("tweets_count", tweets_count);
		bound.setString("url", url);
		
		bound.setSet("urls", urls);
		bound.setInt("utc_offset", utc_offset);
		bound.setSet("withheld_in_countries", withheld_in_countries);

		session.execute(bound);
	}
	
	@SuppressWarnings("unused")
	private static boolean isNumericalArgument(String argument) {
		String args[] = argument.split(",");
		boolean isNumericalArgument = true;
		for (String arg : args) {
			try {
				Integer.parseInt(arg);
			} catch (NumberFormatException nfe) {
				isNumericalArgument = false;
				break;
			}
		}
		return isNumericalArgument;
	}
} // End of class Crawler
