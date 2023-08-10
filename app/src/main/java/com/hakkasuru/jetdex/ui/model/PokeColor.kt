package com.hakkasuru.jetdex.ui.model

import android.content.Context
import androidx.compose.ui.graphics.Color
import androidx.core.content.ContextCompat
import com.hakkasuru.jetdex.R

enum class PokeColor {
    RED,
    GREEN,
    BLUE,
    LIGHT_BLUE,
    YELLOW,
    BROWN,
    LIGHT_BROWN,
    PURPLE,
    LIGHT_PURPLE,
    OLIVE,
    PINK,
    BLACK
}

fun PokeColor.toColor(context: Context): Color {
    val colorInt =  when(this) {
        PokeColor.RED -> ContextCompat.getColor(context, R.color.poke_red)
        PokeColor.GREEN -> ContextCompat.getColor(context, R.color.poke_teal)
        PokeColor.BLUE -> ContextCompat.getColor(context, R.color.poke_blue)
        PokeColor.LIGHT_BLUE -> ContextCompat.getColor(context, R.color.poke_light_blue)
        PokeColor.YELLOW -> ContextCompat.getColor(context, R.color.poke_yellow)
        PokeColor.BROWN -> ContextCompat.getColor(context, R.color.poke_brown)
        PokeColor.LIGHT_BROWN -> ContextCompat.getColor(context, R.color.poke_light_brown)
        PokeColor.PURPLE -> ContextCompat.getColor(context, R.color.poke_purple)
        PokeColor.LIGHT_PURPLE -> ContextCompat.getColor(context, R.color.poke_light_purple)
        PokeColor.OLIVE -> ContextCompat.getColor(context, R.color.poke_olive)
        PokeColor.PINK -> ContextCompat.getColor(context, R.color.poke_pink)
        PokeColor.BLACK -> ContextCompat.getColor(context, R.color.poke_black)
    }

    return Color(colorInt)
}