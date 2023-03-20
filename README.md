# LB Meals

LB Meals is an Android application that provides meal recipes and their details. The application consumes [TheMealDB API](https://www.themealdb.com).

## Description

LB Meals offers the following features:
* Search meal recipes by category
* Filter meal recipes by name
* View pictures, ingredients, and instructions for the selected meal.

## Technologies

The application is built using the following technologies:

* [Coroutines](https://kotlinlang.org/docs/coroutines-overview.html) for asynchronous programming
* [ViewModel](https://developer.android.com/reference/androidx/lifecycle/ViewModel) for managing UI-related data
* [Retrofit](https://square.github.io/retrofit) for consuming RESTful web services
* [Room DB](https://developer.android.com/training/data-storage) for local data storage
* [Jetpack Compose](https://developer.android.com/jetpack/compose/documentation) for building the UI
* [Hilt](https://developer.android.com/training/dependency-injection/hilt-android) for dependency injection
* [JUnit 5](https://junit.org/junit5/docs/current/user-guide) for unit testing (Not yet implemented)
* [MockK](https://mockk.io) for mocking objects in unit tests (Not yet implemented)
* [Espresso](https://developer.android.com/training/testing/espresso) for UI testing (Not yet implemented)
* [Github Actions](https://docs.github.com/pt/actions/learn-github-actions) for Continuous Integration/Continuous Deployment (CI/CD)
* [Dokka](https://github.com/Kotlin/dokka) for generating documentation
* [JaCoCo](https://www.jacoco.org) for generating test coverage reports

## Getting Started

1. Download the LBMeals.apk file from the [Build and Deploy APK](https://github.com/LeonardoBai12/LBMeals/actions/workflows/build_and_deploy_workflow.yml) action artifacts.
2. nstall the APK on your Android device.
3. Use the application to find meal recipes and their details. =)

## Documentation

The documentation is automatically generated and published for every push to the main branch.\
To access the documentation, download the l_lb-meals-documentation_ file from the [Documentation](https://github.com/LeonardoBai12/LBMeals/actions/workflows/documentation_workflow.yml) action artifacts.

## Coverage Report

A unit test coverage report is generated and published for every push to the main branch.\
To access the test coverage report, download the _lb-meals-coverage-report_ file from the [Coverage Report](https://github.com/LeonardoBai12/LBMeals/actions/workflows/coverage_report_worflow.yml) action artifacts.

## Demonstration

You can watch a demo of the application's features on this [YouTube Shorts Video](https://youtube.com/shorts/UrDXk4dbqTw?feature=share)
