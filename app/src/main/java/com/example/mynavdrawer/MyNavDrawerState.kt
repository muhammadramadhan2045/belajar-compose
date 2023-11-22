@file:OptIn(ExperimentalMaterial3Api::class)

package com.example.mynavdrawer

import android.content.Context
import android.widget.Toast
import androidx.compose.material3.DrawerState
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.SnackbarResult
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.platform.LocalContext
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

class MyNavDrawerState(
    val drawerState: DrawerState,
    private val scope: CoroutineScope,
    val snackbarHostState: SnackbarHostState,
    private val context: Context,
) {
    fun onMenuClick() {
        scope.launch {
            if (drawerState.isClosed) drawerState.open()
            else drawerState.close()
        }
    }

    fun onItemSelected(item: MenuItem) {
        scope.launch {
            drawerState.close()
            val snackBarResult = snackbarHostState.showSnackbar(
                message = context.resources.getString(
                    R.string.coming_soon,
                    item.title
                ),
                actionLabel = context.resources.getString(R.string.subscribe_question),
                withDismissAction = true,
                duration = SnackbarDuration.Short
            )
            if (snackBarResult == SnackbarResult.ActionPerformed) {
                Toast.makeText(
                    context,
                    context.resources.getString(R.string.subscribed_info),
                    Toast.LENGTH_SHORT
                ).show()
            }
        }
    }

    fun  onBackPress(){
        if(drawerState.isOpen){
            scope.launch {
                drawerState.close()
            }
        }
    }
}

@Composable
fun rememberMyNavDrawerState(
    drawerState: DrawerState= rememberDrawerState(initialValue =DrawerValue.Closed),
    coroutinesScope: CoroutineScope= rememberCoroutineScope(),
    snackbarHostState: SnackbarHostState=remember{SnackbarHostState()},
    context: Context= LocalContext.current,
) :MyNavDrawerState=
    remember(drawerState,coroutinesScope,snackbarHostState,context){
        MyNavDrawerState(drawerState,coroutinesScope,snackbarHostState,context)
    }