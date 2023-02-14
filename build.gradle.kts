plugins {
    id("com.android.application") version "7.2.0" apply false
    id("com.android.library") version "7.2.0" apply false
    id("org.jetbrains.kotlin.android") version "1.6.10" apply false
    id("com.google.dagger.hilt.android") version "2.44" apply false
    id("com.apollographql.apollo3") version "3.7.3" apply false
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
    dependencies {
        classpath(Dependencies.Navigation.navigationSafeArgs)
    }
}
