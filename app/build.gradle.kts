plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    id("kotlin-kapt")
    id("com.google.dagger.hilt.android")
}

android {
    namespace = "com.app.biztosoproject"
    compileSdk = 36

    defaultConfig {
        applicationId = "com.app.biztosoproject"
        minSdk = 24
        targetSdk = 36
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
    viewBinding {
        enable = true
    }
}

//configurations.all {
//    resolutionStrategy {
//        force("com.squareup:javapoet:1.13.0")
//    }
//}

dependencies {

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    implementation(libs.androidx.activity)
    implementation(libs.androidx.constraintlayout)
    implementation(libs.androidx.recyclerview)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)

    implementation(libs.sdp.android)  // for dimensions

    implementation(libs.ssp.android)  // for text size

    implementation(libs.circleimageview) // circle image view

    implementation(libs.flexbox) // flexbox layout

    // Shimmer Loader Dependency
    implementation(libs.shimmer)

    //Image Loader Library
    implementation(libs.glide)
    implementation(libs.androidx.fragment.ktx.v162)

    // Core Retrofit Library
    implementation("com.squareup.retrofit2:retrofit:2.9.0")

    // Gson Convertor Library
    implementation("com.squareup.retrofit2:converter-gson:2.9.0")

    // OkHttp Dependency
    implementation("com.squareup.okhttp3:logging-interceptor:4.12.0")

    // RecyclerView Item Animation Dependency
    implementation("jp.wasabeef:recyclerview-animators:4.0.2")

    // ViewModel Dependency
    implementation("androidx.lifecycle:lifecycle-viewmodel-ktx:2.7.0")
    implementation("androidx.lifecycle:lifecycle-livedata-ktx:2.7.0")

    // Hilt core
    val hiltVersion = "2.51" // REPLACE WITH LATEST STABLE HILT VERSION
    implementation("com.google.dagger:hilt-android:$hiltVersion")
    kapt("com.google.dagger:hilt-compiler:$hiltVersion")


    // Hilt Navigation Fragment (optional but useful)
    implementation("androidx.hilt:hilt-navigation-fragment:1.1.0")

    // Coroutines (optional for async)
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-android:1.7.3")
}

kapt {
    correctErrorTypes = true
}