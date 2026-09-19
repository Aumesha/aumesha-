package com.example.ui.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.PlayCircleFilled
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.filled.Work
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import com.example.data.model.AppLanguage
import com.example.data.model.UnityAdConfig
import com.example.ui.theme.BackgroundDeep
import com.example.ui.theme.CardBorder
import com.example.ui.theme.PrimaryAmber
import com.example.ui.theme.SecondaryCyan
import com.example.ui.theme.SurfaceCard
import com.example.ui.theme.SurfaceHighlight
import com.example.ui.theme.TextPrimary
import com.example.ui.theme.TextSecondary
import kotlinx.coroutines.delay

@Composable
fun UnityInterstitialAdModal(
    adConfig: UnityAdConfig,
    language: AppLanguage,
    onDismiss: () -> Unit
) {
    // User requirement: "2 ನೇ ಮೆನು ಓಪನ್ ಮಾಡಿದಾಗ ಆದ ಸ್ಕಿಪ್ ಆಪ್ಶನ್ ಸುಮಾರು 10 ಸೆಕೋಣದಶ ಆದಮೇಲೆ ಬರಲಿ"
    var countdown by remember { mutableIntStateOf(10) }
    var canSkip by remember { mutableStateOf(false) }

    LaunchedEffect(Unit) {
        while (countdown > 0) {
            delay(1000)
            countdown--
        }
        canSkip = true
    }

    Dialog(
        onDismissRequest = {
            if (canSkip) onDismiss()
        },
        properties = DialogProperties(usePlatformDefaultWidth = false)
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(BackgroundDeep)
                .testTag("unity_interstitial_ad_modal")
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(24.dp),
                verticalArrangement = Arrangement.SpaceBetween
            ) {
                // Top header: Unity Ads Brand & Skip Counter
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Box(
                            modifier = Modifier
                                .background(Color(0xFF002B49), RoundedCornerShape(6.dp))
                                .border(1.dp, SecondaryCyan, RoundedCornerShape(6.dp))
                                .padding(horizontal = 8.dp, vertical = 4.dp)
                        ) {
                            Text(
                                text = "Unity Ads Interstitial",
                                fontSize = 11.sp,
                                fontWeight = FontWeight.Bold,
                                color = SecondaryCyan
                            )
                        }
                        Spacer(modifier = Modifier.width(8.dp))
                        Text(
                            text = if (adConfig.isTestMode)
                                "Test Unit: ${adConfig.interstitialPlacementId}"
                            else
                                "Live ID: ${adConfig.gameId}",
                            fontSize = 11.sp,
                            color = if (adConfig.isTestMode) PrimaryAmber else Color(0xFF10B981)
                        )
                    }

                    if (!canSkip) {
                        Box(
                            modifier = Modifier
                                .clip(CircleShape)
                                .background(SurfaceHighlight)
                                .border(1.dp, CardBorder, CircleShape)
                                .padding(horizontal = 10.dp, vertical = 6.dp)
                        ) {
                            Text(
                                text = "$countdown s",
                                fontSize = 12.sp,
                                fontWeight = FontWeight.Bold,
                                color = TextPrimary
                            )
                        }
                    } else {
                        IconButton(
                            onClick = onDismiss,
                            modifier = Modifier
                                .clip(CircleShape)
                                .background(PrimaryAmber)
                                .size(36.dp)
                                .testTag("interstitial_ad_close_button")
                        ) {
                            Icon(
                                imageVector = Icons.Default.Close,
                                contentDescription = "Close Ad",
                                tint = BackgroundDeep,
                                modifier = Modifier.size(20.dp)
                            )
                        }
                    }
                }

                // Middle: High-Impact Video/Card Ad Content
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .weight(1f)
                        .padding(vertical = 20.dp),
                    shape = RoundedCornerShape(20.dp),
                    colors = CardDefaults.cardColors(containerColor = SurfaceCard),
                    border = CardDefaults.outlinedCardBorder().copy(brush = Brush.verticalGradient(listOf(SecondaryCyan, PrimaryAmber)))
                ) {
                    Column(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(24.dp),
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Center
                    ) {
                        Box(
                            modifier = Modifier
                                .size(90.dp)
                                .clip(CircleShape)
                                .background(
                                    Brush.radialGradient(
                                        colors = listOf(PrimaryAmber, Color(0xFFFF7A00))
                                    )
                                ),
                            contentAlignment = Alignment.Center
                        ) {
                            Icon(
                                imageVector = Icons.Default.Work,
                                contentDescription = "Opportunity",
                                tint = BackgroundDeep,
                                modifier = Modifier.size(48.dp)
                            )
                        }

                        Spacer(modifier = Modifier.height(20.dp))

                        Row(verticalAlignment = Alignment.CenterVertically) {
                            repeat(5) {
                                Icon(
                                    imageVector = Icons.Default.Star,
                                    contentDescription = null,
                                    tint = PrimaryAmber,
                                    modifier = Modifier.size(16.dp)
                                )
                            }
                            Spacer(modifier = Modifier.width(6.dp))
                            Text(
                                text = "Sponsored",
                                fontSize = 12.sp,
                                color = TextSecondary,
                                fontWeight = FontWeight.SemiBold
                            )
                        }

                        Spacer(modifier = Modifier.height(12.dp))

                        Text(
                            text = if (language == AppLanguage.KANNADA)
                                "ಕರ್ನಾಟಕ ಖಾಸಗಿ ಐಟಿ & ಕಾರ್ಪೊರೇಟ್ ಉದ್ಯೋಗ ಸ್ಕಿಲ್ಲಿಂಗ್ ಆಪ್"
                            else
                                "Karnataka Premier Private IT & Corporate Career App",
                            fontSize = 18.sp,
                            fontWeight = FontWeight.Bold,
                            color = TextPrimary,
                            textAlign = TextAlign.Center
                        )

                        Spacer(modifier = Modifier.height(10.dp))

                        Text(
                            text = if (language == AppLanguage.KANNADA)
                                "ಕೇವಲ 30 ದಿನಗಳಲ್ಲಿ ಉಚಿತವಾಗಿ ಕೋಡಿಂಗ್, ಇಂಟರ್ವ್ಯೂ ಸ್ಕಿಲ್ಸ್ ಕಲಿಯಿರಿ. 500+ ಖಾಸಗಿ ಕಂಪನಿಗಳಲ್ಲಿ ನೇರ ಇಂಟರ್ವ್ಯೂ ಅವಕಾಶ."
                            else
                                "Master Python, Java & Soft Skills in 30 days. Guaranteed interview calls at leading private tech enterprises.",
                            fontSize = 13.sp,
                            color = TextSecondary,
                            textAlign = TextAlign.Center
                        )
                    }
                }

                // Bottom Action Button
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    Button(
                        onClick = {
                            if (canSkip) onDismiss()
                        },
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(52.dp),
                        shape = RoundedCornerShape(12.dp),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = if (canSkip) PrimaryAmber else SurfaceHighlight
                        )
                    ) {
                        Text(
                            text = if (canSkip) {
                                if (language == AppLanguage.KANNADA) "ಜಾಹೀರಾತು ಮುಕ್ತಾಯ - ವೀಡಿಯೊಗಳಿಗೆ ಮುಂದುವರಿಯಿರಿ" else "Continue to Videos"
                            } else {
                                if (language == AppLanguage.KANNADA) "ಜಾಹೀರಾತು ಪೂರ್ಣಗೊಳ್ಳುವವರೆಗೆ ಕಾಯಿರಿ ($countdown s)" else "Please wait ($countdown s)"
                            },
                            fontWeight = FontWeight.Bold,
                            color = if (canSkip) BackgroundDeep else TextSecondary,
                            fontSize = 14.sp
                        )
                    }

                    Spacer(modifier = Modifier.height(8.dp))

                    Text(
                        text = if (language == AppLanguage.KANNADA)
                            "Unity Ad Network ಸುರಕ್ಷಿತ ಅಧಿಕೃತ ಜಾಹೀರಾತು"
                        else
                            "Verified Unity Ad Network Placement",
                        fontSize = 10.sp,
                        color = TextSecondary
                    )
                }
            }
        }
    }
}
