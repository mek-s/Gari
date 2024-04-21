



sealed class Routes (val route: String) {
    object Home: Routes("HomeScreen")
    object Favorite : Routes ("FavoriteScreen")
    object MyResv : Routes ("MyResvScreen")
    object Map : Routes ("MapScreen")

    object Profile : Routes ("ProfileScreen")



}