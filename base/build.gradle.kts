plugins {
    id("com.android.library")
    id("org.jetbrains.kotlin.android")
    id("kotlin-kapt")
    id("com.google.dagger.hilt.android")
}

android {

    compileSdk = Config.compileSdk

    buildTypes {
        debug {
            buildConfigField("String", "BASE_URL", "\"https://swapi.dev/\"")
        }
        release {
            buildConfigField("String", "BASE_URL", "\"https://swapi.dev/\"")
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
    }

    packagingOptions {
        resources {
            excludes.add("/META-INF/{AL2.0,LGPL2.1}")
        }
    }
}

dependencies {
    Dependencies.Retrofit.setup().forEach { implementation(it) }
    Dependencies.Hilt.setup().forEach { implementation(it) }
    Dependencies.Hilt.setupCompilers().forEach { kapt(it) }
}
