
sealed class Routes (val route: String) {

    object Home: Routes("HomeScreen")

    object Map : Routes ("MapScreen")

    object Profile : Routes ("ProfileScreen")

    object Login : Routes ("LoginScreen")

    object SignUp : Routes ("SignUpScreen")

    object Notification : Routes ("NotificationScreen")

    object Security : Routes ("SecurityScreen")

    object InfoProfile : Routes ("InfoProfileScreen")


    //to create a new reservation
    object Reserv: Routes ("ReservationScreen/{parkingId}/{idPlace}")
    {
        fun createRoute (parkingId: Int, idPlace: Int) = "ReservationScreen/$parkingId/$idPlace"
    }


    object ParkingDetails : Routes("ParkingDetails/{parkingId}") {
        fun createRoute(parkingId: Int?) = "ParkingDetails/$parkingId"
    }

    object SelectPlace : Routes("SelectPlace/{parkingId}"){
        fun createRoute(parkingId: Int?) = "SelectPlace/$parkingId"
    }

    object MyReserv : Routes("MyReserv/{username}") {
        fun createRoute(username: String?, parkingId: Int?) = "MyReserv/$username"
    }


    object ReservationDetails : Routes("ReservationDetails/{reservationId}") {
        fun createRoute(reservationId: Int?, parkingId: Int?) = "ReservationDetails/$reservationId"
    }



}