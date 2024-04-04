plugins {
    id("java")
    id("io.qameta.allure") version "2.11.2"
    id("io.qameta.allure-download") version "2.11.2"
    id("io.qameta.allure-adapter-base") version "2.11.2"
}

group = "br.com.exam"
version = "1.0-SNAPSHOT"

allure {
    version.set("2.25.0")

    adapter {
        frameworks {
            junit5 {
                autoconfigureListeners.set(true)
                adapterVersion.set("2.25.0")
                enabled.set(true)
            }
        }
    }
}

repositories {
    mavenCentral()
}

dependencies {
    implementation(platform("org.junit:junit-bom:5.9.1"))
    implementation("org.junit.jupiter:junit-jupiter")
    implementation("org.slf4j:slf4j-simple:2.0.7")
    implementation("io.rest-assured:rest-assured:5.4.0")
    implementation("org.seleniumhq.selenium:selenium-java:4.18.1")
    implementation("com.fasterxml.jackson.core:jackson-databind:2.16.1")
    implementation("com.fasterxml.jackson.datatype:jackson-datatype-jsr310:2.16.1")
    implementation("io.qameta.allure:allure-java-commons:2.25.0")
    implementation("io.qameta.allure:allure-junit5:2.25.0")
    implementation("io.qameta.allure:allure-junit-platform:2.25.0")
    implementation("org.junit.jupiter:junit-jupiter-api:5.9.2")
    runtimeOnly("org.junit.jupiter:junit-jupiter-engine:5.9.2")
}

tasks.test {
    val driver : String = (project.findProperty("driver") ?: "") as String
    val profile : String = (project.findProperty("profile") ?: "dev") as String

    println("Executing the tests with '${profile.uppercase()}' profile")

    systemProperties["profile"] = profile
    systemProperties["web.driver"] = driver

    useJUnitPlatform()
}
