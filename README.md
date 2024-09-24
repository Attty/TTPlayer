# Video Streaming App

This is a video streaming application built with Jetpack Compose for UI, designed to fetch and display videos from a remote server. It also supports offline caching of video metadata and dynamic theme switching between light and dark modes.

## Features
- **Video Playback**: Videos are streamed using **ExoPlayer**.
- **Image Loading**: Remote images are fetched and displayed using **Coil**.
- **Caching**: Video metadata is cached using **Room** for offline access.
- **Theme Switching**: Switch between light and dark themes with the theme data stored in **DataStore**.
- **Media Downloading**: Users can download both videos and images for offline use.
- **Dependency Injection**: Managed using **Hilt**.
- **Networking**: Data is fetched from a remote server using **Retrofit2**.

## Tech Stack
- **UI Framework**: Jetpack Compose
- **Networking**: Retrofit2
- **Caching**: Room Database
- **Dependency Injection**: Hilt
- **Theme Storage**: DataStore
- **Image Loading**: Coil
- **Video Playback**: ExoPlayer

## Screenshots
| Light Theme | Dark Theme |
|-------------|------------|
| ![Light Theme Screenshot](path-to-light-theme-screenshot.png) | ![Dark Theme Screenshot](path-to-dark-theme-screenshot.png) |
