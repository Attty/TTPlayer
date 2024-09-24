# TTPlayer

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
| ![main_light](https://github.com/user-attachments/assets/3bdcc8b2-ffc9-4b08-b9c3-cd5c48f12730) | ![main_dark](https://github.com/user-attachments/assets/a7147a38-c4bd-4182-b68d-6608afe1f464) |
| ![player_light](https://github.com/user-attachments/assets/628df4f5-ccfe-4d5d-b8e4-b80f8fa79757) | ![player_dark](https://github.com/user-attachments/assets/e8509ff0-c0d7-4f52-9fa9-b24f278c96e2) |
| ![settings_light](https://github.com/user-attachments/assets/9d973fa7-2a82-408f-8685-14b116f1265b) | ![settings_dark](https://github.com/user-attachments/assets/886243d5-2d3e-48a1-8121-ed3476a70ab2) |



