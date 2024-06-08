# Weather Application

## Overview

This Weather Application is a Java-based GUI application that fetches and displays the current weather conditions for a specified location. It utilizes the Open-Meteo API for retrieving weather data and provides a user-friendly interface for displaying temperature, humidity, weather conditions, and wind speed.

## Features

- Fetches weather data for any location using the Open-Meteo API.
- Displays current temperature, weather conditions, humidity, and wind speed.
- User-friendly interface with icons representing different weather conditions.
- Input validation to ensure non-empty location queries.

## Prerequisites

- Java Development Kit (JDK) 8 or higher
- Internet connection for API access

## Setup

1. **Clone the repository**

   ```sh
   git clone https://github.com/yourusername/weather-application.git
   cd weather-application

2. **Download Dependencies**

Ensure that you have the necessary libraries. This project uses json-simple for parsing JSON data. You can download it from json-simple and include it in your project's classpath.

## Project Structure

    App.java - Contains the logic for fetching weather data from the Open-Meteo API.
    WeatherApp.java - Defines the GUI for displaying the weather information.
    AppLauncher.java - The main class to launch the application.

## API Usage

This application uses the following APIs:

    Open-Meteo API for weather data
    Open-Meteo Geocoding API for location data

The weather data API endpoint:

https://api.open-meteo.com/v1/forecast?latitude=<latitude>&longitude=<longitude>&hourly=temperature_2m,relativehumidity_2m,weathercode,windspeed_10m&timezone=America%2FLos_Angeles

The geocoding API endpoint:

https://geocoding-api.open-meteo.com/v1/search?name=<locationName>&count=10&language=en&format=json

