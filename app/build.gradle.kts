plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    // ↓↓↓↓ 這就是那行關鍵的新外掛 ↓↓↓↓
    alias(libs.plugins.compose.compiler)
    alias(libs.plugins.ksp) // <<< 加上這一行
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
//    implementation("com.google.android.material:material:1.12.0") // 請確認版本號
    // ↓↓↓↓ 加上這三行 ↓↓↓↓
    // Room 核心函式庫
    implementation(libs.androidx.room.runtime)
    //  Dagger 和 Room 沒有任何直接關係
//    implementation("com.google.dagger:dagger-compiler:2.51.1")
//    ksp("com.google.dagger:dagger-compiler:2.51.1")
    // Room 的 Kotlin 擴充，提供協程支援 (必加！)
    implementation(libs.androidx.room.ktx) // 提供協程 (Coroutines) 支援，必加
    // Room 的 Compiler (編譯器)，它必須透過 ksp 這個工具來使用
    ksp(libs.androidx.room.compiler)      // 這是 Room 的程式碼產生器
    // implementation(libs.androidx.constraintlayout)
    // (可選，但推薦) Room 的測試輔助工具
    // testImplementation(libs.androidx.room.testing)
    // ↑↑↑ 上面這行你需要先在 libs.versions.toml 裡定義好別名
}