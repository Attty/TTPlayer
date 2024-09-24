package com.example.ttplayer

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.viewinterop.AndroidView
import androidx.media3.common.MediaItem
import androidx.media3.exoplayer.ExoPlayer
import androidx.media3.ui.PlayerView
import androidx.navigation.compose.rememberNavController
import com.example.ttplayer.data.local.datastore.StoreSettings
import com.example.ttplayer.presentation.ui.theme.TTPlayerTheme
import com.example.ttplayer.presentation.video_list.screen.VideoListScreen
import dagger.hilt.android.AndroidEntryPoint
import java.lang.reflect.Modifier

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            val dataStore = StoreSettings(this)
            val darkTheme = dataStore.getTheme.collectAsState(initial = false)
            TTPlayerTheme(darkTheme = darkTheme.value!!) {
                TTPlayerApp(darkTheme = darkTheme.value!!)
            }
        }
    }
}



