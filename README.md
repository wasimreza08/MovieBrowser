# MovieBrowser
In this app the user can go through the movies of TMDB. To run this project you have clone this project and take an api key from https://developers.themoviedb.org/3/getting-started/introduction and place it in the domain-movie build.gradle. 

This project is a example short project build with MVI (Model, View , Intent) architecture. In MVI archtecture every action of user is regards as intent or event. The view model handle this events and change the model accordingly to change the view state. The view state is consumed by the view controllers (Activity, Fragment). The contract of view model contains all the view states, effects and user events.  

## State
View state is the single source of truth for the view controllers from which they need to render the view of a screen. The view state extends base view model view state. I used state flow to hold the view state. The state flow is designed to hold state. It needs an initial state and it can cache the last state. So when configuration changed it can provide the last state again to view controllers. 

## Effects
Effects are mostly the side effects of the view e.g., unknown error, network error etc. For an error condition the view controller has to show some side effects like dialog, snackbar or toast. For handling the effects I have used channels which is provided to view controllers as flow. Channels are hot stream which can have only one subscriber. It can cache the data in buffer. By default no data has been dropped by a channel. 

## Events
The user interaction with app is called events. The events are handled by the view model. For events I have used shared flow. Shared flow is a hot stream. If there is no active subscriber then any steam of shared flow is instantly dropped. Shared flow can provide data from 0 to n number of subscriber. 

## Reference:
1. https://proandroiddev.com/mvi-architecture-with-kotlin-flows-and-channels-d36820b2028d
2. https://elizarov.medium.com/shared-flows-broadcast-channels-899b675e805c

![Screenshot_20220404-193846_MovieBrowser](https://user-images.githubusercontent.com/18403399/161601160-9160a597-4efe-4d3c-9d52-87f6c6d455a4.jpg)
![Screenshot_20220404-193902_MovieBrowser](https://user-images.githubusercontent.com/18403399/161601185-8a8e936c-ddf9-4f97-8e85-b15aae0f2aeb.jpg)

