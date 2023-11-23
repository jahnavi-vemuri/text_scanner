package com.example.text_scanner.routing

sealed class Screen(val route: String) {
    object DisplayStartingPage : Screen("start_page")
    object DisplayText : Screen("text_page")
}