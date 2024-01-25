# Food Browser App

A basic Android app written in Kotlin for searching and displaying food items using a REST API.

## Features

- Search for food items by typing into the search bar.
- Fetches data from a REST API to get a list of food items based on the search term.
- Displays a list of food names in real-time as the user types.
- Tap on a food item to view a simple message with the food name.

## Getting Started

### Prerequisites

- Android Studio installed
- Internet permission added to the AndroidManifest.xml file

### Installation

1. Clone the repository: `git clone https://github.com/marto97/food-browser.git`
2. Open the project in Android Studio.
3. Run the app on an emulator or a physical device.

## Usage

1. Launch the app on your Android device.
2. Type a search term (at least 3 characters) into the search bar.
3. The app will query the REST API and update the list of food items in real-time.
4. Tap on a food item to view a simple message with the food name.

## API Details

- **API Base URL:** https://uih0b7slze.execute-api.us-east-1.amazonaws.com/dev/search
- **Query Parameter:** `kv` (at least 3 characters)

## Libraries Used

- [Gson](https://github.com/google/gson): A library for JSON serialization and deserialization.
- [OkHttp](https://github.com/square/okhttp): A networking library for making HTTP requests.
