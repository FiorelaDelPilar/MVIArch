// Top-level build file where you can add configuration options common to all sub-projects/modules.
plugins {
    alias(libs.plugins.android.application) apply false
    alias(libs.plugins.kotlin.android) apply false
    id("com.google.devtools.ksp") version "2.0.21-1.0.25" apply false //https://stackoverflow.com/questions/69656802/unable-to-find-method-void-org-jetbrains-kotlin-gradle-dsl-kotlinjvmoptions-setu
}