package com.example.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
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
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Diamond
import androidx.compose.material.icons.filled.Groups
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.filled.WorkspacePremium
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.R
import com.example.ui.theme.DarkBackground
import com.example.ui.theme.DarkBorderEmerald
import com.example.ui.theme.DarkBorderHairline
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
fun AboutAndStatsSection(
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .background(DarkBackground)
            .padding(horizontal = 20.dp, vertical = 24.dp)
    ) {
        // Section Badge
        Box(
            modifier = Modifier
                .clip(RoundedCornerShape(50))
                .background(DeepPurpleLight.copy(alpha = 0.15f))
                .border(1.dp, DeepPurpleLight.copy(alpha = 0.3f), RoundedCornerShape(50))
                .padding(horizontal = 12.dp, vertical = 5.dp)
        ) {
            Text(
                text = "TRADIÇÃO & EXCELÊNCIA",
                style = MaterialTheme.typography.labelMedium.copy(
                    fontWeight = FontWeight.Bold,
                    letterSpacing = 1.2.sp
                ),
                color = DeepPurpleLight
            )
        }

        Spacer(modifier = Modifier.height(10.dp))

        Text(
            text = "Sobre a Barbearia Navalha",
            style = MaterialTheme.typography.displayMedium.copy(
                fontFamily = FontFamily.Serif,
                fontWeight = FontWeight.Bold
            ),
            color = TextPrimary
        )

        Spacer(modifier = Modifier.height(12.dp))

        Text(
            text = "Nascida da paixão pela alta barbearia clássica, a Barbearia Navalha consolidou-se como a principal referência masculina em Rio Azul e região.",
            style = MaterialTheme.typography.bodyLarge.copy(
                fontWeight = FontWeight.Medium,
                color = TextPrimary
            )
        )

        Spacer(modifier = Modifier.height(8.dp))

        Text(
            text = "Aqui, cada cliente vivencia um santuário de respeito, pontualidade e refinamento. Aliamos a precisão das navalhas manuais à modernidade de técnicas visagistas avançadas, produtos de altíssimo padrão e um ambiente imersivo pensado para homens exigentes.",
            style = MaterialTheme.typography.bodyMedium.copy(
                lineHeight = 22.sp
            ),
            color = TextSecondary
        )

        Spacer(modifier = Modifier.height(24.dp))

        // 4 Monumental Stats Grid
        Text(
            text = "NÚMEROS DE AUTORIDADE",
            style = MaterialTheme.typography.labelMedium.copy(
                fontSize = 11.sp,
                fontWeight = FontWeight.Bold,
                letterSpacing = 1.5.sp
            ),
            color = EmeraldLight
        )

        Spacer(modifier = Modifier.height(12.dp))

        Column(
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                StatCard(
                    number = "5+",
                    label = "Anos de Tradição",
                    sublabel = "Excelência em Rio Azul",
                    icon = Icons.Default.WorkspacePremium,
                    accentColor = EmeraldPrimary,
                    modifier = Modifier.weight(1f)
                )
                StatCard(
                    number = "12k+",
                    label = "Clientes Atendidos",
                    sublabel = "Fidelidade comprovada",
                    icon = Icons.Default.Groups,
                    accentColor = DeepPurpleLight,
                    modifier = Modifier.weight(1f)
                )
            }

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                StatCard(
                    number = "5",
                    label = "Barbeiros Mestres",
                    sublabel = "Especialistas renomados",
                    icon = Icons.Default.Diamond,
                    accentColor = EmeraldLight,
                    modifier = Modifier.weight(1f)
                )
                StatCard(
                    number = "4.9★",
                    label = "Avaliação Média",
                    sublabel = "+850 avaliações 5 estrelas",
                    icon = Icons.Default.Star,
                    accentColor = GoldAccent,
                    modifier = Modifier.weight(1f)
                )
            }
        }

        Spacer(modifier = Modifier.height(36.dp))

        // Galeria Visual
        Box(
            modifier = Modifier
                .clip(RoundedCornerShape(50))
                .background(EmeraldPrimary.copy(alpha = 0.12f))
                .border(1.dp, DarkBorderEmerald, RoundedCornerShape(50))
                .padding(horizontal = 12.dp, vertical = 5.dp)
        ) {
            Text(
                text = "GALERIA EXCLUSIVA",
                style = MaterialTheme.typography.labelMedium.copy(
                    fontWeight = FontWeight.Bold,
                    letterSpacing = 1.2.sp
                ),
                color = EmeraldLight
            )
        }

        Spacer(modifier = Modifier.height(10.dp))

        Text(
            text = "Atmosfera & Detalhes",
            style = MaterialTheme.typography.displaySmall.copy(
                fontFamily = FontFamily.Serif,
                fontWeight = FontWeight.Bold
            ),
            color = TextPrimary
        )

        Spacer(modifier = Modifier.height(6.dp))

        Text(
            text = "Confira a precisão técnica e a atmosfera refinada da nossa cadeira.",
            style = MaterialTheme.typography.bodyMedium,
            color = TextSecondary
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Gallery Showcase
        Column(
            verticalArrangement = Arrangement.spacedBy(14.dp)
        ) {
            GalleryCard(
                title = "Poltronas Clássicas & Acabamento Dark",
                tag = "AMBIENTE",
                imageRes = R.drawable.img_hero_barbershop
            )

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                Box(modifier = Modifier.weight(1f)) {
                    GalleryCard(
                        title = "Degradê e Alinhamento",
                        tag = "PRECISÃO",
                        imageRes = R.drawable.img_corte_masculino,
                        compact = true
                    )
                }
                Box(modifier = Modifier.weight(1f)) {
                    GalleryCard(
                        title = "Toalha Quente e Lâmina",
                        tag = "RITUAL",
                        imageRes = R.drawable.img_barba_terapia,
                        compact = true
                    )
                }
            }
        }
    }
}

@Composable
fun StatCard(
    number: String,
    label: String,
    sublabel: String,
    icon: ImageVector,
    accentColor: Color,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .clip(RoundedCornerShape(14.dp))
            .background(DarkSurfaceCard)
            .border(1.dp, DarkBorderHairline, RoundedCornerShape(14.dp))
            .padding(16.dp)
    ) {
        Column {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Box(
                    modifier = Modifier
                        .size(36.dp)
                        .clip(RoundedCornerShape(8.dp))
                        .background(accentColor.copy(alpha = 0.15f)),
                    contentAlignment = Alignment.Center
                ) {
                    Icon(
                        imageVector = icon,
                        contentDescription = null,
                        tint = accentColor,
                        modifier = Modifier.size(20.dp)
                    )
                }
            }

            Spacer(modifier = Modifier.height(12.dp))

            Text(
                text = number,
                style = MaterialTheme.typography.displaySmall.copy(
                    fontWeight = FontWeight.Black,
                    letterSpacing = (-0.5).sp
                ),
                color = accentColor
            )

            Spacer(modifier = Modifier.height(4.dp))

            Text(
                text = label,
                style = MaterialTheme.typography.titleMedium.copy(
                    fontWeight = FontWeight.Bold,
                    fontSize = 13.sp
                ),
                color = TextPrimary
            )

            Text(
                text = sublabel,
                style = MaterialTheme.typography.bodyMedium.copy(
                    fontSize = 11.sp
                ),
                color = TextMuted
            )
        }
    }
}

@Composable
fun GalleryCard(
    title: String,
    tag: String,
    imageRes: Int,
    compact: Boolean = false
) {
    val height = if (compact) 140.dp else 200.dp

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(height)
            .clip(RoundedCornerShape(14.dp))
            .border(1.dp, DarkBorderHairline, RoundedCornerShape(14.dp))
    ) {
        Image(
            painter = painterResource(id = imageRes),
            contentDescription = title,
            modifier = Modifier.fillMaxWidth(),
            contentScale = ContentScale.Crop
        )

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(height)
                .background(
                    Brush.verticalGradient(
                        colors = listOf(
                            Color.Transparent,
                            DarkBackground.copy(alpha = 0.85f)
                        )
                    )
                )
        )

        Column(
            modifier = Modifier
                .align(Alignment.BottomStart)
                .padding(12.dp)
        ) {
            Box(
                modifier = Modifier
                    .clip(RoundedCornerShape(4.dp))
                    .background(DarkBackground.copy(alpha = 0.8f))
                    .border(1.dp, DarkBorderEmerald, RoundedCornerShape(4.dp))
                    .padding(horizontal = 6.dp, vertical = 2.dp)
            ) {
                Text(
                    text = tag,
                    style = MaterialTheme.typography.labelMedium.copy(
                        fontSize = 9.sp,
                        fontWeight = FontWeight.Bold
                    ),
                    color = EmeraldLight
                )
            }
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = title,
                style = MaterialTheme.typography.labelLarge.copy(
                    fontSize = if (compact) 11.sp else 13.sp,
                    fontWeight = FontWeight.Bold
                ),
                color = TextPrimary,
                maxLines = 1
            )
        }
    }
}
