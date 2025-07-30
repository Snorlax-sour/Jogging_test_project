package com.example.jogging

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.jogging.Jogging_data_class // 确保 import
import com.example.jogging.Jogging_repository // 确保 import
import kotlinx.coroutines.launch

class Joggle_data_class_viewmodel (
    private val repository: Jogging_repository
) : ViewModel() {

    // 被UI call的function
    // 这个函数会被 UI 呼叫
    fun save_record(bmps: String, minute: String, video_source: String){
        // 2. 将 UI 传来的字串，转换成数字
        val bpm = bmps.toIntOrNull()
        val minutes = minute.toIntOrNull()

        // 3. 在这里进行所有的验证！
        if (bpm == null || bpm !in 150..220){
            // TODO: 通知 UI，BPM 输入错误！
            return
        }
        if (minutes == null || minutes <= 0 || minutes > 180){ // << 我帮你修正了逻辑
            // TODO: 通知 UI，分钟数输入错误！
            return
        }

        // 4. 所有验证都通过后，才建立 Jogging_data_class 物件
        val newRecord = Jogging_data_class(
            bpm_video = bpm,
            jogging_minutes = minutes,
            video_source = video_source.ifEmpty { null }
        )

        // 5. 【关键】使用 repository，把资料存起来！
        //    我们必须在 viewModelScope.launch 中呼叫 suspend 函数
        viewModelScope.launch {
            repository.save_record(newRecord)
            // TODO: 可以在这里更新 UI，告诉用户储存成功
        }
    }
}