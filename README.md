# K2DBinarySearch
Mobile application to demonstrate how binary search works.

- [Description](#description)
- [Technologies](#technology)
- [Project stats](#project_stats)

# Description
This application was created to demonstrate how binary search works. The main idea of binary search is to get a sorted array of numbers as input. Pick an average (or random). If we guessed correctly, then success, otherwise we specify whether the guessed number is more or less than the one found? And as a result, instead of the full length of the array, we now need to perform another search in the remaining half. Due to binary search, the search itself is very fast (On²).

## Features
- [Minimalistic design](#minimalistic_design)
- [Getting a random number via API](#)
- [Offline random number generation](#)
- [Non repeating random numbers](#)
- [Very nice menu](#)
- [Database (ROOM) for results](#)
- [Double animation](#)
- [Beautiful icon](#)
- [Mysterious splashscreen](#)

### Minimalistic design
Beautiful and elegant design, without unnecessary elements.


### Getting a random number via API
Retrofit2 was used to get a random number through the API of a third-party resource.


### Offline random number generation
If the Internet is unavailable, the application automatically uses the built-in random number generator.


### Non repeating random numbers
When a regular random number generator is used, one may encounter a situation of repeated numbers. With the help of the code below, it was possible to bypass this limitation.

```fun getFirstRandom(upperLimit: Int = 100000) = SecureRandom().nextInt(upperLimit)```


### Very nice menu


### Database (ROOM) for results


### Double animation


### Beautiful icon


### Mysterious splashscreen




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
<img src="https://github.com/K2D2021/K2DBinarySearch/blob/master/K2DBinarySearchStatistics.jpg">


