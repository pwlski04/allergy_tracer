plugins {
    alias(libs.plugins.kotlin.jvm)
    application
}

kotlin {
    jvmToolchain(11)
}

dependencies {
    implementation(project(":shared"))
}

application {
    mainClass.set("MainKt")
}
