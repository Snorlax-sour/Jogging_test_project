package com.example.jogging

import kotlinx.coroutines.flow.Flow

interface Jogging_repository{
    // @return 搜尋結果，會自動更新
    fun get_all_record(): Flow<List<Jogging_data_class>> // 用flow 可以即時更新suspend 雖然是說可以背景執行，不過沒有互斥關係、影響
    // #param record, 可以新增與修改
    // @return 是否操作 (修改 or 新增) 成功
    suspend fun save_record(record: Jogging_data_class): Boolean // 可以與modify混合，因為有已經的資料時會變成更新
//    suspend fun modify_record(record: Jogging_data_class): Boolean
    // @param record，針對一個資料刪除
    // @return 是否操作 (刪除) 成功
    suspend fun delete_record(record: Jogging_data_class): Boolean
    // @return 是否操作 (上傳) 成功
    suspend fun upload_to_cloud(): Boolean // record: List<Jogging_data_class>不要參數，因為是全部資料上傳
    // @return 是否操作 (恢復雲端資料) 成功
    suspend fun resotre_from_cloud(): Boolean // record: List<Jogging_data_class>不需要，因為是下載下來而已
    // @param search_string
    // @return 會自動更新搜尋值
     fun search_records_stream(search_string: String): Flow<List<Jogging_data_class>> // 可以自動更新，不能與suspend一起出現，本身flow不會消耗太多性能

}