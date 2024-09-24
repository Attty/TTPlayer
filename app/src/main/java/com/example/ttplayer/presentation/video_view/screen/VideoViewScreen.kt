package com.example.ttplayer.presentation.video_view.screen

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Download
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.viewinterop.AndroidView
import androidx.media3.common.MediaItem
import androidx.media3.common.util.UnstableApi
import androidx.media3.exoplayer.ExoPlayer
import androidx.media3.ui.AspectRatioFrameLayout
import androidx.media3.ui.PlayerView
import androidx.navigation.NavController
import com.example.ttplayer.VideoListScreenObj
import com.example.ttplayer.downloader.AndroidDownloader
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@androidx.annotation.OptIn(UnstableApi::class)
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun VideoViewScreen(
    modifier: Modifier = Modifier,
    navController: NavController,
    videoUrl: String,
    videoUrls: List<String>
) {


    val context = LocalContext.current
    var isButtonEnabled by remember { mutableStateOf(true) }
    val scope = rememberCoroutineScope()
    val downloader = AndroidDownloader(context)


    val exoPlayer = remember {
        ExoPlayer.Builder(context).build().apply {
            val mediaItems = videoUrls.map {
                MediaItem.fromUri(it)
            }
            setMediaItems(mediaItems)
            val videoIndex = videoUrls.indexOf(videoUrl)
            seekTo(videoIndex, 0L)
            prepare()
            playWhenReady = true
        }
    }
    BackHandler {
        exoPlayer.release()
        navController.popBackStack()
    }

    Scaffold(
        topBar = {
            TopAppBar(title = { Text("Player", color = MaterialTheme.colorScheme.primary) },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.onBackground,
                    titleContentColor = MaterialTheme.colorScheme.primary,
                    navigationIconContentColor = MaterialTheme.colorScheme.primaryContainer,
                    actionIconContentColor = MaterialTheme.colorScheme.primaryContainer
                ),
                actions = {
                    IconButton(onClick = {
                        downloader.downloadFile(
                            videoUrl,
                            "video/mp4",
                            "video.mp4"
                        )
                    }) {
                        Icon(
                            imageVector = Icons.Default.Download,
                            contentDescription = "Download Video"
                        )
                    }
                },
                navigationIcon = {
                    IconButton(
                        onClick = {
                            if (isButtonEnabled) {
                                isButtonEnabled = false
                                exoPlayer.release()
                                navController.popBackStack()
                                scope.launch {
                                    delay(1500)
                                    isButtonEnabled = true
                                }
                            }
                        },
                        enabled = isButtonEnabled
                    ) {
                        Icon(
                            imageVector = Icons.Default.ArrowBack,
                            contentDescription = "Back",
                            tint = MaterialTheme.colorScheme.primaryContainer
                        )
                    }
                })
        }
    ) { paddingValues ->
        val shutterBackgroundColor = MaterialTheme.colorScheme.background.toArgb()
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
        ) {
            AndroidView(
                factory = {
                    PlayerView(context).apply {
                        player = exoPlayer
                        resizeMode = AspectRatioFrameLayout.RESIZE_MODE_FILL
                        setShutterBackgroundColor(shutterBackgroundColor)
                    }
                },
                modifier = modifier.fillMaxSize()
            )
        }
    }

}


