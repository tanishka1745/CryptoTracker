plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    id ("androidx.navigation.safeargs.kotlin")

}

android {
    namespace = "com.example.cryptotracker"
    compileSdk = 35

    defaultConfig {
        applicationId = "com.example.cryptotracker"
        minSdk = 24
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
    buildFeatures{
        viewBinding= true
    }
}

dependencies {

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    implementation(libs.androidx.activity)
    implementation ("com.airbnb.android:lottie:6.6.2")
    implementation ("com.google.android.material:material:1.7.6")
    implementation ("com.github.ibrahimsn98:SmoothBottomBar:1.7.9")
    implementation ("androidx.navigation:navigation-fragment-ktx:2.8.5")
    implementation ("androidx.navigation:navigation-ui-ktx:2.8.5")
    implementation ("com.squareup.retrofit2:retrofit:2.9.0")
    implementation ("com.github.bumptech.glide:glide:4.15.1")
    implementation ("com.squareup.retrofit2:converter-gson:2.9.0")
    implementation ("androidx.viewpager2:viewpager2:1.1.0")
    implementation ("androidx.lifecycle:lifecycle-runtime-ktx:2.4.1")
    implementation ("com.github.ybq:Android-SpinKit:1.4.0")
    annotationProcessor ("com.github.bumptech.glide:compiler:4.15.1")

    implementation(libs.androidx.constraintlayout)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
}