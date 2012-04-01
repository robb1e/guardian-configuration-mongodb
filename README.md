# Guardian Configuration MongoDB

## Purpose

The idea behind this is two fold; reduce copy and paste code and fix configuration names for mongo connection strings in properties and configuration, so they are the same across applications.

## Usage

Add the following resolvers:

	"robb1e Github Releases" at "http://robb1e.github.com/maven/repo-releases"
	"robb1e Github Snapshots" at "http://robb1e.github.com/maven/repo-snapshots"

Include the dependency:

	"com.gu" %% "mongodb-configuration"

You need to include Guardian Configuration and Guardian Management Mongo in order to use this.

In your chosen configuration object, add these traits

	object ConfigurationManager extends MongoDataSource with MongoConfiguration { ... }

Ensure you include a lazy val config

	lazy val config = ConfigurationFactory getConfiguration ("your-app", "conf/your-app")

Then you can call into the trait to create collections

	lazy val myCollection = createCollection("my")

