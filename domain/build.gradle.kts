plugins {
    `java-library`
    id("kotlin")
}


dependencies {
    implementation(fileTree(mapOf("dir" to "libs", "include" to listOf("*.jar"))))
    implementation(deps.kotlin.jdk7)
    implementation(deps.rx.java)
    implementation(deps.rx.kotlin)
}
