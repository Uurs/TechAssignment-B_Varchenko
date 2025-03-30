package b.varchenko.paymentapp

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import b.varchenko.paymentapp.ui.screen.purchase.PurchaseScreen
import b.varchenko.paymentapp.ui.screen.reciept.ReceiptScreen
import kotlinx.serialization.Serializable

// TODO add deep links

@Serializable
data object PurchaseScreenDestination

@Serializable
data class ReceiptDestination(val id: String)

@Composable
fun PaymentAppNavHost() {
    val navController = rememberNavController()

    NavHost(
        navController,
        startDestination = PurchaseScreenDestination,
    ) {
        composable<PurchaseScreenDestination> {
            PurchaseScreen(
                navigateToReceiptScreen = { id ->
                    navController.navigate(ReceiptDestination(id))
                }
            )
        }

        composable<ReceiptDestination> {
            ReceiptScreen(
                navigateBack = {
                    navController.popBackStack()
                }
            )
        }
    }

}