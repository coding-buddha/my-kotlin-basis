plugins {
    kotlin("jvm")
}

dependencies {

    // jpa & mysql
    implementation("org.springframework.boot:spring-boot-starter-data-jpa")
    implementation("mysql:mysql-connector-java")

    // web
    implementation("org.springframework.boot:spring-boot-starter-web")

    // flyway
    implementation("org.flywaydb:flyway-core")

    implementation("org.jetbrains.kotlin:kotlin-reflect")
    implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")
    implementation("com.fasterxml.jackson.module:jackson-module-kotlin")
    testImplementation("org.springframework.boot:spring-boot-starter-test")
}