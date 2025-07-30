package com.example.jogging

// 档案路径: app/src/main/java/com/example/jogging/data/JoggingDatabase.kt

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase


// 1. 用 @Database 标注，这是总工厂的「营业执照」
@Database(
    // 2. 在 `entities` 阵列里，登记所有你拥有的资料表
    entities = [Jogging_data_class::class],
    // 3. `version` 是资料库的版本号，非常重要！
    //    每次你修改了资料表的结构（例如增删栏位），都必须把这个数字 +1
    version = 1,
    // 4. (可选) 是否要汇出 schema，对于大型专案很有用
    exportSchema = false
)
abstract class Jogging_database : RoomDatabase() { // 5. 必须是 abstract class，继承自 RoomDatabase

    // 6. 在这里，定义一个抽象的函式，来对外提供你的「服务窗口 (DAO)」
    //    Room 会在背后自动帮你实现这个函式
    abstract fun jogging_dao(): Jogging_dao // << Room 会自动帮你实现这个

    /*
     *  如果你未来有 User_dao, Setting_dao...
     *  你就继续在这里增加：
     *  abstract fun user_dao(): User_dao
     *  abstract fun setting_dao(): Setting_dao
     */
    companion object {

        // `@Volatile` 确保 INSTANCE 变数在多执行绪环境下永远是最新值
        @Volatile
        private var INSTANCE: Jogging_database? = null

        // 这是对外提供资料库实例的“总机函式”
        fun get_database(context: Context): Jogging_database { // 裡面有context
            // `?:` (Elvis operator)
            // 如果 INSTANCE 不是 null，就直接回传它
            // 如果 INSTANCE 是 null，就执行 synchronized 区块里的程式码
            return INSTANCE ?: synchronized(this) {
                // `synchronized` 确保在多执行绪下，只有一个执行绪能同时进入这个区块
                // 防止我们意外地创建出两个资料库实例
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    Jogging_database::class.java,
                    "jogging_database" // 这是你资料库在手机硬碟上的档案名
                ).build()
                INSTANCE = instance

                // 把新创建的 instance 回传出去
                instance
            }
        }
    }
}