



sealed class Routes (val route: String) {
    object Home: Routes("HomeScreen")

    object MyResv : Routes ("MyResvScreen")
    object Map : Routes ("MapScreen")

    object Profile : Routes ("ProfileScreen")

    object Login : Routes ("LoginScreen")

    object SignUp : Routes ("SignUpScreen")

    object Reserv: Routes ("ReservationScreen/{parkingId}")
    {
        fun createRoute (parkingId: Int?) = "ReservationScreen/$parkingId"
    }


    object ParkingDetails : Routes("ParkingDetails/{parkingId}") {
        fun createRoute(parkingId: Int?) = "ParkingDetails/$parkingId"
    }



}