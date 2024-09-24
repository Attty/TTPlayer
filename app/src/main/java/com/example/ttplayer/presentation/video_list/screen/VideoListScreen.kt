package com.example.ttplayer.presentation.video_list.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.lazy.staggeredgrid.LazyVerticalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.foundation.lazy.staggeredgrid.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.Card
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.MenuDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.DpOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.PopupProperties
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.example.ttplayer.SettingsScreenObj
import com.example.ttplayer.VideoListScreenObj
import com.example.ttplayer.VideoViewScreenObj
import com.example.ttplayer.downloader.AndroidDownloader
import com.example.ttplayer.presentation.video_list.viewmodel.VideoListViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun VideoListScreen(
    navController: NavController,
    viewModel: VideoListViewModel = hiltViewModel()
) {

    val state = viewModel.state.value
    var isButtonEnabled by remember { mutableStateOf(true) }
    val scope = rememberCoroutineScope()
    val context = LocalContext.current
    val downloader = AndroidDownloader(context)

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        "Player",
                        color = MaterialTheme.colorScheme.primary
                    )
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.onBackground,
                    titleContentColor = MaterialTheme.colorScheme.primary,
                    navigationIconContentColor = MaterialTheme.colorScheme.primaryContainer,
                    actionIconContentColor = MaterialTheme.colorScheme.primaryContainer
                ),
                actions = {
                    IconButton(onClick = { navController.navigate(SettingsScreenObj) }) {
                        Icon(imageVector = Icons.Default.Settings, contentDescription = "Settings")
                    }
                }
            )
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .background(MaterialTheme.colorScheme.background)
        ) {
            LazyVerticalStaggeredGrid(
                modifier = Modifier
                    .padding(8.dp),
                columns = StaggeredGridCells.Fixed(2),
                verticalItemSpacing = 12.dp,
                horizontalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                items(state.videoInfoItems) { photo ->
                    var isMenuExpanded by remember { mutableStateOf(false) }
                    Card(
                        modifier = Modifier
                            .fillMaxWidth()
                            .wrapContentHeight()

                    ) {
                        Box() {
                            AsyncImage(
                                modifier = Modifier
                                    .fillMaxSize()
                                    .clickable(enabled = isButtonEnabled) {
                                        if (isButtonEnabled) {
                                            isButtonEnabled = false
                                            navController.navigate(
                                                VideoViewScreenObj(
                                                    videoUrl = photo.video,
                                                    videoUrls = state.videoInfoItems.map { it.video }
                                                )
                                            )
                                            scope.launch {
                                                delay(1500)
                                                isButtonEnabled = true
                                            }
                                        }
                                    },
                                model = photo.image,
                                contentDescription = "${photo.id}",
                                contentScale = ContentScale.Crop,
                            )
                            Box(
                                modifier = Modifier
                                    .align(Alignment.TopEnd)
                            ) {
                                IconButton(
                                    onClick = { isMenuExpanded = !isMenuExpanded }
                                ) {
                                    Icon(
                                        imageVector = Icons.Default.MoreVert,
                                        contentDescription = "",
                                        tint = MaterialTheme.colorScheme.background,
                                    )
                                }
                                DropdownMenu(
                                    expanded = isMenuExpanded,
                                    onDismissRequest = { isMenuExpanded = false },
                                    modifier = Modifier.background(MaterialTheme.colorScheme.onBackground)
                                ) {
                                    DropdownMenuItem(
                                        text = { Text("Download image") },
                                        onClick = {
                                            downloader.downloadFile(
                                                photo.image,
                                                "image/jpeg",
                                                "image.jpg"
                                            )
                                            isMenuExpanded = false
                                        },
                                        colors = MenuDefaults.itemColors(
                                            textColor = MaterialTheme.colorScheme.primary
                                        )
                                    )
                                    DropdownMenuItem(
                                        text = { Text("Download video") },
                                        onClick = {
                                            downloader.downloadFile(
                                                photo.video,
                                                "video/mp4",
                                                "video.mp4"
                                            )
                                            isMenuExpanded = false
                                        }, colors = MenuDefaults.itemColors(
                                            textColor = MaterialTheme.colorScheme.primary
                                        )
                                    )
                                }
                            }

                            Icon(
                                imageVector = Icons.Default.PlayArrow, contentDescription = "",
                                tint = MaterialTheme.colorScheme.background.copy(alpha = 0.5f),
                                modifier = Modifier
                                    .align(Alignment.Center)
                                    .size(50.dp)
                            )
                        }

                    }


                }

            }

        }
    }

}