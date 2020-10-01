package com.ktorSample

import io.ktor.application.*
import io.ktor.response.*
import io.ktor.request.*
import io.ktor.routing.*
import io.ktor.http.*
import io.ktor.html.*
import kotlinx.html.*
import kotlinx.css.*
import io.ktor.gson.*
import io.ktor.features.*
import io.ktor.client.*
import io.ktor.client.engine.apache.*
import org.jetbrains.exposed.dao.IntEntity
import org.jetbrains.exposed.dao.IntEntityClass
import org.jetbrains.exposed.dao.id.EntityID
import org.jetbrains.exposed.dao.id.IntIdTable
import org.jetbrains.exposed.sql.Database
import org.jetbrains.exposed.sql.SchemaUtils
import org.jetbrains.exposed.sql.transactions.transaction

fun main(args: Array<String>): Unit = io.ktor.server.netty.EngineMain.main(args)

@Suppress("unused") // Referenced in application.conf
@kotlin.jvm.JvmOverloads
fun Application.module(testing: Boolean = false) {

    Database.connect(
        url = "jdbc:mysql://${HiddenData.databaseInstance}/${HiddenData.defaultDatabase}",
        driver = "com.mysql.jdbc.Driver", user = HiddenData.databaseUserName, password = HiddenData.databasePassword
    )

    transaction {
        SchemaUtils.create(Cities)

        // insert new city. SQL: INSERT INTO Cities (name) VALUES ('St. Petersburg')
        val stPete = City.new {
            name = "St. Petersburg"
        }

        // 'select *' SQL: SELECT Cities.id, Cities.name FROM Cities
        println("Cities: ${City.all()}")
    }

    install(ContentNegotiation) {
        gson {
        }
    }

    val client = HttpClient(Apache) {
    }

    routing {
        route("/api") {
            get("/") {
                call.respondText("HELLO WORLD! from ktor project", contentType = ContentType.Text.Plain)
            }

            get("/v1") {
                call.respondText("HELLO WORLD! from ktor project from v1", contentType = ContentType.Text.Plain)
            }
        }
        get("/") {
            call.respondText("HELLO WORLD! from ktor project via port:8090 ", contentType = ContentType.Text.Plain)
        }
        get("/v1") {
            call.respondText(
                "HELLO WORLD! from ktor project from v1 via post:8090",
                contentType = ContentType.Text.Plain
            )
        }
    }
}

object Cities : IntIdTable() {
    val name = varchar("name", 50)
}

class City(id: EntityID<Int>) : IntEntity(id) {
    companion object : IntEntityClass<City>(Cities)

    var name by Cities.name
}