import org.jetbrains.compose.desktop.application.dsl.TargetFormat
import org.jetbrains.kotlin.gradle.ExperimentalKotlinGradlePluginApi
import org.jetbrains.kotlin.gradle.dsl.JvmTarget

plugins {
    alias(libs.plugins.kotlinMultiplatform)
    alias(libs.plugins.androidApplication)
    alias(libs.plugins.composeMultiplatform)
    alias(libs.plugins.composeCompiler)
    id("com.google.gms.google-services")
    alias(libs.plugins.serialization)
    alias(libs.plugins.sqldelight)
}

kotlin {
    androidTarget {
        @OptIn(ExperimentalKotlinGradlePluginApi::class)
        compilerOptions {
            jvmTarget.set(JvmTarget.JVM_17)
        }
    }
    
    listOf(
        iosX64(),
        iosArm64(),
        iosSimulatorArm64()
    ).forEach { iosTarget ->
        iosTarget.binaries.framework {
            baseName = "ComposeApp"
            isStatic = true
        }
    }
    
    sourceSets {
        
        androidMain.dependencies {
            implementation(compose.preview)
            implementation(libs.androidx.activity.compose)
            implementation("com.google.firebase:firebase-config:22.1.1")
            implementation("com.google.firebase:firebase-database")
            implementation("com.google.firebase:firebase-database-ktx:20.3.0")
            implementation("dev.gitlive:firebase-common:1.8.0")
            implementation("com.google.firebase:firebase-firestore-ktx:24.10.0")
            implementation("com.google.firebase:firebase-bom:32.8.0")
            implementation(libs.koin.android)
            implementation(libs.koin.androidx.compose)
            implementation(libs.android.driver)
        }
        commonMain.dependencies {
            implementation(compose.runtime)
            implementation(compose.foundation)
            implementation(compose.material3)
            implementation(compose.ui)
            implementation(compose.components.resources)
            implementation(compose.components.uiToolingPreview)
            implementation(libs.androidx.lifecycle.viewmodel)
            implementation(libs.androidx.lifecycle.runtimeCompose)
            implementation("io.github.mirzemehdi:kmpauth-google:2.4.0-alpha02")      // Google One Tap Sign-In
            implementation("io.github.mirzemehdi:kmpauth-firebase:2.4.0-alpha02")    // Firebase integration
            implementation("io.github.mirzemehdi:kmpauth-uihelper:2.4.0-alpha02")    // UI helper buttons
            implementation("dev.gitlive:firebase-database:2.1.0") // or latest
            implementation("dev.gitlive:firebase-common:1.8.0")
            implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:1.6.3") // required if not already
            implementation("media.kamel:kamel-image-default:1.0.7")
            implementation("dev.gitlive:firebase-firestore:2.1.0")
            api(libs.koin.core)
            implementation(libs.koin.compose)
            implementation(libs.koin.compose.viewmodel)
            implementation(libs.lifecycle.viewmodel)
            implementation(libs.navigation.compose)
            implementation(libs.coroutines.extensions)

        }
        commonTest.dependencies {
            implementation(libs.kotlin.test)
            implementation(libs.native.driver)
        }
    }
}

android {
    namespace = "com.daily.vitals"
    compileSdk = libs.versions.android.compileSdk.get().toInt()

    defaultConfig {
        applicationId = "com.daily.vitals"
        minSdk = libs.versions.android.minSdk.get().toInt()
        targetSdk = libs.versions.android.targetSdk.get().toInt()
        versionCode = 1
        versionName = "1.0"
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
}

dependencies {
    debugImplementation(compose.uiTooling)
}

sqldelight {
    databases {
        create(name = "DailyVitalsDatabase") {
            packageName.set("com.daily.vitals.composeApp.database")
        }
    }
}