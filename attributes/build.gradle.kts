plugins {
    alias(libs.plugins.android.library)
    // Kotlin is provided by AGP's built-in Kotlin (AGP 9+), no need to apply `org.jetbrains.kotlin.android` here
    alias(libs.plugins.kotlin.compose)
}

kotlin {
    jvmToolchain(21)
}

android {
    namespace = "br.com.lucolimac.pokedex.attribute"
    compileSdk = 37

    defaultConfig {
        minSdk = 26

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        consumerProguardFiles("consumer-rules.pro")
    }

    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_21
        targetCompatibility = JavaVersion.VERSION_21
    }
    buildFeatures{
        compose = true
    }
}

dependencies {

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    //Jetpack Compose
    api(platform(libs.androidx.compose.bom))
    api(libs.androidx.compose.ui.ui)
    api(libs.androidx.compose.ui.ui.graphics)
    api(libs.androidx.compose.ui.ui.tooling.preview)
    api(libs.androidx.compose.material3.material3)
    debugImplementation(libs.androidx.compose.ui.ui.tooling)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
}

