Github Repositories Showreel
4 hours total time spent

The task:
The aim of this test assignment is to build an App that connects to the Github API, shows the
public repositories of a particular user and then retrieves their respective last commits

Implementation:
You can find .apk file in the root folder

The structure of the project:

-base
-domain
-data
-presentation

Base
Here is the package that includes .App class and utils package with util classes as well
For DI I've selected Dagger Hilt, it's actually my first experience working with Hilt, I'm more 
comfortable with Koin, but it was the strict amount of 3rd-party libraries. 
So, all dependencies are providing by constructor (where it's possible), to separate interface contracts
and implementations I used @Binds method approach

Domain
Includes Interactors for Repositories list and CommitHistory screens
I think that in large applications with large team as well we need to have different data models for UI layer and Data layer as well. 
It helps to test different layers, provides more flexible architecture as well
That's why I created Mapper for RepositoryResponseItem to RepositoryVO class
The main logic with timer of commits is implemented with RxJava Observable.interval
Speaking about CommitsHistoryInteractor, I'm not fully satisfied about the mapping, 
timer logic and implementation of months extraction in this class. 
Due the time limitations I decided to proceed with current impl, but
its definitely area of future improvement in terms of code complexity and reliability

Data
Includes DataSources with network call to commits history and user repositories. It's kind of a proxy
layer to give more flexibility for possible unit testing

Presentation
Implementation of MVVM with with Fragment as a View and ViewModel as an entry point to getting the data from Interactor
MVVM basically helps to separate the logic and use Fragment class just for a rendering.
Also I created a sealed classes to handle to different states of screens. 
Its the area of possible improvement to implement Loading proper and Error states in the app
For the navigation I used NavController with safe argument passing (navigation-safe-args)


If I had more time, I'd like to implement some unit tests for data and
domain layers and also test that all possible ViewModel states were called in correct order,
Also with more time I'd like to improve CommitsChartView as well. There is no attributes and styleables
handling in the view, it means you can't control it via xml file, then the view is not production ready



