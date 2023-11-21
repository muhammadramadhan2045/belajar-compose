package com.example.jetcoffee

import CategoryItem
import HomeSection
import MenuItem
import Search
import SectionText
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.AlertDialogDefaults.containerColor
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.jetcoffee.model.BottomBarItem
import com.example.jetcoffee.model.Menu
import com.example.jetcoffee.model.dummyBestSellerMenu
import com.example.jetcoffee.model.dummyCategory
import com.example.jetcoffee.model.dummyMenu
import com.example.jetcoffee.ui.theme.JetCoffeeTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            JetCoffeeTheme {
                JetCoffeeApp()
            }
        }
    }
}

@Composable
fun JetCoffeeApp(modifier: Modifier = Modifier) {
    Scaffold(
     bottomBar = {
         BottomBar()
     },
    ){it
        Column(
            modifier = Modifier.verticalScroll(rememberScrollState()).padding(it)
        ) {
            Banner()
            HomeSection(
                title = stringResource(id = R.string.section_category),
                content = {
                    CategoryRow()
                },
            )
            HomeSection(
                title = stringResource(id = R.string.section_favorite_menu),
                content = {
                    MenuRow(listMenu = dummyMenu)
                },
            )
            HomeSection(
                title = stringResource(id = R.string.section_best_seller_menu),
                content = {
                    MenuRow(listMenu = dummyBestSellerMenu)
                },
            )
        }
    }
}

@Preview(showBackground = true, name = "Banner")
@Composable
fun Banner(
    modifier: Modifier = Modifier
) {
    Box(modifier = modifier) {
        Image(
            painter = painterResource(id = R.drawable.banner),
            contentDescription = "ImageBanner",
            contentScale = ContentScale.Crop,
            modifier = Modifier.height(160.dp),
        )
        Search()
    }
}

@Composable
fun CategoryRow(
    modifier: Modifier = Modifier
) {
    LazyRow(
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        contentPadding = PaddingValues(horizontal = 16.dp),
        modifier = modifier,
    ) {
        items(dummyCategory, key = { it.textCategory }) { category ->
            CategoryItem(category)
        }
    }
}

@Composable
fun MenuRow(
    listMenu: List<Menu>,
    modifier: Modifier = Modifier
) {
    LazyRow(
        horizontalArrangement = Arrangement.spacedBy(16.dp),
        contentPadding = PaddingValues(horizontal = 16.dp),
        modifier = modifier,
    ) {
        items(listMenu, key = { it.name }) { menu ->
            MenuItem(menu)
        }
    }
}

@Composable
fun BottomBar(

    modifier: Modifier = Modifier
) {
    NavigationBar(
        containerColor= MaterialTheme.colorScheme.background,
        modifier = modifier,
    ) {
        val navigationItems = listOf(
            BottomBarItem(
                icon = Icons.Default.Home,
                title = stringResource(id = R.string.menu_home)
            ),
            BottomBarItem(
                icon = Icons.Default.Favorite,
                title = stringResource(id = R.string.menu_favorite)
            ),
            BottomBarItem(
                icon = Icons.Default.AccountCircle,
                title = stringResource(id = R.string.menu_profile)
            ),
        )
        navigationItems.map {

            NavigationBarItem(
                icon = {
                    Icon(
                        imageVector = it.icon,
                        contentDescription = it.title,
                    )
                },
                label = {
                    Text(it.title)
                },
                selected = it.title == navigationItems[0].title,
                onClick = {

                },
                modifier = modifier
            )

        }
    }
}

@Preview(showBackground = true)
@Composable
fun JetCoffeePreview() {
    JetCoffeeTheme {
        JetCoffeeApp()
    }
}