package com.example.jogging

import kotlinx.coroutines.flow.Flow

//後端處理資料邏輯，是實現 repository的地方
class  Jogging_dao_impl (private val jogging_dao: Jogging_dao): Jogging_repository {
    override fun get_all_record(): Flow<List<Jogging_data_class>> {
       return jogging_dao.get_all_records()
    }

    override suspend fun save_record(record: Jogging_data_class): Boolean {
        return try {
            jogging_dao.save_record(record)
            true
        } catch (e: Exception){
            e.printStackTrace()
            false
        }
    }

    override suspend fun delete_record(record: Jogging_data_class): Boolean {
        return try {
            jogging_dao.delete_record(record)
            true
        } catch (e: Exception){
            e.printStackTrace()
            false
        }
    }

    override suspend fun upload_to_cloud(): Boolean {
        return try {
            jogging_dao.get_all_records_one_shot()
            true
        } catch (e: Exception){
            e.printStackTrace()
            false
        }
    }

    override suspend fun resotre_from_cloud(): Boolean {
        return try {
            // --- 【第一阶段：云端下载与解析】 ---

            // 1. TODO: 呼叫 Firebase SDK 去下载备份档案，得到 jsonString
            val json_string_from_cloud = download_backup_file_from_firebase() // (这是一个我们未来要写的假想函数)

            // 2. TODO: 呼叫 JSON 解析函式库，把字串变成物件列表
            val records_from_cloud = parse_json_to_data_list(json_string_from_cloud) // (这也是一个假想函数)

            // --- 【第二阶段：写入本地数据库】 ---

            // 3. 把我们从云端拿到的 recordsFromCloud，传给 DAO
            // (可选但推荐) 先清空本地资料
            jogging_dao.clear_table()
            // 批量写入
            jogging_dao.insert_all(records_from_cloud)

            // 所有步骤都没出错，回传成功
            true
        } catch (e: Exception) {
            e.printStackTrace()
            false
        }
    }

    override fun search_records_stream(search_string: String): Flow<List<Jogging_data_class>> {
        return jogging_dao.search_string(search_string)
        // 意外寫成return true false
    }

}