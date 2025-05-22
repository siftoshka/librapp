plugins {
    alias(libs.plugins.androidLibrary)
    alias(libs.plugins.kotlinx.serialization)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlin.compose)
    id("kotlin-parcelize")
}

apply(from = "$rootDir/base-build.gradle")

android {
    namespace = "az.siftoshka.data"

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
        }
    }
}

dependencies {
    implementation(projects.domain)

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.kotlinx.coroutines.android)
    api(libs.gson)
    api(libs.kotlinx.serialization.json)
    implementation(libs.androidx.datastore)
    implementation(libs.runtime)
    implementation(platform(libs.androidx.compose.bom))
    implementation(platform(libs.ktor.bom))
    implementation(libs.bundles.ktor)
    implementation(platform(libs.koin.bom))
    implementation(libs.bundles.koin)
}