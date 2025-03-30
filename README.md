# Payment App
This is a simple application to show the usage of the Clean Architecture and the MVVM pattern

## Architecture

This project is based on the Clean Architecture, which is a way to organize the code in layers, each
one with its own responsibilities. The main idea is to separate the business logic from the
framework and the UI. This way, the business logic can be tested without the need of a UI or a
framework.

The UI layer is build with the MVVM pattern. 

The module structure is simplified to show the main components of the architecture.

If the project was bigger, the module would be separated in sub-module to organize the 
code better and it would have more specific names depending on the package responsibility. 
For example, ":database" or ":purchase:domain" etc.

## Tests

This project has some unit tests as an example of testing approach, that I prefer to use.
The tests here are represented as a complex integration tests, which checks overall logic, and
simple unit tests are for complex or important parts of the application. Complex integration tests
help to detect problems with some flows and if problems are detected it is possible to debug and
find more complex problems. These problems can be covered with smaller unit tests, to prevent
problems in future

## Libraries

This project uses the following libraries:
- [Kotlin](https://kotlinlang.org/)
- [Hilt](https://dagger.dev/hilt/)
- [Jetpack](https://developer.android.com/jetpack)
- [Compose](https://developer.android.com/jetpack/compose)
- [Room](https://developer.android.com/jetpack/androidx/releases/room)
- [Retrofit](https://square.github.io/retrofit/)