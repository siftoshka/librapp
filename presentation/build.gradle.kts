plugins {
    alias(libs.plugins.androidLibrary)
    alias(libs.plugins.kotlinx.serialization)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlin.compose)
    id("kotlin-parcelize")
    id("kotlinx-serialization")
}

apply(from = "$rootDir/base-build.gradle")

android {
    namespace = "az.siftoshka.presentation"

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
        }
    }
}

dependencies {
    implementation(projects.domain)
    implementation(projects.data)

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.core.splash)
    implementation(libs.androidx.appcompat)
    implementation(libs.kotlinx.coroutines.android)
    api(libs.gson)
    api(libs.kotlinx.serialization.json)
    implementation(libs.runtime)

    implementation(libs.compose.navigation)
    implementation(platform(libs.androidx.compose.bom))
    implementation(platform(libs.ktor.bom))
    implementation(platform(libs.koin.bom))
    implementation(libs.bundles.koin)
    implementation(libs.bundles.ktor)
    implementation(libs.bundles.compose)
    implementation(libs.androidx.ui)
    implementation(libs.androidx.ui.graphics)
    implementation(libs.androidx.ui.tooling.preview)
}