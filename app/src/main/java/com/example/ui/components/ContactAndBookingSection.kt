package com.example.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccessTime
import androidx.compose.material.icons.filled.CalendarMonth
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Directions
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Phone
import androidx.compose.material.icons.filled.Send
import androidx.compose.material.icons.filled.Verified
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.model.Barber
import com.example.model.BarbershopData
import com.example.model.BookingState
import com.example.model.ServiceItem
import com.example.ui.theme.DarkBackground
import com.example.ui.theme.DarkBorderEmerald
import com.example.ui.theme.DarkBorderHairline
import com.example.ui.theme.DarkSurface
import com.example.ui.theme.DarkSurfaceCard
import com.example.ui.theme.DarkSurfaceElevated
import com.example.ui.theme.DeepPurpleLight
import com.example.ui.theme.EmeraldLight
import com.example.ui.theme.EmeraldPrimary
import com.example.ui.theme.GoldAccent
import com.example.ui.theme.TextMuted
import com.example.ui.theme.TextPrimary
import com.example.ui.theme.TextSecondary

@Composable
fun ContactAndBookingSection(
    bookingState: BookingState,
    onBookingStateChange: (BookingState) -> Unit,
    onSubmitBooking: (BookingState) -> Unit,
    onOpenMaps: () -> Unit,
    onCallPhone: () -> Unit,
    onOpenWhatsApp: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .background(DarkBackground)
            .padding(horizontal = 20.dp, vertical = 28.dp)
    ) {
        // Section Header
        Box(
            modifier = Modifier
                .clip(RoundedCornerShape(50))
                .background(EmeraldPrimary.copy(alpha = 0.12f))
                .border(1.dp, DarkBorderEmerald, RoundedCornerShape(50))
                .padding(horizontal = 12.dp, vertical = 5.dp)
        ) {
            Text(
                text = "LOCALIZAÇÃO & AGENDAMENTO",
                style = MaterialTheme.typography.labelMedium.copy(
                    fontWeight = FontWeight.Bold,
                    letterSpacing = 1.2.sp
                ),
                color = EmeraldLight
            )
        }

        Spacer(modifier = Modifier.height(10.dp))

        Text(
            text = "Reserve Seu Horário",
            style = MaterialTheme.typography.displayMedium.copy(
                fontFamily = FontFamily.Serif,
                fontWeight = FontWeight.Bold
            ),
            color = TextPrimary
        )

        Spacer(modifier = Modifier.height(6.dp))

        Text(
            text = "Garanta sua cadeira com antecedência. Atendimento pontual com ritual completo de toalha quente e finalização profissional.",
            style = MaterialTheme.typography.bodyLarge,
            color = TextSecondary
        )

        Spacer(modifier = Modifier.height(22.dp))

        // Interactive Booking Form Box
        BookingFormCard(
            bookingState = bookingState,
            onBookingStateChange = onBookingStateChange,
            onSubmitBooking = onSubmitBooking
        )

        Spacer(modifier = Modifier.height(32.dp))

        // Direct Contact Info Cards
        Text(
            text = "CANAL DIRETO",
            style = MaterialTheme.typography.labelMedium.copy(
                fontSize = 11.sp,
                fontWeight = FontWeight.Bold,
                letterSpacing = 1.5.sp
            ),
            color = EmeraldLight
        )

        Spacer(modifier = Modifier.height(12.dp))

        Column(
            verticalArrangement = Arrangement.spacedBy(14.dp)
        ) {
            // Address Card
            ContactDetailCard(
                icon = Icons.Default.LocationOn,
                iconColor = EmeraldLight,
                title = "Endereço",
                value = BarbershopData.ADDRESS,
                actionLabel = "Como Chegar (Mapa)",
                onAction = onOpenMaps,
                actionTag = "open_maps_button"
            )

            // Phone Card
            ContactDetailCard(
                icon = Icons.Default.Phone,
                iconColor = DeepPurpleLight,
                title = "Telefone & Atendimento",
                value = "${BarbershopData.FORMATTED_PHONE}\n(42) 99825-8424",
                actionLabel = "Ligar Agora",
                onAction = onCallPhone,
                actionTag = "call_phone_button"
            )

            // Working Hours Card
            ContactDetailCard(
                icon = Icons.Default.AccessTime,
                iconColor = GoldAccent,
                title = "Horário de Funcionamento",
                value = "${BarbershopData.WORKING_HOURS}\nDomingo e Segunda: Fechado para descanso e afiação",
                actionLabel = "Falar no WhatsApp",
                onAction = onOpenWhatsApp,
                actionTag = "contact_whatsapp_button"
            )
        }

        Spacer(modifier = Modifier.height(30.dp))

        // Footer Section
        BarbershopFooter()
    }
}

@Composable
fun BookingFormCard(
    bookingState: BookingState,
    onBookingStateChange: (BookingState) -> Unit,
    onSubmitBooking: (BookingState) -> Unit
) {
    var nameError by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(18.dp))
            .background(DarkSurfaceCard)
            .border(1.dp, DarkBorderEmerald, RoundedCornerShape(18.dp))
            .padding(20.dp)
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box(
                modifier = Modifier
                    .size(32.dp)
                    .clip(RoundedCornerShape(8.dp))
                    .background(EmeraldPrimary.copy(alpha = 0.15f)),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    imageVector = Icons.Default.CalendarMonth,
                    contentDescription = null,
                    tint = EmeraldPrimary,
                    modifier = Modifier.size(18.dp)
                )
            }
            Spacer(modifier = Modifier.width(10.dp))
            Column {
                Text(
                    text = "Agendamento Online",
                    style = MaterialTheme.typography.titleLarge.copy(
                        fontFamily = FontFamily.Serif,
                        fontWeight = FontWeight.Bold
                    ),
                    color = TextPrimary
                )
                Text(
                    text = "Sem filas, confirmação direta",
                    style = MaterialTheme.typography.bodyMedium.copy(fontSize = 12.sp),
                    color = TextSecondary
                )
            }
        }

        Spacer(modifier = Modifier.height(18.dp))

        // Step 1: Escolha o Serviço
        Text(
            text = "1. SELECIONE O SERVIÇO",
            style = MaterialTheme.typography.labelMedium.copy(
                fontSize = 11.sp,
                fontWeight = FontWeight.Bold,
                letterSpacing = 1.sp
            ),
            color = EmeraldLight
        )

        Spacer(modifier = Modifier.height(8.dp))

        Column(
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            BarbershopData.services.forEach { service ->
                val isSelected = bookingState.selectedServiceId == service.id
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clip(RoundedCornerShape(10.dp))
                        .background(
                            if (isSelected) EmeraldPrimary.copy(alpha = 0.15f)
                            else DarkSurfaceElevated
                        )
                        .border(
                            width = 1.dp,
                            color = if (isSelected) EmeraldPrimary else DarkBorderHairline,
                            shape = RoundedCornerShape(10.dp)
                        )
                        .clickable {
                            onBookingStateChange(bookingState.copy(selectedServiceId = service.id))
                        }
                        .padding(horizontal = 14.dp, vertical = 10.dp),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Box(
                            modifier = Modifier
                                .size(20.dp)
                                .clip(CircleShape)
                                .background(
                                    if (isSelected) EmeraldPrimary else DarkSurface
                                )
                                .border(
                                    1.dp,
                                    if (isSelected) EmeraldPrimary else DarkBorderHairline,
                                    CircleShape
                                ),
                            contentAlignment = Alignment.Center
                        ) {
                            if (isSelected) {
                                Icon(
                                    imageVector = Icons.Default.Check,
                                    contentDescription = null,
                                    tint = Color(0xFF022C22),
                                    modifier = Modifier.size(13.dp)
                                )
                            }
                        }

                        Spacer(modifier = Modifier.width(10.dp))

                        Column {
                            Text(
                                text = service.title,
                                style = MaterialTheme.typography.titleMedium.copy(
                                    fontSize = 14.sp,
                                    fontWeight = FontWeight.SemiBold
                                ),
                                color = TextPrimary
                            )
                            Text(
                                text = service.duration,
                                style = MaterialTheme.typography.bodyMedium.copy(fontSize = 11.sp),
                                color = TextSecondary
                            )
                        }
                    }

                    Text(
                        text = service.price,
                        style = MaterialTheme.typography.titleMedium.copy(
                            fontWeight = FontWeight.Bold
                        ),
                        color = EmeraldPrimary
                    )
                }
            }
        }

        Spacer(modifier = Modifier.height(18.dp))

        // Step 2: Barbeiro de Preferência
        Text(
            text = "2. BARBEIRO ESPECIALISTA",
            style = MaterialTheme.typography.labelMedium.copy(
                fontSize = 11.sp,
                fontWeight = FontWeight.Bold,
                letterSpacing = 1.sp
            ),
            color = EmeraldLight
        )

        Spacer(modifier = Modifier.height(8.dp))

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .horizontalScroll(rememberScrollState()),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            BarbershopData.barbers.forEach { barber ->
                val isSelected = bookingState.selectedBarberId == barber.id
                Box(
                    modifier = Modifier
                        .clip(RoundedCornerShape(8.dp))
                        .background(
                            if (isSelected) DeepPurpleLight.copy(alpha = 0.2f)
                            else DarkSurfaceElevated
                        )
                        .border(
                            1.dp,
                            if (isSelected) DeepPurpleLight else DarkBorderHairline,
                            RoundedCornerShape(8.dp)
                        )
                        .clickable {
                            onBookingStateChange(bookingState.copy(selectedBarberId = barber.id))
                        }
                        .padding(horizontal = 12.dp, vertical = 8.dp)
                ) {
                    Column {
                        Text(
                            text = barber.name,
                            style = MaterialTheme.typography.labelMedium.copy(
                                fontWeight = if (isSelected) FontWeight.Bold else FontWeight.Normal
                            ),
                            color = if (isSelected) Color.White else TextPrimary
                        )
                        Text(
                            text = barber.specialty,
                            style = MaterialTheme.typography.bodyMedium.copy(fontSize = 10.sp),
                            color = TextSecondary
                        )
                    }
                }
            }
        }

        Spacer(modifier = Modifier.height(18.dp))

        // Step 3: Dia e Horário
        Text(
            text = "3. DIA & HORÁRIO PREFERIDO",
            style = MaterialTheme.typography.labelMedium.copy(
                fontSize = 11.sp,
                fontWeight = FontWeight.Bold,
                letterSpacing = 1.sp
            ),
            color = EmeraldLight
        )

        Spacer(modifier = Modifier.height(8.dp))

        // Dates Row
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .horizontalScroll(rememberScrollState()),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            BarbershopData.availableDates.forEach { date ->
                val isSelected = bookingState.selectedDate == date
                Box(
                    modifier = Modifier
                        .clip(RoundedCornerShape(8.dp))
                        .background(
                            if (isSelected) EmeraldPrimary.copy(alpha = 0.2f)
                            else DarkSurfaceElevated
                        )
                        .border(
                            1.dp,
                            if (isSelected) EmeraldPrimary else DarkBorderHairline,
                            RoundedCornerShape(8.dp)
                        )
                        .clickable {
                            onBookingStateChange(bookingState.copy(selectedDate = date))
                        }
                        .padding(horizontal = 14.dp, vertical = 8.dp)
                ) {
                    Text(
                        text = date,
                        style = MaterialTheme.typography.labelMedium.copy(
                            fontWeight = if (isSelected) FontWeight.Bold else FontWeight.Normal,
                            color = if (isSelected) EmeraldLight else TextSecondary
                        )
                    )
                }
            }
        }

        Spacer(modifier = Modifier.height(10.dp))

        // Times Row
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .horizontalScroll(rememberScrollState()),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            BarbershopData.availableTimes.forEach { time ->
                val isSelected = bookingState.selectedTime == time
                Box(
                    modifier = Modifier
                        .clip(RoundedCornerShape(8.dp))
                        .background(
                            if (isSelected) EmeraldPrimary
                            else DarkSurfaceElevated
                        )
                        .border(
                            1.dp,
                            if (isSelected) EmeraldPrimary else DarkBorderHairline,
                            RoundedCornerShape(8.dp)
                        )
                        .clickable {
                            onBookingStateChange(bookingState.copy(selectedTime = time))
                        }
                        .padding(horizontal = 12.dp, vertical = 6.dp)
                ) {
                    Text(
                        text = time,
                        style = MaterialTheme.typography.labelMedium.copy(
                            fontWeight = FontWeight.Bold,
                            color = if (isSelected) Color(0xFF022C22) else TextPrimary
                        )
                    )
                }
            }
        }

        Spacer(modifier = Modifier.height(20.dp))

        // Step 4: Dados do Cliente
        Text(
            text = "4. SEUS DADOS",
            style = MaterialTheme.typography.labelMedium.copy(
                fontSize = 11.sp,
                fontWeight = FontWeight.Bold,
                letterSpacing = 1.sp
            ),
            color = EmeraldLight
        )

        Spacer(modifier = Modifier.height(8.dp))

        OutlinedTextField(
            value = bookingState.customerName,
            onValueChange = {
                nameError = false
                onBookingStateChange(bookingState.copy(customerName = it))
            },
            label = { Text("Seu Nome Completo") },
            placeholder = { Text("Ex: Roberto Antunes") },
            leadingIcon = {
                Icon(
                    imageVector = Icons.Default.Person,
                    contentDescription = null,
                    tint = EmeraldLight
                )
            },
            singleLine = true,
            isError = nameError,
            colors = OutlinedTextFieldDefaults.colors(
                focusedBorderColor = EmeraldPrimary,
                unfocusedBorderColor = DarkBorderHairline,
                focusedLabelColor = EmeraldLight,
                unfocusedLabelColor = TextSecondary,
                focusedTextColor = TextPrimary,
                unfocusedTextColor = TextPrimary,
                focusedContainerColor = DarkSurfaceElevated,
                unfocusedContainerColor = DarkSurfaceElevated
            ),
            modifier = Modifier
                .fillMaxWidth()
                .testTag("booking_name_input")
        )

        if (nameError) {
            Text(
                text = "Por favor, informe seu nome para o agendamento.",
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.error,
                modifier = Modifier.padding(start = 4.dp, top = 4.dp)
            )
        }

        Spacer(modifier = Modifier.height(10.dp))

        OutlinedTextField(
            value = bookingState.customerPhone,
            onValueChange = {
                onBookingStateChange(bookingState.copy(customerPhone = it))
            },
            label = { Text("WhatsApp para Confirmação") },
            placeholder = { Text("(42) 9____-____") },
            leadingIcon = {
                Icon(
                    imageVector = Icons.Default.Phone,
                    contentDescription = null,
                    tint = EmeraldLight
                )
            },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Phone),
            singleLine = true,
            colors = OutlinedTextFieldDefaults.colors(
                focusedBorderColor = EmeraldPrimary,
                unfocusedBorderColor = DarkBorderHairline,
                focusedLabelColor = EmeraldLight,
                unfocusedLabelColor = TextSecondary,
                focusedTextColor = TextPrimary,
                unfocusedTextColor = TextPrimary,
                focusedContainerColor = DarkSurfaceElevated,
                unfocusedContainerColor = DarkSurfaceElevated
            ),
            modifier = Modifier
                .fillMaxWidth()
                .testTag("booking_phone_input")
        )

        Spacer(modifier = Modifier.height(20.dp))

        // Confirm Button
        Button(
            onClick = {
                if (bookingState.customerName.isBlank()) {
                    nameError = true
                } else {
                    onSubmitBooking(bookingState)
                }
            },
            colors = ButtonDefaults.buttonColors(
                containerColor = EmeraldPrimary,
                contentColor = Color(0xFF022C22)
            ),
            shape = RoundedCornerShape(12.dp),
            modifier = Modifier
                .fillMaxWidth()
                .height(52.dp)
                .testTag("confirm_booking_button")
        ) {
            Icon(
                imageVector = Icons.Default.Send,
                contentDescription = null,
                modifier = Modifier.size(18.dp)
            )
            Spacer(modifier = Modifier.width(8.dp))
            Text(
                text = "Entrar em Contato & Confirmar",
                style = MaterialTheme.typography.labelLarge.copy(
                    fontSize = 14.sp,
                    fontWeight = FontWeight.ExtraBold
                )
            )
        }
    }
}

@Composable
fun ContactDetailCard(
    icon: ImageVector,
    iconColor: Color,
    title: String,
    value: String,
    actionLabel: String,
    onAction: () -> Unit,
    actionTag: String
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(14.dp))
            .background(DarkSurfaceCard)
            .border(1.dp, DarkBorderHairline, RoundedCornerShape(14.dp))
            .padding(16.dp)
    ) {
        Column {
            Row(
                verticalAlignment = Alignment.Top
            ) {
                Box(
                    modifier = Modifier
                        .size(38.dp)
                        .clip(RoundedCornerShape(8.dp))
                        .background(iconColor.copy(alpha = 0.15f)),
                    contentAlignment = Alignment.Center
                ) {
                    Icon(
                        imageVector = icon,
                        contentDescription = null,
                        tint = iconColor,
                        modifier = Modifier.size(20.dp)
                    )
                }

                Spacer(modifier = Modifier.width(12.dp))

                Column(modifier = Modifier.weight(1f)) {
                    Text(
                        text = title,
                        style = MaterialTheme.typography.labelMedium.copy(
                            fontSize = 11.sp,
                            fontWeight = FontWeight.Bold,
                            letterSpacing = 0.8.sp
                        ),
                        color = iconColor
                    )
                    Spacer(modifier = Modifier.height(3.dp))
                    Text(
                        text = value,
                        style = MaterialTheme.typography.bodyMedium.copy(
                            lineHeight = 20.sp,
                            fontWeight = FontWeight.Medium
                        ),
                        color = TextPrimary
                    )
                }
            }

            Spacer(modifier = Modifier.height(12.dp))

            Button(
                onClick = onAction,
                colors = ButtonDefaults.buttonColors(
                    containerColor = DarkSurfaceElevated,
                    contentColor = TextPrimary
                ),
                shape = RoundedCornerShape(8.dp),
                modifier = Modifier
                    .fillMaxWidth()
                    .height(38.dp)
                    .border(1.dp, DarkBorderHairline, RoundedCornerShape(8.dp))
                    .testTag(actionTag)
            ) {
                Text(
                    text = actionLabel,
                    style = MaterialTheme.typography.labelMedium.copy(
                        fontWeight = FontWeight.Bold
                    )
                )
            }
        }
    }
}

@Composable
fun BarbershopFooter() {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 10.dp, bottom = 40.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(1.dp)
                .background(DarkBorderHairline)
        )

        Spacer(modifier = Modifier.height(20.dp))

        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "BARBEARIA NAVALHA",
                style = MaterialTheme.typography.labelLarge.copy(
                    fontFamily = FontFamily.Serif,
                    fontWeight = FontWeight.Bold,
                    letterSpacing = 2.sp
                ),
                color = TextPrimary
            )
            Spacer(modifier = Modifier.width(6.dp))
            Box(
                modifier = Modifier
                    .size(6.dp)
                    .clip(CircleShape)
                    .background(EmeraldPrimary)
            )
        }

        Spacer(modifier = Modifier.height(6.dp))

        Text(
            text = "Estilo & Tradição • Rio Azul - PR",
            style = MaterialTheme.typography.bodyMedium.copy(fontSize = 12.sp),
            color = TextSecondary
        )

        Spacer(modifier = Modifier.height(4.dp))

        Text(
            text = "© 2019-2026 Barbearia Navalha. Todos os direitos reservados.",
            style = MaterialTheme.typography.bodySmall.copy(fontSize = 10.sp),
            color = TextMuted
        )
    }
}
