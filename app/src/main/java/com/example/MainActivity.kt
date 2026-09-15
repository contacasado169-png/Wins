package com.example

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.model.BarbershopData
import com.example.model.BookingState
import com.example.ui.components.AboutAndStatsSection
import com.example.ui.components.BookingConfirmationDialog
import com.example.ui.components.ContactAndBookingSection
import com.example.ui.components.FloatingWhatsAppButton
import com.example.ui.components.HeroSection
import com.example.ui.components.NavalhaTopBar
import com.example.ui.components.ServicesSection
import com.example.ui.theme.DarkBackground
import com.example.ui.theme.MyApplicationTheme
import kotlinx.coroutines.launch
import java.net.URLEncoder

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MyApplicationTheme {
                BarbeariaNavalhaApp()
            }
        }
    }
}

@Composable
fun BarbeariaNavalhaApp() {
    val context = LocalContext.current
    val coroutineScope = rememberCoroutineScope()
    val listState = rememberLazyListState()

    var bookingState by remember {
        mutableStateOf(
            BookingState(
                selectedServiceId = "corte",
                selectedBarberId = "qualquer",
                selectedDate = "Hoje",
                selectedTime = "15:00"
            )
        )
    }

    var showConfirmationDialog by remember { mutableStateOf(false) }

    // Derive the currently visible section index (0: Início, 1: Serviços, 2: Sobre/Galeria, 3: Contato)
    val activeSectionIndex by remember {
        derivedStateOf {
            listState.firstVisibleItemIndex.coerceIn(0, 3)
        }
    }

    fun scrollToSection(index: Int) {
        coroutineScope.launch {
            listState.animateScrollToItem(index)
        }
    }

    fun openWhatsApp(customText: String? = null) {
        val message = customText ?: "Olá Barbearia Navalha! Gostaria de informações e agendar um horário."
        try {
            val encodedMessage = URLEncoder.encode(message, "UTF-8")
            val uri = Uri.parse("https://wa.me/55${BarbershopData.PHONE_NUMBER}?text=$encodedMessage")
            val intent = Intent(Intent.ACTION_VIEW, uri)
            context.startActivity(intent)
        } catch (e: Exception) {
            Toast.makeText(context, "Telefone: ${BarbershopData.FORMATTED_PHONE}", Toast.LENGTH_LONG).show()
        }
    }

    fun callPhone() {
        try {
            val intent = Intent(Intent.ACTION_DIAL, Uri.parse("tel:${BarbershopData.PHONE_NUMBER}"))
            context.startActivity(intent)
        } catch (e: Exception) {
            Toast.makeText(context, "Telefone: ${BarbershopData.FORMATTED_PHONE}", Toast.LENGTH_LONG).show()
        }
    }

    fun openMaps() {
        try {
            val encodedAddress = Uri.encode(BarbershopData.MAP_QUERY)
            val uri = Uri.parse("geo:0,0?q=$encodedAddress")
            val intent = Intent(Intent.ACTION_VIEW, uri)
            context.startActivity(intent)
        } catch (e: Exception) {
            Toast.makeText(context, BarbershopData.ADDRESS, Toast.LENGTH_LONG).show()
        }
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(DarkBackground)
    ) {
        Scaffold(
            topBar = {
                NavalhaTopBar(
                    activeSectionIndex = activeSectionIndex,
                    onNavigateToSection = { scrollToSection(it) },
                    onQuickBookingClick = { scrollToSection(3) }
                )
            },
            containerColor = DarkBackground,
            modifier = Modifier.fillMaxSize()
        ) { innerPadding ->
            LazyColumn(
                state = listState,
                modifier = Modifier
                    .fillMaxSize()
                    .padding(innerPadding)
                    .testTag("barbearia_scroll_column")
            ) {
                // Section 0: Início / Hero
                item {
                    HeroSection(
                        onBookingClick = { scrollToSection(3) },
                        onServicesClick = { scrollToSection(1) }
                    )
                }

                // Section 1: Serviços
                item {
                    ServicesSection(
                        onSelectServiceForBooking = { service ->
                            bookingState = bookingState.copy(selectedServiceId = service.id)
                            scrollToSection(3)
                        }
                    )
                }

                // Section 2: Galeria & Sobre Nós
                item {
                    AboutAndStatsSection()
                }

                // Section 3: Contato & Agendamento
                item {
                    ContactAndBookingSection(
                        bookingState = bookingState,
                        onBookingStateChange = { bookingState = it },
                        onSubmitBooking = { completedState ->
                            bookingState = completedState
                            showConfirmationDialog = true
                        },
                        onOpenMaps = { openMaps() },
                        onCallPhone = { callPhone() },
                        onOpenWhatsApp = { openWhatsApp() }
                    )
                }
            }
        }

        // Floating WhatsApp Button (bottom right with pulse)
        FloatingWhatsAppButton(
            onClick = { openWhatsApp() },
            modifier = Modifier
                .align(Alignment.BottomEnd)
                .navigationBarsPadding()
                .padding(end = 18.dp, bottom = 20.dp)
        )

        // Confirmation Modal Dialog
        if (showConfirmationDialog) {
            val service = BarbershopData.services.find { it.id == bookingState.selectedServiceId }
                ?: BarbershopData.services.first()
            val barber = BarbershopData.barbers.find { it.id == bookingState.selectedBarberId }
                ?: BarbershopData.barbers.first()

            val confirmationMessage = """
                *Agendamento - Barbearia Navalha*
                • Cliente: ${bookingState.customerName}
                • WhatsApp: ${bookingState.customerPhone.ifBlank { "Informado pelo cliente" }}
                • Serviço: ${service.title} (${service.price})
                • Barbeiro: ${barber.name}
                • Data: ${bookingState.selectedDate} às ${bookingState.selectedTime}
                • Local: ${BarbershopData.ADDRESS}
                
                Gostaria de confirmar esse horário!
            """.trimIndent()

            BookingConfirmationDialog(
                bookingState = bookingState,
                onDismiss = { showConfirmationDialog = false },
                onSendToWhatsApp = {
                    showConfirmationDialog = false
                    openWhatsApp(confirmationMessage)
                }
            )
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(text = "Barbearia Navalha: $name", modifier = modifier)
}

@Preview(showBackground = true)
@Composable
fun BarbeariaNavalhaPreview() {
    MyApplicationTheme {
        BarbeariaNavalhaApp()
    }
}
