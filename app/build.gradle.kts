plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlin.compose)
}

import java.util.Properties

// Signing: release/keystore.properties (never committed; the build writes it from secrets)
val keystoreProperties = Properties().apply {
    rootProject.file("release/keystore.properties").takeIf { it.exists() }?.inputStream()?.use { load(it) }
}
fun signing(key: String) = keystoreProperties.getProperty(key)?.takeIf { it.isNotBlank() }

android {
    namespace = "io.github.fluxsniffermods.desktop"
    compileSdk = 36

    defaultConfig {
        applicationId = "io.github.fluxsniffermods.fluxdesktop"
        minSdk = 29
        targetSdk = 36
        versionCode = providers.gradleProperty("FLUX_VERSION_CODE").orNull?.toIntOrNull() ?: 1
        versionName = providers.gradleProperty("FLUX_VERSION_NAME").orNull ?: "1.0.0-flux.local"
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    signingConfigs {
        create("release") {
            val store = signing("storeFile")
            if (store != null) {
                storeFile = rootProject.file("release/$store")
                storePassword = signing("storePassword")
                keyAlias = signing("keyAlias")
                keyPassword = signing("keyPassword")
            }
        }
    }

    buildTypes {
        release {
            isMinifyEnabled = true
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
            if (signing("storeFile") != null) signingConfig = signingConfigs.getByName("release")
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
        buildConfig = true
    }
    lint {
        checkReleaseBuilds = false
        abortOnError = false
    }
    packaging {
        jniLibs {
            // PRoot and the GPU server (scripts/build-proot.sh, build-virgl.sh) are run, not loaded: unpacked to disk, as built
            useLegacyPackaging = true
            keepDebugSymbols += "**/libproot.so"
            keepDebugSymbols += "**/libproot-loader.so"
            keepDebugSymbols += "**/libvirgl.so"
        }
    }
    testOptions {
        unitTests.isIncludeAndroidResources = true
    }
}

dependencies {
    implementation(project(":core"))
    // VNC password authentication (DES) for the desktop's own VNC server
    implementation("org.bouncycastle:bcprov-jdk15to18:1.81")
    testImplementation(libs.junit)
    testImplementation(libs.robolectric)
}
