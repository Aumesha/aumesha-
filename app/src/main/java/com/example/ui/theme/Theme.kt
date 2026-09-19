package com.example.ui.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.runtime.Composable

private val FreeJobsColorScheme = darkColorScheme(
    primary = PrimaryAmber,
    onPrimary = BackgroundDeep,
    primaryContainer = SurfaceHighlight,
    onPrimaryContainer = PrimaryAmberLight,
    secondary = SecondaryCyan,
    onSecondary = BackgroundDeep,
    tertiary = TertiaryEmerald,
    onTertiary = BackgroundDeep,
    background = BackgroundDeep,
    onBackground = TextPrimary,
    surface = SurfaceContainer,
    onSurface = TextPrimary,
    surfaceVariant = SurfaceCard,
    onSurfaceVariant = TextSecondary,
    outline = CardBorder
)

@Composable
fun MyApplicationTheme(
    darkTheme: Boolean = true,
    dynamicColor: Boolean = false,
    content: @Composable () -> Unit
) {
    MaterialTheme(
        colorScheme = FreeJobsColorScheme,
        typography = Typography,
        content = content
    )
}
