
# 🗞 PokeDex
**PokeDex** is simple app that fetches pokemon from a json Api 🗞 Android application built to describe the use of Modern Android development tools.  *Made with love ❤️ by [Hash](https://github.com/HenryUdorji)*

## Built With 🛠
- [Kotlin](https://kotlinlang.org/) - First class and official programming language for Android development.
- [Coroutines](https://kotlinlang.org/docs/reference/coroutines-overview.html) - For asynchronous and more..
- [Android Architecture Components](https://developer.android.com/topic/libraries/architecture) - Collection of libraries that help you design robust, testable, and maintainable apps.
- [Flow](https://kotlinlang.org/docs/reference/coroutines/flow.html) - A flow is an asynchronous version of a Sequence, a type of collection whose values are lazily produced.
- [LiveData](https://developer.android.com/topic/libraries/architecture/livedata) - Data objects that notify views when the underlying database changes.
- [ViewModel](https://developer.android.com/topic/libraries/architecture/viewmodel) - Stores UI-related data that isn't destroyed on UI changes. 
- [Room](https://developer.android.com/topic/libraries/architecture/room) - SQLite object mapping library.
- [Android Architecture Components](https://developer.android.com/topic/libraries/architecture) - Collection of libraries that help you design robust, testable, and maintainable apps.
    - [LiveData](https://developer.android.com/topic/libraries/architecture/livedata) - Data objects that notify views when the underlying database changes.
    - [ViewModel](https://developer.android.com/topic/libraries/architecture/viewmodel) - Stores UI-related data that isn't destroyed on UI changes.
    - [ViewBinding](https://developer.android.com/topic/libraries/view-binding) - Generates a binding class for each XML layout file present in that module and allows you to more easily write code that interacts with views.
    - [Room](https://developer.android.com/topic/libraries/architecture/room) - SQLite object mapping library.
- [DaggerHilt](https://dagger.dev/) - Dependency Injection Framework
- [Retrofit](https://square.github.io/retrofit/) - A type-safe HTTP client for Android and Java.  
- [Jetpack Navigation](https://developer.android.com/guide/navigation) - Navigation refers to the interactions that allow users to navigate across, into, and back out from the different pieces of content within your app
- [Jetpack DataStore](https://developer.android.com/topic/libraries/architecture/datastore) - Jetpack DataStore is a data storage solution that allows you to store key-value pairs or typed objects with protocol buffers. DataStore uses Kotlin coroutines and Flow to store data asynchronously, consistently, and transactional  
- [Material Components for Android](https://github.com/material-components/material-components-android) - Modular and customizable Material Design UI components for Android.


## Project Structure 📂
    
    # Root Package
    .
    |
    ├── app                 # For initializing DaggerHilt
    |
    ├── data                # For data handling.
    |   ├── local
    |   |   |   ├── datastore     # Datbase Instance
    |   |   |   ├── db            # Local Persistence Database. Room (SQLite) database
    |   |   
    |   ├── model           # Model classes
    |   |
    |   ├── remote
    |   |
    |   ├── repository
    |
    | 
    ├── di                  # For dependency injection
    |
    |
    ├── ui                  # Activity/View layer
    │   ├── |── base        # Base Activity
    |   │   ├── adapter     # Adapter for RecyclerView
    |   │   └── viewmodel   # Viewmodels for Articles
    |   |   └── fragment    # Viewmodels for Articles
    |
    |
    |── utils               





## Architecture 🗼

This project follows the famous MVVM architecture and best practices from Google's [GithubBrowserSample](https://github.com/android/architecture-components-samples/tree/master/GithubBrowserSample)

![](extras/arch.png)
    

## Features
- Offline first

## Contribute
If you want to contribute to this library, you're always welcome!

## Contact
Have a project? DM me at 👇

Drop a mail to:- henryudorjig@gmail.com

## TODO 🗒️

- [ ] Improve algorithms and code review
- [ ] Add test cases
- [ ] Add pagination
- [ ] Add Light and Dark mode
- [ ] Implement search


## License
```
MIT License

Copyright (c) 2021 Udorji Henry

Permission is hereby granted, free of charge, to any person obtaining a copy
of this software and associated documentation files (the "Software"), to deal
in the Software without restriction, including without limitation the rights
to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
copies of the Software, and to permit persons to whom the Software is
furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all
copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
SOFTWARE.
```
