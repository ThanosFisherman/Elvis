import org.jetbrains.kotlin.konan.properties.hasProperty
import java.util.*

plugins {
    java
    kotlin("jvm")
    // Documentation for our code
    id("org.jetbrains.dokka") version Versions.DOKKA_VERSION
    // Publication to bintray
    id("com.jfrog.bintray") version Versions.BINTRAY_VERSION
    // Maven publication
    `maven-publish`
}


configure<JavaPluginConvention> {
    sourceCompatibility = JavaVersion.VERSION_1_8
    targetCompatibility = JavaVersion.VERSION_1_8
}

tasks.withType<org.jetbrains.kotlin.gradle.tasks.KotlinCompile> {
    kotlinOptions {
        jvmTarget = "1.8"
    }
}
dependencies {
    implementation(fileTree(mapOf("dir" to "libs", "include" to listOf("*.jar"))))
    addLibModuleDependencies()
    addTestDependencies()
}

// Configure existing Dokka task to output HTML to typical Javadoc directory
val dokka by tasks.getting(org.jetbrains.dokka.gradle.DokkaTask::class) {
    outputFormat = "html"
    outputDirectory = "$buildDir/javadoc"
}

// Create dokka Jar task from dokka task output
val dokkaJar by tasks.creating(Jar::class) {
    group = JavaBasePlugin.DOCUMENTATION_GROUP
    description = "Assembles Kotlin docs with Dokka"
    classifier = "javadoc"
    // dependsOn(dokka) not needed; dependency automatically inferred by from(dokka)
    from(dokka)
}

// Create sources Jar from main kotlin sources
val sourcesJar by tasks.creating(Jar::class) {
    group = JavaBasePlugin.DOCUMENTATION_GROUP
    description = "Assembles sources JAR"
    classifier = "sources"
    from(project.the<SourceSetContainer>()["main"].allSource)
}

val artifactDir = "$buildDir/libs/${project.name}.jar"

publishing {
    publications {
        create<MavenPublication>(Artifact.ARTIFACT_NAME) {
            groupId = Artifact.ARTIFACT_GROUP
            artifactId = Artifact.ARTIFACT_NAME
            version = Artifact.VERSION_NAME
            from(components["java"])
            artifacts {
                //artifact(artifactDir)
                artifact(sourcesJar)
                artifact(dokkaJar)
            }

            pom.withXml {
                asNode().apply {
                    appendNode("description", Artifact.POM_DESC)
                    appendNode("name", Artifact.LIBRARY_NAME)
                    appendNode("url", Artifact.POM_URL)
                    appendNode("licenses").appendNode("license").apply {
                        appendNode("name", Artifact.POM_LICENSE_NAME)
                        appendNode("url", Artifact.POM_LICENSE_URL)
                        appendNode("distribution", Artifact.POM_LICENSE_DIST)
                    }
                    appendNode("developers").appendNode("developer").apply {
                        appendNode("id", Artifact.POM_DEVELOPER_ID)
                        appendNode("name", Artifact.POM_DEVELOPER_NAME)
                    }
                    appendNode("scm").apply {
                        appendNode("url", Artifact.POM_SCM_URL)
                    }
                }
            }
        }
    }
}

bintray {
    val properties = Properties()
    properties.load(project.rootProject.file("local.properties").inputStream())

    // Getting bintray user and key from properties file or command line
    user =
        if (properties.hasProperty("bintrayUser")) properties.getProperty("bintrayUser") as String else "thanosfisherman"
    key =
        if (properties.hasProperty("bintrayKey")) properties.getProperty("bintrayKey") as String else ""

    // Automatic publication enabled
    publish = true
    dryRun = false

    // Set maven publication onto bintray plugin
    setPublications(Artifact.ARTIFACT_NAME)

    // Configure package
    pkg.apply {
        repo = "maven"
        name = Artifact.BINTRAY_NAME
        setLicenses("Apache-2.0")
        setLabels("kotlin", "optional", "java", "elvis")
        vcsUrl = Artifact.POM_SCM_URL
        websiteUrl = Artifact.POM_URL
        issueTrackerUrl = Artifact.POM_ISSUE_URL
        githubRepo = Artifact.GITHUB_REPO
        githubReleaseNotesFile = Artifact.GITHUB_README

        // Configure version
        version.apply {
            name = Artifact.VERSION_NAME
            desc = Artifact.POM_DESC
            released = Date().toString()
            vcsTag = Artifact.VERSION_NAME
        }
    }
}
