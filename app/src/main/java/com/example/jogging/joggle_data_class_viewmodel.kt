package com.example.jogging

import androidx.lifecycle.ViewModel

class joggle_data_class_viewmodel : ViewModel() {

    // 被UI call的function
    fun save_record(bmps: String, minute: String, video_source: String){
        val bpm = bmps.toIntOrNull()
        val minutes = minute.toIntOrNull()
        if (bpm == null || bpm !in 150..220){ // is null or not in 150 <= bpm <= 220
            // [TODO] UI業務處理邏輯
            return
        }
        if (minutes == null || minutes !in 1..179){
            // [TODO] UI業務處理邏輯
            return
        }
        // 3. 所有驗證都通過後，才建立 JoggingData 物件
        val newRecord = Jogging_data_class(bpm_video = bpm, jogging_minutes = minutes, video_source = video_source.ifEmpty { null })
    }
}