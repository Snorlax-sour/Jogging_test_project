package com.example.jogging

// 档案路径: app/src/main/java/com/example/jogging/JoggingApplication.kt

import android.app.Application
import com.example.jogging.data.Jogging_database // 引入你的 Database class

class JoggingApplication : Application() { // 需要在AndroidManifest.xml修改：  android:name=".JoggingApplication"  // <<< 就是加上这一行！

    // 我们在这里使用 `lazy` 委托，来确保资料库只在“第一次被需要时”才会被创建
    // 这是一种更高效的单例实现方式
    val database: Jogging_database by lazy {
        // `this` 在 Application class 里，就代表了 Application Context
        Jogging_database.get_database(this)
    }

    // 未来，我们也会在这里创建 Repository 的实例
    // val repository: JoggingRepository by lazy { ... }
}