package com.example.model

import androidx.annotation.DrawableRes
import com.example.R

data class ServiceItem(
    val id: String,
    val title: String,
    val price: String,
    val rawPrice: Int,
    val duration: String,
    val tag: String,
    val description: String,
    val benefits: List<String>,
    @DrawableRes val imageRes: Int
)

data class Barber(
    val id: String,
    val name: String,
    val specialty: String,
    val rating: String,
    val experience: String
)

data class StatItem(
    val number: String,
    val label: String,
    val sublabel: String
)

data class GalleryItem(
    val id: String,
    val title: String,
    val category: String,
    @DrawableRes val imageRes: Int
)

data class BookingState(
    val customerName: String = "",
    val customerPhone: String = "",
    val selectedServiceId: String = "corte",
    val selectedBarberId: String = "qualquer",
    val selectedDate: String = "Hoje",
    val selectedTime: String = "15:00",
    val notes: String = "",
    val isConfirmed: Boolean = false
)

object BarbershopData {
    const val PHONE_NUMBER = "42998258424"
    const val FORMATTED_PHONE = "(42) 99825-8424"
    const val ADDRESS = "R. 15 de Novembro, 356 - Centro, Rio Azul - PR, 84560-000 (para baixo do posto Ipiranga)"
    const val MAP_QUERY = "R. 15 de Novembro, 356, Rio Azul - PR, 84560-000"
    const val WORKING_HOURS = "Terça a Sábado: 08:30 às 19:30"

    val services = listOf(
        ServiceItem(
            id = "corte",
            title = "Corte Masculino",
            price = "R$ 35",
            rawPrice = 35,
            duration = "45 min",
            tag = "MAIS PROCURADO",
            description = "Consultoria visagista, degradê navalhado de precisão cirúrgica, alinhamento simétrico e finalização com pomada matte premium.",
            benefits = listOf("Visagismo personalizado", "Degradê navalhado", "Finalização com pomada importada"),
            imageRes = R.drawable.img_corte_masculino
        ),
        ServiceItem(
            id = "barba",
            title = "Barba & Terapia",
            price = "R$ 25",
            rawPrice = 25,
            duration = "35 min",
            tag = "RITUAL CLÁSSICO",
            description = "Barboterapia autêntica com toalha quente aromatizada, óleos emolientes, lâmina afiada em ângulo perfeito e bálsamo refrescante.",
            benefits = listOf("Toalha quente com essência de eucalipto", "Navalha descartável de precisão", "Bálsamo anti-irritação"),
            imageRes = R.drawable.img_barba_terapia
        ),
        ServiceItem(
            id = "tratamento",
            title = "Tratamento Capilar",
            price = "R$ 80",
            rawPrice = 80,
            duration = "50 min",
            tag = "SPA EXCLUSIVO",
            description = "Higienização profunda do couro cabeludo, esfoliação desintoxicante, banho de vapor ozonizado e tônico antiqueda fortificante.",
            benefits = listOf("Esfoliação e peeling capilar", "Vapor de ozônio revigorante", "Massagem craniana estimulante"),
            imageRes = R.drawable.img_tratamento_capilar
        )
    )

    val barbers = listOf(
        Barber("qualquer", "Qualquer Barbeiro Disponível", "Primeiro horário livre", "5.0", "Equipe Navalha"),
        Barber("carlos", "Mestre Carlos Navalha", "Especialista em Degradê & Visagismo", "4.9", "8 anos exp."),
        Barber("rafael", "Rafael Silva", "Mestre em Barboterapia Clássica", "4.9", "6 anos exp."),
        Barber("andre", "André Navalha", "Tratamentos & Cortes Freestyle", "5.0", "5 anos exp.")
    )

    val stats = listOf(
        StatItem("5+", "Anos de Tradição", "Excelência comprovada"),
        StatItem("12k+", "Clientes Atendidos", "Satisfação inegociável"),
        StatItem("5", "Barbeiros Mestres", "Técnica internacional"),
        StatItem("4.9★", "Avaliação Média", "Mais de 850 avaliações")
    )

    val availableDates = listOf("Hoje", "Amanhã", "Quarta", "Quinta", "Sexta", "Sábado")
    val availableTimes = listOf("09:00", "10:00", "11:15", "14:00", "15:00", "16:30", "17:45", "18:30")
}
