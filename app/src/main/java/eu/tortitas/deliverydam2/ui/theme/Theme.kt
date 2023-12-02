package eu.tortitas.deliverydam2.ui.theme

import android.app.Activity
import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalView
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import androidx.core.view.WindowCompat
import eu.tortitas.deliverydam2.R

private val DarkColorScheme = darkColorScheme(
    primary = Purple80,
    secondary = PurpleGrey80,
    tertiary = Pink80
)

private val LightColorScheme = lightColorScheme(
    primary = Purple40,
    secondary = PurpleGrey40,
    tertiary = Pink40

    /* Other default colors to override
    background = Color(0xFFFFFBFE),
    surface = Color(0xFFFFFBFE),
    onPrimary = Color.White,
    onSecondary = Color.White,
    onTertiary = Color.White,
    onBackground = Color(0xFF1C1B1F),
    onSurface = Color(0xFF1C1B1F),
    */
)

@Composable
fun DeliveryDAM2Theme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    // Dynamic color is available on Android 12+
    dynamicColor: Boolean = true,
    content: @Composable () -> Unit
) {
    val colorScheme = when {
        dynamicColor && Build.VERSION.SDK_INT >= Build.VERSION_CODES.S -> {
            val context = LocalContext.current
            if (darkTheme) dynamicDarkColorScheme(context) else dynamicLightColorScheme(context)
        }

        darkTheme -> DarkColorScheme
        else -> LightColorScheme
    }
    val view = LocalView.current
    if (!view.isInEditMode) {
        SideEffect {
            val window = (view.context as Activity).window
            window.statusBarColor = colorScheme.primary.toArgb()
            WindowCompat.getInsetsController(window, view).isAppearanceLightStatusBars = darkTheme
        }
    }

    val inikaFontFamily = FontFamily(
        Font(R.font.inika_regular, FontWeight.Normal),
        Font(R.font.inika_bold, FontWeight.Bold),
    )

    val typography = MaterialTheme.typography.copy(
        titleLarge = MaterialTheme.typography.titleLarge.copy(
            fontFamily = inikaFontFamily,
            fontWeight = FontWeight.Normal,
            fontSize = 32.sp
        ),
        titleMedium = MaterialTheme.typography.titleMedium.copy(
            fontFamily = inikaFontFamily,
            fontWeight = FontWeight.Normal,
            fontSize = 20.sp,
            color = colorScheme.secondary
        ),
        titleSmall = MaterialTheme.typography.titleSmall.copy(
            fontFamily = inikaFontFamily,
            fontWeight = FontWeight.Normal
        ),
        bodyLarge = MaterialTheme.typography.bodyLarge.copy(
            fontWeight = FontWeight.Normal
        ),
        bodyMedium = MaterialTheme.typography.bodyMedium.copy(
            fontWeight = FontWeight.Normal
        ),
        bodySmall = MaterialTheme.typography.bodySmall.copy(
            fontWeight = FontWeight.Normal
        ),
    )

    MaterialTheme(
        colorScheme = colorScheme,
        typography = typography,
        content = content
    )
}