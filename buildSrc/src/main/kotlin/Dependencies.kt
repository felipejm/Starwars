import org.gradle.api.Project

object Dependencies {

    object Projects{
        val base = ":base"
    }

    object Hilt {
        const val HILT_VERSION = "2.44"

        val hilt = "com.google.dagger:hilt-android:$HILT_VERSION"
        val hiltCompiler = "com.google.dagger:hilt-compiler:$HILT_VERSION"

        fun setup(): Array<String> {
            return arrayOf(hilt)
        }

        fun setupCompilers(): Array<String> {
            return arrayOf(hiltCompiler)
        }
    }

    object Coroutines {
        const val COROUTINES_VERSION = "1.3.4"

        val coroutines = "org.jetbrains.kotlinx:kotlinx-coroutines-android:$COROUTINES_VERSION"

        fun setup(): Array<String> {
            return arrayOf(coroutines)
        }
    }

    object Navigation {
        const val NAVIGATION_VERSION = "2.5.3"

        val navigationFragment = "androidx.navigation:navigation-fragment-ktx:$NAVIGATION_VERSION"
        val navigationUi = "androidx.navigation:navigation-ui-ktx:$NAVIGATION_VERSION"
        val navigationSafeArgs = "androidx.navigation:navigation-safe-args-gradle-plugin:$NAVIGATION_VERSION"

        fun setup(): Array<String> {
            return arrayOf(navigationUi, navigationFragment)
        }
    }

    object Picasso {
        const val PICASSO_VERSION = "2.8"

        val picasso = "com.squareup.picasso:picasso:$PICASSO_VERSION"

        fun setup(): Array<String> {
            return arrayOf(picasso)
        }
    }

    object Retrofit {
        const val RETROFIT_VERSION = "1.7.0"
        const val MOSHI_VERSION = "2.4.0"
        const val OKHTTP_VERSION = "4.10.0"

        val retrofit = "com.squareup.retrofit2:retrofit:$RETROFIT_VERSION"
        val moshi = "com.squareup.retrofit2:converter-moshi:$MOSHI_VERSION"
        val okHttp = "com.squareup.okhttp3:okhttp:$OKHTTP_VERSION"
        val okHttpLoggingInterceptor = "com.squareup.okhttp3:logging-interceptor:$OKHTTP_VERSION"

        fun setup(): Array<String> {
            return arrayOf(moshi, retrofit, okHttp, okHttpLoggingInterceptor)


        }
    }



    object RecyclerView {
        const val RECYCLER_VIEW_VERSION = "1.2.1"

        val recyclerView = "androidx.recyclerview:recyclerview:$RECYCLER_VIEW_VERSION"


        fun setup(): Array<String> {
            return arrayOf(recyclerView)

        }
    }

    object AndroidKtx {
        const val CORE_KTX_VERSION = "1.7.0"
        const val KTX_VERSION = "2.5.1"
        const val FRAGMENT_KTX = "1.5.5"

        val coreKtx = "androidx.core:core-ktx:$CORE_KTX_VERSION"
        val fragments = "androidx.fragment:fragment-ktx:$FRAGMENT_KTX"
        val viewModel = "androidx.lifecycle:lifecycle-viewmodel-ktx:$KTX_VERSION"
        val viewModelSavedState = "androidx.lifecycle:lifecycle-viewmodel-savedstate:$KTX_VERSION"
        val liveData = "androidx.lifecycle:lifecycle-livedata-ktx:$KTX_VERSION"


        fun setup(): Array<String> {
            return arrayOf(coreKtx, fragments, viewModel, liveData, viewModelSavedState)
        }
    }

    object AppCompat {
        const val APP_COMPAT_VERSION = "1.5.1"
        val appCompat = "androidx.appcompat:appcompat:$APP_COMPAT_VERSION"

        fun setup(): Array<String> {
            return arrayOf(appCompat)
        }
    }

    object Material {
        const val MATERIAL_VERSION = "1.5.0-alpha04"
        val material = "com.google.android.material:material:$MATERIAL_VERSION"

        fun setup(): Array<String> {
            return arrayOf(material)
        }
    }

    object ConstraintLayout {
        const val CONSTRAINT_LAYOUT_VERSION = "2.0.4"
        val constraintLayout =
            "androidx.constraintlayout:constraintlayout:$CONSTRAINT_LAYOUT_VERSION"

        fun setup(): Array<String> {
            return arrayOf(constraintLayout)
        }
    }

    object UnitTest {
        const val JUNIT_VERSION = "4.13.2"
        const val MOCKK_VERSION = "1.13.4"

        val junit = "junit:junit:$JUNIT_VERSION"
        val mockk = "io.mockk:mockk:$MOCKK_VERSION"
        val mockkAndroid = "io.mockk:mockk-android:$MOCKK_VERSION"

        fun setup(): Array<String> {
            return arrayOf(junit, mockk, mockkAndroid)
        }
    }
}