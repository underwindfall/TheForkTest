plugins {
    `java-library`
    id("kotlin")
    id("kotlin-kapt")
}

dependencies {
    implementation (project(":domain"))
    implementation(fileTree(mapOf("dir" to "libs", "include" to listOf("*.jar"))))
    implementation(deps.kotlin.jdk7)
    implementation(deps.rx.java)
    implementation(deps.rx.kotlin)
    implementation(deps.gson)
    implementation(deps.okhttp.core)
    implementation(deps.retrofit.core)
    implementation(deps.retrofit.rxJava2)
    implementation(deps.retrofit.gson)
    implementation(deps.dagger.core)
    kapt(deps.dagger.compile)
}

