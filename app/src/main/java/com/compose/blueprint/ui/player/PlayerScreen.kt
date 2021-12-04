package com.compose.blueprint.ui.player

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.GridCells
import androidx.compose.foundation.lazy.LazyVerticalGrid
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import coil.compose.rememberImagePainter
import com.compose.blueprint.base.AppState
import com.compose.blueprint.data.model.Player


/**
 * Jai Khambhayta
 */



@ExperimentalMaterialApi
@ExperimentalFoundationApi
@Composable
fun PlayerScreen(navController: NavController,teamName : String) {
    val viewModel: PlayerScreenViewModel = hiltViewModel()

    Scaffold(topBar = {
        TopAppBar(
            title = {
                Text(text = teamName, style = TextStyle(color = Color.White, fontWeight = FontWeight(500), fontSize = 22.sp))
            },
            navigationIcon = {
                IconButton(onClick = {
                    navController.popBackStack()
                }) {
                    Icon(imageVector = Icons.Filled.ArrowBack, tint = Color.White, contentDescription = "")
                }
            },
            backgroundColor = MaterialTheme.colors.primary,
            contentColor = Color.Gray,
            elevation = 2.dp
        )
    }
    ) {
        Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
            when (viewModel.state.value) {
                is AppState.Loading -> {
                    CircularProgressIndicator()
                }
                is AppState.Success -> {
                    ListPlayers(
                        (viewModel.state.value as AppState.Success).any as List<Player>,
                    )
                }
                is AppState.Error -> {
                    // show error message here
                    Text("Something went wrong")
                }
            }
        }
    }


}



@ExperimentalFoundationApi
@ExperimentalMaterialApi
@Composable
fun ListPlayers(list: List<Player>) {
    if (list.isEmpty()) {
        Text(text = "No data available")
    } else {
        LazyVerticalGrid(cells = GridCells.Fixed(3), Modifier.padding(bottom = 60.dp)) {
            items(list.size) {
                Column(
                    modifier = Modifier.padding(10.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    PlayerCard(list[it])
                }
            }
        }
    }

}

@ExperimentalMaterialApi
@Composable
fun PlayerCard(player: Player) {
    Card(
        modifier = Modifier.height(200.dp),
        shape = RoundedCornerShape(10.dp),
        backgroundColor = Color.White,
        elevation = 10.dp,
        onClick = {
            // navigate to view screen pass the country name in argument

        }
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(5.dp)
        ) {
            //set image on background
            Image(
                modifier = Modifier.fillMaxSize(),
                painter = rememberImagePainter(
                    data = player.image_path,
                    builder = { crossfade(true) }),
                contentDescription = null
            )
            // set name on bottom
            Column(
                modifier = Modifier.fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Bottom
            ) {
                //Your composable elements
                Text(
                    text = player.firstname.plus(" ${player.lastname}"),
                    style = TextStyle(
                        color = Color.Black,
                        fontStyle = FontStyle.Normal,
                        fontSize = 18.sp,
                        fontWeight = FontWeight(500),
                    ),
                    textAlign = TextAlign.Center
                )
            }
        }
    }
}
