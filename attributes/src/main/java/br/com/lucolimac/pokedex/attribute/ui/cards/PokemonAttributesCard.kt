package br.com.lucolimac.pokedex.attribute.ui.cards

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import br.com.lucolimac.pokedex.attribute.domain.entity.Attribute
import br.com.lucolimac.pokedex.attribute.ui.component.PokemonAttribute

@Composable
fun PokemonAttributesCard(pokemonAttributes: List<Attribute>) {
    Row(horizontalArrangement = Arrangement.Absolute.SpaceEvenly) {
        repeat(pokemonAttributes.size) {
            if (it == 0) {
                PokemonAttribute(pokemonAttributes[it])
            } else {
                Box(
                    modifier = Modifier
                        .border(1.dp, Color.Gray)
                        .width(1.dp)
                        .fillMaxHeight()
                )
                PokemonAttribute(pokemonAttributes[it])
            }
        }
    }
}