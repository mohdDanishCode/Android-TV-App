import BuildAndroidConfig.APPLICATION_ID
import dependencies.AnnotationProcessorsDependencies
import dependencies.Dependencies
import extensions.* // ktlint-disable no-wildcard-imports
import java.util.Properties

plugins {
    id(BuildPlugins.ANDROID_APPLICATION)
    id(BuildPlugins.KOTLIN_ANDROID)
    id(BuildPlugins.KOTLIN_ANDROID_EXTENSIONS)
    id(BuildPlugins.NAVIGATION)
    id(BuildPlugins.HILT)
    id(BuildPlugins.KOTLIN_KAPT)
    id("org.jlleitschuh.gradle.ktlint")
}

android {
    namespace = "com.stage.app"

    compileSdk = BuildAndroidConfig.COMPILE_SDK_VERSION
    defaultConfig {
        applicationId = APPLICATION_ID
        minSdk = BuildAndroidConfig.MIN_SDK_VERSION
        targetSdk = BuildAndroidConfig.TARGET_SDK_VERSION

        versionCode = BuildAndroidConfig.VERSION_CODE
        versionName = BuildAndroidConfig.VERSION_NAME

        testInstrumentationRunner = "com.android.sample.app.AppTestRunner"
        vectorDrawables {
            useSupportLibrary = true
        }


    }

    bundle {
        language {
            enableSplit = false
        }
    }

    ktlint {
//        debug.set(true)
        disabledRules.add("no-wildcard-imports")
        android.set(true)
        ignoreFailures.set(true)
        reporters {
            reporter(org.jlleitschuh.gradle.ktlint.reporter.ReporterType.PLAIN)
            reporter(org.jlleitschuh.gradle.ktlint.reporter.ReporterType.CHECKSTYLE)
            reporter(org.jlleitschuh.gradle.ktlint.reporter.ReporterType.SARIF)
        }
    }

    signingConfigs {

        create("release") {
            val properties = Properties().apply {
                load(File("gradle.properties").reader())
            }
            storeFile = rootProject.file(properties.getProperty("storeFilePath"))
            storePassword = properties.getProperty("storePassword")
            keyPassword = properties.getProperty("keyPassword")
            keyAlias = properties.getProperty("keyAlias")
        }
        getByName("debug") {
            val properties = Properties().apply {
                load(File("gradle.properties").reader())
            }
            storeFile = rootProject.file(properties.getProperty("debugStoreFilePath"))
            storePassword = properties.getProperty("debugStorePassword")
            keyPassword = properties.getProperty("debugKeyPassword")
            keyAlias = properties.getProperty("debugKeyAlias")
        }
    }

    flavorDimensions += "environment"

    productFlavors {
        create("staging") {
            dimension = "environment"
            applicationIdSuffix = ".staging"
            resValue("string", "stage_app_name", "Staging-Stage")
        }
        create("dev") {
            dimension = "environment"
            applicationIdSuffix = ".dev"
            resValue("string", "stage_app_name", "Dev-Stage")
        }
        create("prod") {
            dimension = "environment"
            resValue("string", "stage_app_name", "Stage")
        }
    }

    buildTypes {
        getByName("release") {
            signingConfig = signingConfigs.getByName("release")
            isMinifyEnabled = true
            isShrinkResources = true
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
        getByName("debug") {
            signingConfig = signingConfigs.getByName("debug")
            isDebuggable = true
        }
    }






    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
    kotlinOptions {
        jvmTarget = "17"
    }

    dataBinding {
        enable = true
    }
    buildFeatures {
        buildConfig = true
        compose = true
    }
    composeOptions {
        val composeVersion = "1.5.15"
        kotlinCompilerExtensionVersion = composeVersion
    }

    kotlin {
        jvmToolchain(17)
    }
    kapt {
        correctErrorTypes = true
    }
}

dependencies {
    implementation(project(BuildModules.CORE_BUSINESS_LOGIC))


    implementation(Dependencies.HILT)
    implementation(Dependencies.ROOM_RUNTIME)
    implementation(Dependencies.CCP)

    implementation("com.google.android.gms:play-services-auth:21.3.0")
    implementation("com.google.android.gms:play-services-auth-api-phone:18.1.0")
    implementation("com.google.android.gms:play-services-base:18.5.0")
    implementation("com.google.android.gms:play-services-identity:18.1.0")

    implementation("androidx.appcompat:appcompat:1.7.0")
    implementation("com.google.android.material:material:1.12.0")
    implementation("androidx.constraintlayout:constraintlayout:2.2.0")
    implementation("androidx.lifecycle:lifecycle-runtime-ktx:2.8.7")
    implementation("androidx.activity:activity-compose:1.9.3")
    implementation(platform("androidx.compose:compose-bom:2024.11.00"))
    implementation("androidx.compose.ui:ui")
    implementation("androidx.compose.ui:ui-graphics")
    implementation("androidx.compose.ui:ui-tooling-preview")
    implementation("androidx.compose.material3:material3:1.3.1")
    implementation("androidx.hilt:hilt-work:1.2.0")

    androidTestImplementation(platform("androidx.compose:compose-bom:2024.11.00"))
    androidTestImplementation("androidx.compose.ui:ui-test-junit4")
    debugImplementation("androidx.compose.ui:ui-tooling")
    debugImplementation("androidx.compose.ui:ui-test-manifest")

    implementation("androidx.core:core-splashscreen:1.0.1")

    implementation("com.airbnb.android:lottie-compose:6.0.1")



//     bottom sheet
    implementation("com.holix.android:bottomsheetdialog-compose:1.4.1")

    implementation("com.google.code.gson:gson:2.11.0")
    implementation("com.google.android.gms:play-services-wallet:19.4.0")

    implementation("de.charlex.compose.material3:material3-html-text:2.0.0-beta01")


    implementation("androidx.appcompat:appcompat:1.7.0")


    implementation("io.github.stoyan-vuchev:squircle-shape-android:3.0.0")

    kapt(AnnotationProcessorsDependencies.HILT)
    kapt("androidx.hilt:hilt-compiler:1.2.0")
    kapt(AnnotationProcessorsDependencies.ROOM)


    // TV Compose
    implementation("androidx.tv:tv-foundation:1.0.0-alpha12")
    implementation("androidx.tv:tv-material:1.0.0")

}
