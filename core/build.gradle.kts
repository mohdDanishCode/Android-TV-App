import dependencies.AnnotationProcessorsDependencies
import dependencies.Dependencies
import extensions.api
import extensions.implementation

plugins {
    id("commons.android-library")
    `maven-publish`
    id(BuildPlugins.KOTLIN_KAPT)
}

android {
    namespace = "com.danish.common"
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

dependencies {
    api(Dependencies.APPCOMPAT)
    api(Dependencies.CORE_KTX)
    api(Dependencies.TIMBER)
    api(Dependencies.MATERIAL)

    implementation(Dependencies.HILT)
    kapt(AnnotationProcessorsDependencies.HILT)
}
