﻿
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

	String[] words = {"mass immigration",
			"resistance movement",
			"multicultural agenda",
			"immigration dilution",
			"anti white",
			"racial issues",
			"black diversity",
			"black rule",
			"africa project",
			"white people",
			"illegal aliens",
			"race war",
			"anti western",
			"emancipate anti",
			"boer war",
			"gunowners rights",
			"africa communist",
			"racial differences",
			"africa history",
			"africa boer",
			"defending borders",
			"borders preserving",
			"white discrimination",
			"aliens immigration",
			"amendment rights",
			"socialist movement",
			"movement racist",
			"white ethnostate",
			"rights freedom",
			"anarchist gangs",
			"illegal immigration",
			"race riots",
			"hispanic offenders",
			"offenders african",
			"race equality",
			"existent threats",
			"rejects equality",
			"border security",
			"mexican border",
			"border war",
			"border wall",
			"multicultural",
			"diversity",
			"freedom",
			"race",
			"nationalist",
			"leftist",
			"violence",
			"boer",
			"marxism",
			"racist",
			"equality",
			"discrimination",
			"borders",
			"nationalism",
			"islam",
			"apartheid",
			"immigrants",
			"zionism",
			"capitalism",
			"constitution",
			"socialist",
			"gangs",
			"multiculturalism",
			"holodomor",
			"communsim",
			"border",
			"racism",
			"marxist",
			"minority",
			"drugs",
			"demilitarization",
			"socialism",
			"diaspora",
			"ethnostate",
			"holocaust",
			"multiracialism",
			"minorities",
			"zionism quoted",
			"civilized hate",
			"whites extermination",
			"racial hate",
			"southern heritage",
			"illegal immigrants",
			"genocide white",
			"muslim immigrants",
			"mob attacks",
			"biodiversity heritage",
			"gun control",
			"black community",
			"racial hatred",
			"white victims",
			"whites forced",
			"black race",
			"racial slurs",
			"attacks white",
			"nationalism race",
			"white privilege",
			"white murders",
			"gang rape",
			"white farmers",
			"african immigrants",
			"holocaust denial",
			"drug cartels",
			"black robbers",
			"sunni jihadists",
			"anti gentilism",
			"american jews",
			"racial slur",
			"white nationalist",
			"european americans",
			"white supremacist",
			"white victim",
			"white countries",
			"anti immigration",
			"christian civilization",
			"anti gun",
			"white conservatives",
			"holocaust denier",
			"fear jews",
			"black mobs",
			"illegal immigrant",
			"white america",
			"immigration reform",
			"murdered white",
			"radical black",
			"white supremacists",
			"asylum seekers",
			"anti christ",
			"diversity genocide",
			"drug gangs",
			"anti communist",
			"white racism",
			"racist white",
			"immigration law",
			"murdering white",
			"sharia law",
			"white forced",
			"muslim immigration",
			"radical islam",
			"racial violence",
			"white confederate",
			"white majority",
			"confederate flags",
			"homosexual",
			"homosexuals",
			"establishment",
			"confederate battle",
			"confederate monument",
			"confederate veterans",
			"confederate monuments",
			"confederate memorial",
			"confederate states",
			"confederate soldiers",
			"confederate symbols",
			"confederate soldier",
			"confederate heritage",
			"confederates",
			"white",
			"black",
			"european",
			"whites",
			"african",
			"jewish",
			"jews",
			"blacks",
			"greeks",
			"rhodesians",
			"muslims",
			"nazis",
			"hispanic",
			"africans",
			"zionist",
			"mexican",
			"jew",
			"hispanics",
			"arab",
			"european american",
			"white man",
			"black people",
			"heritage european",
			"black male",
			"black majority",
			"civilization white",
			"european knights",
			"europe jewish",
			"black males",
			"attacked white",
			"white race",
			"african american",
			"black muslims",
			"white women",
			"zionist groups",
			"anti greek",
			"muslims black",
			"white crime",
			"black thugs",
			"victims white",
			"white students",
			"nationalism black",
			"white americans",
			"black history",
			"black power",
			"white european",
			"innocent white",
			"white hispanic",
			"white american",
			"white folks",
			"american whites",
			"whites group",
			"jewish homeland",
			"white zionism",
			"jews marxism",
			"pro white",
			"jewish groups",
			"freedom party",
			"left wing",
			"nationalist party",
			"black nationalism",
			"political velcraft",
			"people nationalist",
			"ku klux",
			"klux klan",
			"klan ahepa",
			"neo nazis",
			"black panther",
			"al qaeda",
			"nazis kkk",
			"whites amren",
			"splc",
			"nsm",
			"afp",
			"amren",
			"sacp",
			"naacp",
			"darkmoon",
			"ahepa",
			"bollyn",
			"velcraft",
			"isis",
			"adl",
			"kke",
			"kkk",
			"nbpp",
			"nazi",
			"Loyal White Knights of the Ku Klux Klan",
			"Racial Nationalist Party of America",
			"Militant Knights Ku Klux Klan",
			"Advanced White Society facebook",
			"Ku Klos Knights of the Ku Klux Klan",
			"Right Wing Resistance",
			"Council of Conservative Citizens",
			"Golden Dawn",
			"Blood and Honour Social Club",
			"National Socialist Movement, Syracuse, New York",
			"National Socialist MovementNew York (statewide)",
			"Aryan Strikeforce, New York",
			"South Africa Project, NY",
			"White Voice, TheNew York",
			"American Freedom Party",
			"bill johnson",
			"steve smith",
			"michael walsh",
			"pamphlets aryan",
			"karathanasis panagiotis",
			"jared taylor",
			"trayvon martin",
			"donald trump",
			"paul fromm",
			"james kelso",
			"sam dickson",
			"al sharpton",
			"matt heimbach",
			"philippe rushton",
			"trayvon",
			"racial",
			"hustlers",
			"emancipate",
			"conservative",
			"aliens",
			"renegade",
			"americans",
			"communists",
			"muslim",
			"tribune",
			"thugs",
			"conservatives",
			"criminals",
			"ethnic",
			"miscegenation",
			"racists",
			"offenders",
			"minded individuals",
			"dilution european",
			"rights identity",
			"colonial rights",
			"destroy colonial",
			"local activist",
			"american freedom",
			"hate crimes",
			"golden dawn",
			"diversity strength",
			"lack diversity",
			"diversity anti",
			"slogan diversity",
			"disappointed white",
			"white weak",
			"white court",
			"white courthouse",
			"diversity county",
			"posted anti",
			"race hustlers",
			"hate crime",
			"white genocide",
			"crimes racial",
			"white hate",
			"discrimination whites",
			"social nationalists",
			"majority rule",
			"racially motivated",
			"cultural marxism",
			"marxism culture",
			"war rhodesia",
			"apartheid black",
			"war apartheid",
			"african crisis",
			"boer farm",
			"zion traitors",
			"tribally zionism",
			"european guardian",
			"zionism traditional",
			"culture equality",
			"crime racial",
			"promoting culture",
			"greatest nationalist",
			"national socialist"
};
	
	String[] cities = 
		{
				"Manhattan","Brooklyn","Bronx","Queens","Newyorkcity","NewYork","NewJersey","Staten Island","New York City",
				"New York", "New Jersey"
		};
}
