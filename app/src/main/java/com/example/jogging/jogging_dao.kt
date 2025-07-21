package com.example.jogging
import androidx.room.*
import kotlinx.coroutines.flow.Flow

// 與repository對應功能
// 1. 用 @Dao 标注，告诉 Room 这是我们的「指令清单」
@Dao //Interface 只能「宣告」，不能「實做」
interface Jogging_dao{
    // --- 【对应 Repository 的 Flow<List> 功能】 ---

    // 2. 用 @Query 注解，在里面直接写 SQL 查询语句！
    @Query("SELECT * FROM jogging_records_table ORDER BY id DESC")
    // `ORDER BY id DESC` 让最新的纪录显示在最上面
    fun get_all_records(): Flow<List<Jogging_data_class>>
    // 只須一次獲取

    @Query("SELECT * FROM jogging_records_table WHERE video_source LIKE '%' || :query_string || '%' ORDER BY id DESC")
    fun search_string(query_string: String): Flow<List<Jogging_data_class>>

    // 3. 用 @Insert / @Update 注解。Room 强大之处在于，你不需要写 SQL！
    //    `onConflict` 策略是我们实现「save = insert + update」的关键
    @Upsert // 这是 Room 2.5.0 新增的注解，直接 = insert + update，更简单！
    suspend fun save_record(record_data: Jogging_data_class)
    /*
        // 如果你的 Room 版本较旧，需要用下面这种方式
        @Insert(onConflict = OnConflictStrategy.REPLACE)
        suspend fun insert_or_Update(record: Jogging_data_class)
    */

    // 4. 用 @Delete 注解，同样不需要写 SQL！Room 会自动根据物件的主键去删除。
    @Delete
    suspend fun delete_record(delete_record_data: Jogging_data_class)
    // --- 【为了云端功能，补充下面的辅助函数】 ---

    /**
     * 【为了备份】一次性获取所有记录的静态列表。
     * 和 Flow 不同，这个函数只执行一次查询并返回结果。
     * @return 当前所有记录的 List 快照。
     */
    @Query("SELECT * FROM jogging_records_table")
    suspend fun get_all_records_one_shot(): List<Jogging_data_class>

    /**
     * 【为了恢复】批量插入一个记录列表。
     * 这比逐一插入高效得多。
     * onConflict = REPLACE 确保了如果 ID 冲突，会用新数据覆盖。
     * @param records 要插入的记录列表。
     */
    @Insert(onConflict = OnConflictStrategy.REPLACE)
        suspend fun insert_all(records: List<Jogging_data_class>)

    /**
     * 【为了恢复】清空整个数据表。
     * 在恢复数据前执行此操作，可以确保本地数据库和备份文件完全一致。
     */
    @Query("DELETE FROM jogging_records_table")
    suspend fun clear_table()



}

