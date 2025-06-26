import dependencies.AnnotationProcessorsDependencies
import dependencies.Dependencies
import extensions.*


plugins {
    id("commons.android-library")
    id(BuildPlugins.KOTLIN_KAPT)
}

android {
    namespace = "com.stage.businesslogic"
    flavorDimensions += "environment"
    productFlavors {
        create("staging") {
        }
        create("dev") {
        }
        create("prod") {
        }
    }

    sourceSets {
        getByName("main") {
            jniLibs.srcDirs("src/main/jniLibs")
        }
    }
}

dependencies {

    implementation("androidx.hilt:hilt-work:1.2.0")

    val composeVersion = "1.7.5"



    api("com.google.android.libraries.places:places:4.1.0")
    api("com.google.maps:google-maps-services:2.2.0")
    api("org.slf4j:slf4j-simple:1.7.25")

    api("com.github.KevinnZou:compose-webview:0.33.2")
    api("androidx.compose.ui:ui:$composeVersion")
    api("androidx.compose.material:material:$composeVersion")
    api("androidx.compose.ui:ui-tooling-preview:$composeVersion")
    api("androidx.activity:activity-compose:1.9.3")
    api("androidx.lifecycle:lifecycle-viewmodel-compose:2.8.7")
    api("androidx.compose.runtime:runtime:$composeVersion")
    api("androidx.compose.runtime:runtime-livedata:$composeVersion")
    api("androidx.compose.ui:ui-test-junit4:$composeVersion")
    api("androidx.compose.ui:ui-tooling:$composeVersion")
    implementation("androidx.compose.foundation:foundation:$composeVersion")
    api("androidx.lifecycle:lifecycle-runtime-compose:2.8.7")
    debugImplementation("androidx.compose.ui:ui-test-manifest:$composeVersion")
    api("com.google.maps.android:maps-compose:4.3.0")
    api("androidx.navigation:navigation-compose:2.8.4")
    api("androidx.hilt:hilt-navigation-compose:1.2.0")

    api("com.google.accompanist:accompanist-systemuicontroller:0.34.0")


    api("androidx.constraintlayout:constraintlayout-compose:1.1.0")


    api("com.google.android.gms:play-services-location:21.3.0")

    api("io.coil-kt:coil-compose:2.7.0")
    api("io.coil-kt:coil-gif:2.7.0")





    api(project(BuildModules.CORE_DATASOURCE))
    implementation(Dependencies.HILT)

    implementation(Dependencies.ROOM_RUNTIME)
    implementation(Dependencies.GSON)
    kapt(AnnotationProcessorsDependencies.HILT)
    kapt("androidx.hilt:hilt-compiler:1.2.0")
    kapt(AnnotationProcessorsDependencies.ROOM)


    // ExoPlayer
    api("androidx.media3:media3-exoplayer:1.7.1")
    api("androidx.media3:media3-ui:1.7.1")
    api("androidx.media3:media3-common:1.7.1")
    implementation("androidx.media3:media3-exoplayer-hls:1.7.1")


}
