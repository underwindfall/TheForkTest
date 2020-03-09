plugins {
    `java-library`
    id("kotlin")
    id("kotlin-kapt")
}


dependencies {
    implementation(fileTree(mapOf("dir" to "libs", "include" to listOf("*.jar"))))
    implementation(deps.kotlin.jdk7)
    implementation(deps.rx.java)
    implementation(deps.rx.kotlin)
    implementation(deps.dagger.core)
    kapt(deps.dagger.compile)
    testImplementation(deps.test.mockito)
    testImplementation(deps.test.junit)
}
