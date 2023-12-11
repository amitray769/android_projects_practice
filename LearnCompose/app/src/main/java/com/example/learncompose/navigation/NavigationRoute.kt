package com.example.learncompose.navigation

import com.example.learncompose.navigation.Constants.Companion.COMPONENT_ROUTE
import com.example.learncompose.navigation.Constants.Companion.HOME_ROUTE
import com.example.learncompose.navigation.Constants.Companion.TEXT_FIELD_ROUTE

sealed class NavigationRoute(val route: String){

   object HomeRoute : NavigationRoute(HOME_ROUTE) {
       object HomeL1Route : NavigationRoute("$HOME_ROUTE/homel1")
   }

    object ComponentRoute : NavigationRoute(COMPONENT_ROUTE){
       object TextFieldRoute : NavigationRoute("$COMPONENT_ROUTE/$TEXT_FIELD_ROUTE")
   }


}