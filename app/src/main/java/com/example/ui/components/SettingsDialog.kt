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
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Key
import androidx.compose.material.icons.filled.Language
import androidx.compose.material.icons.filled.Link
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.LockOpen
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.filled.SwitchAccessShortcut
import androidx.compose.material.icons.filled.VideoLibrary
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Switch
import androidx.compose.material3.SwitchDefaults
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.TabRowDefaults
import androidx.compose.material3.TabRowDefaults.tabIndicatorOffset
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import com.example.data.datasource.InitialDataSources
import com.example.data.model.AppLanguage
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

@Composable
fun SettingsDialog(
    adConfig: UnityAdConfig,
    language: AppLanguage,
    onClose: () -> Unit,
    onUpdateConfig: (UnityAdConfig) -> Unit,
    onAddWebsite: (String) -> Unit,
    onAddChannel: (String) -> Unit,
    onTriggerDailySync: () -> Unit
) {
    // Password lock state - password MUST be "aumesha" per prompt instructions
    var isUnlocked by remember { mutableStateOf(false) }
    var enteredPassword by remember { mutableStateOf("") }
    var passwordError by remember { mutableStateOf(false) }

    // Unlocked settings form states
    var gameIdInput by remember { mutableStateOf(adConfig.gameId) }
    var bannerIdInput by remember { mutableStateOf(adConfig.bannerPlacementId) }
    var interstitialIdInput by remember { mutableStateOf(adConfig.interstitialPlacementId) }
    var rewardedIdInput by remember { mutableStateOf(adConfig.rewardedPlacementId) }
    var isTestMode by remember { mutableStateOf(adConfig.isTestMode) }

    var websiteInput by remember { mutableStateOf("") }
    var channelInput by remember { mutableStateOf("") }

    var activeSubTab by remember { mutableIntStateOf(0) } // 0: Ads, 1: Websites, 2: YouTube, 3: Sync

    Dialog(
        onDismissRequest = onClose,
        properties = DialogProperties(usePlatformDefaultWidth = false)
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(BackgroundDeep)
                .padding(16.dp)
                .testTag("settings_dialog_container"),
            contentAlignment = Alignment.Center
        ) {
            if (!isUnlocked) {
                // Password Challenge Screen
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clip(RoundedCornerShape(20.dp)),
                    colors = CardDefaults.cardColors(containerColor = SurfaceCard),
                    border = CardDefaults.outlinedCardBorder().copy(brush = androidx.compose.ui.graphics.SolidColor(CardBorder))
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
                            Row(verticalAlignment = Alignment.CenterVertically) {
                                Box(
                                    modifier = Modifier
                                        .size(36.dp)
                                        .clip(CircleShape)
                                        .background(PrimaryAmber.copy(alpha = 0.2f)),
                                    contentAlignment = Alignment.Center
                                ) {
                                    Icon(
                                        imageVector = Icons.Default.Lock,
                                        contentDescription = "Admin Lock",
                                        tint = PrimaryAmber,
                                        modifier = Modifier.size(20.dp)
                                    )
                                }
                                Spacer(modifier = Modifier.width(10.dp))
                                Text(
                                    text = if (language == AppLanguage.KANNADA) "ಸೆಟ್ಟಿಂಗ್ಸ್ ಪ್ರವೇಶ" else "Settings Protection",
                                    fontSize = 17.sp,
                                    fontWeight = FontWeight.Bold,
                                    color = TextPrimary
                                )
                            }

                            IconButton(onClick = onClose) {
                                Icon(
                                    imageVector = Icons.Default.Close,
                                    contentDescription = "Close",
                                    tint = TextSecondary
                                )
                            }
                        }

                        Spacer(modifier = Modifier.height(16.dp))

                        Text(
                            text = if (language == AppLanguage.KANNADA)
                                "ಸೆಟ್ಟಿಂಗ್ಸ್ ಬಾಕ್ಸ್ ಓಪನ್ ಮಾಡಲು ಪಾಸ್‌ವರ್ಡ್ ನಮೂದಿಸಿ:"
                            else
                                "Please enter administrator password to unlock settings:",
                            fontSize = 13.sp,
                            color = TextSecondary,
                            textAlign = TextAlign.Center
                        )

                        Spacer(modifier = Modifier.height(16.dp))

                        OutlinedTextField(
                            value = enteredPassword,
                            onValueChange = {
                                enteredPassword = it
                                passwordError = false
                            },
                            label = {
                                Text(if (language == AppLanguage.KANNADA) "ಪಾಸ್‌ವರ್ಡ್" else "Password")
                            },
                            visualTransformation = PasswordVisualTransformation(),
                            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
                            isError = passwordError,
                            singleLine = true,
                            modifier = Modifier
                                .fillMaxWidth()
                                .testTag("settings_password_input"),
                            colors = OutlinedTextFieldDefaults.colors(
                                focusedBorderColor = PrimaryAmber,
                                unfocusedBorderColor = CardBorder,
                                focusedTextColor = TextPrimary,
                                unfocusedTextColor = TextPrimary,
                                focusedLabelColor = PrimaryAmber,
                                unfocusedLabelColor = TextSecondary
                            )
                        )

                        if (passwordError) {
                            Spacer(modifier = Modifier.height(6.dp))
                            Text(
                                text = if (language == AppLanguage.KANNADA)
                                    "ತಪ್ಪಾದ ಪಾಸ್‌ವರ್ಡ್! ಸರಿಯಾದ ಪಾಸ್‌ವರ್ಡ್ ನಮೂದಿಸಿ."
                                else
                                    "Incorrect password! Please check again.",
                                color = Color(0xFFEF4444),
                                fontSize = 12.sp,
                                fontWeight = FontWeight.SemiBold
                            )
                        }

                        Spacer(modifier = Modifier.height(20.dp))

                        Button(
                            onClick = {
                                // Strictly check "aumesha" per prompt instructions
                                if (enteredPassword == "aumesha") {
                                    isUnlocked = true
                                    passwordError = false
                                } else {
                                    passwordError = true
                                }
                            },
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(48.dp)
                                .testTag("settings_unlock_button"),
                            colors = ButtonDefaults.buttonColors(containerColor = PrimaryAmber),
                            shape = RoundedCornerShape(10.dp)
                        ) {
                            Icon(
                                imageVector = Icons.Default.LockOpen,
                                contentDescription = null,
                                tint = BackgroundDeep,
                                modifier = Modifier.size(18.dp)
                            )
                            Spacer(modifier = Modifier.width(8.dp))
                            Text(
                                text = if (language == AppLanguage.KANNADA) "ಓಪನ್ ಮಾಡಿ (Unlock)" else "Unlock Settings",
                                fontWeight = FontWeight.Bold,
                                color = BackgroundDeep,
                                fontSize = 14.sp
                            )
                        }
                    }
                }
            } else {
                // Unlocked Full Settings Screen
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
                                        .background(TertiaryEmerald.copy(alpha = 0.2f)),
                                    contentAlignment = Alignment.Center
                                ) {
                                    Icon(
                                        imageVector = Icons.Default.Settings,
                                        contentDescription = null,
                                        tint = TertiaryEmerald,
                                        modifier = Modifier.size(20.dp)
                                    )
                                }
                                Spacer(modifier = Modifier.width(10.dp))
                                Column {
                                    Text(
                                        text = if (language == AppLanguage.KANNADA) "FreeJobs ಸೆಟ್ಟಿಂಗ್ಸ್ ಬಾಕ್ಸ್" else "FreeJobs Admin Controls",
                                        fontSize = 16.sp,
                                        fontWeight = FontWeight.Bold,
                                        color = TextPrimary
                                    )
                                    Text(
                                        text = if (language == AppLanguage.KANNADA) "Unity Ads & ಲಿಂಕ್ ನಿರ್ವಹಣೆ" else "Unity Ads & Link Manager",
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

                        // Sub Tabs (Ads, Websites, Channels, Sync)
                        TabRow(
                            selectedTabIndex = activeSubTab,
                            containerColor = SurfaceHighlight,
                            contentColor = PrimaryAmber,
                            indicator = { tabPositions ->
                                TabRowDefaults.SecondaryIndicator(
                                    Modifier.tabIndicatorOffset(tabPositions[activeSubTab]),
                                    color = PrimaryAmber
                                )
                            },
                            modifier = Modifier.clip(RoundedCornerShape(8.dp))
                        ) {
                            Tab(
                                selected = activeSubTab == 0,
                                onClick = { activeSubTab = 0 },
                                text = { Text(if (language == AppLanguage.KANNADA) "Unity Ad" else "Unity Ad", fontSize = 11.sp) }
                            )
                            Tab(
                                selected = activeSubTab == 1,
                                onClick = { activeSubTab = 1 },
                                text = { Text(if (language == AppLanguage.KANNADA) "ವೆಬ್‌ಸೈಟ್" else "Websites", fontSize = 11.sp) }
                            )
                            Tab(
                                selected = activeSubTab == 2,
                                onClick = { activeSubTab = 2 },
                                text = { Text(if (language == AppLanguage.KANNADA) "YouTube" else "YouTube", fontSize = 11.sp) }
                            )
                            Tab(
                                selected = activeSubTab == 3,
                                onClick = { activeSubTab = 3 },
                                text = { Text(if (language == AppLanguage.KANNADA) "ಸಿಂಕ್ (Sync)" else "Sync & 7d", fontSize = 11.sp) }
                            )
                        }

                        Spacer(modifier = Modifier.height(12.dp))

                        // Tab Content
                        Column(
                            modifier = Modifier
                                .weight(1f)
                                .verticalScroll(rememberScrollState())
                        ) {
                            when (activeSubTab) {
                                0 -> {
                                    // Unity Ad Network Settings
                                    Text(
                                        text = if (language == AppLanguage.KANNADA) "Unity Ad Network ಐಡಿಗಳು" else "Unity Ad Network Configuration",
                                        fontWeight = FontWeight.Bold,
                                        fontSize = 14.sp,
                                        color = SecondaryCyan
                                    )
                                    Text(
                                        text = if (language == AppLanguage.KANNADA)
                                            "ಇಲ್ಲಿ ನಿಮ್ಮ ನಿಜವಾದ Unity Ad ID ಗಳನ್ನು ಹಾಕಿ ನಂತರ ಟೆಸ್ಟ್ ಮೋಡ್ ಆಫ್ ಮಾಡಿದರೆ ನಿಜವಾದ ads ತೋರಿಸುತ್ತದೆ."
                                        else
                                            "Enter your actual Unity Ad Game ID and placement IDs. Turn off Test Mode to show live ads.",
                                        fontSize = 11.sp,
                                        color = TextSecondary
                                    )

                                    Spacer(modifier = Modifier.height(12.dp))

                                    // Test Mode Switch
                                    Row(
                                        modifier = Modifier
                                            .fillMaxWidth()
                                            .background(SurfaceHighlight, RoundedCornerShape(10.dp))
                                            .padding(horizontal = 12.dp, vertical = 8.dp),
                                        horizontalArrangement = Arrangement.SpaceBetween,
                                        verticalAlignment = Alignment.CenterVertically
                                    ) {
                                        Column {
                                            Text(
                                                text = if (language == AppLanguage.KANNADA) "Test Mode (ಪರೀಕ್ಷಾ ಮೋಡ್)" else "Ad Test Mode",
                                                fontWeight = FontWeight.Bold,
                                                fontSize = 13.sp,
                                                color = TextPrimary
                                            )
                                            Text(
                                                text = if (isTestMode)
                                                    (if (language == AppLanguage.KANNADA) "ಪ್ರಸ್ತುತ: ಟೆಸ್ಟ್ Ad ಪ್ರದರ್ಶನ" else "Currently: Test Ads Active")
                                                else
                                                    (if (language == AppLanguage.KANNADA) "ಪ್ರಸ್ತುತ: ನೈಜ ಲೈವ್ Ad ಪ್ರದರ್ಶನ" else "Currently: Live Ads Active"),
                                                fontSize = 11.sp,
                                                color = if (isTestMode) PrimaryAmber else TertiaryEmerald
                                            )
                                        }

                                        Switch(
                                            checked = isTestMode,
                                            onCheckedChange = { isTestMode = it },
                                            colors = SwitchDefaults.colors(
                                                checkedThumbColor = PrimaryAmber,
                                                checkedTrackColor = SurfaceCard,
                                                uncheckedThumbColor = TertiaryEmerald,
                                                uncheckedTrackColor = SurfaceCard
                                            ),
                                            modifier = Modifier.testTag("test_mode_switch")
                                        )
                                    }

                                    Spacer(modifier = Modifier.height(10.dp))

                                    OutlinedTextField(
                                        value = gameIdInput,
                                        onValueChange = { gameIdInput = it },
                                        label = { Text("Unity Game ID (Android)") },
                                        singleLine = true,
                                        modifier = Modifier.fillMaxWidth().testTag("unity_game_id_input"),
                                        colors = OutlinedTextFieldDefaults.colors(
                                            focusedBorderColor = PrimaryAmber,
                                            unfocusedBorderColor = CardBorder,
                                            focusedTextColor = TextPrimary,
                                            unfocusedTextColor = TextPrimary
                                        )
                                    )

                                    Spacer(modifier = Modifier.height(8.dp))

                                    OutlinedTextField(
                                        value = bannerIdInput,
                                        onValueChange = { bannerIdInput = it },
                                        label = { Text("Banner Placement ID") },
                                        singleLine = true,
                                        modifier = Modifier.fillMaxWidth(),
                                        colors = OutlinedTextFieldDefaults.colors(
                                            focusedBorderColor = PrimaryAmber,
                                            unfocusedBorderColor = CardBorder,
                                            focusedTextColor = TextPrimary,
                                            unfocusedTextColor = TextPrimary
                                        )
                                    )

                                    Spacer(modifier = Modifier.height(8.dp))

                                    OutlinedTextField(
                                        value = interstitialIdInput,
                                        onValueChange = { interstitialIdInput = it },
                                        label = { Text("Interstitial Placement ID") },
                                        singleLine = true,
                                        modifier = Modifier.fillMaxWidth(),
                                        colors = OutlinedTextFieldDefaults.colors(
                                            focusedBorderColor = PrimaryAmber,
                                            unfocusedBorderColor = CardBorder,
                                            focusedTextColor = TextPrimary,
                                            unfocusedTextColor = TextPrimary
                                        )
                                    )

                                    Spacer(modifier = Modifier.height(8.dp))

                                    OutlinedTextField(
                                        value = rewardedIdInput,
                                        onValueChange = { rewardedIdInput = it },
                                        label = { Text("Rewarded Placement ID") },
                                        singleLine = true,
                                        modifier = Modifier.fillMaxWidth(),
                                        colors = OutlinedTextFieldDefaults.colors(
                                            focusedBorderColor = PrimaryAmber,
                                            unfocusedBorderColor = CardBorder,
                                            focusedTextColor = TextPrimary,
                                            unfocusedTextColor = TextPrimary
                                        )
                                    )

                                    Spacer(modifier = Modifier.height(14.dp))

                                    Button(
                                        onClick = {
                                            onUpdateConfig(
                                                adConfig.copy(
                                                    gameId = gameIdInput.trim(),
                                                    bannerPlacementId = bannerIdInput.trim(),
                                                    interstitialPlacementId = interstitialIdInput.trim(),
                                                    rewardedPlacementId = rewardedIdInput.trim(),
                                                    isTestMode = isTestMode
                                                )
                                            )
                                        },
                                        modifier = Modifier.fillMaxWidth(),
                                        colors = ButtonDefaults.buttonColors(containerColor = PrimaryAmber),
                                        shape = RoundedCornerShape(8.dp)
                                    ) {
                                        Icon(Icons.Default.Check, contentDescription = null, tint = BackgroundDeep)
                                        Spacer(modifier = Modifier.width(6.dp))
                                        Text(
                                            text = if (language == AppLanguage.KANNADA) "Ad ಸೆಟ್ಟಿಂಗ್ಸ್ ಸೇವ್ ಮಾಡಿ" else "Save Unity Ad Settings",
                                            fontWeight = FontWeight.Bold,
                                            color = BackgroundDeep
                                        )
                                    }
                                }

                                1 -> {
                                    // Add Website Links
                                    Text(
                                        text = if (language == AppLanguage.KANNADA) "ಖಾಸಗಿ ವೆಬ್‌ಸೈಟ್ ಲಿಂಕ್ ಸೇರಿಸಿ" else "Add Private Job Website Link",
                                        fontWeight = FontWeight.Bold,
                                        fontSize = 14.sp,
                                        color = SecondaryCyan
                                    )
                                    Text(
                                        text = if (language == AppLanguage.KANNADA)
                                            "ಇಲ್ಲಿ ಹಾಕುವ ವೆಬ್‌ಸೈಟ್‌ಗಳನ್ನು ಆಪ್ ದಿನಕ್ಕೆ ಒಂದು ಬಾರಿ ಪರಿಶೀಲಿಸಿ ಹೊಸ ಪೋಸ್ಟ್‌ಗಳನ್ನು ಪಬ್ಲಿಷ್ ಮಾಡುತ್ತದೆ. (ಸರ್ಕಾರಿ ವೆಬ್‌ಸೈಟ್‌ಗಳು ಸೇರಿಸಬೇಡಿ)."
                                        else
                                            "Add private job portal links. FreeJobs checks daily and indexes fresh vacancies automatically.",
                                        fontSize = 11.sp,
                                        color = TextSecondary
                                    )

                                    Spacer(modifier = Modifier.height(10.dp))

                                    Row(
                                        modifier = Modifier.fillMaxWidth(),
                                        verticalAlignment = Alignment.CenterVertically
                                    ) {
                                        OutlinedTextField(
                                            value = websiteInput,
                                            onValueChange = { websiteInput = it },
                                            placeholder = { Text("https://company.com/careers", fontSize = 12.sp) },
                                            singleLine = true,
                                            modifier = Modifier.weight(1f).testTag("custom_website_input"),
                                            colors = OutlinedTextFieldDefaults.colors(
                                                focusedBorderColor = PrimaryAmber,
                                                unfocusedBorderColor = CardBorder,
                                                focusedTextColor = TextPrimary,
                                                unfocusedTextColor = TextPrimary
                                            )
                                        )

                                        Spacer(modifier = Modifier.width(8.dp))

                                        Button(
                                            onClick = {
                                                if (websiteInput.isNotBlank()) {
                                                    onAddWebsite(websiteInput.trim())
                                                    websiteInput = ""
                                                }
                                            },
                                            colors = ButtonDefaults.buttonColors(containerColor = PrimaryAmber),
                                            shape = RoundedCornerShape(8.dp),
                                            modifier = Modifier.height(54.dp).testTag("add_website_button")
                                        ) {
                                            Icon(Icons.Default.Add, contentDescription = null, tint = BackgroundDeep)
                                        }
                                    }

                                    Spacer(modifier = Modifier.height(14.dp))

                                    Text(
                                        text = if (language == AppLanguage.KANNADA) "ಸಕ್ರಿಯ 10 ಖಾಸಗಿ ಜಾಬ್ ವೆಬ್‌ಸೈಟ್‌ಗಳು:" else "Active 10 Private Job Portals:",
                                        fontSize = 12.sp,
                                        fontWeight = FontWeight.Bold,
                                        color = TextPrimary
                                    )

                                    Spacer(modifier = Modifier.height(6.dp))

                                    val allWebsites = InitialDataSources.TEN_PRIVATE_JOB_WEBSITES + adConfig.customWebsites
                                    allWebsites.forEachIndexed { i, site ->
                                        Row(
                                            modifier = Modifier
                                                .fillMaxWidth()
                                                .padding(vertical = 4.dp)
                                                .background(SurfaceHighlight, RoundedCornerShape(6.dp))
                                                .padding(horizontal = 8.dp, vertical = 6.dp),
                                            verticalAlignment = Alignment.CenterVertically
                                        ) {
                                            Icon(Icons.Default.Link, contentDescription = null, tint = SecondaryCyan, modifier = Modifier.size(14.dp))
                                            Spacer(modifier = Modifier.width(6.dp))
                                            Text(
                                                text = "${i + 1}. $site",
                                                fontSize = 11.sp,
                                                color = TextPrimary,
                                                maxLines = 1
                                            )
                                        }
                                    }
                                }

                                2 -> {
                                    // Add YouTube Channel Links
                                    Text(
                                        text = if (language == AppLanguage.KANNADA) "YouTube ಚಾನೆಲ್ ಲಿಂಕ್ ಸೇರಿಸಿ" else "Add YouTube Channel Link",
                                        fontWeight = FontWeight.Bold,
                                        fontSize = 14.sp,
                                        color = SecondaryCyan
                                    )
                                    Text(
                                        text = if (language == AppLanguage.KANNADA)
                                            "ಇಲ್ಲಿ ನೀವು ಸೇರಿಸುವ ಚಾನೆಲ್‌ನಲ್ಲಿ ಹೊಸ job notification video ಬಂದಾಗ ತಕ್ಷಣ ಆಪ್‌ನಲ್ಲಿ ಅಪ್ಡೇಟ್ ಆಗುತ್ತದೆ."
                                        else
                                            "Add job notification channels. Videos update instantly and stream in-app after a rewarded ad.",
                                        fontSize = 11.sp,
                                        color = TextSecondary
                                    )

                                    Spacer(modifier = Modifier.height(10.dp))

                                    Row(
                                        modifier = Modifier.fillMaxWidth(),
                                        verticalAlignment = Alignment.CenterVertically
                                    ) {
                                        OutlinedTextField(
                                            value = channelInput,
                                            onValueChange = { channelInput = it },
                                            placeholder = { Text("https://youtube.com/@KannadaJobGuide", fontSize = 12.sp) },
                                            singleLine = true,
                                            modifier = Modifier.weight(1f).testTag("custom_channel_input"),
                                            colors = OutlinedTextFieldDefaults.colors(
                                                focusedBorderColor = PrimaryAmber,
                                                unfocusedBorderColor = CardBorder,
                                                focusedTextColor = TextPrimary,
                                                unfocusedTextColor = TextPrimary
                                            )
                                        )

                                        Spacer(modifier = Modifier.width(8.dp))

                                        Button(
                                            onClick = {
                                                if (channelInput.isNotBlank()) {
                                                    onAddChannel(channelInput.trim())
                                                    channelInput = ""
                                                }
                                            },
                                            colors = ButtonDefaults.buttonColors(containerColor = PrimaryAmber),
                                            shape = RoundedCornerShape(8.dp),
                                            modifier = Modifier.height(54.dp).testTag("add_channel_button")
                                        ) {
                                            Icon(Icons.Default.Add, contentDescription = null, tint = BackgroundDeep)
                                        }
                                    }

                                    Spacer(modifier = Modifier.height(14.dp))

                                    Text(
                                        text = if (language == AppLanguage.KANNADA) "ಸಕ್ರಿಯ ಪ್ರಸಿದ್ಧ 6 YouTube ಚಾನೆಲ್‌ಗಳು:" else "Active 6 YouTube Channels:",
                                        fontSize = 12.sp,
                                        fontWeight = FontWeight.Bold,
                                        color = TextPrimary
                                    )

                                    Spacer(modifier = Modifier.height(6.dp))

                                    val allChannels = InitialDataSources.SIX_YOUTUBE_CHANNELS + adConfig.customChannels
                                    allChannels.forEachIndexed { i, ch ->
                                        Row(
                                            modifier = Modifier
                                                .fillMaxWidth()
                                                .padding(vertical = 4.dp)
                                                .background(SurfaceHighlight, RoundedCornerShape(6.dp))
                                                .padding(horizontal = 8.dp, vertical = 6.dp),
                                            verticalAlignment = Alignment.CenterVertically
                                        ) {
                                            Icon(Icons.Default.VideoLibrary, contentDescription = null, tint = PrimaryAmber, modifier = Modifier.size(14.dp))
                                            Spacer(modifier = Modifier.width(6.dp))
                                            Text(
                                                text = "${i + 1}. $ch",
                                                fontSize = 11.sp,
                                                color = TextPrimary,
                                                maxLines = 1
                                            )
                                        }
                                    }
                                }

                                3 -> {
                                    // Daily Sync & 7-Day Purge Rules
                                    Text(
                                        text = if (language == AppLanguage.KANNADA) "ದೈನಂದಿನ ಪರಿಶೀಲನೆ ಮತ್ತು 7 ದಿನಗಳ ಸುತ್ತೋಲೆ ನಿಯಮ" else "Daily Sync & 7-Day Retention Window",
                                        fontWeight = FontWeight.Bold,
                                        fontSize = 14.sp,
                                        color = SecondaryCyan
                                    )

                                    Spacer(modifier = Modifier.height(8.dp))

                                    Box(
                                        modifier = Modifier
                                            .fillMaxWidth()
                                            .background(SurfaceHighlight, RoundedCornerShape(10.dp))
                                            .padding(12.dp)
                                    ) {
                                        Text(
                                            text = if (language == AppLanguage.KANNADA)
                                                "ನಿಯಮ: ಬಂದ ಮಾಹಿತಿಗಳು 7 ದಿನಗಳು ಇರುತ್ತವೆ. 8ನೇ ದಿನ ಬಂದಾಗ 1ನೇ ದಿನದ ಅಷ್ಟು ಮಾಹಿತಿಗಳು ಸ್ವಯಂಚಾಲಿತವಾಗಿ ಡಿಲೀಟ್ ಆಗುತ್ತವೆ (ಸುತ್ತೋಲೆ ಮಾದರಿ). ದಿನಕ್ಕೆ 1 ಬಾರಿ ಆಪ್ ಎಲ್ಲಾ ಮೂಲಗಳನ್ನು ತಪಾಸಣೆ ಮಾಡುತ್ತದೆ."
                                            else
                                                "Rule: Job notifications remain active for 7 days. On the 8th day, day 1 posts automatically expire and are cleared in a circular rolling queue.",
                                            fontSize = 12.sp,
                                            color = TextPrimary,
                                            lineHeight = 18.sp
                                        )
                                    }

                                    Spacer(modifier = Modifier.height(16.dp))

                                    Button(
                                        onClick = onTriggerDailySync,
                                        modifier = Modifier.fillMaxWidth().height(48.dp),
                                        colors = ButtonDefaults.buttonColors(containerColor = PrimaryAmber),
                                        shape = RoundedCornerShape(10.dp)
                                    ) {
                                        Icon(Icons.Default.Refresh, contentDescription = null, tint = BackgroundDeep)
                                        Spacer(modifier = Modifier.width(8.dp))
                                        Text(
                                            text = if (language == AppLanguage.KANNADA) "ಈಗಲೇ ದಿನದ ಪರಿಶೀಲನೆ ನಡೆಸಿ (Sync Now)" else "Check & Sync Daily Updates Now",
                                            fontWeight = FontWeight.Bold,
                                            color = BackgroundDeep
                                        )
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}
