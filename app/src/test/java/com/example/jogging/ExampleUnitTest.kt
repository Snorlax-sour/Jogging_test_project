package com.example.jogging

import org.junit.Test

import org.junit.Assert.*
import kotlin.test.assertFailsWith
/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ExampleUnitTest {
    // 測試案例 1: 測試完全合法的資料是否能成功建立物件
    @Test
    fun `create JoggingData with valid inputs succeeds`() {
        // Arrange (準備資料)
        val bpm = 160
        val minutes = 45
        val source = "YouTube"

        // Act (執行動作)
        val joggingData = Jogging_data_class(
            bpm_video = bpm,
            jogging_minutes = minutes,
            video_source = source
        )

        // Assert (驗證結果)
        assertEquals(bpm, joggingData.bpm_video)
        assertEquals(minutes, joggingData.jogging_minutes)
        assertEquals(source, joggingData.video_source)
    }

    // 測試案例 2: 測試當 BPM 過低時，是否會如預期般拋出錯誤
    @Test
    fun `create JoggingData with too low BPM throws exception`() {
        // Assert & Act: 驗證當執行 { } 區塊內的程式碼時，會拋出指定的 IllegalArgumentException
        assertFailsWith<IllegalArgumentException>("BPM 必須介於 150 到 220 之間") {
            Jogging_data_class(bpm_video = 100, jogging_minutes = 30)
        }
    }

    // 測試案例 3: 測試當跑步分鐘數為 0 時，是否會拋出錯誤
    @Test
    fun `create JoggingData with zero minutes throws exception`() {
        assertFailsWith<IllegalArgumentException>("跑步分鐘數必須大於 0") {
            Jogging_data_class(jogging_minutes = 0)
        }
    }
}