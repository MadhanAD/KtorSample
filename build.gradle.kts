import org.jetbrains.kotlin.gradle.dsl.Coroutines
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

val ktor_version: String by project
val kotlin_version: String by project
val logback_version: String by project

buildscript {
    repositories { jcenter() }

    dependencies {
        classpath("com.github.jengelman.gradle.plugins:shadow:2.0.4")
    }
}

plugins {
    application
    kotlin("jvm") version "1.4.0"
    id("com.github.johnrengelman.shadow") version "5.0.0"
}

group = "com.ktorSample"
version = "0.0.3"

application {
    mainClassName = "io.ktor.server.netty.EngineMain"
}

repositories {
    mavenLocal()
    jcenter()
    maven { url = uri("https://kotlin.bintray.com/ktor") }
    maven { url = uri("https://kotlin.bintray.com/kotlin-js-wrappers") }
}

dependencies {
    val exposedVersion : String = "0.24.1"
    implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8:$kotlin_version")
    implementation("io.ktor:ktor-server-netty:$ktor_version")
    implementation("ch.qos.logback:logback-classic:$logback_version")
    implementation("io.ktor:ktor-server-core:$ktor_version")
    implementation("io.ktor:ktor-html-builder:$ktor_version")
    implementation("org.jetbrains:kotlin-css-jvm:1.0.0-pre.31-kotlin-1.2.41")
    implementation("io.ktor:ktor-gson:$ktor_version")
    implementation("io.ktor:ktor-client-core:$ktor_version")
    implementation("io.ktor:ktor-client-core-jvm:$ktor_version")
    implementation("io.ktor:ktor-client-apache:$ktor_version")

//    implementation("org.jetbrains.exposed:exposed-core:$exposedVersion")
//    implementation("org.jetbrains.exposed:exposed-dao:$exposedVersion")
//    implementation("org.jetbrains.exposed:exposed-jdbc:$exposedVersion")

    implementation("org.jetbrains.exposed", "exposed-core", exposedVersion)
    implementation("org.jetbrains.exposed", "exposed-dao", exposedVersion)
    implementation("org.jetbrains.exposed", "exposed-jdbc", exposedVersion)

    implementation("mysql:mysql-connector-java:5.1.48")

    testImplementation("io.ktor:ktor-server-tests:$ktor_version")
}

kotlin.sourceSets["main"].kotlin.srcDirs("src")
kotlin.sourceSets["test"].kotlin.srcDirs("test")

sourceSets["main"].resources.srcDirs("resources")
sourceSets["test"].resources.srcDirs("testresources")
