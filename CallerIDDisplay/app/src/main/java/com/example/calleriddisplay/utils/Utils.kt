package com.example.calleriddisplay.utils

import android.content.ComponentName
import android.content.Context
import android.content.pm.PackageManager

/**
 * Changing the launcher icon to two different icons based on the toggle boolean
 * @param toggle true for launcher icon 1 and false launcher icon 2
 */
 fun toggleIcon(context: Context, toggle: Boolean) : Boolean{
    val manager = context.packageManager
    manager.setComponentEnabledSetting(
        ComponentName(
            context,
            "com.example.calleriddisplay.view.MainActivity"
        ),
        isIconEnabled(toggle),
        PackageManager.DONT_KILL_APP
    )

    manager.setComponentEnabledSetting(
        ComponentName(
           context,
            "com.example.calleriddisplay.dynamicIcon.MainActivityAlias"
        ),
        isIconEnabled(!toggle),
        PackageManager.DONT_KILL_APP
    )
    return !toggle
}


private fun isIconEnabled(isEnabled: Boolean) =
    if (isEnabled) PackageManager.COMPONENT_ENABLED_STATE_ENABLED else PackageManager.COMPONENT_ENABLED_STATE_DISABLED
