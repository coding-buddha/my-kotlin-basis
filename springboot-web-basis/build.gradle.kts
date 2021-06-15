plugins {
    kotlin("jvm")
}

dependencies {

    /**
     * group:module:version
     */

    // web
    implementation("org.springframework.boot:spring-boot-starter-web")

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
}