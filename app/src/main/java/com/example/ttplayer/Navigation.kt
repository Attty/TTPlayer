package com.example.ttplayer

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable

import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import com.example.ttplayer.presentation.settings.screen.SettingsScreen
import com.example.ttplayer.presentation.ui.theme.TTPlayerTheme
import com.example.ttplayer.presentation.video_list.screen.VideoListScreen
import com.example.ttplayer.presentation.video_view.screen.VideoViewScreen
import kotlinx.serialization.Serializable

@Composable
fun TTPlayerApp(
    modifier: Modifier = Modifier,
    darkTheme: Boolean
) {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = VideoListScreenObj) {
        composable<VideoListScreenObj> {
            VideoListScreen(navController = navController)
        }
        composable<VideoViewScreenObj> {
            val args = it.toRoute<VideoViewScreenObj>()
            VideoViewScreen(
                navController = navController,
                videoUrl = args.videoUrl,
                videoUrls = args.videoUrls
            )
        }
        composable<SettingsScreenObj> {
            SettingsScreen(
                navController = navController,
                darkThemeSaved = darkTheme
            )
        }
    }
}

@Serializable
object VideoListScreenObj

@Serializable
object SettingsScreenObj

@Serializable
data class VideoViewScreenObj(
    val videoUrl: String,
    val videoUrls: List<String>
)