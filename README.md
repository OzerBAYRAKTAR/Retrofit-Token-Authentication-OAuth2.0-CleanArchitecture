
# Retrofit-Token-Authentication-OAuth2.0-CleanArchitecture 📱 

## Authenticate to OAuth2 Services 🔍

- In order to securely access an online service, users need to authenticate to the service they need to provide proof of their identity. For an application that accesses a third-party service, the security problem is even more complicated. 
Not only does the user need to be authenticated to access the service, but the application also needs to be authorized to act on the user's behalf.
The industry standard way to deal with authentication to third-party services is the OAuth2 protocol. OAuth2 provides a single value, called an auth token, that represents both the user's identity and the application's authorization to act on the user's behalf. 
This lesson demonstrates connecting to a Google server that supports OAuth2. Although Google services are used as an example, the techniques demonstrated will work on any service that correctly supports the OAuth2 protocol.

Using OAuth2 is good for:

1- Getting permission from the user to access an online service using their account.

2- Authenticating to an online service on behalf of the user.

3-Handling authentication errors.  

### OAuth Refresh Tokens 🔍

An OAuth Refresh Token is a string that the OAuth client can use to get a new access token without the user's interaction.
A refresh token must not allow the client to gain any access beyond the scope of the original grant. 
The refresh token exists to enable authorization servers to use short lifetimes for access tokens without needing to involve the user when the token expires.


# Built With 🛠

* [Clean Architecture](https://blog.cleancoder.com/uncle-bob/2012/08/13/the-clean-architecture.html):  Clean architecture is a software design philosophy that separates the elements of a design into ring levels. An important goal of clean architecture is to provide developers with a way to organize code in such a way that it encapsulates the business logic but keeps it separate from the delivery mechanism. 

        1-) Domain: contains the definitions of the business logic of the app, the server data model, the abstract definition of repositories, and the definition of the use cases. It’s a simple, pure kotlin module (android independent)

        2-) Data: contains the implementation of the abstract definitions of the domain layer. Can be reused by any application without modifications. It contains repositories and data sources implementations, the database definition and its DAOs, the network APIs definitions, some mappers to convert network API models to database models, and vice versa.

        3-) Presentation: it’s android specific and contains fragments, view models, adapters, activities, and so on. It also contains a service locator to manage dependencies, but you can use Hilt if you prefer.


* [MVVM(Model-View-ViewModel)](https://www.digitalocean.com/community/tutorials/android-mvvm-design-pattern) : Software design pattern that is structured to separate program logic and user interface control

* [Retrofit](https://square.github.io/retrofit/) : Retrofit is a type-safe REST client for Android, Java and Kotlin developed by Square. The library provides a powerful framework for authenticating and interacting with APIs and sending network requests with OkHttp.

* [Room DB](https://developer.android.com/training/data-storage/room) : The Room persistence library provides an abstraction layer over SQLite to allow fluent database access while harnessing the full power of SQLite

* [Shared Preferences](https://developer.android.com/training/data-storage/shared-preferences) : A SharedPreferences object points to a file containing key-value pairs and provides simple methods to read and write them. Each SharedPreferences file is managed by the framework and can be private or shared.

* [Dependency injection with Hilt](https://developer.android.com/training/dependency-injection/hilt-android) : Hilt provides a standard way to use DI in your application by providing containers for every Android class in your project and managing their lifecycles automatically. Hilt is built on top of the popular DI library Dagger to benefit from the compile-time correctness, runtime performance, scalability, and Android Studio support that Dagger provides.

* [Coroutines](https://developer.android.com/kotlin/coroutines?hl=en) : A coroutine is a concurrency design pattern that you can use on Android to simplify code that executes asynchronously.

* [LiveData](https://developer.android.com/topic/libraries/architecture/livedata) : LiveData is an observable data holder class. Unlike a regular observable, LiveData is lifecycle-aware, meaning it respects the lifecycle of other app components, such as activities, fragments, or services. 

* [Kotlin Flow](https://developer.android.com/kotlin/flow?hl=en) : In coroutines, a flow is a type that can emit multiple values sequentially, as opposed to suspend functions that return only a single value. 

* [ViewBinding](https://developer.android.com/topic/libraries/view-binding) : View binding is a feature that makes it easier to write code that interacts with views. Once view binding is enabled in a module, it generates a binding class for each XML layout file present in that module. 

* [Retrofit Pagination](https://medium.com/nerd-for-tech/pagination-in-android-with-paging-3-retrofit-and-kotlin-flow-2c2454ff776e) : With the paging library, you can load and display small chunks of data at a time. Loading partial data on demand reduces the usage of network bandwidth and system resources. 

* [OKHttp](https://www.baeldung.com/guide-to-okhttp) : OkHttp is an efficient HTTP & HTTP/2 client for Android and Java applications.

         It comes with advanced features, such as connection pooling (if HTTP/2 isn’t available), transparent GZIP compression, and response caching, to avoid the network completely for repeated requests.

         It's also able to recover from common connection problems; on a connection failure, if a service has multiple IP addresses, it can retry the request to alternate addresses.




