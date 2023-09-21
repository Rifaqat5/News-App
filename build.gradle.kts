// Top-level build file where you can add configuration options common to all sub-projects/modules.
plugins {
    id("com.android.application") version "8.1.0" apply false
    id("org.jetbrains.kotlin.android") version "1.8.0" apply false
    //    This is ksp library alternate of kotlin kapt and one is included in second gradle file.
    id("com.google.devtools.ksp") version "1.8.21-1.0.11" apply false

//    This is safe args for navigation between fragments and one is included in second gradle file.
    id("androidx.navigation.safeargs.kotlin") version "2.6.0" apply false
}