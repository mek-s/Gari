//package com.example.tdm.ui.screens
//
//import android.Manifest
//import android.annotation.SuppressLint
//import android.content.pm.PackageManager
//import androidx.annotation.RequiresPermission
//import androidx.compose.animation.animateContentSize
//import androidx.compose.foundation.layout.Arrangement
//import androidx.compose.foundation.layout.Column
//import androidx.compose.foundation.layout.fillMaxWidth
//import androidx.compose.foundation.layout.padding
//import androidx.compose.material3.Button
//import androidx.compose.material3.Text
//import androidx.compose.runtime.*
//import androidx.compose.ui.Alignment
//import androidx.compose.ui.Modifier
//import androidx.compose.ui.platform.LocalContext
//import androidx.compose.ui.unit.dp
//import androidx.core.app.ActivityCompat
//import androidx.core.content.ContextCompat
//import androidx.lifecycle.LifecycleOwner
//import androidx.lifecycle.lifecycleScope
//import com.google.android.gms.location.LocationServices
//import com.google.android.gms.location.Priority
//import com.google.android.gms.tasks.CancellationTokenSource
//import kotlinx.coroutines.Dispatchers
//import kotlinx.coroutines.launch
//import kotlinx.coroutines.tasks.await
//
//@Composable
//fun PermissionBox(
//    permissions: List<String>,
//    requiredPermissions: List<String>,
//    onGranted: (List<String>) -> Unit,
//) {
//    val context = LocalContext.current
//    var hasPermission by remember {
//        mutableStateOf(
//            permissions.all { permission ->
//                ContextCompat.checkSelfPermission(context, permission) == PackageManager.PERMISSION_GRANTED
//            }
//        )
//    }
//
//    val grantedPermissions = remember {
//        mutableStateListOf<String>()
//    }
//
//    val lifecycleOwner = LocalContext.current as LifecycleOwner
//
//    LaunchedEffect(key1 = Unit) {
//        requiredPermissions.forEach { permission ->
//            if (ContextCompat.checkSelfPermission(context, permission) == PackageManager.PERMISSION_GRANTED) {
//                grantedPermissions.add(permission)
//            } else {
//                ActivityCompat.requestPermissions(
//                    context as android.app.Activity,
//                    arrayOf(permission),
//                    0
//                )
//            }
//        }
//        hasPermission = grantedPermissions.containsAll(requiredPermissions)
//    }
//
//    if (hasPermission) {
//        onGranted(grantedPermissions)
//    }
//}
//
//@SuppressLint("MissingPermission")
//@Composable
//fun CurrentLocationScreen() {
//    val permissions = listOf(
//        Manifest.permission.ACCESS_COARSE_LOCATION,
//        Manifest.permission.ACCESS_FINE_LOCATION,
//    )
//    PermissionBox(
//        permissions = permissions,
//        requiredPermissions = listOf(permissions.first()),
//        onGranted = {
//            CurrentLocationContent(
//                usePreciseLocation = it.contains(Manifest.permission.ACCESS_FINE_LOCATION),
//            )
//        },
//    )
//}
//
//@RequiresPermission(
//    anyOf = [Manifest.permission.ACCESS_COARSE_LOCATION, Manifest.permission.ACCESS_FINE_LOCATION],
//)
//@Composable
//fun CurrentLocationContent(usePreciseLocation: Boolean) {
//    val scope = rememberCoroutineScope()
//    val context = LocalContext.current
//    val locationClient = remember {
//        LocationServices.getFusedLocationProviderClient(context)
//    }
//    var locationInfo by remember {
//        mutableStateOf("")
//    }
//
//    Column(
//        Modifier
//            .fillMaxWidth()
//            .animateContentSize()
//            .padding(16.dp),
//        verticalArrangement = Arrangement.spacedBy(8.dp),
//        horizontalAlignment = Alignment.CenterHorizontally,
//    ) {
//        Button(
//            onClick = {
//                scope.launch(Dispatchers.IO) {
//                    try {
//                        val result = locationClient.lastLocation.await()
//                        locationInfo = if (result == null) {
//                            "No last known location. Try fetching the current location first"
//                        } else {
//                            "Current location is \n" + "lat : ${result.latitude}\n" +
//                                    "long : ${result.longitude}\n" + "fetched at ${System.currentTimeMillis()}"
//                        }
//                    } catch (e: Exception) {
//                        locationInfo = "Failed to get last known location: ${e.message}"
//                    }
//                }
//            },
//        ) {
//            Text("Get last known location")
//        }
//
//        Button(
//            onClick = {
//                scope.launch(Dispatchers.IO) {
//                    try {
//                        val priority = if (usePreciseLocation) {
//                            Priority.PRIORITY_HIGH_ACCURACY
//                        } else {
//                            Priority.PRIORITY_BALANCED_POWER_ACCURACY
//                        }
//                        val result = locationClient.getCurrentLocation(
//                            priority,
//                            CancellationTokenSource().token,
//                        ).await()
//                        result?.let { fetchedLocation ->
//                            locationInfo =
//                                "Current location is \n" + "lat : ${fetchedLocation.latitude}\n" +
//                                        "long : ${fetchedLocation.longitude}\n" + "fetched at ${System.currentTimeMillis()}"
//                        } ?: run {
//                            locationInfo = "Failed to get current location"
//                        }
//                    } catch (e: Exception) {
//                        locationInfo = "Failed to get current location: ${e.message}"
//                    }
//                }
//            },
//        ) {
//            Text(text = "Get current location")
//        }
//        Text(
//            text = locationInfo,
//        )
//    }
//}
