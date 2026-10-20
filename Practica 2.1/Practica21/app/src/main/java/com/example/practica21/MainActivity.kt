
package com.example.practica21

import android.app.Activity
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults.topAppBarColors
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.practica21.ui.theme.Practica21Theme
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        setContent {
            Practica21Theme {
                ViewContainer()
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ViewContainer() {

    val intentContext = LocalContext.current

    val snackbarHostState = remember {
        SnackbarHostState()
    }

    val scope = rememberCoroutineScope()

    val messages = remember {
        listOf("K onda papus", "hola tilines", "tralalerito", "pupupupupupuca ", "Dulce amoooooooooooooor")
    }

    var plusCounter by rememberSaveable {
        mutableStateOf(0)
    }

    Scaffold(
        modifier = Modifier.fillMaxSize(),

        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = stringResource(R.string.home_screen)
                    )
                },
                colors = topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.secondary,
                    titleContentColor = Color.White
                ),
                navigationIcon = {
                    IconButton(
                        onClick = {
                            (intentContext as? Activity)?.finish()
                        }
                    ) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = stringResource(
                                R.string.back_btn_desc
                            ),
                            tint = Color.White
                        )
                    }
                }
            )
        },

        bottomBar = {
            BottomAppBar(
                containerColor = MaterialTheme.colorScheme.secondary,
                contentColor = Color.White
            ) {
                Text(
                    text = stringResource(R.string.bottom_app_bar),
                    modifier = Modifier.fillMaxWidth(),
                    textAlign = TextAlign.Center,
                    color = Color.White,
                    fontSize = 18.sp
                )
            }
        },

        floatingActionButton = {
            FloatingActionButton(
                onClick = {
                    val message = messages[plusCounter]

                    plusCounter = (plusCounter + 1) % messages.size

                    snackbarHostState.currentSnackbarData?.dismiss()

                    scope.launch {
                        snackbarHostState.showSnackbar(message)
                    }
                }
            ) {
                Icon(
                    imageVector = Icons.Default.Add,
                    contentDescription = stringResource(R.string.home_fab_text)
                )
            }
        },

        snackbarHost = {
            SnackbarHost(
                hostState = snackbarHostState
            )
        }
    ) { innerPadding ->

        ContentView(
            modifier = Modifier.padding(innerPadding)
        )
    }
}

@Composable
fun ContentView(modifier: Modifier = Modifier) {

    var counter by rememberSaveable {
        mutableStateOf(0)
    }

    LazyColumn(
        modifier = modifier
            .fillMaxSize()
            .background(Color.LightGray),
        contentPadding = androidx.compose.foundation.layout.PaddingValues(
            8.dp
        )
    ) {

        item {
            Image(
                painter = painterResource(
                    id = R.drawable.garupu
                ),
                contentDescription = "Ilustración de Android",
                modifier = Modifier
                    .fillMaxWidth()
                    .height(300.dp),
                contentScale = ContentScale.Fit
            )
        }

        item {
            androidx.compose.foundation.layout.Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(
                        start = 16.dp,
                        top = 12.dp,
                        bottom = 12.dp
                    ),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Image(
                    painter = painterResource(
                        id = R.drawable.twotone_add_reaction_24
                    ),
                    contentDescription = "Botón de reacción",
                    modifier = Modifier
                        .size(32.dp)
                        .clickable {
                            counter++
                        }
                )

                Text(
                    text = counter.toString(),
                    fontSize = 20.sp,
                    color = Color.White,
                    modifier = Modifier.padding(start = 12.dp)
                )
            }
        }

        item {
            Text(
                text = stringResource(R.string.materia),
                fontSize = 24.sp,
                color = Color.White,
                modifier = Modifier.fillMaxWidth(),
                textAlign = TextAlign.Center
            )

            Text(
                text = stringResource(R.string.nombre),
                fontSize = 20.sp,
                color = Color.White,
                modifier = Modifier.fillMaxWidth(),
                textAlign = TextAlign.Center
            )

            Text(
                text = stringResource(R.string.apellido),
                fontSize = 20.sp,
                color = Color.White,
                modifier = Modifier.fillMaxWidth(),
                textAlign = TextAlign.Center
            )
        }

        item {
            LazyRow(
                horizontalArrangement = Arrangement.spacedBy(8.dp),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(
                        top = 12.dp,
                        bottom = 12.dp
                    )
            ) {
                item {
                    Image(
                        painter = painterResource(
                            id = R.drawable.garu
                        ),
                        contentDescription = "Garu",
                        modifier = Modifier
                            .size(200.dp)
                            .padding(5.dp),
                        contentScale = ContentScale.Fit
                    )
                }

                item {
                    Image(
                        painter = painterResource(
                            id = R.drawable.pucagaru
                        ),
                        contentDescription = "Garuxpuca",
                        modifier = Modifier
                            .size(200.dp)
                            .padding(5.dp),
                        contentScale = ContentScale.Fit
                    )
                }

                item {
                    Image(
                        painter = painterResource(
                            id = R.drawable.pupuca
                        ),
                        contentDescription = "garu",
                        modifier = Modifier
                            .size(200.dp)
                            .padding(5.dp),
                        contentScale = ContentScale.Fit
                    )
                }

                item {
                    Image(
                        painter = painterResource(
                            id = R.drawable.pucabuu
                        ),
                        contentDescription = "pucagaru",
                        modifier = Modifier
                            .size(200.dp)
                            .padding(5.dp),
                        contentScale = ContentScale.Fit
                    )
                }
            }
        }
    }
}

@Preview(
    showBackground = true,
    device = "spec:width=411dp,height=891dp,orientation=portrait",
    locale = "es-rMX"
)
@Composable
fun PreviewViewContainer() {
    Practica21Theme {
        ViewContainer()
    }
}
