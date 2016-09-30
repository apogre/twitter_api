from tweepy import Stream
from tweepy import OAuthHandler
from tweepy.streaming import StreamListener
from pymongo import MongoClient
import ast
import json

client = MongoClient()
col = client.local.epl_tweets


#consumer key, consumer secret, access token, access secret.
ckey="v56jERn7xh9xRoIxX2JGSeeKl"
csecret="Oq10zcJC0SomweK790KRZoFT7CuudiotePqwBJZPGPM2BA09wX"
atoken="300823393-ksw43zLieQopGX1YGjmiwyigNpHYAbHSs5P8r17d"
asecret="YiWcTljsahRdh243kDj4NZrx4AErYPMJ2XWeIEWaiQKA0"

keywords = ['AFC Bournemouth', 'Bournemouth', 'Dean Court', 'Arsenal', 'London', 'Emirates Stadium', 'Burnley', 'Burnley', 'Turf Moor', 'Chelsea', 'London', 'Stamford Bridge', 'Crystal Palace', 'London', 'Selhurst Park', 'Everton', 'Liverpool', 'Goodison Park', 'Hull City', 'Hull', 'KCOM Stadium', 'Leicester City', 'Leicester', 'King Power Stadium', 'Liverpool', 'Liverpool', 'Anfield', 'Manchester City', 'Manchester', 'City of Manchester Stadium', 'Manchester United', 'Manchester', 'Old Trafford', 'Middlesbrough', 'Middlesbrough', 'Riverside Stadium', 'Southampton', 'Southampton', "St Mary's Stadium", 'Stoke City', 'Stoke-on-Trent', 'Bet365 Stadium', 'Sunderland', 'Sunderland', 'Stadium of Light', 'Swansea City', 'Swansea', 'Liberty Stadium', 'Tottenham Hotspur', 'London', 'White Hart Lane', 'Watford', 'Watford', 'Vicarage Road', 'West Bromwich Albion', 'West Bromwich', 'The Hawthorns', 'West Ham United', 'London', 'Olympic Stadium', 'Howe Eddie', 'Wenger Arsne', 'Dyche Sean', 'Conte Antonio', 'Pardew Alan', 'Koeman Ronald', 'Phelan Mike', 'Ranieri Claudio', 'Klopp Jrgen', 'Guardiola Pep', 'Mourinho Jos', 'Karanka Aitor', 'Puel Claude', 'Hughes Mark', 'Moyes David', 'Guidolin Francesco', 'Pochettino Mauricio', 'Mazzarri Walter', 'Pulis Tony', 'Bilic Slaven','AFCB','Arsenal','AVFC','CFC','COYS','CPFC','EFC','LCFC','LFC','MCFC','MUFC','NCFC','NUFC','SaintsFC','SCFC','SAFC','Swans','WatfordFC','WBA','WHUFC','BPL','epl','premier league','english premier league']

class listener(StreamListener):	
	def on_data(self, data):
		data = json.loads(data)
		try:
			print data['text']
		except:
			pass
		# print "======================"
		col.insert_one(data)
		return(True)


	def on_error(self, status):
		print status

def stream():
	auth = OAuthHandler(ckey, csecret)
	auth.set_access_token(atoken, asecret)
	twitterStream = Stream(auth, listener())
	twitterStream.filter(track=keywords)

stream()