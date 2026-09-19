package com.example.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AdsClick
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.OpenInNew
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.data.model.AppLanguage
import com.example.data.model.UnityAdConfig
import com.example.ui.theme.BackgroundDeep
import com.example.ui.theme.BannerAdBg
import com.example.ui.theme.CardBorder
import com.example.ui.theme.PrimaryAmber
import com.example.ui.theme.SecondaryCyan
import com.example.ui.theme.TextPrimary
import com.example.ui.theme.TextSecondary

data class SponsoredAdContent(
    val titleKn: String,
    val titleEn: String,
    val descKn: String,
    val descEn: String,
    val advertiser: String,
    val buttonKn: String,
    val buttonEn: String
)

val AD_CREATIVES = listOf(
    SponsoredAdContent(
        titleKn = "ಖಾಸಗಿ ಕಂಪನಿ ಉದ್ಯೋಗ ಪರೀಕ್ಷೆಗೆ ಉಚಿತ ಆಪ್ಟಿಟ್ಯೂಡ್ & ಕೋಡಿಂಗ್ ತರಬೇತಿ",
        titleEn = "Free Aptitude & Coding Bootcamp for Private Tech Companies",
        descKn = "ಇನ್ಫೋಸಿಸ್ ಮತ್ತು ಟಿಸಿಎಸ್ ಇಂಟರ್ವ್ಯೂ ಕ್ರ್ಯಾಕ್ ಮಾಡಲು ಉಚಿತ ಕೋರ್ಸ್ ನೋಂದಣಿ",
        descEn = "Master corporate interview rounds with 100% scholarship seats.",
        advertiser = "SkillUp Karnataka Academy",
        buttonKn = "ನೋಂದಾಯಿಸಿ",
        buttonEn = "Enroll Now"
    ),
    SponsoredAdContent(
        titleKn = "ವೃತ್ತಿಪರ ರೆಸ್ಯೂಮೆ (CV) ಮೇಕರ್ - 5 ನಿಮಿಷದಲ್ಲಿ ರೆಡಿ ಮಾಡಿ",
        titleEn = "AI Resume Builder - Build ATS-Friendly CV in 5 Minutes",
        descKn = "ಖಾಸಗಿ ಕಂಪನಿಗಳಿಗೆ ಸಲ್ಲಿಸಲು ಉಚಿತ ಪ್ರೊಫೆಷನಲ್ ರೆಸ್ಯೂಮೆ ಡೌನ್‌ಲೋಡ್ ಮಾಡಿ",
        descEn = "Get 3x more interview calls with verified corporate formats.",
        advertiser = "CareerCraft Tech",
        buttonKn = "ಡೌನ್‌ಲೋಡ್",
        buttonEn = "Create CV"
    ),
    SponsoredAdContent(
        titleKn = "ಕರ್ನಾಟಕ ಮೆಗಾ ಪ್ರೈವೇಟ್ ಜಾಬ್ ಮೇಳ 2026 - 50+ ಕಂಪನಿಗಳು",
        titleEn = "Karnataka Mega Private Job Fair 2026 - 50+ Top Recruiters",
        descKn = "ಸ್ಥಳದಲ್ಲೇ ಆಫರ್ ಲೆಟರ್ ಪಡೆಯಿರಿ. ಯಾವುದೇ ನೋಂದಣಿ ಶುಲ್ಕವಿಲ್ಲ",
        descEn = "Direct walk-in with top IT, Banking, Auto & Logistics recruiters.",
        advertiser = "Karnataka Career Connect",
        buttonKn = "ಪಾಲ್ಗೊಳ್ಳಿ",
        buttonEn = "Join Fair"
    )
)

@Composable
fun UnityBannerAd(
    adConfig: UnityAdConfig,
    language: AppLanguage,
    index: Int = 0,
    modifier: Modifier = Modifier
) {
    val creative = remember(index) {
        AD_CREATIVES[index % AD_CREATIVES.size]
    }

    Box(
        modifier = modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(12.dp))
            .background(
                brush = Brush.horizontalGradient(
                    colors = listOf(
                        Color(0xFF132338),
                        BannerAdBg,
                        Color(0xFF1A2E47)
                    )
                )
            )
            .border(1.dp, CardBorder.copy(alpha = 0.8f), RoundedCornerShape(12.dp))
            .padding(10.dp)
            .testTag("unity_banner_ad_${index}")
    ) {
        Column {
            // Unity Ad Network header badge
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Box(
                        modifier = Modifier
                            .background(Color(0xFF002B49), RoundedCornerShape(4.dp))
                            .border(0.5.dp, SecondaryCyan.copy(alpha = 0.5f), RoundedCornerShape(4.dp))
                            .padding(horizontal = 5.dp, vertical = 2.dp)
                    ) {
                        Text(
                            text = "Unity Ads",
                            fontSize = 9.sp,
                            fontWeight = FontWeight.ExtraBold,
                            color = SecondaryCyan
                        )
                    }

                    Spacer(modifier = Modifier.width(6.dp))

                    Text(
                        text = if (adConfig.isTestMode)
                            "[Test Mode: ${adConfig.bannerPlacementId}]"
                        else
                            "[Live: ID ${adConfig.gameId}]",
                        fontSize = 9.sp,
                        color = if (adConfig.isTestMode) PrimaryAmber else Color(0xFF10B981),
                        fontWeight = FontWeight.Medium
                    )
                }

                Row(verticalAlignment = Alignment.CenterVertically) {
                    Text(
                        text = "Ad",
                        fontSize = 9.sp,
                        fontWeight = FontWeight.SemiBold,
                        color = TextSecondary
                    )
                    Spacer(modifier = Modifier.width(2.dp))
                    Icon(
                        imageVector = Icons.Default.Info,
                        contentDescription = "Ad Info",
                        tint = TextSecondary,
                        modifier = Modifier.size(11.dp)
                    )
                }
            }

            Spacer(modifier = Modifier.height(6.dp))

            // Ad Creative Body
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Row(
                    modifier = Modifier.weight(1f),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Box(
                        modifier = Modifier
                            .size(36.dp)
                            .clip(RoundedCornerShape(8.dp))
                            .background(PrimaryAmber.copy(alpha = 0.15f)),
                        contentAlignment = Alignment.Center
                    ) {
                        Icon(
                            imageVector = Icons.Default.AdsClick,
                            contentDescription = "Sponsored Ad",
                            tint = PrimaryAmber,
                            modifier = Modifier.size(20.dp)
                        )
                    }

                    Spacer(modifier = Modifier.width(10.dp))

                    Column {
                        Text(
                            text = if (language == AppLanguage.KANNADA) creative.titleKn else creative.titleEn,
                            fontSize = 12.sp,
                            fontWeight = FontWeight.Bold,
                            color = TextPrimary,
                            maxLines = 1,
                            overflow = TextOverflow.Ellipsis
                        )
                        Text(
                            text = if (language == AppLanguage.KANNADA) creative.descKn else creative.descEn,
                            fontSize = 10.sp,
                            color = TextSecondary,
                            maxLines = 1,
                            overflow = TextOverflow.Ellipsis
                        )
                    }
                }

                Spacer(modifier = Modifier.width(8.dp))

                // CTA Action Button
                Box(
                    modifier = Modifier
                        .clip(RoundedCornerShape(6.dp))
                        .background(PrimaryAmber)
                        .clickable { /* Simulates ad action */ }
                        .padding(horizontal = 10.dp, vertical = 6.dp)
                ) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Text(
                            text = if (language == AppLanguage.KANNADA) creative.buttonKn else creative.buttonEn,
                            fontSize = 11.sp,
                            fontWeight = FontWeight.Bold,
                            color = BackgroundDeep
                        )
                        Spacer(modifier = Modifier.width(3.dp))
                        Icon(
                            imageVector = Icons.Default.OpenInNew,
                            contentDescription = "Open Ad Link",
                            tint = BackgroundDeep,
                            modifier = Modifier.size(11.dp)
                        )
                    }
                }
            }
        }
    }
}
