plugins {
    id("com.android.application")
    kotlin("android")
    id("kotlin-android-extensions")
    id("kotlin-kapt")
}

android {
    compileSdkVersion(deps.build.compileSdkVersion)

    defaultConfig {
        applicationId = "com.qifan.theforktest"
        minSdkVersion(deps.build.minSdkVersion)
        targetSdkVersion(deps.build.targetSdkVersion)
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }

    kotlinOptions {
        val options = this
        options.jvmTarget = "1.8"
    }

}


dependencies {
    implementation (project(":domain"))
    implementation (project(":data"))
    implementation(fileTree(mapOf("dir" to "libs", "include" to listOf("*.jar"))))
    implementation(deps.kotlin.jdk7)
    implementation(deps.rx.java)
    implementation(deps.rx.kotlin)
    implementation(deps.rx.android)
    implementation(deps.android.androidx.appcompat)
    implementation(deps.android.androidx.coreKtx)
    implementation(deps.android.androidx.constraintlayout)
    implementation(deps.android.androidx.material)
    implementation(deps.android.viewModel.core)
    implementation(deps.android.viewModel.ktx)
//    implementation(deps.android.viewModel.savedState)
    implementation(deps.dagger.core)
    kapt(deps.dagger.compile)
    implementation(deps.dagger.android)
    implementation(deps.dagger.androidSupport)
    kapt(deps.dagger.processor)

    implementation(deps.gson)
    implementation(deps.okhttp.core)
    implementation(deps.retrofit.rxJava2)
    implementation(deps.retrofit.gson)

    testImplementation(deps.test.junit)
    androidTestImplementation(deps.test.android.extensionJunit)
    androidTestImplementation(deps.test.android.expresso)
}
