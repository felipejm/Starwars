plugins {
    id("com.android.application") version "7.2.0" apply false
    id("com.android.library") version "7.2.0" apply false
    id("org.jetbrains.kotlin.android") version "1.6.10" apply false
    id("com.google.dagger.hilt.android") version "2.44" apply false
}

subprojects {
    repositories {
        google()
        mavenCentral()
    }
}

buildscript {
    repositories {
        google()
    }
    dependencies {
        classpath(Dependencies.Navigation.navigationSafeArgs)
    }
}
