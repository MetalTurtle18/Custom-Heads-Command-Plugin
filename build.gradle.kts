import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    kotlin("jvm") version "1.5.10"
}

group = "io.github.metalturtle18"
version = "2.0.0"

repositories {
    mavenCentral()
    maven("https://hub.spigotmc.org/nexus/content/repositories/snapshots")
}

dependencies {
    compileOnly("org.spigotmc:spigot-api:1.17-R0.1-SNAPSHOT")
    implementation("net.axay:kspigot:1.17.2")
}

tasks.withType<KotlinCompile> {
    kotlinOptions.jvmTarget = "16"
}