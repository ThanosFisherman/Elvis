plugins {
    base
    kotlin("jvm") version Versions.KOTLIN
}

buildscript {
    repositories {
        jcenter()
        mavenCentral()
    }
    dependencies {}
}
allprojects {

    group = Artifact.ARTIFACT_GROUP

    version = Artifact.VERSION_NAME

    repositories {
        jcenter()
        mavenCentral()
    }
}
