package org.sopt.at

import android.content.Intent
import androidx.compose.foundation.ExperimentalFoundationApi
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
import androidx.compose.material3.TabRow
import androidx.compose.material3.TopAppBarDefaults.topAppBarColors
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberUpdatedState
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
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import org.sopt.at.model.Top20
import org.sopt.at.screen.ui.theme.ATSOPTANDROIDTheme
import org.sopt.at.screen.ui.theme.BackgroundColor
import org.sopt.at.screen.ui.theme.GenreSelectedColor
import org.sopt.at.screen.ui.theme.GenreUnselectedColor
import org.sopt.at.viewmodel.HomeViewModel


@OptIn(ExperimentalMaterial3Api::class, ExperimentalFoundationApi::class)
@Composable
fun HomeScreen(viewModel: HomeViewModel = viewModel(), navController: NavController) {
    
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(56.dp)
                .background(MaterialTheme.colorScheme.background)
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
                            navController.navigate("my")
                        }
                )
            }
        }
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(top = 56.dp)
                .background(MaterialTheme.colorScheme.background)
        ) {
            stickyHeader {
                Row {
                    viewModel.genres.forEach {genre ->
                        Text(
                            text = genre,
                            style = MaterialTheme.typography.bodyLarge,
                            color = if (genre == viewModel.selectedGenre)
                                MaterialTheme.colorScheme.primary
                            else
                                MaterialTheme.colorScheme.onBackground,
                            modifier = Modifier
                                .clickable { viewModel.onGenreSelected(genre) }
                        )
                    }
                }

            }
            item {
                Spacer(modifier = Modifier.height(5.dp))
                LazyRow(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 8.dp)
                ) {
                    items(viewModel.currentContents) { imageId ->
                        Image(
                            painter = painterResource(id = imageId),
                            contentDescription = null,
                            modifier = Modifier
                                .width(365.dp)
                                .height(440.dp)
                                .padding(horizontal = 5.dp)
                                .clip(RoundedCornerShape(12.dp))
                        )
                    }
                }
            }
            item {
                Spacer(modifier = Modifier.height(18.dp))
                Text(
                    text = "오늘의 티빙 TOP 20",
                    color = MaterialTheme.colorScheme.onBackground,
                    style = MaterialTheme.typography.titleLarge,
                    modifier = Modifier.padding(horizontal = 16.dp)
                )
            }
            item{
                Spacer(modifier = Modifier.height(8.dp))
                TodayTop(viewModel.topList)

            }
            item {
                Spacer(modifier = Modifier.height(20.dp))
                Text(
                    text = "지금 방영 중인 콘텐츠",
                    color = MaterialTheme.colorScheme.onBackground,
                    style = MaterialTheme.typography.titleLarge,
                    modifier = Modifier.padding(horizontal = 16.dp)
                )
            }
            item{
                Spacer(modifier = Modifier.height(8.dp))
                ContentsNow(viewModel.contentsList)
            }

        }
    }
}


@Composable
fun TodayTop(topList: List<Top20>) {

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
                    style = MaterialTheme.typography.headlineLarge,
                    color = MaterialTheme.colorScheme.onBackground,
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
fun ContentsNow(contentsList: List<Int>) {

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

/*@Composable
fun Genre(genres: List<String>) {
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
fun Banner(banners: List<Int>) {

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
}*/


