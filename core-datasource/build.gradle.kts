import dependencies.AnnotationProcessorsDependencies
import dependencies.Dependencies.GSON
import extensions.api
import extensions.implementation
import extensions.kapt

plugins {
    id("commons.android-library")
    `maven-publish`
}

android {
    namespace = "com.core.core_datasource"
    compileSdk = 35

    defaultConfig {
        minSdk = 24

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        consumerProguardFiles("consumer-rules.pro")
    }
    buildTypes {
        flavorDimensions += "environment"
        productFlavors {
            create("staging") {
            }
            create("dev") {
            }
            create("prod") {
            }
        }
    }


    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
    kotlinOptions {
        jvmTarget = "17"
    }
}

dependencies {

    implementation("androidx.hilt:hilt-common:1.2.0")
    implementation("androidx.lifecycle:lifecycle-service:2.8.7")
    val pagingVersion = "3.3.4"
    api(project(BuildModules.CORE_NETWORk))
    implementation("com.github.skydoves:sandwich:2.0.8")

    implementation("org.jsoup:jsoup:1.14.3")
    implementation("androidx.core:core-ktx:1.15.0")
    implementation("androidx.appcompat:appcompat:1.7.0")
    implementation("com.google.android.material:material:1.12.0")

    implementation(GSON)
    implementation("androidx.paging:paging-runtime:$pagingVersion")
    implementation("androidx.paging:paging-compose:$pagingVersion")
    kapt(AnnotationProcessorsDependencies.HILT)
    kapt("androidx.hilt:hilt-compiler:1.2.0")
    implementation("androidx.hilt:hilt-work:1.2.0")
    kapt(AnnotationProcessorsDependencies.ROOM)
}
