plugins {
    kotlin("jvm")
}

dependencies {

    /**
     * group:module:version
     */

    // web
    implementation("org.springframework.boot:spring-boot-starter-web")

    // redis
    implementation("org.springframework.boot:spring-boot-starter-data-redis")
    implementation("org.springframework.boot:spring-boot-starter-data-redis-reactive")

    // logging
    implementation("io.github.microutils:kotlin-logging:1.12.5")

    // basic
    implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")
    implementation("org.jetbrains.kotlin:kotlin-reflect")
    implementation("com.fasterxml.jackson.module:jackson-module-kotlin")

    // test
    testImplementation("org.springframework.boot:spring-boot-starter-test")
    testImplementation("io.kotest:kotest-assertions-core:4.4.3")
    testImplementation("io.mockk:mockk:1.10.6")
    testImplementation("it.ozimov:embedded-redis:0.7.3"){
        exclude(group = "org.slf4j", module = "slf4j-simple")
    }
    testImplementation("io.projectreactor:reactor-test:3.2.3.RELEASE")
}