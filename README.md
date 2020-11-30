![Android Unit Tests](https://github.com/AnirudhBhat/NASA-pictures-app/workflows/Android%20Unit%20Tests/badge.svg)

# NASA-pictures-app

![](https://github.com/AnirudhBhat/NASA-pictures-app/blob/master/gifs/Nasa_pictures_app.gif)

## Installation

Just clone this repository and you should be good to run the app

**NOTE:** At the time of development apod.nasa.gov was not loading in India. I have used VPN throughout the development. You might need to use VPN to see the app functioning 

<br>

## Architecture

I have used MVI architecture for this app. Unidirection data flow helps and makes writing unit tests a breeze. <br>
Since the data was static json file, i have used `Interceptor` from `Retrofit` to mock the network request.

<br>

## Tests

This project has unit tests for repository and viewmodel <br>
To run all the unit tests just enter the below command <br>
./gradlew test 

<br>

## Things that could have been improved the app

1. Shimmer effect for loading
2. Better icon and placeholder

<br>

## Stuff i wanted to do but didn't had time

UI and integration tests. This project has 0 UI and integration tests since i don't have good experience writing UI tests
