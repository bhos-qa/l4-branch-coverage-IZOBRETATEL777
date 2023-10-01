import com.github.spotbugs.snom.SpotBugsTask

/*
 * This file was generated by the Gradle 'init' task.
 *
 * This generated file contains a sample Java application project to get you started.
 * For more details on building Java & JVM projects, please refer to https://docs.gradle.org/8.3/userguide/building_java_projects.html in the Gradle documentation.
 */

plugins {
    // Apply the application plugin to add support for building a CLI application in Java.
    application
    id("org.sonarqube") version "4.3.1.3277"
    id("jacoco")
    id("com.github.spotbugs") version "5.1.3"
}

repositories {
    // Use Maven Central for resolving dependencies.
    mavenCentral()
}

dependencies {
    // Use JUnit Jupiter for testing.
    testImplementation("org.junit.jupiter:junit-jupiter:5.9.3")
    testRuntimeOnly("org.junit.platform:junit-platform-launcher")

    //    JaCoCo
    testImplementation("org.jacoco:org.jacoco.agent:0.8.10")

    // This dependency is used by the application.
    implementation("com.google.guava:guava:32.1.1-jre")

    // H2 Database
    implementation("com.h2database:h2:2.2.224")

    // ORM
    implementation("org.hibernate.orm:hibernate-core:6.3.1.Final")

    //    Lombok
    compileOnly("org.projectlombok:lombok:1.18.30")
    annotationProcessor("org.projectlombok:lombok:1.18.30")
    testCompileOnly ("org.projectlombok:lombok:1.18.30")
    testAnnotationProcessor ("org.projectlombok:lombok:1.18.30")

    // SpotBugs and FindSecBugs
    spotbugsPlugins("com.h3xstream.findsecbugs:findsecbugs-plugin:1.12.0")
}

// Apply a specific Java toolchain to ease working on different environments.
java {
    toolchain {
        languageVersion.set(JavaLanguageVersion.of(17))
    }
}

application {
    // Define the main class for the application.
    mainClass.set("com.example.Main")
}

tasks.withType<JacocoReport> {
    classDirectories.setFrom(
            fileTree(
                    mapOf("dir" to "build/classes/java/main",
                            "excludes" to listOf("**/Main.class", "**/config/**", "**/controller/**")
                    )
            )
    )
    reports {
        xml.required = true
        xml.outputLocation = file("$buildDir/reports/tests/JaCoCo/xml/coverage.xml")

        html.required = true
        html.outputLocation = file("$buildDir/reports/tests/JaCoCo/html")
    }
}

tasks.withType<Test> {
    useJUnitPlatform()
    finalizedBy("jacocoTestReport")
    reports {
        junitXml.required = true
        junitXml.outputLocation = file("$buildDir/reports/tests/JUnit/xml")

        html.required = true
        html.outputLocation = file("$buildDir/reports/tests/JUnit/html")
    }
}

sonar {
    properties {
        property("sonar.projectKey", "bhos-qa_l4-branch-coverage-IZOBRETATEL777")
        property("sonar.organization", "bhos-qa")
        property("sonar.host.url", "https://sonarcloud.io")
        property("sonar.jacoco.reportPaths", "build/reports/tests/JaCoCo/xml")
        property("sonar.junit.reportPaths", "build/reports/tests/JUnit/xml")
        property("sonar.java.coveragePlugin", "jacoco")
        property("sonar.java.binaries", "build/classes/java/main")
        property("sonar.java.test.binaries", "build/classes/java/test")
        property("sonar.sources", "src/main/java")
        property("sonar.tests", "src/test/java")
        property("sonar.dynamicAnalysis", "reuseReports")
        property("sonar.coverage.jacoco.xmlReportPaths", "build/reports/tests/JaCoCo/xml/coverage.xml")
        property("sonar.exclusions", "**/Main.class,**/config/**,**/controller/**")
    }
}

spotbugs {
    ignoreFailures = true
    showProgress = true
    showStackTraces = true
}


tasks.withType<SpotBugsTask> {
    reports.create("html") {
        required.set(true)
        outputLocation.set(file("$buildDir/reports/spotbugs/spotbugs.html"))
        setStylesheet("color.xsl")
    }
    reports.create("xml") {
        required.set(true)
        outputLocation.set(file("$buildDir/reports/spotbugs/spotbugs.xml"))
    }
}