plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    // ↓↓↓↓ 這就是那行關鍵的新外掛 ↓↓↓↓
    alias(libs.plugins.compose.compiler)
}

android {
    namespace = "com.example.jogging"
    compileSdk = 36

    defaultConfig {
        applicationId = "com.example.jogging"
        minSdk = 24
        targetSdk = 36
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    buildFeatures {
        compose = true
    }
//    composeOptions { 編譯器插件的版本是跟著 Kotlin 版本走的，所以這一行通常也可以安全地移除了。新的 Gradle 插件會自動處理好這一切。你可以試著先把它註解掉或刪掉。
//        kotlinCompilerExtensionVersion = "1.5.1" // 版本號可能會變
//    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    kotlinOptions {
        jvmTarget = "11"
    }
}

dependencies {

    // 核心
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.activity.compose) // 使用這個，它包含了 activity 和 compose 的整合

    // Compose (BOM 會管理版本)
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.compose.ui)
    implementation(libs.androidx.compose.ui.graphics)
    implementation(libs.androidx.compose.ui.tooling.preview)
    implementation(libs.androidx.compose.material3)

    // 測試
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(platform(libs.androidx.compose.bom)) // 測試也用 BOM
    androidTestImplementation(libs.androidx.compose.ui.test.junit4)

    // Debug
    debugImplementation(libs.androidx.compose.ui.tooling)
    debugImplementation(libs.androidx.compose.ui.test.manifest)

    // 如果你沒有要混用 XML，下面這幾行可以考慮移除
    // implementation(libs.androidx.appcompat)
     implementation(libs.material)
    implementation("com.google.android.material:material:1.12.0") // 請確認版本號
    // implementation(libs.androidx.constraintlayout)
}