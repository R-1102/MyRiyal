plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlin.compose)
    //Hilt DI
    id("kotlin-kapt")
    id("com.google.dagger.hilt.android")
    id("com.google.gms.google-services")
}

android {
    namespace = "com.example.myriyal"
    compileSdk = 35

    defaultConfig {
        applicationId = "com.example.myriyal"
        minSdk = 24
        targetSdk = 35
        versionCode = 1
        versionName = "1.0"

        multiDexEnabled = true

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    kotlinOptions {
        jvmTarget = "11"
    }
    buildFeatures {
        compose = true
    }
}

dependencies {

    // Core dependencies
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.activity.compose)
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.ui)
    implementation(libs.androidx.ui.graphics)
    implementation(libs.androidx.ui.tooling.preview)
    implementation(libs.androidx.material3)
    implementation(libs.androidx.room.common.jvm)
    implementation(libs.androidx.room.runtime.android)

    // Google Play Services dependency
    //implementation(libs.play.services.auth)
    //Firebase dependencies
    implementation(platform(libs.firebase.bom))
    implementation(libs.firebase.auth.ktx)
    implementation(libs.firebase.firestore.ktx)
    implementation(libs.firebase.analytics)
    implementation(libs.firebase.messaging.ktx)

    // Testing dependencies
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.ui.test.junit4)
    debugImplementation(libs.androidx.ui.tooling)
    debugImplementation(libs.androidx.ui.test.manifest)

    // Icons and Material
    implementation(libs.androidx.material.icons.extended)
    implementation(libs.androidx.compose.material3.material3)

    // Room dependencies
    implementation(libs.androidx.room.runtime)
    kapt(libs.androidx.room.compiler)

    // Hilt dependencies
    implementation(libs.hilt.android)
    kapt(libs.hilt.android.compiler)

    // Navigation dependencies
    implementation(libs.androidx.navigation.compose)

    // Accompanist Animation (Consider replacing with androidx.navigation)
    implementation(libs.accompanist.navigation.animation)

    // Hilt Navigation for Jetpack Compose
    implementation(libs.androidx.hilt.navigation.compose)

    // For encrypted SharedPreferences
    implementation(libs.androidx.security.crypto)

    // Retrofit core library for making HTTP requests (GET, POST, etc.)
    implementation(libs.retrofit)

    // Retrofit converter that parses JSON responses into Kotlin/Java objects using Gson
    implementation(libs.converter.gson)

    // OkHttp logging interceptor for debugging network requests/responses in Logcat
    implementation(libs.logging.interceptor)

    //for color picker
    implementation("com.github.skydoves:colorpicker-compose:1.1.2")


}
// Allow references to generated code
kapt {
    correctErrorTypes = true
}