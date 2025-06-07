plugins {
    id("java-library")
    alias(libs.plugins.kotlinx.serialization)
    alias(libs.plugins.jetbrainsKotlinJvm)
    id("kotlinx-serialization")
}

java {
    sourceCompatibility = JavaVersion.VERSION_17
    targetCompatibility = JavaVersion.VERSION_17
}

kotlin {
    compilerOptions {
        jvmTarget = org.jetbrains.kotlin.gradle.dsl.JvmTarget.JVM_17
    }
}

dependencies {
    implementation(libs.kotlinx.coroutines.core)
    implementation(libs.kotlinx.serialization.json)
    implementation(libs.kotlinx.coroutines.core)
    implementation(libs.annotation)
    implementation(libs.gson)
    implementation(platform(libs.koin.bom))
    implementation(libs.koin.core)
}