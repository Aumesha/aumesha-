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
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Language
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.filled.Work
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
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
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
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
fun AppTopBanner(
    language: AppLanguage,
    onToggleLanguage: () -> Unit,
    onOpenSettings: () -> Unit,
    onOpenPolicyPage: (PolicyPage) -> Unit,
    modifier: Modifier = Modifier
) {
    var isMenuExpanded by remember { mutableStateOf(false) }

    Surface(
        modifier = modifier.fillMaxWidth(),
        color = Color.Transparent
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .background(
                    brush = Brush.verticalGradient(
                        colors = listOf(
                            SurfaceHighlight,
                            SurfaceCard
                        )
                    ),
                    shape = RoundedCornerShape(bottomStart = 20.dp, bottomEnd = 20.dp)
                )
                .border(
                    width = 1.dp,
                    color = CardBorder,
                    shape = RoundedCornerShape(bottomStart = 20.dp, bottomEnd = 20.dp)
                )
                .padding(horizontal = 16.dp, vertical = 14.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                // Left side: App Icon & Name
                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Box(
                        modifier = Modifier
                            .size(44.dp)
                            .clip(RoundedCornerShape(12.dp))
                            .background(
                                Brush.linearGradient(
                                    colors = listOf(PrimaryAmber, Color(0xFFFF7A00))
                                )
                            ),
                        contentAlignment = Alignment.Center
                    ) {
                        Icon(
                            imageVector = Icons.Default.Work,
                            contentDescription = "FreeJobs Icon",
                            tint = BackgroundDeep,
                            modifier = Modifier.size(26.dp)
                        )
                    }

                    Spacer(modifier = Modifier.width(12.dp))

                    Column {
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Text(
                                text = "FreeJobs",
                                fontSize = 20.sp,
                                fontWeight = FontWeight.ExtraBold,
                                color = TextPrimary,
                                letterSpacing = 0.5.sp
                            )
                            Spacer(modifier = Modifier.width(6.dp))
                            Box(
                                modifier = Modifier
                                    .background(
                                        PrimaryAmber.copy(alpha = 0.18f),
                                        RoundedCornerShape(6.dp)
                                    )
                                    .padding(horizontal = 6.dp, vertical = 2.dp)
                            ) {
                                Text(
                                    text = if (language == AppLanguage.KANNADA) "ಖಾಸಗಿ" else "PRIVATE",
                                    fontSize = 10.sp,
                                    fontWeight = FontWeight.Bold,
                                    color = PrimaryAmber
                                )
                            }
                        }
                        Text(
                            text = if (language == AppLanguage.KANNADA)
                                "ಕರ್ನಾಟಕ ಖಾಸಗಿ ಜಾಬ್ ಅಲರ್ಟ್"
                            else
                                "Private Career Notifications",
                            fontSize = 11.sp,
                            color = TextSecondary
                        )
                    }
                }

                // Right side: Language Toggle, Settings Box, 3-dots Menu
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(6.dp)
                ) {
                    // Language Switch Button
                    Box(
                        modifier = Modifier
                            .clip(RoundedCornerShape(10.dp))
                            .background(SurfaceHighlight)
                            .border(1.dp, CardBorder, RoundedCornerShape(10.dp))
                            .clickable { onToggleLanguage() }
                            .padding(horizontal = 8.dp, vertical = 6.dp)
                            .testTag("language_toggle_button"),
                        contentAlignment = Alignment.Center
                    ) {
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Icon(
                                imageVector = Icons.Default.Language,
                                contentDescription = "Change Language",
                                tint = SecondaryCyan,
                                modifier = Modifier.size(16.dp)
                            )
                            Spacer(modifier = Modifier.width(4.dp))
                            Text(
                                text = if (language == AppLanguage.KANNADA) "ಕನ್ನಡ" else "ENG",
                                fontSize = 12.sp,
                                fontWeight = FontWeight.Bold,
                                color = TextPrimary
                            )
                        }
                    }

                    // Settings Box Button (as requested in prompt)
                    IconButton(
                        onClick = onOpenSettings,
                        modifier = Modifier
                            .size(38.dp)
                            .clip(RoundedCornerShape(10.dp))
                            .background(SurfaceHighlight)
                            .border(1.dp, CardBorder, RoundedCornerShape(10.dp))
                            .testTag("settings_box_button")
                    ) {
                        Icon(
                            imageVector = Icons.Default.Settings,
                            contentDescription = "Settings",
                            tint = PrimaryAmber,
                            modifier = Modifier.size(20.dp)
                        )
                    }

                    // 3-dots Menu Button
                    Box {
                        IconButton(
                            onClick = { isMenuExpanded = true },
                            modifier = Modifier
                                .size(38.dp)
                                .clip(RoundedCornerShape(10.dp))
                                .background(SurfaceHighlight)
                                .border(1.dp, CardBorder, RoundedCornerShape(10.dp))
                                .testTag("three_dots_menu_button")
                        ) {
                            Icon(
                                imageVector = Icons.Default.MoreVert,
                                contentDescription = "Menu Options",
                                tint = TextPrimary,
                                modifier = Modifier.size(20.dp)
                            )
                        }

                        DropdownMenu(
                            expanded = isMenuExpanded,
                            onDismissRequest = { isMenuExpanded = false },
                            modifier = Modifier
                                .background(SurfaceCard)
                                .border(1.dp, CardBorder, RoundedCornerShape(8.dp))
                        ) {
                            DropdownMenuItem(
                                text = {
                                    Text(
                                        text = if (language == AppLanguage.KANNADA) "FreeJobs ಬಗ್ಗೆ (About App)" else "About FreeJobs",
                                        color = TextPrimary
                                    )
                                },
                                onClick = {
                                    isMenuExpanded = false
                                    onOpenPolicyPage(PolicyPage.ABOUT)
                                }
                            )
                            DropdownMenuItem(
                                text = {
                                    Text(
                                        text = if (language == AppLanguage.KANNADA) "ಗೌಪ್ಯತಾ ನೀತಿ (Privacy Policy)" else "Privacy Policy",
                                        color = TextPrimary
                                    )
                                },
                                onClick = {
                                    isMenuExpanded = false
                                    onOpenPolicyPage(PolicyPage.PRIVACY_POLICY)
                                }
                            )
                            DropdownMenuItem(
                                text = {
                                    Text(
                                        text = if (language == AppLanguage.KANNADA) "ನಿಯಮಗಳು (Terms of Service)" else "Terms of Service",
                                        color = TextPrimary
                                    )
                                },
                                onClick = {
                                    isMenuExpanded = false
                                    onOpenPolicyPage(PolicyPage.TERMS)
                                }
                            )
                            DropdownMenuItem(
                                text = {
                                    Text(
                                        text = if (language == AppLanguage.KANNADA) "ಹಕ್ಕುತ್ಯಾಗ (Ad & Policy Disclaimer)" else "Policy Disclaimer",
                                        color = TextPrimary
                                    )
                                },
                                onClick = {
                                    isMenuExpanded = false
                                    onOpenPolicyPage(PolicyPage.DISCLAIMER)
                                }
                            )
                            DropdownMenuItem(
                                text = {
                                    Text(
                                        text = if (language == AppLanguage.KANNADA) "ಸಂಪರ್ಕ (Contact & Support)" else "Contact Us",
                                        color = TextPrimary
                                    )
                                },
                                onClick = {
                                    isMenuExpanded = false
                                    onOpenPolicyPage(PolicyPage.CONTACT)
                                }
                            )
                        }
                    }
                }
            }
        }
    }
}
