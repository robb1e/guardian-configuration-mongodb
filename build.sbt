import com.typesafe.sbtscalariform._

organization := "com.gu"

name := "mongodb-configuration"

version := "0.1-SNAPAHOT"

scalaVersion := "2.9.1"

resolvers ++= Seq(
  "Guardian Github Releases" at "http://guardian.github.com/maven/repo-releases"
)

libraryDependencies ++= Seq(
  "com.gu" %% "management-mongo" % "5.6.4",
  "com.gu" %% "configuration" % "3.6"
)

seq(ScalariformPlugin.settings: _*)

publishTo <<= (version) { version: String =>
    val publishType = if (version.endsWith("SNAPSHOT")) "snapshots" else "releases"
    Some(
        Resolver.file(
            "guardian github " + publishType,
            file(System.getProperty("user.home") + "/robb1e.github.com/maven/repo-" + publishType)
        )
    )
}