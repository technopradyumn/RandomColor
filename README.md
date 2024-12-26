# Random Color App

**Random Color App** is an Android application built using Kotlin, Jetpack Compose, and Room Database for storing colors locally. The app allows users to add random colors, view them in a grid, and sync unsynced colors to Firebase.

## Features

- **Add Random Color**: Adds a new random color with a timestamp to the local Room database.
- **Offline Mode**: The app shows the list of colors even when the device has no internet connectivity, as colors are stored locally using RoomDB.
- **Sync Colors**: Unsynced colors are sent to Firebase when the "Sync" button is clicked. Only unsynced colors are uploaded to Firebase to prevent duplicates.
- **Unsynced Color Count**: Displays the number of colors that haven't been synced yet. This number gets updated automatically when new colors are added or synced.
- **Persistent Data**: The app stores color information in the Room database, making the data available even if the app is closed or the device is offline.

## Tech Stack

- **Kotlin**: Main programming language for the app.
- **Jetpack Compose**: For building the user interface.
- **Room Database**: For offline data storage.
- **Firebase**: For syncing unsynced colors to the cloud.
- **StateFlow**: Used for managing and updating the UI with Live data from the database.

## How to Use

1. **Add Color**: Tap the "Add Color" button to add a new random color. The color and timestamp are stored locally in RoomDB.
2. **Sync Colors**: Tap the "Sync" button to upload unsynced colors to Firebase. The app automatically updates the local database to mark the synced colors.
3. **View Colors**: All colors, synced and unsynced, are displayed in a grid on the main screen.

## Setup

1. Clone the repository.
2. Open the project in Android Studio.
3. Make sure you have a Firebase project set up and connected to your app (for Firebase syncing functionality).
4. Run the app on an emulator or physical device.

## Contributing

Feel free to fork the repository, submit issues, and open pull requests. Contributions are welcome!
