# Dublin Bikes Mobile App

- **Mobile Apps II Assignment 6**

## Project Description

This project is an Android application designed to provide information about Dublin bike stations.  
The app integrates Firebase Authentication for user management and Google Maps API to visualize bike station locations on an interactive map. 
It also uses data from a `dublinbike.json` file provided in the Full Stack / Back-End course. 
The project demonstrates the integration of external APIs and authentication systems in a mobile application, using modern Android technologies such as **Jetpack Compose** and **Kotlin Coroutines**.

### Key Features:

- **User Authentication**: Secure sign-up, login, and logout functionalities using Firebase Authentication.
- **Google Maps Integration**: Displays Dublin bike stations on a map, with station details such as address, available bikes, and available stands.
- **JSON Data Integration**: Utilizes data from a provided `dublinbike.json` file to populate the map with station information.
- **Dynamic Navigation**: Easy navigation between screens, including login, signup, map, and station details screens.
- **Modern UI Design**: Built using **Jetpack Compose** for a responsive and visually appealing user interface.

### Screens:

- **Login Screen**: Allows users to log in with their credentials.
- **Sign-Up Screen**: Enables new users to register securely, with password guidelines and validation.
- **Map Screen**: Displays an interactive map of Dublin with bike station locations marked.
- **Station Details Screen**: Shows detailed information about selected bike stations, such as address, status, and availability.
- **Logout Option**: Users can securely log out of their accounts.

### Technical Details:

- **Jetpack Compose** is used for building the UI with a declarative and dynamic design.
- **Firebase Authentication** is integrated for secure user management.
- **Google Maps API** enables geospatial visualization of bike station data.
- **Kotlin Coroutines** handle asynchronous tasks such as API calls and authentication processes.
- **JSON Data Parsing**: Reads and uses station data from the `dublinbike.json` file.

### References:

- [Firebase Console](https://console.firebase.google.com/)  
- [Google Maps API Codelabs](https://developers.google.com/codelabs/maps-platform/maps-platform-101-android#11)  
- [Firebase Authentication with Jetpack Compose](https://firebase.blog/posts/2022/05/adding-firebase-auth-to-jetpack-compose-app)  
- [Using Coroutines with Firebase](https://firebase.blog/posts/2022/10/using-coroutines-flows-with-firebase-on-android)  
- [Dublin Bikes Web App Project](https://filipelutz.github.io/dublin_bikes/index.html)

**Lecturer**: Eugene O'Regan  
**Back-End Lecturer**: John Rowley  
**Module**: Mobile App Development 2  

## Disclaimer

This project is for educational purposes only. 
The information and code presented in this report are intended to demonstrate the application of Jetpack Compose in Android development and are not intended for commercial use. 
While efforts have been made to ensure the accuracy and reliability of the content, there may be errors or omissions. 
I am not responsible for any consequences arising from the use of this project or its implementation in real-world applications. 
Users are encouraged to verify and adapt the code as necessary for their specific use cases.

***Filipe Lutz***
