package com.gu.conf.mongodb

import com.mongodb.casbah.{ MongoURI, MongoDB, MongoConnection }
import com.mongodb.{ ServerAddress }
import com.gu.management.mongodb.MongoManagement

trait MongoDataSource extends MongoManagement {

  val mongoDbUsername: String
  val mongoDbPassword: String
  val mongoDbName: String

  val mongoDbUriOption: Option[String]
  val mongoDbHost: Option[String]

  val (connection, db) = mongoDbUriOption match {
    case Some(uri) =>
      val mongoUri = MongoURI(uri)
      val connection: MongoConnection = MongoConnection(mongoUri)
      wireInTimingMetric(connection)
      val db: MongoDB = MongoDB(connection, mongoUri.database)
      (connection, db)
    case None =>
      val host = mongoDbHost get
      val connection = MongoConnection(host.split(",").toList.map(server => new ServerAddress(server)))
      wireInTimingMetric(connection)
      val db = connection(mongoDbName)
      (connection, db)
  }

  if (!db.authenticate(mongoDbUsername, mongoDbPassword))
    throw new Exception("Authentication failed")

  protected def createCollection(name: String) = {
    val collection = db(name)
    collection.slaveOk()
    collection
  }

}