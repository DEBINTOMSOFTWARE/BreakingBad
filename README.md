# ChallengeGan
The App consists of two screens with below specifications The first screen displays a list of of Breaking Bad characters from the Breaking Bad API (https://breakingbadapi.com/api/characters) and present 
the result in a scrolling GridView. Tapping a character from the list navigate to the second screen. The second screen will then load with Data: (Image, Name, Occupation, Status
,Nickname, Season appearance of selected character. 
The App runs successfully and works on below scenario's. As a user running the application I can select character from the list So that I can view detailed description of that character
Scenario 1: Viewing the character list When I launch the app Then I see a list of characters with images and names
Scenario 2: The user selects a character to transition to screen 2
Senario 3 :The user should be able to search for a character by name
Senario 4 : The user should be able to filter characters by season appearance

The App was developed in MVVM architecture with android jetpack components to make the project lifecycle aware by following the clean code architecture and added the test 
cases for data layer, interactors layer, framworklayer and presentation layer. And also added ViewModel testing, fragment , navigation testing.

Technologies Used for ChallengeGan

Kotlin (Clean code architecture),
MVVM architecture,
ConstrainLayout,
ViewModel data binding
Navigation Graph
Koin Dependency Injection
jUnit, mock, espresso for unit testing
RxJava with executor and scheduler
Retrofit
Glide for loading images
