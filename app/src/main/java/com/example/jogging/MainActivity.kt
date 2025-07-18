package com.example.jogging

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.activity.enableEdgeToEdge // <<< 確保 import 這個函式庫
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.activity.compose.setContent
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text // <<< 確保有這個 import
//import org.w3c.dom.Text // java 的
import com.example.jogging.ui.theme.JoggingTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            // ↓↓↓↓ 這個才是真正控制你 Compose UI 樣式的部分 ↓↓↓↓
            JoggingTheme {
                // A surface container using the 'background' color from the theme
                enableEdgeToEdge()

                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    // 你其他的 Composable 函式放這裡，例如 SimpleCounterScreen()
                    Greeting("Android") // 我們來呼叫一個簡單的函式
                }
            }
        }

    }
}
@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}