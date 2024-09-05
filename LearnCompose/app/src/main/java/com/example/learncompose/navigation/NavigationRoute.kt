package com.example.learncompose.navigation

import com.example.learncompose.navigation.Constants.Companion.BOTTOM_NAVIGATION_ROUTE
import com.example.learncompose.navigation.Constants.Companion.COMPONENT_ROUTE
import com.example.learncompose.navigation.Constants.Companion.HOME_ROUTE
import com.example.learncompose.navigation.Constants.Companion.LAZY_COLUMN_ROUTE
import com.example.learncompose.navigation.Constants.Companion.TEXT_FIELD_ROUTE

sealed class NavigationRoute(val route: String) {

    data object HomeRoute : NavigationRoute(HOME_ROUTE) {
        data object HomeL1Route : NavigationRoute("$HOME_ROUTE/homel1")
    }

    data object ComponentRoute : NavigationRoute(COMPONENT_ROUTE) {
        data object TextFieldRoute : NavigationRoute("$COMPONENT_ROUTE/$TEXT_FIELD_ROUTE")
        data object BottomNavigationRoute :
            NavigationRoute("$COMPONENT_ROUTE/$BOTTOM_NAVIGATION_ROUTE")

        data object LazyColumnRoute : NavigationRoute("$COMPONENT_ROUTE/$LAZY_COLUMN_ROUTE")

    }


}