package com.example.yrl.ui.theme

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.yrl.R

@Composable
fun YrlHomeScreen(modifier: Modifier = Modifier) {
    Scaffold(
        modifier = modifier,
        topBar = {
            TopAppBar(modifier = Modifier.padding(start = 50.dp, top = 12.dp))

        }) { paddingValues ->

        Column(
            modifier = Modifier
                .padding(paddingValues)
                .padding(horizontal = 28.dp)
        ) {
            CarCard(modifier = Modifier.padding(top = 20.dp))
            Row(horizontalArrangement = Arrangement.SpaceEvenly) {
                ProfileCard()
                MapCard(modifier = Modifier.padding(start = 16.dp))
            }

        }


    }
}

@Composable
fun TopAppBar(modifier: Modifier = Modifier) {
    Row(modifier = modifier, horizontalArrangement = Arrangement.SpaceBetween) {
        IconText(
            modifier = Modifier.padding(vertical = 16.dp, horizontal = 26.dp),
            imagePainter = painterResource(R.drawable.info),
            text = "Notifications"
        )
        IconText(
            modifier = Modifier.padding(vertical = 16.dp),
            imagePainter = painterResource(R.drawable.notification),
            text = "Notifications"
        )

    }
}

@Composable
fun IconText(modifier: Modifier = Modifier, imagePainter: Painter, text: String, size: Dp = 24.dp) {
    Row(modifier = modifier) {
        Image(modifier = Modifier.size(size), painter = imagePainter, contentDescription = null)
        Text(modifier = Modifier.padding(start = 12.dp), text = text)
    }
}

@Composable
fun CarCard(modifier: Modifier = Modifier) {
    Card(modifier = Modifier.padding(vertical = 16.dp)) {
        Column(modifier = Modifier.padding(vertical = 16.dp)) {
            Text(modifier = Modifier.padding(start = 8.dp), text = "NEAREST CAR")
            Image(
                modifier = Modifier.offset(x = -70.dp),
                painter = painterResource(R.drawable.car),
                contentDescription = null
            )
            Text(modifier = Modifier.padding(start = 8.dp), text = "Fortuner GR", fontSize = 20.sp)
            Row(
                modifier = Modifier.padding(vertical = 8.dp, horizontal = 8.dp),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Row {
                    IconText(imagePainter = painterResource(R.drawable.nav), text = "> 870km")
                    IconText(imagePainter = painterResource(R.drawable.pump), text = "50L")
                }

                Text("\$ 45,00/h")
            }

        }
    }
}

@Composable
fun MoreCars(modifier: Modifier = Modifier) {
    Card(modifier = Modifier.padding(vertical = 16.dp)) {
       Row {  }
      
    }
}

@Composable
fun MoreCardItem(modifier: Modifier = Modifier, text: String) {
    Row {
        Column {
            Text(text = text)
            Row {
             //   IconText(imagePainter = )
            }
        }
      //  Image()
    }
}

@Composable
fun ProfileCard(modifier: Modifier = Modifier) {
    Card(modifier = modifier) {
        Column(Modifier.padding(vertical = 27.dp, horizontal = 39.dp)) {
            Image(
                modifier = Modifier.size(73.dp),
                painter = painterResource(R.drawable.avatar),
                contentDescription = null
            )
            Text(
                modifier = Modifier.padding(top = 16.dp),
                text = "Jane Cooper",
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold
            )
            Text(
                modifier = Modifier.padding(top = 6.dp),
                text = "\$ 4,253",
                fontSize = 14.sp,
                fontWeight = FontWeight.Bold
            )

        }

    }
}

@Composable
fun MapCard(modifier: Modifier = Modifier) {
    Card(modifier = modifier) {
        Column() {
            Box() {
                Image(
                    modifier = Modifier.height(200.dp).width(151.dp),
                    painter = painterResource(R.drawable.map),
                    contentDescription = null,
                    contentScale = ContentScale.FillBounds
                )
            }
        }

    }
}

@Composable
@Preview
fun MapCardPreview(modifier: Modifier = Modifier) {
    MapCard()
}

@Composable
@Preview
fun ProfileCardPreview(modifier: Modifier = Modifier) {
    ProfileCard()
}


@Composable
@Preview
fun CarCardPreview(modifier: Modifier = Modifier) {
    CarCard(modifier = Modifier.padding(16.dp))
}