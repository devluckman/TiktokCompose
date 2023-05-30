package com.man.home.tab.following

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PagerState
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.man.core.DestinationRoute
import com.man.core.extension.Space
import com.man.data.model.ContentCreatorFollowingModel
import com.man.home.tab.following.component.CreatorCard
import com.man.theme.DarkBlue
import com.man.theme.R
import com.man.theme.SubTextColor

/**
 *
 * Created by Lukmanul Hakim on  24/05/23
 * devs.lukman@gmail.com
 */
@OptIn(ExperimentalFoundationApi::class)
@Composable
fun FollowingScreen(
    navController: NavController,
    parentPagerState: PagerState,
    viewModel: FollowingViewModel = hiltViewModel()
) {

    val viewState by viewModel.viewState.collectAsState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(DarkBlue),
    ) {
        80.Space()

        Text(
            text = stringResource(id = R.string.trending_creators),
            fontWeight = FontWeight.Bold,
            modifier = Modifier.fillMaxWidth(),
            textAlign = TextAlign.Center,
            fontSize = 20.sp
        )

        10.Space()

        Text(
            text = stringResource(id = R.string.follow_and_account_to_see),
            fontWeight = FontWeight.SemiBold,
            fontSize = 14.sp,
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 30.dp),
            textAlign = TextAlign.Center,
            color = SubTextColor
        )

        22.Space()

        if (parentPagerState.settledPage == 0) {
            viewState?.contentCreators?.let {
                VideoItem(creatorList = it, onclickUser = { userId ->
                    navController.navigate("${DestinationRoute.CREATOR_PROFILE_ROUTE}/$userId")
                })
            }
        }

    }
}


@OptIn(ExperimentalFoundationApi::class)
@Composable
fun VideoItem(
    creatorList: List<ContentCreatorFollowingModel>,
    onclickUser: (userId: Long) -> Unit
) {

    val pagerState = rememberPagerState()

    HorizontalPager(
        pageCount = creatorList.size,
        contentPadding = PaddingValues(horizontal = 54.dp),
        state = pagerState,
        beyondBoundsPageCount = 1
    ) {

        CreatorCard(
            page = it,
            pagerState = pagerState,
            item = creatorList[it],
            onClickFollow = onclickUser,
            onClickUser = {}
        )

    }
}