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
        // A surface container using the 'background' color from the theme
        enableEdgeToEdge()
        // 1. 获取我们的 Application 实例
        val application = this.application as JoggingApplication
        // 2. 从 Application 中，拿到那个唯一的资料库实例
        val database = application.database
        // 3. 从资料库实例中，拿到 DAO
        val dao = database.jogging_dao()

        // 4. 创建 Repository，并把 DAO 作为“工人”传给它
        val repository = Jogging_dao_impl(jogging_dao = dao)
        // TODO: 我们还需要一个 ViewModelFactory 来告诉 Android 如何创建这个需要参数的 ViewModel
        // val viewModel = ...
        // 5. 最后，创建 ViewModel，并把 Repository 作为“资料管理员”传给它
        //    (这需要一个 ViewModelFactory，我们后面再讲)
        viewModel = Joggle_data_class_viewmodel(repository = repository)

        setContent {
            // ↓↓↓↓ 這個才是真正控制你 Compose UI 樣式的部分 ↓↓↓↓
            JoggingTheme {


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