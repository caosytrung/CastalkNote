plugins {
    alias(libs.plugins.note.android.library)
}

android {
    namespace = "com.note.data"
    testOptions {
        unitTests {
            isIncludeAndroidResources = true
            isReturnDefaultValues = true
        }
    }
}

dependencies {
    implementation(projects.common)
    implementation(projects.database)
}
