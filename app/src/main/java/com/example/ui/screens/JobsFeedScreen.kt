package com.example.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.border
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
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material.icons.filled.Business
import androidx.compose.material.icons.filled.CalendarToday
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.MonetizationOn
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Work
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
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
import com.example.ui.theme.TextMuted
import com.example.ui.theme.TextPrimary
import com.example.ui.theme.TextSecondary

@Composable
fun JobsFeedScreen(
    jobs: List<JobPost>,
    searchQuery: String,
    onSearchQueryChange: (String) -> Unit,
    adConfig: UnityAdConfig,
    language: AppLanguage,
    onReadMoreClicked: (JobPost) -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .background(BackgroundDeep)
            .padding(horizontal = 16.dp)
            .testTag("jobs_feed_screen")
    ) {
        Spacer(modifier = Modifier.height(10.dp))

        // Search Box (Right below top banner)
        // Requirement: "ನಂತರ ಇದೆಲ್ಲ ಆದಮೇಲೆ ಒಂದು Search box ಇರಲಿ. ಅಲ್ಲಿ ಯಾವುದಾದರೂ ಉದ್ಯೋಗದ ಬಗ್ಗೆ ಹುಡುಕಿದರೆ ಮೊದಲು ಬರಬೇಕು."
        OutlinedTextField(
            value = searchQuery,
            onValueChange = onSearchQueryChange,
            modifier = Modifier
                .fillMaxWidth()
                .testTag("job_search_input"),
            placeholder = {
                Text(
                    text = if (language == AppLanguage.KANNADA)
                        "ಉದ್ಯೋಗ, ಕಂಪನಿ, ಅಥವಾ ಜಿಲ್ಲೆಯ ಹೆಸರು ಹುಡುಕಿ..."
                    else
                        "Search private jobs, company, skills, location...",
                    fontSize = 13.sp,
                    color = TextMuted
                )
            },
            leadingIcon = {
                Icon(
                    imageVector = Icons.Default.Search,
                    contentDescription = "Search Icon",
                    tint = PrimaryAmber
                )
            },
            trailingIcon = {
                if (searchQuery.isNotEmpty()) {
                    IconButton(onClick = { onSearchQueryChange("") }) {
                        Icon(
                            imageVector = Icons.Default.Clear,
                            contentDescription = "Clear",
                            tint = TextSecondary,
                            modifier = Modifier.size(18.dp)
                        )
                    }
                }
            },
            singleLine = true,
            shape = RoundedCornerShape(14.dp),
            colors = OutlinedTextFieldDefaults.colors(
                focusedContainerColor = SurfaceCard,
                unfocusedContainerColor = SurfaceCard,
                focusedBorderColor = PrimaryAmber,
                unfocusedBorderColor = CardBorder,
                focusedTextColor = TextPrimary,
                unfocusedTextColor = TextPrimary
            )
        )

        Spacer(modifier = Modifier.height(10.dp))

        // Small Unity Banner Ad right after Search Box as explicitly requested:
        // "ಇದರ ನಂತರ unity ad ನ ಒಂದು ಚಿಕ್ಕ banner ad ಬರಬೇಕು."
        UnityBannerAd(
            adConfig = adConfig,
            language = language,
            index = 0,
            modifier = Modifier.fillMaxWidth().testTag("top_search_banner_ad")
        )

        Spacer(modifier = Modifier.height(10.dp))

        // 7-day rolling notice tag
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .background(SurfaceHighlight, RoundedCornerShape(8.dp))
                .padding(horizontal = 10.dp, vertical = 6.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Box(
                    modifier = Modifier
                        .size(8.dp)
                        .clip(CircleShape)
                        .background(TertiaryEmerald)
                )
                Spacer(modifier = Modifier.width(6.dp))
                Text(
                    text = if (language == AppLanguage.KANNADA)
                        "ಖಾಸಗಿ ಉದ್ಯೋಗಗಳು (7 ದಿನಗಳ ಸುತ್ತೋಲೆ ಅಪ್ಡೇಟ್)"
                    else
                        "Verified Private Careers (7-Day Rolling Active)",
                    fontSize = 11.sp,
                    fontWeight = FontWeight.SemiBold,
                    color = TextPrimary
                )
            }

            Text(
                text = "${jobs.size} ${if (language == AppLanguage.KANNADA) "ಪೋಸ್ಟ್‌ಗಳು" else "Jobs"}",
                fontSize = 11.sp,
                fontWeight = FontWeight.Bold,
                color = PrimaryAmber
            )
        }

        Spacer(modifier = Modifier.height(10.dp))

        // Job Feed List
        LazyColumn(
            modifier = Modifier.weight(1f),
            verticalArrangement = Arrangement.spacedBy(12.dp),
            contentPadding = PaddingValues(bottom = 16.dp)
        ) {
            itemsIndexed(jobs, key = { _, item -> item.id }) { index, job ->
                JobPostCard(
                    job = job,
                    language = language,
                    onReadMore = { onReadMoreClicked(job) }
                )

                // Every 4 job posts, insert a Unity Banner Ad!
                // Requirement: "ನಂತರ ಪ್ರತಿ 4 ಮಾಹಿತಿಯ ಕೆಳಗಡೆ ಒಂದು banner ad ಬರಬೇಕು."
                if ((index + 1) % 4 == 0 && index != jobs.lastIndex) {
                    Spacer(modifier = Modifier.height(6.dp))
                    UnityBannerAd(
                        adConfig = adConfig,
                        language = language,
                        index = (index / 4) + 1,
                        modifier = Modifier.fillMaxWidth()
                    )
                    Spacer(modifier = Modifier.height(6.dp))
                }
            }
        }

        Spacer(modifier = Modifier.height(6.dp))

        // Fixed Bottom Unity Banner Ad right at the bottom of Articles screen
        // Explicit Requirement: "ಮೊದಲ್ಲೂ ಅಂದರೆ ಅರ್ಟಿಕಾಲಸ ಬರುವ ಜಾಗದಲ್ಲಿ ಕೆಳಗಡೆ ಬ್ಯಾನರ್ ಆದ ಇಲ್ಲ ಇದನ್ನು ಸರಿಪಡಿಸು."
        UnityBannerAd(
            adConfig = adConfig,
            language = language,
            index = 99,
            modifier = Modifier
                .fillMaxWidth()
                .testTag("bottom_fixed_feed_banner_ad")
        )
    }
}

@Composable
fun JobPostCard(
    job: JobPost,
    language: AppLanguage,
    onReadMore: () -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .testTag("job_card_${job.id}"),
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(containerColor = SurfaceCard),
        border = CardDefaults.outlinedCardBorder().copy(brush = androidx.compose.ui.graphics.SolidColor(CardBorder))
    ) {
        Column(modifier = Modifier.padding(14.dp)) {
            // Category & Age Row
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Box(
                    modifier = Modifier
                        .background(PrimaryAmber.copy(alpha = 0.15f), RoundedCornerShape(6.dp))
                        .padding(horizontal = 7.dp, vertical = 3.dp)
                ) {
                    Text(
                        text = job.getCategory(language),
                        fontSize = 10.sp,
                        fontWeight = FontWeight.Bold,
                        color = PrimaryAmber
                    )
                }

                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(
                        imageVector = Icons.Default.CalendarToday,
                        contentDescription = null,
                        tint = TextSecondary,
                        modifier = Modifier.size(11.dp)
                    )
                    Spacer(modifier = Modifier.width(4.dp))
                    Text(
                        text = job.getFormattedAge(language),
                        fontSize = 11.sp,
                        color = TextSecondary
                    )
                }
            }

            Spacer(modifier = Modifier.height(8.dp))

            // Job Title
            Text(
                text = job.getTitle(language),
                fontSize = 15.sp,
                fontWeight = FontWeight.Bold,
                color = TextPrimary,
                lineHeight = 20.sp,
                maxLines = 2,
                overflow = TextOverflow.Ellipsis
            )

            Spacer(modifier = Modifier.height(6.dp))

            // Company & Location
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    imageVector = Icons.Default.Business,
                    contentDescription = null,
                    tint = SecondaryCyan,
                    modifier = Modifier.size(14.dp)
                )
                Spacer(modifier = Modifier.width(4.dp))
                Text(
                    text = job.company,
                    fontSize = 12.sp,
                    fontWeight = FontWeight.SemiBold,
                    color = SecondaryCyan,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                    modifier = Modifier.weight(1f)
                )

                Spacer(modifier = Modifier.width(8.dp))

                Icon(
                    imageVector = Icons.Default.LocationOn,
                    contentDescription = null,
                    tint = TextSecondary,
                    modifier = Modifier.size(14.dp)
                )
                Spacer(modifier = Modifier.width(3.dp))
                Text(
                    text = job.getLocation(language),
                    fontSize = 11.sp,
                    color = TextSecondary,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )
            }

            Spacer(modifier = Modifier.height(8.dp))

            // Salary & Experience badge
            Row(verticalAlignment = Alignment.CenterVertically) {
                Icon(
                    imageVector = Icons.Default.MonetizationOn,
                    contentDescription = null,
                    tint = TertiaryEmerald,
                    modifier = Modifier.size(13.dp)
                )
                Spacer(modifier = Modifier.width(4.dp))
                Text(
                    text = "${job.salaryRange} • ${job.experience}",
                    fontSize = 11.sp,
                    fontWeight = FontWeight.Bold,
                    color = TertiaryEmerald
                )
            }

            Spacer(modifier = Modifier.height(8.dp))

            // Short Description
            Text(
                text = job.getShortDesc(language),
                fontSize = 12.sp,
                color = TextSecondary,
                lineHeight = 17.sp,
                maxLines = 2,
                overflow = TextOverflow.Ellipsis
            )

            Spacer(modifier = Modifier.height(12.dp))

            // Read More Button (Requirement: "ಅದರ ಕೆಳಗಡೆ ರೀಡ್ ಮೋರ್ ಎಂಬ buttun ಒತ್ತಿದ ಕೂಡಲೇ ಅಧಿಕೃತ ವೆಬ್ಸೈಟ್ ನಲ್ಲಿ ಇರುವ ಮಾಹಿತಿಯು ಕಾಪಿ ಆಗಿ ನನ್ನ app ನಲ್ಲಿ ಬರಬೇಕು ಮತ್ತು ಅದರಲ್ಲಿ ಅವರ ads ಬರಬಾರದು")
            Button(
                onClick = onReadMore,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(42.dp)
                    .testTag("read_more_button_${job.id}"),
                shape = RoundedCornerShape(10.dp),
                colors = ButtonDefaults.buttonColors(containerColor = SurfaceHighlight)
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center
                ) {
                    Text(
                        text = if (language == AppLanguage.KANNADA) "ಹೆಚ್ಚು ಓದಿ (Read More)" else "Read More & Details",
                        fontWeight = FontWeight.Bold,
                        color = PrimaryAmber,
                        fontSize = 13.sp
                    )
                    Spacer(modifier = Modifier.width(6.dp))
                    Icon(
                        imageVector = Icons.Default.ArrowForward,
                        contentDescription = "Read More",
                        tint = PrimaryAmber,
                        modifier = Modifier.size(16.dp)
                    )
                }
            }
        }
    }
}
