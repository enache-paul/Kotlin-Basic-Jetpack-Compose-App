package com.example.mobilesecurityproject.screens

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import coil.compose.rememberImagePainter
import coil.size.Scale
import coil.transform.CircleCropTransformation
import com.example.mobilesecurityproject.api.model.Wizard
import com.example.mobilesecurityproject.viewmodels.MainViewModel

@Composable
fun MainScreen(
    navController: NavController,
    viewModel : MainViewModel = viewModel()
) {
    Scaffold(topBar = { AppBar() }) {
        contentPadding ->

        WizardsList(
            listWizards = viewModel.wizardListResponse,
            modifier = Modifier.padding(contentPadding)
        )
    }
}

@Composable
fun WizardsList(listWizards: List<Wizard>, modifier: Modifier) {
    LazyColumn(
        modifier = modifier
    ) {
        itemsIndexed(items = listWizards) {index, item->
            WizardItem(wizard = item)
        }
    }
}

@Composable
fun WizardItem(wizard: Wizard) {
    Card(
        modifier = Modifier
            .padding(7.dp, 3.dp)
            .fillMaxWidth()
            .height(110.dp), shape = RoundedCornerShape(8.dp), elevation = 4.dp
    ) {
        Surface {

            Row(
                Modifier
                    .padding(4.dp)
                    .fillMaxSize()
            ) {

                Image(
                    painter = rememberImagePainter(
                        data = wizard.image,

                        builder = {
                            scale(Scale.FILL)
                            placeholder(coil.compose.base.R.drawable.notification_action_background)
                            transformations(CircleCropTransformation())

                        }
                    ),
                    contentDescription = wizard.name,
                    modifier = Modifier
                        .fillMaxHeight()
                        .weight(0.2f)
                )


                Column(
                    verticalArrangement = Arrangement.Center,
                    modifier = Modifier
                        .padding(5.dp)
                        .fillMaxHeight()
                        .weight(0.9f)
                ) {
                    Text(
                        text = wizard.name,
                        style = MaterialTheme.typography.subtitle2,
                        fontWeight = FontWeight.Bold
                    )
                    Text(
                        text = checkIfStringIsNull(wizard.dateOfBirth),
                        style = MaterialTheme.typography.caption,
                        modifier = Modifier
                            .background(
                                Color.LightGray
                            )
                            .padding(5.dp)
                    )
                    Text(
                        text = wizard.species,
                        style = MaterialTheme.typography.body1,
                        overflow = TextOverflow.Ellipsis
                    )

                    Text(
                        text = aliveOrDead(wizard.alive),
                        style = MaterialTheme.typography.body2,
                        overflow = TextOverflow.Ellipsis,
                        fontWeight = FontWeight.ExtraBold
                    )

                }
            }
        }
    }

}
fun checkIfStringIsNull(item : String?) : String{
    return item ?: "not available"
}

fun aliveOrDead(isAlive : Boolean) : String {
    return if (isAlive) "Alive" else "Dead"
}