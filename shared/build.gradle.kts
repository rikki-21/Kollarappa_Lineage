import org.jetbrains.kotlin.gradle.dsl.JvmTarget

plugins {
    alias(libs.plugins.kotlinMultiplatform)
    alias(libs.plugins.androidLibrary)
    // Add the Compose Multiplatform plugin alias here
    alias(libs.plugins.composeCompiler)
}

kotlin {
    androidTarget {
        compilations.all {
            compileTaskProvider.configure {
                compilerOptions {
                    jvmTarget.set(JvmTarget.JVM_1_8)
                }
            }
        }
    }

    listOf(
        iosX64(),
        iosArm64(),
        iosSimulatorArm64()
    ).forEach {
        it.binaries.framework {
            baseName = "shared"
            isStatic = true
        }
    }

    sourceSets {
        // Dependencies for code shared across ALL platforms (Android, iOS, etc.)
        commonMain.dependencies {
            // Add Compose Multiplatform dependencies here
            implementation(libs.compose.runtime)
            implementation(libs.compose.foundation)
            implementation(libs.compose.material3)
            implementation(libs.compose.ui)
            implementation(libs.identity.jvm)
            implementation(libs.firebase.dataconnect)
            implementation(libs.androidx.runtime.desktop)
            implementation(libs.androidx.runtime.android)
        }

        // Dependencies ONLY for the Android platform
        val androidMain by getting {
            dependencies {
                implementation(libs.google.firebase.common.ktx)
                implementation(libs.google.firebase.auth.ktx)
                // Add any other Android-specific libraries here
            }
        }

        commonTest.dependencies {
            implementation(libs.kotlin.test)
        }
    }
}

android {
    namespace = "com.rikki.kollarappalineage"
    compileSdk = 35
    defaultConfig {
        minSdk = 29
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    // The dependencies block inside the 'android' closure is not needed for KMP library modules.
    // Place Android dependencies in the 'androidMain' source set as shown above.
}

// The root-level 'dependencies' block has been removed as it's incorrect for a KMP module.



//
//implementation(libs.identity.jvm)
//implementation(libs.firebase.dataconnect)
//implementation(libs.google.firebase.common.ktx)
//implementation(libs.google.firebase.auth.ktx)
//implementation(libs.androidx.runtime.desktop)
//implementation(libs.androidx.runtime.android)