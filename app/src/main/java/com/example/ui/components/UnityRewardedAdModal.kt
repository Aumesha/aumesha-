package com.example.ui.components

import androidx.compose.animation.core.animateFloatAsState
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
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material.icons.filled.PlayCircle
import androidx.compose.material.icons.filled.WorkspacePremium
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
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
import com.example.data.model.JobVideo
import com.example.data.model.UnityAdConfig
import com.example.ui.theme.BackgroundDeep
import com.example.ui.theme.CardBorder
import com.example.ui.theme.PrimaryAmber
import com.example.ui.theme.SecondaryCyan
import com.example.ui.theme.SurfaceCard
import com.example.ui.theme.SurfaceHighlight
import com.example.ui.theme.TertiaryEmerald
import com.example.ui.theme.TextPrimary
import com.example.ui.theme.TextSecondary
import kotlinx.coroutines.delay

@Composable
fun UnityRewardedAdModal(
    video: JobVideo,
    adConfig: UnityAdConfig,
    language: AppLanguage,
    onDismiss: () -> Unit,
    onAdCompleted: () -> Unit
) {
    var isPlayingRewardedAd by remember { mutableStateOf(false) }
    val totalDuration = maxOf(1, adConfig.rewardedAdDurationSeconds)
    var secondsLeft by remember(adConfig.rewardedAdDurationSeconds) { mutableIntStateOf(totalDuration) }
    var isRewarded by remember { mutableStateOf(false) }

    LaunchedEffect(isPlayingRewardedAd) {
        if (isPlayingRewardedAd) {
            while (secondsLeft > 0) {
                delay(1000)
                secondsLeft--
            }
            isRewarded = true
            delay(1000)
            onAdCompleted()
        }
    }

    Dialog(
        onDismissRequest = {
            if (!isPlayingRewardedAd || isRewarded) onDismiss()
        },
        properties = DialogProperties(usePlatformDefaultWidth = false)
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(BackgroundDeep.copy(alpha = 0.95f))
                .padding(20.dp)
                .testTag("unity_rewarded_ad_modal"),
            contentAlignment = Alignment.Center
        ) {
            if (!isPlayingRewardedAd) {
                // Initial prompt dialog as required:
                // "video click ಮಾಡಿದ ತಕ್ಷಣ Watch ad and unlock your video ಅಂತ ಒಂದು buttun ಬರಬೇಕು"
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clip(RoundedCornerShape(24.dp)),
                    colors = CardDefaults.cardColors(containerColor = SurfaceCard),
                    border = CardDefaults.outlinedCardBorder().copy(brush = Brush.verticalGradient(listOf(CardBorder, SecondaryCyan)))
                ) {
                    Column(
                        modifier = Modifier.padding(24.dp),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Box(
                                modifier = Modifier
                                    .background(Color(0xFF002B49), RoundedCornerShape(6.dp))
                                    .padding(horizontal = 8.dp, vertical = 3.dp)
                            ) {
                                Text(
                                    text = "Unity Rewarded Ad",
                                    fontSize = 11.sp,
                                    fontWeight = FontWeight.Bold,
                                    color = SecondaryCyan
                                )
                            }

                            IconButton(
                                onClick = onDismiss,
                                modifier = Modifier.size(28.dp)
                            ) {
                                Icon(
                                    imageVector = Icons.Default.Close,
                                    contentDescription = "Cancel",
                                    tint = TextSecondary
                                )
                            }
                        }

                        Spacer(modifier = Modifier.height(16.dp))

                        Box(
                            modifier = Modifier
                                .size(70.dp)
                                .clip(CircleShape)
                                .background(PrimaryAmber.copy(alpha = 0.15f)),
                            contentAlignment = Alignment.Center
                        ) {
                            Icon(
                                imageVector = Icons.Default.Lock,
                                contentDescription = "Locked Video",
                                tint = PrimaryAmber,
                                modifier = Modifier.size(36.dp)
                            )
                        }

                        Spacer(modifier = Modifier.height(16.dp))

                        Text(
                            text = if (language == AppLanguage.KANNADA) "ವೀಡಿಯೊ ಅನ್‌ಲಾಕ್ ಮಾಡಲು ಸಿದ್ಧರಾಗಿ" else "Unlock Job Preparation Video",
                            fontSize = 18.sp,
                            fontWeight = FontWeight.Bold,
                            color = TextPrimary,
                            textAlign = TextAlign.Center
                        )

                        Spacer(modifier = Modifier.height(8.dp))

                        Text(
                            text = video.getTitle(language),
                            fontSize = 13.sp,
                            color = SecondaryCyan,
                            fontWeight = FontWeight.SemiBold,
                            textAlign = TextAlign.Center,
                            maxLines = 2
                        )

                        Spacer(modifier = Modifier.height(12.dp))

                        Text(
                            text = if (language == AppLanguage.KANNADA)
                                "ಕೇವಲ 5 ಸೆಕೆಂಡುಗಳ ಉಚಿತ Unity ಜಾಹೀರಾತನ್ನು ವೀಕ್ಷಿಸುವ ಮೂಲಕ ನೀವು ಈ ವೀಡಿಯೊವನ್ನು ಸಂಪೂರ್ಣವಾಗಿ ಉಚಿತವಾಗಿ ವೀಕ್ಷಿಸಬಹುದು."
                            else
                                "Watch a quick 5-second Unity video ad to unlock and stream this career guidance video directly in the app.",
                            fontSize = 12.sp,
                            color = TextSecondary,
                            textAlign = TextAlign.Center
                        )

                        Spacer(modifier = Modifier.height(24.dp))

                        // Explicit button required by user:
                        // "Watch ad and unlock your video ಅಂತ ಒಂದು buttun ಬರಬೇಕು"
                        Button(
                            onClick = { isPlayingRewardedAd = true },
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(50.dp)
                                .testTag("watch_ad_and_unlock_video_button"),
                            colors = ButtonDefaults.buttonColors(containerColor = PrimaryAmber),
                            shape = RoundedCornerShape(12.dp)
                        ) {
                            Icon(
                                imageVector = Icons.Default.WorkspacePremium,
                                contentDescription = null,
                                tint = BackgroundDeep,
                                modifier = Modifier.size(20.dp)
                            )
                            Spacer(modifier = Modifier.width(8.dp))
                            Text(
                                text = if (language == AppLanguage.KANNADA)
                                    "Watch ad and unlock your video (ಜಾಹೀರಾತು ವೀಕ್ಷಿಸಿ)"
                                else
                                    "Watch ad and unlock your video",
                                fontWeight = FontWeight.ExtraBold,
                                color = BackgroundDeep,
                                fontSize = 13.sp
                            )
                        }

                        Spacer(modifier = Modifier.height(10.dp))

                        OutlinedButton(
                            onClick = onDismiss,
                            modifier = Modifier.fillMaxWidth(),
                            shape = RoundedCornerShape(12.dp)
                        ) {
                            Text(
                                text = if (language == AppLanguage.KANNADA) "ರದ್ದುಮಾಡಿ" else "Cancel",
                                color = TextSecondary,
                                fontSize = 12.sp
                            )
                        }
                    }
                }
            } else {
                // Active Rewarded Ad playing screen
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clip(RoundedCornerShape(24.dp)),
                    colors = CardDefaults.cardColors(containerColor = SurfaceCard),
                    border = CardDefaults.outlinedCardBorder().copy(brush = Brush.verticalGradient(listOf(SecondaryCyan, TertiaryEmerald)))
                ) {
                    Column(
                        modifier = Modifier.padding(24.dp),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Text(
                                text = "Unity Rewarded Video",
                                fontSize = 12.sp,
                                fontWeight = FontWeight.Bold,
                                color = SecondaryCyan
                            )
                            Box(
                                modifier = Modifier
                                    .clip(CircleShape)
                                    .background(SurfaceHighlight)
                                    .padding(horizontal = 8.dp, vertical = 4.dp)
                            ) {
                                Text(
                                    text = if (!isRewarded) "$secondsLeft s" else "DONE",
                                    fontSize = 12.sp,
                                    fontWeight = FontWeight.Bold,
                                    color = if (isRewarded) TertiaryEmerald else PrimaryAmber
                                )
                            }
                        }

                        Spacer(modifier = Modifier.height(20.dp))

                        Box(
                            modifier = Modifier
                                .size(80.dp)
                                .clip(CircleShape)
                                .background(
                                    if (isRewarded) TertiaryEmerald.copy(alpha = 0.2f) else PrimaryAmber.copy(alpha = 0.2f)
                                ),
                            contentAlignment = Alignment.Center
                        ) {
                            Icon(
                                imageVector = if (isRewarded) Icons.Default.CheckCircle else Icons.Default.PlayCircle,
                                contentDescription = null,
                                tint = if (isRewarded) TertiaryEmerald else PrimaryAmber,
                                modifier = Modifier.size(46.dp)
                            )
                        }

                        Spacer(modifier = Modifier.height(16.dp))

                        Text(
                            text = if (isRewarded) {
                                if (language == AppLanguage.KANNADA) "ಅನ್‌ಲಾಕ್ ಆಗಿದೆ! ವೀಡಿಯೊ ಪ್ಲೇ ಆಗುತ್ತಿದೆ..." else "Reward Granted! Video Unlocked..."
                            } else {
                                if (language == AppLanguage.KANNADA) "ಜಾಹೀರಾತು ಪ್ರಸಾರವಾಗುತ್ತಿದೆ..." else "Ad Playing - Please Wait..."
                            },
                            fontSize = 16.sp,
                            fontWeight = FontWeight.Bold,
                            color = TextPrimary
                        )

                        Spacer(modifier = Modifier.height(16.dp))

                        val progress = (totalDuration - secondsLeft).toFloat() / totalDuration
                        LinearProgressIndicator(
                            progress = { progress },
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(8.dp)
                                .clip(RoundedCornerShape(4.dp)),
                            color = if (isRewarded) TertiaryEmerald else PrimaryAmber,
                            trackColor = SurfaceHighlight
                        )

                        Spacer(modifier = Modifier.height(12.dp))

                        Text(
                            text = if (language == AppLanguage.KANNADA)
                                "ಜಾಹೀರಾತು ಸಂಪೂರ್ಣವಾದ ತಕ್ಷಣ ವೀಡಿಯೊ ನಿಮ್ಮ ಆ್ಯಪ್‌ನಲ್ಲೇ ಶುರುವಾಗುತ್ತದೆ."
                            else
                                "Reward will be credited immediately upon completion.",
                            fontSize = 11.sp,
                            color = TextSecondary,
                            textAlign = TextAlign.Center
                        )
                    }
                }
            }
        }
    }
}
