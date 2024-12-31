plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlin.compose)
}

android {
    namespace = "com.warrantysafe.app"
    compileSdk = 35

    defaultConfig {
        applicationId = "com.warrantysafe.app"
        minSdk = 24
        //noinspection OldTargetApi
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

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

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.activity.compose)
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.ui)
    implementation(libs.androidx.ui.graphics)
    implementation(libs.androidx.ui.tooling.preview)
    implementation(libs.androidx.material3)
    implementation(libs.androidx.navigation.compose)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.ui.test.junit4)
    debugImplementation(libs.androidx.ui.tooling)
    debugImplementation(libs.androidx.ui.test.manifest)
    implementation(libs.ccp) // Add CountryCodePicker here

    // Koin Core
    implementation(libs.koin.core)
    implementation(libs.koin.androidx.compose)
    implementation(libs.koin.android)

    // Ktor dependencies
    implementation(libs.ktor.server.core)  // Ktor core
    implementation(libs.ktor.server.cio)  // CIO Engine
    implementation(libs.ktor.server.content.negotiation)  // Content Negotiation
    implementation(libs.ktor.serialization.kotlinx.json)  // JSON Serialization
    implementation(libs.kotlinx.serialization.json)  // kotlinx.serialization

    // Ktor logging (optional, for debugging)
       implementation(libs.ktor.server.logging)  // Logging

    // Optional: Koin for testing (if you plan to write tests)
    testImplementation(libs.koin.test)
    testImplementation(libs.koin.test.junit4)
}