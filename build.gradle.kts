// Top-level build file where you can add configuration options common to all sub-projects/modules.
plugins {
    alias(libs.plugins.android.application) apply false
    alias(libs.plugins.kotlin.android) apply false
    alias(libs.plugins.compose.compiler) apply false
    alias(libs.plugins.ksp) apply false
//    alias(libs.plugins.room) apply false // apply false 的意思我們之前講過：「我只是在這裡登記一下有這個工具，別亂用。」
}