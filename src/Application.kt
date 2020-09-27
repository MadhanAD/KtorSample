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

fun main(args: Array<String>): Unit = io.ktor.server.netty.EngineMain.main(args)

@Suppress("unused") // Referenced in application.conf
@kotlin.jvm.JvmOverloads
fun Application.module(testing: Boolean = false) {
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
        }
        get("/") {
            call.respondText("HELLO WORLD! from ktor project via port:8090 ", contentType = ContentType.Text.Plain)
        }
    }
}
