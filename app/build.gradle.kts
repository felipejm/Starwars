plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    id("kotlin-kapt")
    id("com.google.dagger.hilt.android")
    id("androidx.navigation.safeargs.kotlin")
}

android {
    compileSdk = Config.compileSdk

    defaultConfig {
        applicationId = Config.applicationId
        minSdk = Config.minSdk
        targetSdk = Config.targetSdk
        versionCode = Config.versionCode
        versionName = Config.versionName

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }
    }

    buildTypes {
        debug {
            isMinifyEnabled = false
        }
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro"
            )
        }
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }

    kotlinOptions {
        jvmTarget = "1.8"
    }

    buildFeatures {
        viewBinding = true
        dataBinding = true
    }

    packagingOptions {
        resources {
            excludes.add("/META-INF/{AL2.0,LGPL2.1}")
            excludes.add("META-INF/gradle/incremental.annotation.processors")
        }
    }
}

dependencies {
    //Modules
    implementation(project(Dependencies.Projects.base))

    //Libs
    Dependencies.AndroidKtx.setup().forEach{ implementation(it) }
    Dependencies.AppCompat.setup().forEach{ implementation(it) }
    Dependencies.Retrofit.setup().forEach{ implementation(it) }
    Dependencies.Coroutines.setup().forEach{ implementation(it) }

    Dependencies.Picasso.setup().forEach{ implementation(it) }
    Dependencies.Navigation.setup().forEach{ implementation(it) }
    Dependencies.Material.setup().forEach{ implementation(it) }
    Dependencies.ConstraintLayout.setup().forEach{ implementation(it) }
    Dependencies.RecyclerView.setup().forEach{ implementation(it) }

    Dependencies.Hilt.setup().forEach{ implementation(it) }
    Dependencies.Hilt.setupCompilers().forEach{ kapt(it) }

    Dependencies.UnitTest.setup().forEach{ testImplementation(it) }
}