# K2DBinarySearch
Mobile application to demonstrate how binary search works.

- [Description](#description)
- [Technologies](#technologies)
- [Project stats](#project-stats)

# Description
This application was created to demonstrate how binary search works. The main idea of binary search is to get a sorted array of numbers as input. Pick an average (or random). If we guessed correctly, then success, otherwise we specify whether the guessed number is more or less than the one found? And as a result, instead of the full length of the array, we now need to perform another search in the remaining half. Due to binary search, the search itself is very fast (On²).

## Features
- [Minimalistic design](#minimalistic-design)
- [Getting a random number via API](#getting-a-random-number-via-api)
- [Offline random number generation](#offline-random-number-generation)
- [Non repeating random numbers](#non-repeating-random-numbers)
- [Very nice menu](#very-nice-menu)
- [Database (ROOM) for results](#database-\(room\)-for-results)
- [Double animation](#double-animation)
- [Beautiful icon](#beautiful-icon)
- [Mysterious splashscreen](#mysterious-splashscreen)

### Minimalistic design
Beautiful and elegant design, without unnecessary elements.

<img src="https://github.com/K2D2021/K2DBinarySearch/blob/master/Gifs/K2DBinarySearchVideosDesign.gif">



### Getting a random number via API
Retrofit2 was used to get a random number through the API of a third-party resource.

<img src="https://github.com/K2D2021/K2DBinarySearch/blob/master/Gifs/K2DBinarySearchVideosAPI.gif">



### Offline random number generation
If the Internet is unavailable, the application automatically uses the built-in random number generator.

<img src="https://github.com/K2D2021/K2DBinarySearch/blob/master/Gifs/K2DBinarySearchVideosOffline.gif">



### Non repeating random numbers
When a regular random number generator is used, one may encounter a situation of repeated numbers. With the help of the code below, it was possible to bypass this limitation.

```fun getFirstRandom(upperLimit: Int = 100000) = SecureRandom().nextInt(upperLimit)```

<img src="https://github.com/K2D2021/K2DBinarySearch/blob/master/Gifs/K2DBinarySearchVideosTrueRandom.gif">



### Very nice menu
Beautiful and understandable icons are specially designed for this application.

<img src="https://github.com/K2D2021/K2DBinarySearch/blob/master/Gifs/K2DBinarySearchVideosMenu.gif">



### Database (ROOM) for results
ROOM database for storing game results with the possibility of complete cleaning

<img src="https://github.com/K2D2021/K2DBinarySearch/blob/master/Gifs/K2DBinarySearchVideosROOM.gif">



### Double animation
An unobtrusive animated background and a catchy foreground element

<img src="https://github.com/K2D2021/K2DBinarySearch/blob/master/Gifs/K2DBinarySearchVideosAnimation.gif">



### Beautiful icon
Especially for this application, an icon has been developed in the same style with this application and the icons of my other applications.

<img src="https://github.com/K2D2021/K2DBinarySearch/blob/master/Gifs/K2DBinarySearchIcon.jpg">



### Mysterious splashscreen
Deep and mysterious splash screen at the loading stage

<img src="https://github.com/K2D2021/K2DBinarySearch/blob/master/Gifs/K2DBinarySearchSplashScreen.jpg">



# Technologies
- Clean architecture 
- SOLID
- MVVM
- ROOM
- Androidx.navigation
- Lifecycle
- Retrofit
- Single activity approach
- [View binding](https://developer.android.com/topic/libraries/view-binding)
- Kotlin coroutines are used for asynchronous operations

# Project stats
<img src="https://github.com/K2D2021/K2DBinarySearch/blob/master/Gifs/K2DBinarySearchStatistics.jpg">


