plugins {
    id("java")
}

group = "de.thro.vv"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    testImplementation(platform("org.junit:junit-bom:5.10.0"))
    testImplementation("org.junit.jupiter:junit-jupiter")

    implementation("com.openai:openai-java:1.5.1")
}


tasks.test {
    useJUnitPlatform()
}