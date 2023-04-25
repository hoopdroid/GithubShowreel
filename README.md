# My Android Github Repositories Showreel

This Android app connects to the Github API, fetches the public repositories of a specified user, and retrieves their respective last commits. The project took a total of 4 hours to develop.

<table>
  <tr>
    <td
      <img src="video.gif" alt="Demo Video" width="125" height="250" />
    </td>
  </tr>
</table>


## Table of Contents

- [Features](#features)
- [Installation](#installation)
- [Project Structure](#project-structure)
- [Future Improvements](#future-improvements)

## Features

- Fetches public repositories of a specified user
- Retrieves the last commits for each repository
- Displays the repositories and commits in an organized manner

## Installation

Download the .apk file located in the root folder of the repository and install it on your Android device.

## Project Structure

The project is divided into four main packages:

1. base: Contains the .App class and a utils package with utility classes. Dependency injection is achieved through Dagger Hilt.
2. domain: Includes Interactors for the Repositories list and CommitHistory screens. A Mapper is used for mapping between RepositoryResponseItem and RepositoryVO classes.
3. data: Contains DataSources with network calls to retrieve commits history and user repositories. It acts as a proxy layer for unit testing.
4. presentation: Implements the MVVM architecture with Fragments as Views and ViewModels as the entry points to retrieve data from the Interactors.

### Base

This package includes the .App class and a utils package with utility classes. Dagger Hilt is used for dependency injection. Dependencies are provided by constructors, and the @Binds method approach is used to separate interface contracts and implementations.

### Domain

This package contains Interactors for the Repositories list and CommitHistory screens. It implements the separation of data models for UI and Data layers to facilitate testing and provide a more flexible architecture. The main logic with timer of commits is implemented using RxJava's Observable.interval. The current implementation of CommitsHistoryInteractor can be improved in terms of code complexity and reliability.

### Data

This package includes DataSources with network calls to commits history and user repositories. It acts as a proxy layer for unit testing.

### Presentation

The MVVM architecture is implemented with Fragments as Views and ViewModels as the entry points for retrieving data from Interactors. The app utilizes NavController for navigation with safe argument passing (navigation-safe-args). The current implementation can be improved by adding proper Loading and Error states.

## Future Improvements

- Add unit tests for data and domain layers
- Test ViewModel states for correct order
- Improve the implementation of CommitsChartView by adding attributes and styleables handling
- Implement proper Loading and Error states in the app
