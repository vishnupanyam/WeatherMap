plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.jetbrains.kotlin.android)
    id("kotlin-kapt")
//    id("com.google.devtools.ksp")
}
android {
    namespace = "com.example.weathermap"
    compileSdk = 34
    viewBinding {
        enable = true
    }

    defaultConfig {

        applicationId = "com.example.weathermap"
        minSdk = 30
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }
//        defaultConfig {
//            buildConfigField("String", "OPENWEATHER_API_KEY", "\"${project.findProperty("OPENWEATHER_API_KEY")}\"")
//        }
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
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.5.1"
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
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
    implementation(libs.androidx.room.common)
    implementation(libs.androidx.room.ktx)
    implementation(libs.androidx.work.runtime.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.androidx.constraintlayout)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.ui.test.junit4)
    debugImplementation(libs.androidx.ui.tooling)
    debugImplementation(libs.androidx.ui.test.manifest)
    kapt(libs.androidx.room.compiler)
    implementation(libs.squareup.retrofit2.retrofit)
    implementation(libs.squareup.retrofit2.converter.gson)
    implementation(libs.bumptech.glide)
    implementation(libs.androidx.recyclerview)
    testImplementation("org.jetbrains.kotlinx:kotlinx-coroutines-test:1.5.2") // For coroutines testing
    testImplementation("junit:junit:4.13.2") // JUnit for unit testing
    testImplementation("org.mockito:mockito-core:4.2.0") // Mockito for mocking
    testImplementation("org.mockito.kotlin:mockito-kotlin:4.0.0") // Mockito Kotlin support
    testImplementation("androidx.arch.core:core-testing:2.1.0") // For ViewModel testing
    testImplementation("org.mockito:mockito-inline:4.2.0")
    testImplementation ("org.jetbrains.kotlinx:kotlinx-coroutines-test:1.6.0")

}