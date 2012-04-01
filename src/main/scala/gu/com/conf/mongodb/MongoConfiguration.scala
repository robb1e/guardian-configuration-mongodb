package com.gu.conf.mongodb

import com.gu.conf._

trait MongoConfiguration {

  val config: Configuration

  lazy val mongoDbName = config("mongo.database.name")
  lazy val mongoDbUsername = config("mongo.database.username")
  lazy val mongoDbPassword = config("mongo.database.password")

  lazy val mongoDbHost: Option[String] = config.getStringProperty("mongo.database.host")
  lazy val mongoDbUriOption: Option[String] = config.getStringProperty("mongo.database.uri")

}