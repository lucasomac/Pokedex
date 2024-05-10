package br.com.lucolimac.pokedex.attribute.ui.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import br.com.lucolimac.pokedex.attribute.domain.entity.Attribute

@Composable
fun PokemonAttribute(attribute: Attribute) {
    Column(
        modifier = Modifier
            .padding(vertical = 16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Row(horizontalArrangement = Arrangement.Center) {
            Image(
                painter = painterResource(id = attribute.icon),
                contentDescription = attribute.name
            )
            Box(modifier = Modifier.width(8.dp))
            Text(attribute.value, textAlign = TextAlign.Center)
        }
        Text(attribute.name, color = Color.Gray)
    }
}

@Preview
@Composable
fun PokemonAttributePreview() {
    PokemonAttribute(
        Attribute(
            com.google.android.material.R.drawable.abc_ic_menu_overflow_material,
            "65",
            "Height"
        )
    )
}