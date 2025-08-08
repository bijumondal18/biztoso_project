plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
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

    // ViewModel Dependency
    implementation("androidx.lifecycle:lifecycle-viewmodel-ktx:2.7.0")
    implementation("androidx.lifecycle:lifecycle-livedata-ktx:2.7.0")

    // OkHttp Dependency
    implementation("com.squareup.okhttp3:logging-interceptor:4.12.0")

    // RecyclerView Item Animation Dependency
    implementation("jp.wasabeef:recyclerview-animators:4.0.2")
}