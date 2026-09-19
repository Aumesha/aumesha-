package com.example.ui.screens

import android.content.Intent
import android.net.Uri
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
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Business
import androidx.compose.material.icons.filled.CalendarToday
import androidx.compose.material.icons.filled.Category
import androidx.compose.material.icons.filled.Launch
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.MonetizationOn
import androidx.compose.material.icons.filled.OpenInNew
import androidx.compose.material.icons.filled.Share
import androidx.compose.material.icons.filled.Verified
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.data.model.AppLanguage
import com.example.data.model.JobPost
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
fun JobDetailScreen(
    job: JobPost,
    adConfig: UnityAdConfig,
    language: AppLanguage,
    onBack: () -> Unit,
    modifier: Modifier = Modifier
) {
    val context = LocalContext.current

    Surface(
        modifier = modifier
            .fillMaxSize()
            .background(BackgroundDeep)
            .testTag("job_detail_screen"),
        color = BackgroundDeep
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 16.dp, vertical = 10.dp)
        ) {
            // Navigation Bar
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 12.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    IconButton(
                        onClick = onBack,
                        modifier = Modifier
                            .size(40.dp)
                            .clip(CircleShape)
                            .background(SurfaceHighlight)
                            .border(1.dp, CardBorder, CircleShape)
                            .testTag("job_detail_back_button")
                    ) {
                        Icon(
                            imageVector = Icons.Default.ArrowBack,
                            contentDescription = "Back",
                            tint = TextPrimary
                        )
                    }

                    Spacer(modifier = Modifier.width(12.dp))

                    Text(
                        text = if (language == AppLanguage.KANNADA) "ಉದ್ಯೋಗ ವಿವರ" else "Career Notification Details",
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Bold,
                        color = TextPrimary
                    )
                }

                IconButton(
                    onClick = {
                        val shareIntent = Intent(Intent.ACTION_SEND).apply {
                            type = "text/plain"
                            putExtra(
                                Intent.EXTRA_TEXT,
                                "${job.getTitle(language)}\nCompany: ${job.company}\nApply: ${job.applyUrl}\nShared via FreeJobs App"
                            )
                        }
                        context.startActivity(Intent.createChooser(shareIntent, "Share Job"))
                    },
                    modifier = Modifier
                        .size(40.dp)
                        .clip(CircleShape)
                        .background(SurfaceHighlight)
                        .border(1.dp, CardBorder, CircleShape)
                ) {
                    Icon(
                        imageVector = Icons.Default.Share,
                        contentDescription = "Share",
                        tint = PrimaryAmber
                    )
                }
            }

            // Article Content with AT LEAST 3 Banner Ads placed seamlessly!
            // Requirement: "ನಂತರ ಅಲ್ಲಿ ಬಂದ ಮಾಹಿತಿಯಲ್ಲಿ banner ad ಗಳನ್ನು ಸೇರಿಸು ಕನಿಷ್ಠ ಅಂದ್ರೆ 3 ಇರಬೇಕು."
            Column(
                modifier = Modifier
                    .weight(1f)
                    .verticalScroll(rememberScrollState())
            ) {
                // Main Header Card
                Card(
                    modifier = Modifier.fillMaxWidth(),
                    colors = CardDefaults.cardColors(containerColor = SurfaceCard),
                    shape = RoundedCornerShape(16.dp),
                    border = CardDefaults.outlinedCardBorder().copy(brush = androidx.compose.ui.graphics.SolidColor(CardBorder))
                ) {
                    Column(modifier = Modifier.padding(16.dp)) {
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Box(
                                modifier = Modifier
                                    .background(PrimaryAmber.copy(alpha = 0.15f), RoundedCornerShape(6.dp))
                                    .padding(horizontal = 8.dp, vertical = 4.dp)
                            ) {
                                Text(
                                    text = job.getCategory(language),
                                    fontSize = 11.sp,
                                    fontWeight = FontWeight.Bold,
                                    color = PrimaryAmber
                                )
                            }

                            Row(verticalAlignment = Alignment.CenterVertically) {
                                Icon(
                                    imageVector = Icons.Default.CalendarToday,
                                    contentDescription = null,
                                    tint = TextSecondary,
                                    modifier = Modifier.size(12.dp)
                                )
                                Spacer(modifier = Modifier.width(4.dp))
                                Text(
                                    text = job.getFormattedAge(language),
                                    fontSize = 11.sp,
                                    color = TextSecondary
                                )
                            }
                        }

                        Spacer(modifier = Modifier.height(10.dp))

                        Text(
                            text = job.getTitle(language),
                            fontSize = 17.sp,
                            fontWeight = FontWeight.ExtraBold,
                            color = TextPrimary,
                            lineHeight = 23.sp
                        )

                        Spacer(modifier = Modifier.height(10.dp))

                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Icon(
                                imageVector = Icons.Default.Business,
                                contentDescription = null,
                                tint = SecondaryCyan,
                                modifier = Modifier.size(16.dp)
                            )
                            Spacer(modifier = Modifier.width(6.dp))
                            Text(
                                text = job.company,
                                fontSize = 13.sp,
                                fontWeight = FontWeight.SemiBold,
                                color = SecondaryCyan
                            )
                            Spacer(modifier = Modifier.width(4.dp))
                            Icon(
                                imageVector = Icons.Default.Verified,
                                contentDescription = "Verified Private Employer",
                                tint = TertiaryEmerald,
                                modifier = Modifier.size(14.dp)
                            )
                        }

                        Spacer(modifier = Modifier.height(6.dp))

                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Icon(
                                imageVector = Icons.Default.LocationOn,
                                contentDescription = null,
                                tint = TextSecondary,
                                modifier = Modifier.size(14.dp)
                            )
                            Spacer(modifier = Modifier.width(4.dp))
                            Text(
                                text = job.getLocation(language),
                                fontSize = 12.sp,
                                color = TextSecondary
                            )
                        }

                        Spacer(modifier = Modifier.height(6.dp))

                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Icon(
                                imageVector = Icons.Default.MonetizationOn,
                                contentDescription = null,
                                tint = TertiaryEmerald,
                                modifier = Modifier.size(14.dp)
                            )
                            Spacer(modifier = Modifier.width(4.dp))
                            Text(
                                text = "${job.salaryRange} • ${job.experience}",
                                fontSize = 12.sp,
                                fontWeight = FontWeight.Bold,
                                color = TertiaryEmerald
                            )
                        }
                    }
                }

                Spacer(modifier = Modifier.height(14.dp))

                // Unity Banner Ad 1 (Inside Article - Top)
                UnityBannerAd(
                    adConfig = adConfig,
                    language = language,
                    index = 0,
                    modifier = Modifier.fillMaxWidth()
                )

                Spacer(modifier = Modifier.height(14.dp))

                // Official Article Details (Clean text without source ads)
                Card(
                    modifier = Modifier.fillMaxWidth(),
                    colors = CardDefaults.cardColors(containerColor = SurfaceCard),
                    shape = RoundedCornerShape(16.dp),
                    border = CardDefaults.outlinedCardBorder().copy(brush = androidx.compose.ui.graphics.SolidColor(CardBorder))
                ) {
                    Column(modifier = Modifier.padding(16.dp)) {
                        Text(
                            text = if (language == AppLanguage.KANNADA) "ಅಧಿಕೃತ ಅಧಿಸೂಚನೆ ವಿವರಗಳು" else "Official Notification Details",
                            fontSize = 14.sp,
                            fontWeight = FontWeight.Bold,
                            color = PrimaryAmber
                        )

                        Spacer(modifier = Modifier.height(8.dp))

                        Text(
                            text = job.getFullArticle(language),
                            fontSize = 13.sp,
                            color = TextPrimary,
                            lineHeight = 22.sp
                        )

                        Spacer(modifier = Modifier.height(12.dp))

                        Box(
                            modifier = Modifier
                                .fillMaxWidth()
                                .background(SurfaceHighlight, RoundedCornerShape(8.dp))
                                .padding(10.dp)
                        ) {
                            Column {
                                Text(
                                    text = if (language == AppLanguage.KANNADA) "ಮೂಲ ವೆಬ್‌ಸೈಟ್:" else "Source Portal:",
                                    fontSize = 11.sp,
                                    fontWeight = FontWeight.Bold,
                                    color = TextSecondary
                                )
                                Text(
                                    text = "${job.sourceWebsiteName} (${job.sourceWebsiteUrl})",
                                    fontSize = 11.sp,
                                    color = SecondaryCyan
                                )
                            }
                        }
                    }
                }

                Spacer(modifier = Modifier.height(14.dp))

                // Unity Banner Ad 2 (Inside Article - Middle)
                UnityBannerAd(
                    adConfig = adConfig,
                    language = language,
                    index = 1,
                    modifier = Modifier.fillMaxWidth()
                )

                Spacer(modifier = Modifier.height(14.dp))

                // Direct Apply Button inside article as explicitly requested:
                // "ಮತ್ತು ಆರ್ಟಿಕಲ್ open ಆದಾಗ ಅದಕ್ಕೆ ಸಂಬಂಧಿಸಿದ ಕೆಲಸಕ್ಕೆ ಅರ್ಜಿ ಸಲ್ಲಿಸಲು ಆರ್ಟಿಕಲ್ ಒಳಗಡೆ ಲಿಂಕ್ ಇದ್ದರೆ ಅದನ್ನು ಒಂದು buttun ರೂಪದಲ್ಲಿ ಆಯಾ ಆರ್ಟಿಕಲ್ ಒಳಗಡೆ ಕೊಡು. ಇದರಿಂದ users apply ಮಾಡಲು ಸುಲಭ ಆಗುತ್ತದೆ."
                Card(
                    modifier = Modifier.fillMaxWidth(),
                    colors = CardDefaults.cardColors(containerColor = SurfaceHighlight),
                    shape = RoundedCornerShape(16.dp),
                    border = CardDefaults.outlinedCardBorder().copy(brush = Brush.horizontalGradient(listOf(SecondaryCyan, PrimaryAmber)))
                ) {
                    Column(
                        modifier = Modifier.padding(16.dp),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Text(
                            text = if (language == AppLanguage.KANNADA)
                                "ಈ ಉದ್ಯೋಗಕ್ಕೆ ನೇರವಾಗಿ ಅರ್ಜಿ ಸಲ್ಲಿಸಿ"
                            else
                                "Submit Application Directly",
                            fontSize = 14.sp,
                            fontWeight = FontWeight.Bold,
                            color = TextPrimary
                        )

                        Spacer(modifier = Modifier.height(6.dp))

                        Text(
                            text = if (language == AppLanguage.KANNADA)
                                "ಕೆಳಗಿನ ಬಟನ್ ಕ್ಲಿಕ್ ಮಾಡಿದಾಗ ಕಂಪನಿಯ ಅಧಿಕೃತ ಕೆರಿಯರ್ ಪೇಜ್ ತೆರೆಯುತ್ತದೆ. ಯಾವುದೇ ಮಧ್ಯವರ್ತಿ ಶುಲ್ಕವಿರುವುದಿಲ್ಲ."
                            else
                                "Click below to visit the employer's official verified portal. No application fees.",
                            fontSize = 11.sp,
                            color = TextSecondary,
                            textAlign = androidx.compose.ui.text.style.TextAlign.Center
                        )

                        Spacer(modifier = Modifier.height(14.dp))

                        Button(
                            onClick = {
                                try {
                                    val intent = Intent(Intent.ACTION_VIEW, Uri.parse(job.applyUrl))
                                    context.startActivity(intent)
                                } catch (_: Exception) {}
                            },
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(50.dp)
                                .testTag("article_apply_now_button"),
                            colors = ButtonDefaults.buttonColors(containerColor = PrimaryAmber),
                            shape = RoundedCornerShape(12.dp)
                        ) {
                            Icon(
                                imageVector = Icons.Default.OpenInNew,
                                contentDescription = null,
                                tint = BackgroundDeep,
                                modifier = Modifier.size(18.dp)
                            )
                            Spacer(modifier = Modifier.width(8.dp))
                            Text(
                                text = if (language == AppLanguage.KANNADA) "ಅರ್ಜಿ ಸಲ್ಲಿಸಿ (Apply Now)" else "Apply Now (Official Link)",
                                fontWeight = FontWeight.ExtraBold,
                                color = BackgroundDeep,
                                fontSize = 14.sp
                            )
                        }
                    }
                }

                Spacer(modifier = Modifier.height(14.dp))

                // Unity Banner Ad 3 (Inside Article - Bottom)
                UnityBannerAd(
                    adConfig = adConfig,
                    language = language,
                    index = 2,
                    modifier = Modifier.fillMaxWidth()
                )

                Spacer(modifier = Modifier.height(20.dp))
            }
        }
    }
}
