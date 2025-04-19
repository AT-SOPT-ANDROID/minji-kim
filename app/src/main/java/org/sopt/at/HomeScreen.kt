package org.sopt.at

import android.content.Intent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.material3.TopAppBar
import androidx.compose.ui.graphics.Color
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.TopAppBarDefaults.topAppBarColors
import androidx.compose.ui.Alignment
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import org.sopt.at.screen.ui.theme.ATSOPTANDROIDTheme



@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen() {
    val context = LocalContext.current
    
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Black)
    ) {

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(56.dp)
                .background(Color.Black)
                .padding(horizontal = 12.dp)
                .align(Alignment.TopCenter),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                painter = painterResource(id = R.drawable.tviing),
                contentDescription = "tving logo",
                modifier = Modifier.height(40.dp)
            )
            Row(
                horizontalArrangement = Arrangement.spacedBy(20.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Image(
                    painter = painterResource(id = R.drawable.tv),
                    contentDescription = "tv",
                    modifier = Modifier.height(28.dp)
                )
                Image(
                    painter = painterResource(id = R.drawable.ic_tving),
                    contentDescription = "profile",
                    modifier = Modifier
                        .height(22.dp)
                        .clickable {
                            val intent = Intent(context, MyActivity::class.java)
                            context.startActivity(intent)

                        }

                )
            }
        }
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(top = 56.dp)
                .background(Color.Black)
        ) {
            item{
                Spacer(modifier = Modifier.height(4.dp))
                Genre()


            }

            item{
                Spacer(modifier = Modifier.height(10.dp))
                Banner()
            }
            item {
                Spacer(modifier = Modifier.height(18.dp))
                Text(
                    text = "오늘의 티빙 TOP 20",
                    color = Color.White,
                    style = MaterialTheme.typography.titleMedium,
                    modifier = Modifier.padding(horizontal = 16.dp)
                )
            }
            item{
                Spacer(modifier = Modifier.height(8.dp))
                TodayTop()

            }
            item {
                Spacer(modifier = Modifier.height(20.dp))
                Text(
                    text = "지금 방영 중인 콘텐츠",
                    color = Color.White,
                    style = MaterialTheme.typography.titleMedium,
                    modifier = Modifier.padding(horizontal = 16.dp)
                )
            }
            item{
                Spacer(modifier = Modifier.height(8.dp))
                ContentsNow()
            }

        }
    }
}

@Composable
fun Genre() {
    val genres = listOf(
        "DRAMA", "VARIETY", "MOVIE", "SPORTS", "ANIMATION"
    )
    LazyRow(
        contentPadding = PaddingValues(horizontal = 1.dp),
        horizontalArrangement = Arrangement.spacedBy(15.dp)
    ) {
        items(genres){ genre ->
            Text(
                text = genre,
                fontSize = 17.sp,
                color = Color.White
            )
            Spacer(modifier = Modifier.height(20.dp))

        }
    }
}
@Composable
fun Banner() {
    val banners = listOf(
        R.drawable.banner1,
        R.drawable.banner1,
        R.drawable.banner1
    )

    LazyRow(
        contentPadding = PaddingValues(horizontal = 16.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        items(banners) { bannerId ->
            Image(
                painter = painterResource(id = bannerId),
                contentDescription = null,
                modifier = Modifier
                    .fillParentMaxWidth()
                    .height(455.dp)
                    .clip(RoundedCornerShape(8.dp)),
                contentScale = ContentScale.Crop
            )
        }
    }
}
data class Top20(
    val rank: Int,
    val imageId: Int
)

@Composable
fun TodayTop() {
    val topList = listOf(
        Top20(1, R.drawable.top1),
        Top20(2, R.drawable.top2),
        Top20(3, R.drawable.top2),
        Top20(4, R.drawable.top1),
        Top20(5, R.drawable.top1),
        Top20(6, R.drawable.top1),
        Top20(7, R.drawable.top1),
    )
    LazyRow(
        contentPadding = PaddingValues(horizontal = 15.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        items(topList) { topItem ->
            Row(
                horizontalArrangement = Arrangement.spacedBy(0.dp),
                verticalAlignment = Alignment.Bottom
            ) {
                Text(
                    text = topItem.rank.toString(),
                    fontSize = 65.sp,
                    color = Color.White,
                    fontWeight = FontWeight.Bold
                    )
                Image(
                    painter = painterResource(id = topItem.imageId),
                    contentDescription = null,
                    modifier = Modifier
                        .height(142.dp)
                        .width(104.dp)
                        .clip(RoundedCornerShape(8.dp))
                )
            }
        }
    }
}

@Composable
fun ContentsNow() {
    val contentsList = listOf(
        R.drawable.now1,
        R.drawable.now2,
        R.drawable.now3,
        R.drawable.now1,
        R.drawable.now1,
        R.drawable.now1,
    )
    LazyRow(
        contentPadding = PaddingValues(horizontal = 15.dp),
        horizontalArrangement = Arrangement.spacedBy(9.dp),
    ) {
        items(contentsList) { contentId ->
            Image(
                painter = painterResource(id = contentId),
                contentDescription = null,
                modifier = Modifier
                    .height(150.dp)
                    .width(102.dp)
                    .clip(RoundedCornerShape(3.dp)),
                contentScale = ContentScale.Crop
            )

        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun HomeScreenPreview() {
    ATSOPTANDROIDTheme {
        HomeScreen()
    }
}
