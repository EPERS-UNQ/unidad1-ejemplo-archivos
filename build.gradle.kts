plugins {
    java
    kotlin("jvm") version "1.3.41"
}

group = "ar.edu.unq.unidad1"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    implementation(kotlin("stdlib-jdk8"))
    testImplementation("junit:junit:4.12")
    testImplementation("io.kotlintest:kotlintest-runner-junit4:3.4.2")

    // JSON
    implementation("com.fasterxml.jackson.core:jackson-core:2.10.3")
    implementation("com.fasterxml.jackson.core:jackson-databind:2.10.3")
    implementation("com.fasterxml.jackson.core:jackson-annotations:2.10.3")

    // XML
    implementation("com.thoughtworks.xstream:xstream:1.4.11.1")
}

tasks.withType<org.jetbrains.kotlin.gradle.tasks.KotlinCompile> {
    kotlinOptions.jvmTarget = "1.8"
}