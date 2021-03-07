plugins {
    kotlin("jvm")

    // flyway
    val flywayVersion = "7.6.0"
    id("org.flywaydb.flyway") version flywayVersion
}

dependencies {

    // jpa & mysql
    implementation("org.springframework.boot:spring-boot-starter-data-jpa")
    implementation("mysql:mysql-connector-java")

    // web
    implementation("org.springframework.boot:spring-boot-starter-web")

    implementation("com.fasterxml.jackson.module:jackson-module-kotlin")
    implementation("org.jetbrains.kotlin:kotlin-reflect")
    implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")
    testImplementation("org.springframework.boot:spring-boot-starter-test")
}

flyway {
    // FlywayExtension.class 내에 org.flywaydb.gradle 내에 map 파일 확인
    url = "jdbc:mysql://localhost:13309/bookstore?useSSL=false&allowPublicKeyRetrieval=true&useTimezone=true&serverTimezone=Asia/Seoul"
    user = "root"
    password = "rootpass"
    encoding = "UTF-8"
}