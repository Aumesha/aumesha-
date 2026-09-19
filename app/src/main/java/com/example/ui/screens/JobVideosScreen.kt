package com.example.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material.icons.filled.SmartDisplay
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.WorkspacePremium
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.example.data.model.AppLanguage
import com.example.data.model.JobVideo
import com.example.data.model.UnityAdConfig
import com.example.ui.components.UnityBannerAd
import com.example.ui.theme.BackgroundDeep
import com.example.ui.theme.CardBorder
import com.example.ui.theme.PrimaryAmber
import com.example.ui.theme.SecondaryCyan
import com.example.ui.theme.SurfaceCard
import com.example.ui.theme.SurfaceHighlight
import com.example.ui.theme.TertiaryEmerald
import com.example.ui.theme.TextPrimary
import com.example.ui.theme.TextSecondary

@Composable
fun JobVideosScreen(
    videos: List<JobVideo>,
    adConfig: UnityAdConfig,
    language: AppLanguage,
    onVideoClicked: (JobVideo) -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .background(BackgroundDeep)
            .padding(horizontal = 16.dp)
            .testTag("job_videos_screen")
    ) {
        Spacer(modifier = Modifier.height(10.dp))

        // Top Banner Ad as requested: "ಮತ್ತು ಮೇಲೆ ಮತ್ತು ಕೆಳಗಡೆ ಒಂದು banner ad"
        UnityBannerAd(
            adConfig = adConfig,
            language = language,
            index = 10,
            modifier = Modifier.fillMaxWidth().testTag("videos_top_banner_ad")
        )

        Spacer(modifier = Modifier.height(10.dp))

        // Info Banner about Rewarded Ad Unlock
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .background(SurfaceHighlight, RoundedCornerShape(10.dp))
                .padding(horizontal = 12.dp, vertical = 8.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box(
                modifier = Modifier
                    .size(28.dp)
                    .clip(CircleShape)
                    .background(PrimaryAmber.copy(alpha = 0.2f)),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    imageVector = Icons.Default.WorkspacePremium,
                    contentDescription = null,
                    tint = PrimaryAmber,
                    modifier = Modifier.size(16.dp)
                )
            }
            Spacer(modifier = Modifier.width(8.dp))
            Column {
                Text(
                    text = if (language == AppLanguage.KANNADA)
                        "ಪ್ರಸಿದ್ಧ 6 YouTube ಚಾನೆಲ್‌ಗಳ ಉದ್ಯೋಗ ವೀಡಿಯೊಗಳು"
                    else
                        "6 Top YouTube Career Channels - In-App Stream",
                    fontSize = 12.sp,
                    fontWeight = FontWeight.Bold,
                    color = TextPrimary
                )
                Text(
                    text = if (language == AppLanguage.KANNADA)
                        "ವೀಡಿಯೊ ಕ್ಲಿಕ್ ಮಾಡಿದಾಗ ಚಿಕ್ಕ ad ನೋಡಿ ಆ್ಯಪ್‌ನಲ್ಲೇ ಉಚಿತವಾಗಿ ವೀಕ್ಷಿಸಿ"
                    else
                        "Watch a quick rewarded ad to unlock and stream directly in FreeJobs",
                    fontSize = 10.sp,
                    color = TextSecondary
                )
            }
        }

        Spacer(modifier = Modifier.height(10.dp))

        // Videos Feed
        LazyColumn(
            modifier = Modifier.weight(1f),
            verticalArrangement = Arrangement.spacedBy(12.dp),
            contentPadding = PaddingValues(bottom = 16.dp)
        ) {
            items(videos, key = { it.id }) { video ->
                VideoCard(
                    video = video,
                    language = language,
                    onVideoClick = { onVideoClicked(video) }
                )
            }

            // Bottom Banner Ad as requested: "ಮತ್ತು ಮೇಲೆ ಮತ್ತು ಕೆಳಗಡೆ ಒಂದು banner ad"
            item {
                Spacer(modifier = Modifier.height(6.dp))
                UnityBannerAd(
                    adConfig = adConfig,
                    language = language,
                    index = 20,
                    modifier = Modifier.fillMaxWidth().testTag("videos_bottom_banner_ad")
                )
                Spacer(modifier = Modifier.height(6.dp))
            }
        }
    }
}

@Composable
fun VideoCard(
    video: JobVideo,
    language: AppLanguage,
    onVideoClick: () -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onVideoClick() }
            .testTag("video_card_${video.id}"),
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(containerColor = SurfaceCard),
        border = CardDefaults.outlinedCardBorder().copy(brush = androidx.compose.ui.graphics.SolidColor(CardBorder))
    ) {
        Column {
            // Thumbnail container with play button overlay and duration tag
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(160.dp)
                    .background(Color.Black)
            ) {
                AsyncImage(
                    model = video.thumbnailUrl,
                    contentDescription = video.getTitle(language),
                    contentScale = ContentScale.Crop,
                    modifier = Modifier.fillMaxSize()
                )

                // Dark gradient overlay
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(
                            Brush.verticalGradient(
                                colors = listOf(Color.Transparent, Color.Black.copy(alpha = 0.7f))
                            )
                        )
                )

                // Center Play / Unlock Button
                Box(
                    modifier = Modifier
                        .size(48.dp)
                        .clip(CircleShape)
                        .background(PrimaryAmber.copy(alpha = 0.9f))
                        .align(Alignment.Center),
                    contentAlignment = Alignment.Center
                ) {
                    Icon(
                        imageVector = Icons.Default.PlayArrow,
                        contentDescription = "Unlock & Play",
                        tint = BackgroundDeep,
                        modifier = Modifier.size(30.dp)
                    )
                }

                // Top Left Lock / Rewarded Badge
                Box(
                    modifier = Modifier
                        .padding(10.dp)
                        .align(Alignment.TopStart)
                        .background(Color.Black.copy(alpha = 0.75f), RoundedCornerShape(6.dp))
                        .padding(horizontal = 7.dp, vertical = 3.dp)
                ) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Icon(
                            imageVector = Icons.Default.Lock,
                            contentDescription = null,
                            tint = PrimaryAmber,
                            modifier = Modifier.size(11.dp)
                        )
                        Spacer(modifier = Modifier.width(4.dp))
                        Text(
                            text = if (language == AppLanguage.KANNADA) "Ad ಅನ್‌ಲಾಕ್" else "Rewarded Ad",
                            fontSize = 10.sp,
                            fontWeight = FontWeight.Bold,
                            color = PrimaryAmber
                        )
                    }
                }

                // Bottom Right Duration
                Box(
                    modifier = Modifier
                        .padding(10.dp)
                        .align(Alignment.BottomEnd)
                        .background(Color.Black.copy(alpha = 0.85f), RoundedCornerShape(4.dp))
                        .padding(horizontal = 6.dp, vertical = 2.dp)
                ) {
                    Text(
                        text = video.duration,
                        fontSize = 10.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.White
                    )
                }
            }

            // Video Details Section
            Column(modifier = Modifier.padding(14.dp)) {
                Text(
                    text = video.getTitle(language),
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Bold,
                    color = TextPrimary,
                    lineHeight = 19.sp,
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis
                )

                Spacer(modifier = Modifier.height(8.dp))

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Icon(
                            imageVector = Icons.Default.SmartDisplay,
                            contentDescription = null,
                            tint = SecondaryCyan,
                            modifier = Modifier.size(15.dp)
                        )
                        Spacer(modifier = Modifier.width(5.dp))
                        Text(
                            text = video.getChannel(language),
                            fontSize = 12.sp,
                            fontWeight = FontWeight.SemiBold,
                            color = SecondaryCyan
                        )
                    }

                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Icon(
                            imageVector = Icons.Default.Visibility,
                            contentDescription = null,
                            tint = TextSecondary,
                            modifier = Modifier.size(12.dp)
                        )
                        Spacer(modifier = Modifier.width(4.dp))
                        Text(
                            text = "${video.viewsCount} views",
                            fontSize = 11.sp,
                            color = TextSecondary
                        )
                    }
                }

                Spacer(modifier = Modifier.height(10.dp))

                // Watch Ad & Unlock button
                Button(
                    onClick = onVideoClick,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(38.dp),
                    shape = RoundedCornerShape(8.dp),
                    colors = ButtonDefaults.buttonColors(containerColor = PrimaryAmber)
                ) {
                    Icon(
                        imageVector = Icons.Default.PlayArrow,
                        contentDescription = null,
                        tint = BackgroundDeep,
                        modifier = Modifier.size(16.dp)
                    )
                    Spacer(modifier = Modifier.width(6.dp))
                    Text(
                        text = if (language == AppLanguage.KANNADA)
                            "Watch ad & unlock (ಪ್ಲೇ ಮಾಡಿ)"
                        else
                            "Watch ad and unlock your video",
                        fontSize = 12.sp,
                        fontWeight = FontWeight.Bold,
                        color = BackgroundDeep
                    )
                }
            }
        }
    }
}
