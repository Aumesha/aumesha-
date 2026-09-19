package com.example.ui.components

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
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Description
import androidx.compose.material.icons.filled.Gavel
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Mail
import androidx.compose.material.icons.filled.PrivacyTip
import androidx.compose.material.icons.filled.Security
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.ScrollableTabRow
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRowDefaults
import androidx.compose.material3.TabRowDefaults.tabIndicatorOffset
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import com.example.data.model.AppLanguage
import com.example.ui.theme.BackgroundDeep
import com.example.ui.theme.CardBorder
import com.example.ui.theme.PrimaryAmber
import com.example.ui.theme.SecondaryCyan
import com.example.ui.theme.SurfaceCard
import com.example.ui.theme.SurfaceHighlight
import com.example.ui.theme.TextPrimary
import com.example.ui.theme.TextSecondary
import com.example.viewmodel.PolicyPage

@Composable
fun PolicyDialog(
    initialPage: PolicyPage,
    language: AppLanguage,
    onClose: () -> Unit,
    onPageSelected: (PolicyPage) -> Unit
) {
    Dialog(
        onDismissRequest = onClose,
        properties = DialogProperties(usePlatformDefaultWidth = false)
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(BackgroundDeep)
                .padding(16.dp)
                .testTag("policy_dialog_container")
        ) {
            Card(
                modifier = Modifier
                    .fillMaxSize()
                    .clip(RoundedCornerShape(20.dp)),
                colors = CardDefaults.cardColors(containerColor = SurfaceCard),
                border = CardDefaults.outlinedCardBorder().copy(brush = androidx.compose.ui.graphics.SolidColor(CardBorder))
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(16.dp)
                ) {
                    // Header
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Box(
                                modifier = Modifier
                                    .size(36.dp)
                                    .clip(CircleShape)
                                    .background(PrimaryAmber.copy(alpha = 0.2f)),
                                contentAlignment = Alignment.Center
                            ) {
                                Icon(
                                    imageVector = Icons.Default.Security,
                                    contentDescription = null,
                                    tint = PrimaryAmber,
                                    modifier = Modifier.size(20.dp)
                                )
                            }
                            Spacer(modifier = Modifier.width(10.dp))
                            Column {
                                Text(
                                    text = if (language == AppLanguage.KANNADA) "FreeJobs ನೀತಿ & ಮಾಹಿತಿ ಪುಟಗಳು" else "FreeJobs Approval & Policy Portal",
                                    fontSize = 16.sp,
                                    fontWeight = FontWeight.Bold,
                                    color = TextPrimary
                                )
                                Text(
                                    text = if (language == AppLanguage.KANNADA) "Unity Ad Network & Google Play ನಿಯಮಾವಳಿಗಳು" else "Compliant with Unity Ad Network Policies",
                                    fontSize = 11.sp,
                                    color = TextSecondary
                                )
                            }
                        }

                        IconButton(onClick = onClose) {
                            Icon(
                                imageVector = Icons.Default.Close,
                                contentDescription = "Close",
                                tint = TextSecondary
                            )
                        }
                    }

                    Spacer(modifier = Modifier.height(12.dp))

                    // Tab bar for pages
                    ScrollableTabRow(
                        selectedTabIndex = initialPage.ordinal,
                        containerColor = SurfaceHighlight,
                        contentColor = PrimaryAmber,
                        edgePadding = 8.dp,
                        indicator = { tabPositions ->
                            TabRowDefaults.SecondaryIndicator(
                                Modifier.tabIndicatorOffset(tabPositions[initialPage.ordinal]),
                                color = PrimaryAmber
                            )
                        },
                        modifier = Modifier.clip(RoundedCornerShape(8.dp))
                    ) {
                        PolicyPage.entries.forEach { page ->
                            Tab(
                                selected = initialPage == page,
                                onClick = { onPageSelected(page) },
                                text = {
                                    Text(
                                        text = when (page) {
                                            PolicyPage.ABOUT -> if (language == AppLanguage.KANNADA) "ನನ್ನ app ಬಗ್ಗೆ" else "About App"
                                            PolicyPage.PRIVACY_POLICY -> if (language == AppLanguage.KANNADA) "ಗೌಪ್ಯತಾ ನೀತಿ" else "Privacy Policy"
                                            PolicyPage.TERMS -> if (language == AppLanguage.KANNADA) "ನಿಯಮಗಳು" else "Terms of Use"
                                            PolicyPage.DISCLAIMER -> if (language == AppLanguage.KANNADA) "ಹಕ್ಕುತ್ಯಾಗ" else "Disclaimer"
                                            PolicyPage.CONTACT -> if (language == AppLanguage.KANNADA) "ಸಂಪರ್ಕ" else "Contact Us"
                                        },
                                        fontSize = 12.sp,
                                        fontWeight = if (initialPage == page) FontWeight.Bold else FontWeight.Normal
                                    )
                                }
                            )
                        }
                    }

                    Spacer(modifier = Modifier.height(14.dp))

                    // Policy Content
                    Column(
                        modifier = Modifier
                            .weight(1f)
                            .verticalScroll(rememberScrollState())
                            .padding(end = 4.dp)
                    ) {
                        when (initialPage) {
                            PolicyPage.ABOUT -> {
                                Text(
                                    text = if (language == AppLanguage.KANNADA) "FreeJobs ಅಪ್ಲಿಕೇಶನ್ ಬಗ್ಗೆ (About FreeJobs)" else "About FreeJobs App",
                                    fontSize = 16.sp,
                                    fontWeight = FontWeight.Bold,
                                    color = SecondaryCyan
                                )
                                Spacer(modifier = Modifier.height(8.dp))
                                Text(
                                    text = if (language == AppLanguage.KANNADA) """
                                        FreeJobs ನಮ್ಮ ದೇಶದಲ್ಲಿ ಮತ್ತು ಕರ್ನಾಟಕದಲ್ಲಿ ಇರುವ ನಿರುದ್ಯೋಗ ಸಮಸ್ಯೆಯನ್ನು ನಿವಾರಿಸಲು ರಚಿಸಲಾದ ಒಂದು ಉಚಿತ ಖಾಸಗಿ ಉದ್ಯೋಗ ಮಾರ್ಗದರ್ಶಿ ಆ್ಯಪ್ ಆಗಿದೆ.

                                        ನಮ್ಮ ಧ್ಯೇಯೋದ್ದೇಶ:
                                        - ಗ್ರಾಮೀಣ ಮತ್ತು ನಗರ ಪ್ರದೇಶದ ವಿದ್ಯಾವಂತ ಯುವಕ-ಯುವತಿಯರಿಗೆ ಯಾವುದೇ ಶುಲ್ಕವಿಲ್ಲದೆ ಖಾಸಗಿ ಕಂಪನಿಗಳ ನೈಜ ಉದ್ಯೋಗಾವಕಾಶಗಳನ್ನು ತಲುಪಿಸುವುದು.
                                        - ಉದ್ಯೋಗ ಆಕಾಂಕ್ಷಿಗಳಿಗೆ ವಂಚನೆ ಅಥವಾ ನಕಲಿ ಜಾಬ್‌ಗಳಿಂದ ರಕ್ಷಣೆ ನೀಡಲು ಕೇವಲ ಪರಿಶೀಲಿಸಲ್ಪಟ್ಟ ಕಾರ್ಪೊರೇಟ್ ಖಾಸಗಿ ಸಂಸ್ಥೆಗಳ ಅಧಿಸೂಚನೆಗಳನ್ನು ಒದಗಿಸುವುದು.
                                        - ನವ ಪದವೀಧರರಿಗೆ ಪ್ರಸಿದ್ಧ YouTube ಚಾನೆಲ್‌ಗಳ ಮೂಲಕ ಸಂದರ್ಶನ ಮತ್ತು ಆಪ್ಟಿಟ್ಯೂಡ್ ತರಬೇತಿ ವೀಡಿಯೊಗಳನ್ನು ಉಚಿತವಾಗಿ ನೀಡುವುದು.

                                        ವೈಶಿಷ್ಟ್ಯಗಳು:
                                        1. ಸಂಪೂರ್ಣ ಖಾಸಗಿ ಉದ್ಯೋಗ ಅಧಿಸೂಚನೆಗಳು (ಯಾವುದೇ ಸರ್ಕಾರಿ ನೇಮಕಾತಿ ಇರುವುದಿಲ್ಲ).
                                        2. 7 ದಿನಗಳ ಸ್ವಯಂಚಾಲಿತ ಸುತ್ತೋಲೆ ಮಾದರಿ: ಹಳೆಯ ಪೋಸ್ಟ್‌ಗಳು 7 ದಿನಗಳ ನಂತರ ಡಿಲೀಟ್ ಆಗಿ ಕೇವಲ ತಾಜಾ ಅಪ್ಡೇಟ್‌ಗಳು ಮಾತ್ರ ಲಭ್ಯ.
                                        3. ಕನ್ನಡ ಮತ್ತು ಇಂಗ್ಲಿಷ್ ದ್ವಿಭಾಷಾ ಬೆಂಬಲ.
                                        4. ಜಾಹೀರಾತು ರಹಿತ ನೇರ ಆರ್ಟಿಕಲ್ ಮತ್ತು ನೇರ ಅರ್ಜಿ ಸಲ್ಲಿಕೆ ಲಿಂಕ್.
                                    """.trimIndent()
                                    else """
                                        FreeJobs is a community-driven career assistance mobile application created to address youth unemployment by aggregating verified private sector job notifications.

                                        Our Mission:
                                        - To connect fresh graduates, diploma holders, and experienced professionals across Karnataka and India directly with genuine private employers without intermediary charges.
                                        - To ensure complete transparency by indexing career opportunities from trusted private corporate career dashboards.
                                        - To empower job aspirants with free, curated video tutorials covering aptitude, coding, and HR interview rounds.

                                        Key Pillars:
                                        - 100% Private Sector Opportunities (No government vacancies).
                                        - Automated 7-Day Rolling Retention ensuring only fresh, active job notifications.
                                        - Seamless bilingual Kannada and English interface.
                                        - In-app video guidance and instant direct apply links.
                                    """.trimIndent(),
                                    fontSize = 13.sp,
                                    color = TextPrimary,
                                    lineHeight = 20.sp
                                )
                            }

                            PolicyPage.PRIVACY_POLICY -> {
                                Text(
                                    text = if (language == AppLanguage.KANNADA) "ಗೌಪ್ಯತಾ ನೀತಿ (Privacy Policy & Unity Ads Disclosure)" else "Privacy Policy & Ad Network Disclosure",
                                    fontSize = 16.sp,
                                    fontWeight = FontWeight.Bold,
                                    color = SecondaryCyan
                                )
                                Spacer(modifier = Modifier.height(8.dp))
                                Text(
                                    text = if (language == AppLanguage.KANNADA) """
                                        ಅಂತಿಮ ಪರಿಷ್ಕರಣೆ: 2026

                                        FreeJobs ಬಳಕೆದಾರರ ಗೌಪ್ಯತೆಯನ್ನು ಅತ್ಯುನ್ನತವಾಗಿ ಗೌರವಿಸುತ್ತದೆ. ಈ ಕೆಳಗಿನ ನೀತಿಯು Unity Ad Network ಮತ್ತು Google Play ನೀತಿ ನಿಯಮಗಳಿಗೆ ಬದ್ಧವಾಗಿದೆ.

                                        1. ಮಾಹಿತಿ ಸಂಗ್ರಹಣೆ:
                                        - FreeJobs ಬಳಕೆದಾರರ ವೈಯಕ್ತಿಕ ವಿವರಗಳು, ಮೊಬೈಲ್ ಸಂಖ್ಯೆ, ಅಥವಾ ಬ್ಯಾಂಕ್ ಖಾತೆ ವಿವರಗಳನ್ನು ಎಂದಿಗೂ ಸಂಗ್ರಹಿಸುವುದಿಲ್ಲ ಅಥವಾ ಸರ್ವರ್‌ಗೆ ರವಾನಿಸುವುದಿಲ್ಲ.
                                        - ಭಾಷೆ (ಕನ್ನಡ/ಇಂಗ್ಲಿಷ್) ಮತ್ತು ಸೆಟ್ಟಿಂಗ್ಸ್ ಆಯ್ಕೆಗಳನ್ನು ಕೇವಲ ಬಳಕೆದಾರರ ಸ್ವಂತ ಮೊಬೈಲ್‌ನ ಸ್ಥಳೀಯ ಮೆಮೊರಿಯಲ್ಲಿ (SharedPreferences/Room) ಮಾತ್ರ ಸುರಕ್ಷಿತವಾಗಿಡಲಾಗುತ್ತದೆ.

                                        2. Unity Ad Network ಬಳಕೆ:
                                        - ಈ ಆಪ್ Unity Ad Network (Unity Technologies) ಸೇವೆಗಳನ್ನು ಬಳಸುತ್ತದೆ.
                                        - Unity Ad Network ಪ್ರಸ್ತುತ ಪಡಿಸುವ ಬ್ಯಾನರ್, ಇಂಟರ್‌ಸ್ಟಿಷಿಯಲ್ ಮತ್ತು ರಿವಾರ್ಡೆಡ್ ಜಾಹೀರಾತುಗಳಿಗಾಗಿ ಅನಾಮಧೇಯ ಸಾಧನ ಗುರುತಿಸುವಿಕೆ (Advertising ID) ಬಳಸಲ್ಪಡಬಹುದು.
                                        - ಬಳಕೆದಾರರು ತಮ್ಮ ಮೊಬೈಲ್‌ನ Google Settings ನಲ್ಲಿ Ad Personalization ಅನ್ನು ಯಾವಾಗ ಬೇಕಾದರೂ ನಿಷ್ಕ್ರಿಯಗೊಳಿಸಬಹುದು.

                                        3. ಬಾಹ್ಯ ವೆಬ್‌ಸೈಟ್ ಲಿಂಕ್‌ಗಳು:
                                        - ಉದ್ಯೋಗ ಅರ್ಜಿಯನ್ನು ಸಲ್ಲಿಸಲು ಒದಗಿಸಲಾದ ಲಿಂಕ್‌ಗಳು ಆಯಾ ಖಾಸಗಿ ಕಂಪನಿಗಳ ಅಧಿಕೃತ ಕೆರಿಯರ್ ಪೋರ್ಟಲ್‌ಗಳಿಗೆ ಕರೆದೊಯ್ಯುತ್ತವೆ. ಆಯಾ ಕಂಪನಿಗಳ ಗೌಪ್ಯತಾ ನೀತಿಯನ್ನು ಬಳಕೆದಾರರು ಪ್ರತ್ಯೇಕವಾಗಿ ಗಮನಿಸತಕ್ಕದ್ದು.
                                    """.trimIndent()
                                    else """
                                        Last Updated: 2026

                                        FreeJobs operates with strict commitment to user privacy and full compliance with Unity Ad Network and Google Play Developer policies.

                                        1. Information Collection & Usage:
                                        - FreeJobs does NOT harvest, store, or transmit personally identifiable information (PII) such as phone numbers, addresses, or financial data.
                                        - Language selections and custom URLs are stored strictly locally on device storage using Android Room and SharedPreferences.

                                        2. Third-Party Advertising (Unity Ad Network):
                                        - FreeJobs integrates Unity Ads (Unity Technologies Inc.) to serve banners, interstitial transitions, and rewarded video ads.
                                        - Unity Ads may process non-sensitive device telemetry (such as anonymous Advertising ID, device model, and approximate region) to serve compliant, age-appropriate advertisements.
                                        - Users may opt out of personalized ads at any time via Android System Settings -> Privacy -> Ads.

                                        3. Children's Privacy (COPPA Compliant):
                                        - FreeJobs is intended for job seekers aged 18 and above. We do not knowingly target or collect information from children under 13.
                                    """.trimIndent(),
                                    fontSize = 13.sp,
                                    color = TextPrimary,
                                    lineHeight = 20.sp
                                )
                            }

                            PolicyPage.TERMS -> {
                                Text(
                                    text = if (language == AppLanguage.KANNADA) "ಬಳಕೆಯ ನಿಯಮಗಳು (Terms of Service)" else "Terms of Service",
                                    fontSize = 16.sp,
                                    fontWeight = FontWeight.Bold,
                                    color = SecondaryCyan
                                )
                                Spacer(modifier = Modifier.height(8.dp))
                                Text(
                                    text = if (language == AppLanguage.KANNADA) """
                                        1. ಉಚಿತ ಸೇವೆ:
                                        FreeJobs ಆ್ಯಪ್ ಉದ್ಯೋಗಾಕಾಂಕ್ಷಿಗಳಿಗೆ ಸಂಪೂರ್ಣ ಉಚಿತವಾಗಿರುತ್ತದೆ. ಯಾವುದೇ ಕಾರಣಕ್ಕೂ ಉದ್ಯೋಗ ನೀಡಲು ಹಣ ಅಥವಾ ಲಂಚವನ್ನು ಕೇಳುವವರನ್ನು ಆಪ್ ಪ್ರೋತ್ಸಾಹಿಸುವುದಿಲ್ಲ.

                                        2. ಮಾಹಿತಿ ನಿಖರತೆ:
                                        ಆಪ್‌ನಲ್ಲಿ ಪ್ರಕಟಿಸಲಾಗುವ ಎಲ್ಲಾ ಉದ್ಯೋಗ ವಿವರಗಳು ವಿವಿಧ ಖಾಸಗಿ ಕಂಪನಿಗಳ ಅಧಿಕೃತ ವೆಬ್‌ಸೈಟ್‌ಗಳಿಂದ ಸ್ವೀಕರಿಸಲ್ಪಟ್ಟಿರುತ್ತದೆ. ಅಭ್ಯರ್ಥಿಗಳು ಅರ್ಜಿ ಸಲ್ಲಿಸುವ ಮುನ್ನ ಕಂಪನಿಯ ನಿಖರ ನಿಯಮಾವಳಿಗಳನ್ನು ಪರಿಶೀಲಿಸಿಕೊಳ್ಳುವುದು.

                                        3. 7 ದಿನಗಳ ಸುತ್ತೋಲೆ:
                                        ಯಾವುದೇ ಉದ್ಯೋಗ ಪೋಸ್ಟ್ 7 ದಿನಗಳ ನಂತರ ತನ್ನಷ್ಟಕ್ಕೇ ಅಳಿಸಿಹೋಗುತ್ತದೆ. ಮುಕ್ತಾಯಗೊಂಡ ಉದ್ಯೋಗಗಳಿಗೆ ಆಪ್ ಹೊಣೆಯಾಗಿರುವುದಿಲ್ಲ.
                                    """.trimIndent()
                                    else """
                                        1. Free Educational & Informational Access:
                                        FreeJobs is provided strictly free of charge. We never charge users or sell application forms.

                                        2. Application Verification:
                                        Job postings are aggregated from verified corporate career websites. Users are advised to review the respective company policies before submitting confidential documents.

                                        3. Automated 7-Day Expiry:
                                        Notices remain active for 7 days and are purged on day 8.
                                    """.trimIndent(),
                                    fontSize = 13.sp,
                                    color = TextPrimary,
                                    lineHeight = 20.sp
                                )
                            }

                            PolicyPage.DISCLAIMER -> {
                                Text(
                                    text = if (language == AppLanguage.KANNADA) "ಖಾಸಗಿ ಉದ್ಯೋಗ ಹಕ್ಕುತ್ಯಾಗ (Official Disclaimer)" else "Official Non-Government Disclaimer",
                                    fontSize = 16.sp,
                                    fontWeight = FontWeight.Bold,
                                    color = PrimaryAmber
                                )
                                Spacer(modifier = Modifier.height(8.dp))
                                Text(
                                    text = if (language == AppLanguage.KANNADA) """
                                        ಮುಖ್ಯ ಹಕ್ಕುತ್ಯಾಗ (CRITICAL DISCLAIMER):

                                        1. ಯಾವುದೇ ಸರ್ಕಾರಿ ಸಂಬಂಧವಿಲ್ಲ (NO GOVERNMENT AFFILIATION):
                                        FreeJobs ಯಾವುದೇ ಕೇಂದ್ರ ಅಥವಾ ರಾಜ್ಯ ಸರ್ಕಾರದ ಇಲಾಖೆ, ನಿಗಮ, ಮಂಡಳಿ, ಅಥವಾ ಸರ್ಕಾರಿ ಏಜೆನ್ಸಿಗೆ ಸೇರಿರುವುದಿಲ್ಲ. ಇದು ಸಂಪೂರ್ಣವಾಗಿ ಸ್ವತಂತ್ರ, ಖಾಸಗಿ ಉದ್ಯೋಗ ಮಾಹಿತಿ ಆ್ಯಪ್ ಆಗಿದೆ.
                                        ನಾವು ಯಾವುದೇ ಸರ್ಕಾರಿ ಉದ್ಯೋಗಗಳನ್ನು ಪ್ರಕಟಿಸುವುದಿಲ್ಲ.

                                        2. ಖಾಸಗಿ ಕಂಪನಿಗಳ ನೇಮಕಾತಿ ಮಾತ್ರ (PRIVATE JOBS ONLY):
                                        ಇಲ್ಲಿ ತೋರಿಸಲಾಗುವ ಎಲ್ಲಾ ನೇಮಕಾತಿಗಳು ಕೇವಲ ಖಾಸಗಿ ಕಂಪನಿಗಳು (ಉದಾ: IT, ಸಾಫ್ಟ್‌ವೇರ್, ಮೆಕ್ಯಾನಿಕಲ್, ಬಿಪಿಒ, ಬ್ಯಾಂಕಿಂಗ್, ಆಟೋಮೊಬೈಲ್, ಲಾಜಿಸ್ಟಿಕ್ಸ್) ಮಾತ್ರ ಆಗಿರುತ್ತವೆ.

                                        3. ವಂಚನೆಗಳಿಂದ ಎಚ್ಚರ:
                                        ಖಾಸಗಿ ಉದ್ಯೋಗಗಳಿಗೆ ಯಾವುದೇ ಸಂದರ್ಶನ ಶುಲ್ಕ ಅಥವಾ ಸೆಕ್ಯೂರಿಟಿ ಡೆಪಾಸಿಟ್ ನೀಡಬೇಡಿ. ಅಧಿಕೃತ ಕಂಪನಿಗಳು ಯಾವುದೇ ಶುಲ್ಕ ಕೇಳುವುದಿಲ್ಲ.
                                    """.trimIndent()
                                    else """
                                        CRITICAL OFFICIAL DISCLAIMER:

                                        1. NO GOVERNMENT ENTITY AFFILIATION:
                                        FreeJobs is NOT affiliated with, authorized by, or associated with the Government of India, the Government of Karnataka, or any state or central governmental department. 
                                        FreeJobs does not publish any government or public sector jobs.

                                        2. 100% PRIVATE SECTOR EMPLOYMENT NOTIFICATIONS:
                                        All career notifications published on FreeJobs are exclusively for the private corporate sector (including IT, Software, Manufacturing, Automobile, Logistics, Retail, and Private Banking).

                                        3. Direct Career Portal Referrals:
                                        All 'Apply Now' buttons direct the user to the respective private enterprise's verified recruitment website.
                                    """.trimIndent(),
                                    fontSize = 13.sp,
                                    color = TextPrimary,
                                    lineHeight = 20.sp
                                )
                            }

                            PolicyPage.CONTACT -> {
                                Text(
                                    text = if (language == AppLanguage.KANNADA) "ಸಂಪರ್ಕ ಮತ್ತು ಬೆಂಬಲ (Contact Us)" else "Contact & Support",
                                    fontSize = 16.sp,
                                    fontWeight = FontWeight.Bold,
                                    color = SecondaryCyan
                                )
                                Spacer(modifier = Modifier.height(8.dp))
                                Text(
                                    text = if (language == AppLanguage.KANNADA) """
                                        FreeJobs ಕುರಿತು ಯಾವುದೇ ಸಲಹೆಗಳು ಅಥವಾ ದೂರುಗಳಿದ್ದಲ್ಲಿ ನಮ್ಮನ್ನು ಸಂಪರ್ಕಿಸಿ:

                                        - ಇಮೇಲ್: aumesha27@gmail.com
                                        - ಡೆವಲಪರ್ & ಪ್ರಕಾಶಕ: FreeJobs Team Karnataka
                                        - ಸ್ಥಳ: ಕರ್ನಾಟಕ, ಭಾರತ
                                        - ಪ್ರತಿಕ್ರಿಯೆ ಸಮಯ: 24-48 ಗಂಟೆಗಳು

                                        ಖಾಸಗಿ ಕಂಪನಿಗಳ ಜಾಬ್ ಪೋಸ್ಟಿಂಗ್ ಅಥವಾ Unity Ad ಪರಿಶೀಲನೆಗಾಗಿ ಅಧಿಕೃತ ಇಮೇಲ್ ಮೂಲಕ ಸಂಪರ್ಕಿಸಬಹುದು.
                                    """.trimIndent()
                                    else """
                                        For feedback, business inquiries, or Unity Ad Network compliance verification, reach us at:

                                        - Official Support Email: aumesha27@gmail.com
                                        - Publisher: FreeJobs Karnataka
                                        - Region: Karnataka, India
                                        - Response Turnaround: Within 24-48 business hours
                                    """.trimIndent(),
                                    fontSize = 13.sp,
                                    color = TextPrimary,
                                    lineHeight = 20.sp
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}
