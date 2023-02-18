plugins {
    id("com.android.application") version "7.2.1" apply false
    id("com.android.library") version "7.2.1" apply false
    id("org.jetbrains.kotlin.android") version "1.7.10" apply false
    id("com.google.dagger.hilt.android") version "2.44" apply false
    id("org.jlleitschuh.gradle.ktlint") version "11.1.0"
}

subprojects {
    repositories {
        google()
        mavenCentral()
    }

    apply(plugin = "org.jlleitschuh.gradle.ktlint")
}

buildscript {
    repositories {
        google()
    }
}
