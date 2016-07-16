
public interface IKeywords {

	String columns_tweets = "tid,"
			+ "uid,"
			+ "co_ord,"
			+ "contributors,"
			+ "fav_count,"
			+ "hashtags,"
			+ "images,"
			+ "is_retweet,"
			+ "is_retweeted,"
			+ "language,"
			+ "place,"
			+ "possibly_sensitive,"
			+ "quoted_status_id,"
			+ "reply_2_scr_name,"
			+ "reply_2_status_id,"
			+ "reply_2_user_id,"
			+ "retweet_author_name,"
			+ "retweet_count,"
			+ "retweet_id,"
			+ "scopes,"
			+ "source,"
			+ "timestamp,"
			+ "truncated,"
			+ "tweet,"
			+ "urls,"
			+ "user_mentions,"
			+ "user_name,"
			+ "user_timestamp,"
			+ "witheld_in_countries";

	String insert_query_tweets = "INSERT INTO tweets ("
			+ columns_tweets
			+ ") "
			+ "VALUES "
			+ "("
			+ "?,?,?,?,  ?,?,?,?,  ?,?,?,?,  ?,?,?,?,  ?,?,?,?,"
			+ "?,?,?,?,  ?,?,?,?, ?"
			+ ");";
	
	String columns_users = "uid,"
			+ "created_at,"
			+ "default_profile,"
			+ "default_profile_image,"
			+ "description,"
			+ "favourites_count,"
			+ "followers_count,"
			+ "friends_count,"
			+ "geo_enabled,"
			+ "lang,"
			+ "listed_count,"
			+ "location,"
			+ "name,"
			+ "profile_background_color,"
			+ "profile_background_image_url,"
			+ "profile_banner_url,"
			+ "profile_image_url,"
			+ "protected,"
			+ "screen_name,"
			+ "time_zone,"
			+ "tweets_count,"
			+ "url,"
			+ "urls,"
			+ "utc_offset,"
			+ "withheld_in_countries ";
	
	String insert_query_users = "INSERT INTO users ("
			+ columns_users
			+ ") "
			+ " VALUES "
			+ "("
			+ "?,?,?,?,  ?,?,?,?,  ?,?,?,?,  ?,?,?,?,  ?,?,?,?,"
			+ "?,?,?,?,  ?"
			+ ");";

	String[] words = {"european culture",
			"culture pride",
			"dilution european",
			"multicultural agenda",
			"anti white",
			"racial issues",
			"black diversity",
			"black rule",
			"africa project",
			"white people",
			"race war",
			"anti western",
			"racial differences",
			"white discrimination",
			"movement racist",
			"white ethnostate",
			"race riots",
			"hispanic offenders",
			"offenders african",
			"race equality",
			"diversity anti",
			"disappointed white",
			"white weak",
			"black rule",
			"race hustlers",
			"white genocide",
			"western civilization",
			"european american",
			"white hate",
			"american people",
			"white man",
			"black people",
			"discrimination whites",
			"heritage european",
			"anti western",
			"black crime",
			"racially motivated",
			"black nationalism",
			"race race",
			"african national",
			"black majority",
			"crime black",
			"empowerment black",
			"rule black",
			"civilization white",
			"thoughts white",
			"guardian european",
			"aryan bootlegs",
			"crisis afrikaner",
			"american hope",
			"pamphlets aryan",
			"tales white",
			"black genocide",
			"debates black",
			"white discrimination",
			"mob black",
			"attacked white",
			"white race",
			"ku klux",
			"african american",
			"klux klan",
			"race play",
			"white ethnostate",
			"vision white",
			"racist zionist",
			"racist interlopers",
			"media racial",
			"killed black",
			"black thugs",
			"victims white",
			"forced integration",
			"white americans",
			"black history",
			"immigration islam"};
	
	double [][]location ={{-74,40},{-73,41}};
}
