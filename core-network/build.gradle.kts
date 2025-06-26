import dependencies.AnnotationProcessorsDependencies
import dependencies.Dependencies
import extensions.api
import extensions.implementation
import extensions.kapt

plugins {
    id("commons.android-library")
    `maven-publish`
    id(BuildPlugins.KOTLIN_KAPT)
    id("org.jetbrains.kotlin.plugin.serialization")
}

android {
    namespace = "com.network.core"
    buildFeatures {
        buildConfig = true
    }

    defaultConfig {
        buildConfigField("String", "DATABASE_NAME", "\"app-db\"")
        buildConfigField("int", "DATABASE_VERSION", "1")
        buildConfigField("boolean", "DATABASE_EXPORT_SCHEMA", "false")
    }

    flavorDimensions += "environment"
    productFlavors {
        create("staging") {
            buildConfigField("String", "PUSHER_API_KEY", "\"${project.properties["PUSHER_API_KEY_STAGING"]}\"")
            buildConfigField("String", "PUSHER_CLUSTER", "\"${project.properties["PUSHER_CLUSTER_STAGING"]}\"")
        }
        create("dev") {
            buildConfigField("String", "PUSHER_API_KEY", "\"${project.properties["PUSHER_API_KEY_DEV"]}\"")
            buildConfigField("String", "PUSHER_CLUSTER", "\"${project.properties["PUSHER_CLUSTER_DEV"]}\"")
        }
        create("prod") {
            buildConfigField("String", "PUSHER_API_KEY", "\"${project.properties["PUSHER_API_KEY_PROD"]}\"")
            buildConfigField("String", "PUSHER_CLUSTER", "\"${project.properties["PUSHER_CLUSTER_PROD"]}\"")
        }
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
    kotlinOptions {
        jvmTarget = "17"
    }
    compileSdk = 35
}

dependencies {
    api(project(BuildModules.CORE))
    api(project(BuildModules.CORE_DOMAIN))
    val pagingVersion = "3.3.6"

    implementation("com.google.android.gms:play-services-maps:19.0.0")
    implementation("com.google.android.gms:play-services-places:17.1.0")
    implementation("com.google.android.libraries.places:places:4.1.0")
    implementation("com.google.maps:google-maps-services:2.2.0")

    implementation("id.zelory:compressor:3.0.1")


    implementation("androidx.core:core:1.15.0")


    api("com.github.skydoves:sandwich:2.0.8")
    api("com.github.skydoves:sandwich-retrofit:2.0.8")
    api(Dependencies.RETROFIT)
    api(Dependencies.MOSHI)
    api("com.squareup.moshi:moshi-adapters:1.15.0")
    kapt(Dependencies.MOSHI_CODE_GEN)


    // Datastore
    api("androidx.datastore:datastore:1.1.3")
    api("org.jetbrains.kotlinx:kotlinx-collections-immutable:0.3.5")
    implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:1.6.3")


//    implementation(Dependencies.ROOM)
    implementation("androidx.room:room-ktx:${BuildDependenciesVersions.ROOM}")
    implementation(Dependencies.ROOM_RUNTIME)
    implementation("androidx.room:room-paging:2.6.1")

//    implementation(Dependencies.RETROFIT_ADAPTER)
    implementation(Dependencies.RETROFIT_CONVERTER)
//    implementation(Dependencies.RX_JAVA)
    implementation(Dependencies.LOGGING)
//    implementation(Dependencies.MOSHI_KTX)

    api("androidx.paging:paging-runtime:$pagingVersion")
    api("androidx.paging:paging-compose:$pagingVersion")

    // Kotlin + coroutines
    api("androidx.work:work-runtime-ktx:2.10.0")

    api("com.pusher:pusher-java-client:2.4.4")

    implementation(Dependencies.HILT)
    kapt(AnnotationProcessorsDependencies.HILT)
    kapt(AnnotationProcessorsDependencies.ROOM)
}
