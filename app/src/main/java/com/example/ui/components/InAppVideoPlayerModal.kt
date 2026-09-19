package com.example.ui.components

import android.annotation.SuppressLint
import android.content.Intent
import android.graphics.Bitmap
import android.net.Uri
import android.view.View
import android.view.ViewGroup
import android.webkit.RenderProcessGoneDetail
import android.webkit.WebChromeClient
import android.webkit.WebResourceRequest
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
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
import androidx.compose.material.icons.filled.AdsClick
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.OpenInNew
import androidx.compose.material.icons.filled.PlayCircle
import androidx.compose.material.icons.filled.SmartDisplay
import androidx.compose.material.icons.filled.Verified
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedButton
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
import androidx.compose.ui.viewinterop.AndroidView
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

@SuppressLint("SetJavaScriptEnabled")
@Composable
fun InAppVideoPlayerModal(
    video: JobVideo,
    adConfig: UnityAdConfig,
    language: AppLanguage,
    onClose: () -> Unit
) {
    val context = LocalContext.current

    Dialog(
        onDismissRequest = onClose,
        properties = DialogProperties(usePlatformDefaultWidth = false)
    ) {
        Surface(
            modifier = Modifier
                .fillMaxSize()
                .background(BackgroundDeep)
                .testTag("in_app_video_player_modal"),
            color = BackgroundDeep
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .verticalScroll(rememberScrollState())
                    .padding(16.dp)
            ) {
                // Top Action Bar
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        IconButton(
                            onClick = onClose,
                            modifier = Modifier
                                .size(40.dp)
                                .clip(CircleShape)
                                .background(SurfaceHighlight)
                                .border(1.dp, CardBorder, CircleShape)
                                .testTag("close_in_app_player_button")
                        ) {
                            Icon(
                                imageVector = Icons.Default.ArrowBack,
                                contentDescription = "Back",
                                tint = TextPrimary
                            )
                        }

                        Spacer(modifier = Modifier.width(12.dp))

                        Column {
                            Row(verticalAlignment = Alignment.CenterVertically) {
                                Box(
                                    modifier = Modifier
                                        .background(SecondaryCyan.copy(alpha = 0.2f), RoundedCornerShape(4.dp))
                                        .padding(horizontal = 6.dp, vertical = 2.dp)
                                ) {
                                    Text(
                                        text = if (language == AppLanguage.KANNADA) "ಆ್ಯಪ್‌ನಲ್ಲೇ ಪ್ಲೇ ಆಗುತ್ತಿದೆ" else "In-App Direct Player",
                                        fontSize = 10.sp,
                                        fontWeight = FontWeight.Bold,
                                        color = SecondaryCyan
                                    )
                                }
                            }
                            Text(
                                text = video.getChannel(language),
                                fontSize = 12.sp,
                                color = TextSecondary,
                                fontWeight = FontWeight.Medium
                            )
                        }
                    }

                    IconButton(
                        onClick = onClose,
                        modifier = Modifier
                            .size(36.dp)
                            .clip(CircleShape)
                            .background(SurfaceHighlight)
                    ) {
                        Icon(
                            imageVector = Icons.Default.Close,
                            contentDescription = "Close",
                            tint = TextSecondary,
                            modifier = Modifier.size(18.dp)
                        )
                    }
                }

                Spacer(modifier = Modifier.height(14.dp))

                // In-App Embedded Video Player Screen
                // Requirement: "ವೀಡಿಯೋಸ್ ನನ್ನ ಅಪ್ ನಲ್ಲೇ ಪ್ಲೇ ಆಗ್ತಾ ಇಲ್ಲ ಬದಲಿಗೆ watch on youtube ಅಂತ ಬರ್ತಿದೆ... ನನ್ನ ಅಪ್ ನಲ್ಲೇ ಪ್ಲೇ ಆಗಬೇಕು"
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(230.dp)
                        .clip(RoundedCornerShape(16.dp)),
                    colors = CardDefaults.cardColors(containerColor = Color.Black),
                    border = CardDefaults.outlinedCardBorder().copy(brush = androidx.compose.ui.graphics.SolidColor(CardBorder))
                ) {
                    AndroidView(
                        factory = { ctx ->
                            WebView(ctx).apply {
                                layoutParams = ViewGroup.LayoutParams(
                                    ViewGroup.LayoutParams.MATCH_PARENT,
                                    ViewGroup.LayoutParams.MATCH_PARENT
                                )

                                // Using LAYER_TYPE_NONE prevents Mesa GPU driver from attempting to access
                                // unavailable DRI rendernodes (/dev/dri/renderD128) in virtualized/cloud emulators
                                setLayerType(View.LAYER_TYPE_NONE, null)

                                settings.javaScriptEnabled = true
                                settings.domStorageEnabled = true
                                settings.databaseEnabled = true
                                settings.mediaPlaybackRequiresUserGesture = false
                                settings.allowFileAccess = true
                                settings.allowContentAccess = true
                                settings.useWideViewPort = true
                                settings.loadWithOverviewMode = true
                                settings.userAgentString = "Mozilla/5.0 (Linux; Android 13; Mobile) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/122.0.0.0 Mobile Safari/537.36"

                                webChromeClient = object : WebChromeClient() {
                                    override fun getDefaultVideoPoster(): Bitmap? {
                                        return Bitmap.createBitmap(1, 1, Bitmap.Config.ARGB_8888)
                                    }
                                }

                                webViewClient = object : WebViewClient() {
                                    override fun shouldOverrideUrlLoading(view: WebView?, request: WebResourceRequest?): Boolean {
                                        // Keep video strictly playing inside this In-App player
                                        return false
                                    }

                                    override fun onRenderProcessGone(view: WebView?, detail: RenderProcessGoneDetail?): Boolean {
                                        // Prevents MESA rendernode or Chromium GPU crashes from killing host app
                                        try {
                                            view?.destroy()
                                        } catch (_: Throwable) {}
                                        return true
                                    }
                                }

                                val html = """
                                    <!DOCTYPE html>
                                    <html>
                                    <head>
                                        <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no">
                                        <style>
                                            * { margin: 0; padding: 0; box-sizing: border-box; }
                                            body, html { width: 100%; height: 100%; background-color: #000; overflow: hidden; display: flex; align-items: center; justify-content: center; }
                                            iframe { width: 100%; height: 100%; border: none; }
                                        </style>
                                    </head>
                                    <body>
                                        <iframe 
                                            src="https://www.youtube-nocookie.com/embed/${video.youtubeVideoId}?autoplay=1&playsinline=1&controls=1&rel=0&modestbranding=1&enablejsapi=1&origin=https://localhost" 
                                            allow="accelerometer; autoplay; clipboard-write; encrypted-media; gyroscope; picture-in-picture; web-share" 
                                            allowfullscreen>
                                        </iframe>
                                    </body>
                                    </html>
                                """.trimIndent()

                                // Using https://localhost prevents origin mismatch and removes "Watch on YouTube" warning
                                loadDataWithBaseURL("https://localhost", html, "text/html", "UTF-8", null)
                            }
                        },
                        onRelease = { webView ->
                            try {
                                webView.stopLoading()
                                webView.loadUrl("about:blank")
                                webView.onPause()
                                webView.removeAllViews()
                                webView.destroy()
                            } catch (_: Throwable) {}
                        },
                        modifier = Modifier.fillMaxSize()
                    )
                }

                Spacer(modifier = Modifier.height(14.dp))

                // Video Title and Channel info
                Text(
                    text = video.getTitle(language),
                    fontSize = 15.sp,
                    fontWeight = FontWeight.Bold,
                    color = TextPrimary,
                    lineHeight = 21.sp
                )

                Spacer(modifier = Modifier.height(6.dp))

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Icon(
                            imageVector = Icons.Default.SmartDisplay,
                            contentDescription = null,
                            tint = PrimaryAmber,
                            modifier = Modifier.size(15.dp)
                        )
                        Spacer(modifier = Modifier.width(5.dp))
                        Text(
                            text = "${video.viewsCount} views • ${video.duration}",
                            fontSize = 12.sp,
                            color = TextSecondary
                        )
                    }

                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Icon(
                            imageVector = Icons.Default.Verified,
                            contentDescription = "Verified Channel",
                            tint = TertiaryEmerald,
                            modifier = Modifier.size(14.dp)
                        )
                        Spacer(modifier = Modifier.width(4.dp))
                        Text(
                            text = if (language == AppLanguage.KANNADA) "ದೃಢೀಕೃತ ಚಾನೆಲ್" else "Verified Channel",
                            fontSize = 11.sp,
                            color = TertiaryEmerald,
                            fontWeight = FontWeight.SemiBold
                        )
                    }
                }

                Spacer(modifier = Modifier.height(8.dp))

                // Optional fallback button to open in YouTube if emulator/network restricts embedded video
                OutlinedButton(
                    onClick = {
                        try {
                            val intent = Intent(Intent.ACTION_VIEW, Uri.parse("https://www.youtube.com/watch?v=${video.youtubeVideoId}"))
                            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                            context.startActivity(intent)
                        } catch (_: Throwable) {}
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(34.dp),
                    shape = RoundedCornerShape(8.dp),
                    colors = ButtonDefaults.outlinedButtonColors(contentColor = TextSecondary),
                    border = androidx.compose.foundation.BorderStroke(0.8.dp, CardBorder)
                ) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Icon(
                            imageVector = Icons.Default.OpenInNew,
                            contentDescription = null,
                            tint = TextSecondary,
                            modifier = Modifier.size(12.dp)
                        )
                        Spacer(modifier = Modifier.width(6.dp))
                        Text(
                            text = if (language == AppLanguage.KANNADA) "ಯೂಟ್ಯೂಬ್ ಆ್ಯಪ್‌ನಲ್ಲಿ ವೀಕ್ಷಿಸಿ (Open in YouTube)" else "Open in YouTube App",
                            fontSize = 11.sp,
                            fontWeight = FontWeight.Medium,
                            color = TextSecondary
                        )
                    }
                }

                Spacer(modifier = Modifier.height(14.dp))

                // PROMINENT AD PLACEMENT IN THE EMPTY SPACE BELOW VIDEO PLAYER:
                // User Requirement: "ವಿಡಿಯೋ ಪ್ಲೇ ಆಗುವ ಜಾಗದಲ್ಲಿ ಕೆಳಗಡೆ ತುಂಬಾ ಜಾಗ ಇದೆ ಅಲ್ಲಿ ಒಂದು ad ಬರುವ ಹಾಗೆ ಮಾಡು."
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clip(RoundedCornerShape(14.dp)),
                    colors = CardDefaults.cardColors(containerColor = SurfaceCard),
                    border = CardDefaults.outlinedCardBorder().copy(
                        brush = Brush.horizontalGradient(listOf(SecondaryCyan, PrimaryAmber))
                    )
                ) {
                    Column(modifier = Modifier.padding(14.dp)) {
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Row(verticalAlignment = Alignment.CenterVertically) {
                                Box(
                                    modifier = Modifier
                                        .background(Color(0xFF002B49), RoundedCornerShape(4.dp))
                                        .border(0.5.dp, SecondaryCyan, RoundedCornerShape(4.dp))
                                        .padding(horizontal = 6.dp, vertical = 2.dp)
                                ) {
                                    Text(
                                        text = "Unity Ads Sponsor",
                                        fontSize = 10.sp,
                                        fontWeight = FontWeight.ExtraBold,
                                        color = SecondaryCyan
                                    )
                                }
                                Spacer(modifier = Modifier.width(6.dp))
                                Text(
                                    text = if (adConfig.isTestMode) "[Placement: ${adConfig.bannerPlacementId}]" else "[Unity Live Ad]",
                                    fontSize = 10.sp,
                                    color = if (adConfig.isTestMode) PrimaryAmber else TertiaryEmerald
                                )
                            }

                            Text(
                                text = "ADVERTISEMENT",
                                fontSize = 9.sp,
                                fontWeight = FontWeight.Bold,
                                color = TextSecondary
                            )
                        }

                        Spacer(modifier = Modifier.height(10.dp))

                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Box(
                                modifier = Modifier
                                    .size(46.dp)
                                    .clip(RoundedCornerShape(10.dp))
                                    .background(PrimaryAmber.copy(alpha = 0.2f)),
                                contentAlignment = Alignment.Center
                            ) {
                                Icon(
                                    imageVector = Icons.Default.AdsClick,
                                    contentDescription = "Ad Icon",
                                    tint = PrimaryAmber,
                                    modifier = Modifier.size(24.dp)
                                )
                            }

                            Spacer(modifier = Modifier.width(12.dp))

                            Column(modifier = Modifier.weight(1f)) {
                                Text(
                                    text = if (language == AppLanguage.KANNADA) 
                                        "ಕರ್ನಾಟಕ ಖಾಸಗಿ ಕಂಪನಿಗಳ ಉಚಿತ ಇಂಟರ್ವ್ಯೂ & ಆಪ್ಟಿಟ್ಯೂಡ್ ಕೋರ್ಸ್"
                                    else 
                                        "Free Aptitude & Corporate Interview Prep for Freshers",
                                    fontSize = 13.sp,
                                    fontWeight = FontWeight.Bold,
                                    color = TextPrimary
                                )
                                Spacer(modifier = Modifier.height(2.dp))
                                Text(
                                    text = if (language == AppLanguage.KANNADA)
                                        "ಇನ್ಫೋಸಿಸ್, ಟಿಸಿಎಸ್, ವಿಪ್ರೋ ಪರೀಕ್ಷೆಗಳಿಗೆ 100% ಉಚಿತ ನೋಂದಣಿ ಲಭ್ಯವಿದೆ"
                                    else
                                        "Sponsored by Karnataka Career Academy • Zero Registration Fee",
                                    fontSize = 11.sp,
                                    color = TextSecondary
                                )
                            }
                        }

                        Spacer(modifier = Modifier.height(12.dp))

                        Button(
                            onClick = { /* Simulated Unity ad action */ },
                            modifier = Modifier.fillMaxWidth().height(38.dp),
                            shape = RoundedCornerShape(8.dp),
                            colors = ButtonDefaults.buttonColors(containerColor = PrimaryAmber)
                        ) {
                            Row(verticalAlignment = Alignment.CenterVertically) {
                                Text(
                                    text = if (language == AppLanguage.KANNADA) "ಉಚಿತವಾಗಿ ನೋಂದಾಯಿಸಿ (Enroll Free)" else "Enroll Free Now",
                                    color = BackgroundDeep,
                                    fontSize = 12.sp,
                                    fontWeight = FontWeight.Bold
                                )
                                Spacer(modifier = Modifier.width(6.dp))
                                Icon(
                                    imageVector = Icons.Default.OpenInNew,
                                    contentDescription = null,
                                    tint = BackgroundDeep,
                                    modifier = Modifier.size(14.dp)
                                )
                            }
                        }
                    }
                }

                Spacer(modifier = Modifier.height(14.dp))

                // Standard Unity Banner Ad at the bottom of the player card
                UnityBannerAd(
                    adConfig = adConfig,
                    language = language,
                    index = 2,
                    modifier = Modifier.fillMaxWidth().testTag("video_player_bottom_banner_ad")
                )

                Spacer(modifier = Modifier.height(16.dp))
            }
        }
    }
}
