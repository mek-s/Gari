



sealed class Routes (val route: String) {
    object Home: Routes("HomeScreen")

    object MyResv : Routes ("MyResvScreen")
    object Map : Routes ("MapScreen")

    object Profile : Routes ("ProfileScreen")


    object ParkingDetails : Routes("ParkingDetails/{parkingId}") {
        fun createRoute(parkingId: Int?) = "ParkingDetails/$parkingId"
    }


}