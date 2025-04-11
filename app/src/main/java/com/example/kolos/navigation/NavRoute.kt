package com.example.kolos.navigation

enum class NavRoute(val route: String) {
    MAIN("main"),
    FAVOURITE("favourite"),
    DETAILS("details"),
//    DETAILS("details/{coinDataJson}"),
    FAVOURITECOIN("favouriteCoin"),
//    FAVOURITECOIN("favouriteCoin/{id}"),
    SIGNUP("signUp"),
    SIGNIN("signIn"),
    ACCOUNT("account")
}