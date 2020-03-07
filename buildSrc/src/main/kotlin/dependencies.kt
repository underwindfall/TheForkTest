object deps {
    object versions {
        //gradle
        const val gradle = "3.6.1"
        //kotlin
        const val kotlin = "1.3.61"
        // android
        const val appcompat = "1.1.0"
        const val coreKtx = "1.2.0"
        const val constraintlayout = "1.1.3"
        const val material = "1.1.0"
        const val viewModel = "2.2.0"
        const val viewModelSaveState = "1.0.0"
        //external lib
        const val dagger = "2.26"
        const val okhttp = "4.4.0"
        const val retrofit = "2.7.1"
        const val gson = "2.8.6"
        const val rxJava = "2.2.18"
        const val rxKotlin = "2.4.0"
        const val rxAndroid = "2.1.1"
        const val picasso = "2.71828"
        // test
        const val juint = "4.13"
        const val junitExt = "1.1.1"
        const val expresso = "3.2.0"
    }

    object android {

        object androidx {
            const val appcompat = "androidx.appcompat:appcompat:${versions.appcompat}"
            const val coreKtx = "androidx.core:core-ktx:${versions.coreKtx}"
            const val constraintlayout =
                "androidx.constraintlayout:constraintlayout:${versions.constraintlayout}"
            const val material = "com.google.android.material:material:${versions.material}"
        }

        object viewModel {
            const val core = "androidx.lifecycle:lifecycle-viewmodel:${versions.viewModel}"
            const val ktx = "androidx.lifecycle:lifecycle-viewmodel-ktx:${versions.viewModel}"
            const val savedState =
                "androidx.lifecycle:lifecycle-viewmodel-savedstate:${versions.viewModelSaveState}"
        }
    }

    object build {
        const val compileSdkVersion = 29
        const val minSdkVersion = 21
        const val targetSdkVersion = 29
    }

    object rx {
        const val java = "io.reactivex.rxjava2:rxjava:${versions.rxJava}"
        const val kotlin = "io.reactivex.rxjava2:rxkotlin:${versions.rxKotlin}"
        const val android = "io.reactivex.rxjava2:rxandroid:${versions.rxAndroid}"
    }

    object okhttp {
        const val core = "com.squareup.okhttp3:okhttp:${versions.okhttp}"
    }

    const val gson = "com.google.code.gson:gson:${versions.gson}"

    object retrofit {
        const val core = "com.squareup.retrofit2:retrofit:${versions.retrofit}"
        const val rxJava2 = "com.squareup.retrofit2:adapter-rxjava2:${versions.retrofit}"
        const val gson = "com.squareup.retrofit2:converter-gson:${versions.retrofit}"
    }

    object plugins {
        const val gradle = "com.android.tools.build:gradle:${versions.gradle}"
        const val kotlin = "org.jetbrains.kotlin:kotlin-gradle-plugin:${versions.kotlin}"
    }

    object kotlin {
        const val jdk7 = "org.jetbrains.kotlin:kotlin-stdlib-jdk7:${versions.kotlin}"
    }

    const val picasso = "com.squareup.picasso:picasso:${versions.picasso}"

    object dagger {
        const val core = "com.google.dagger:dagger:${versions.dagger}"
        const val compile = "com.google.dagger:dagger-compiler:${versions.dagger}"
        const val android = "com.google.dagger:dagger-android:${versions.dagger}"
        const val androidSupport = "com.google.dagger:dagger-android-support:${versions.dagger}"
        const val processor = "com.google.dagger:dagger-android-processor:${versions.dagger}"
    }

    object test {
        object android {
            const val expresso = "androidx.test.espresso:espresso-core:${versions.expresso}"
            const val extensionJunit = "androidx.test.ext:junit:${versions.junitExt}"
        }

        const val junit = "junit:junit:${versions.juint}"
    }
}