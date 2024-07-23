plugins {
    alias(libs.plugins.note.android.library)
    alias(libs.plugins.note.android.room)
}

android {
    namespace = "com.note.database"
    testOptions {
        unitTests {
            isIncludeAndroidResources = true
            isReturnDefaultValues = true
        }
    }
}

dependencies {
    api(projects.model)

    implementation(libs.kotlinx.datetime)
}
