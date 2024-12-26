plugins {
    java
    id("org.springframework.boot") version "3.4.0"  // Обновленная версия Spring Boot
    id("io.spring.dependency-management") version "1.1.6"
}

group = "groovy.test"
version = "0.0.1-SNAPSHOT"

java {
    toolchain {
        languageVersion.set(JavaLanguageVersion.of(21))
    }
}

configurations {
    compileOnly {
        extendsFrom(configurations.annotationProcessor.get())
    }
}

repositories {
    mavenCentral()
}

dependencies {

    // Explicit Spring dependencies (ensure using 6.2.1)  
    implementation("org.jgrapht:jgrapht-core:1.5.2")
    implementation("org.springframework:spring-core:6.2.1") 
    implementation("org.springframework:spring-jcl:6.2.0")
    implementation("org.springframework:spring-jcl:6.2.1")
    implementation("org.springframework:spring-aop:6.2.1")
    implementation("org.springframework:spring-tx:6.2.1")
    implementation("org.springframework:spring-jdbc:6.2.1")
    implementation("org.springframework:spring-expression:6.2.1")

    // Apache Tomcat Dependencies
    implementation("org.apache.tomcat.embed:tomcat-embed-websocket:11.0.2")  // Обновленная версия
    implementation("org.apache.tomcat.embed:tomcat-embed-el:11.0.2")  // Обновленная версия
    implementation("org.apache.tomcat.embed:tomcat-embed-core:11.0.2")  // Обновленная версия


    implementation("com.fasterxml.jackson.core:jackson-core:2.18.2")  // Обновленная версия

    // Logging Dependencies
    implementation("org.apache.logging.log4j:log4j-api:3.0.0")  // Обновленная версия
    implementation("org.slf4j:slf4j-api:2.1.0")  // Обновленная версия

    // Other Dependencies
    implementation("org.checkerframework:checker-qual:3.48.3")
    implementation("org.apache.groovy:groovy-xml:5.0.0-alpha-11")
    implementation("org.apache.groovy:groovy-templates:5.0.0-alpha-11")
    implementation("org.apache.groovy:groovy:5.0.0-alpha-11")
    implementation("org.apache.groovy:groovy-bom:5.0.0-alpha-11")
    implementation("io.micrometer:micrometer-commons:1.14.2")

    compileOnly("org.projectlombok:lombok:1.18.36")
    annotationProcessor("org.projectlombok:lombok:1.18.36")

}

dependencyManagement {
    imports {
        mavenBom("org.springframework.boot:spring-boot-dependencies:3.4.0")
    }
    dependencies {
        dependency("org.springframework:spring-core:6.2.1")
    }
}

configurations.all {
    resolutionStrategy {
        eachDependency {
            if (requested.group == "org.springframework") {
                useVersion("6.2.1")
            }
if (requested.group == "com.fasterxml.jackson") {
                useVersion("2.18.2")
            }
            if (requested.group == "org.apache.tomcat.embed") {
                useVersion("11.0.2")
            }
            if (requested.group == "org.apache.logging.log4j") {
                useVersion("3.0.0")
            }
            if (requested.group == "org.slf4j") {
                useVersion("2.1.0")
            }
        }
        force("org.springframework:spring-core:6.2.1")
        force ("io.micrometer:micrometer-commons:1.14.2")
        force ("io.micrometer:micrometer-observation:1.14.2")
    }
}

tasks.withType<Test> {
    useJUnitPlatform()
}
