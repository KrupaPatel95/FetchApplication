**Overview**
The Fetch Hiring App is an Android application built using Kotlin, Jetpack Compose and Hilt for dependency injection. 
The app retrieves data from a remote API, processes it, 
and displays it in a structured format using Jetpack Compose UI Screen. 
It uses coroutines to handle background operations and displays a list of items, grouped by listId, and sorted by name.

The app demonstrates modern Android development practices such as:
-Kotlin is programming language
-Jetpack Compose for UI rendering
-Hilt for dependency injection
-Coroutines for asynchronous operations
-StateFlow for managing UI state
-Unit testing with Mockito(JUNIT 4)

**Directory Structure**
root name :com.krupa.fetchapplication
**network: Contains classes for making network requests and parsing API responses.**
ApiService: Interface to define API calls.
FetchHiringRepository: Repository class to handle data fetching.
HiringResponse: Data class representing the response structure.
**viewModel: Contains the ViewModel responsible for managing UI-related data and logic.**
HiringListViewModel: ViewModel class that fetches and processes the data.
**screen: Composables for displaying the UI.**
HiringListScreen: Composable that shows the list of items.
ItemRow: Composable to display each individual item.
**Entry Point: Start Application**
MainActivity: Activity is entry point which has Jetpack screen

**App ScreenShot: **
<img width="410" alt="image" src="https://github.com/user-attachments/assets/10e5f7a0-ed0b-4c2f-88fe-50875b534a1b" />
