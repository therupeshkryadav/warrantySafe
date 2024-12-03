package com.warrantysafe.app.presentation.profile

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.warrantysafe.app.R


@Composable
fun ProfileScreen(navController: NavController) {

    var fullName by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(start = 8.dp, end = 8.dp, top = 8.dp)
    ) {
        //profile_avatarcontainer
        Box(
            modifier = Modifier
                .size(244.dp)
                .clip(shape = CircleShape)
                .align(alignment = Alignment.CenterHorizontally)
                .background(color = colorResource(R.color.black))
        ) {
            Image(
                painter = painterResource(R.drawable.profile_avatar),
                modifier = Modifier
                    .size(240.dp)
                    .align(Alignment.Center)
                    .clip(shape = CircleShape),
                contentScale = ContentScale.Crop,
                contentDescription = null
            )
        }

        //Name,Email,Phone Number,change password container
        Box(
            modifier = Modifier
                .fillMaxWidth(1f)
        ) {
            Column(
                modifier = Modifier.fillMaxWidth(1f)
            ) {

                Box(
                    modifier = Modifier
                        .padding(vertical = 8.dp)
                ) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(48.dp)
                    ) {
                        Text(
                            modifier = Modifier
                                .fillMaxHeight(1f)
                                .padding(vertical = 12.dp),
                            text = "Email:",
                            fontSize = 16.sp,
                            color = colorResource(id = R.color.black)
                        )

                        Box(
                            modifier = Modifier
                                .fillMaxWidth()
                                .fillMaxHeight(1f)
                                .border(width = 1.dp, color = colorResource(R.color.black))
                        ) {
                            Text(
                                modifier = Modifier
                                    .fillMaxHeight(1f)
                                    .fillMaxWidth()
                                    .padding(all = 8.dp),
                                text = "yourEmail@domain.com",
                                textAlign = TextAlign.Start,
                                fontSize = 16.sp,
                                color = colorResource(id = R.color.purple_500)
                            )
                        }

                    }
                }

                Box(
                    modifier = Modifier
                        .padding(vertical = 8.dp)
                ) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(48.dp)
                    ) {
                        Text(
                            modifier = Modifier
                                .fillMaxHeight(1f)
                                .padding(vertical = 12.dp),
                            text = "Phone:",
                            fontSize = 16.sp,
                            color = colorResource(id = R.color.black)
                        )

                        Box(
                            modifier = Modifier
                                .fillMaxWidth()
                                .fillMaxHeight(1f)
                                .border(width = 1.dp, color = colorResource(R.color.black))
                        ) {
                            Text(
                                modifier = Modifier
                                    .fillMaxHeight(1f)
                                    .fillMaxWidth()
                                    .padding(all = 8.dp),
                                text = "XXXXXXXXXX",
                                textAlign = TextAlign.Start,
                                fontSize = 16.sp,
                                color = colorResource(id = R.color.purple_500)
                            )
                        }

                    }
                }

                Box(
                    modifier = Modifier
                        .padding(vertical = 8.dp)
                ) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(48.dp)
                    ) {
                        Text(
                            modifier = Modifier
                                .fillMaxHeight(1f)
                                .padding(vertical = 12.dp),
                            text = "Name:",
                            fontSize = 16.sp,
                            color = colorResource(id = R.color.black)
                        )

                        Box(
                            modifier = Modifier
                                .fillMaxWidth()
                                .fillMaxHeight(1f)
                                .border(width = 1.dp, color = colorResource(R.color.black))
                        ) {
                            Text(
                                modifier = Modifier
                                    .fillMaxHeight(1f)
                                    .fillMaxWidth()
                                    .padding(all = 8.dp),
                                text = "Intellectual Person",
                                textAlign = TextAlign.Start,
                                fontSize = 16.sp,
                                color = colorResource(id = R.color.purple_500)
                            )
                        }

                    }
                }

                Box(
                    modifier = Modifier
                        .padding(vertical = 8.dp)
                        .fillMaxWidth()
                        .border(width = 1.dp, color = colorResource(R.color.purple_500))
                ) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth(1f)
                            .height(48.dp)
                            .background(color = colorResource(R.color.purple_700)),
                        horizontalArrangement = Arrangement.Center
                    ) {
                        Text(
                            modifier = Modifier
                                .fillMaxHeight(1f)
                                .padding(vertical = 11.dp)
                                .padding(end = 4.dp)
                                .align(Alignment.CenterVertically),
                            text = "Change Your Password",
                            fontSize = 18.sp,
                            color = colorResource(id = R.color.white)
                        )
                        Icon(
                            modifier = Modifier
                                .width(24.dp)
                                .fillMaxHeight(1f)
                                .padding(vertical = 9.dp),
                            painter = painterResource(R.drawable.fast_forward),
                            tint = colorResource(R.color.white),
                            contentDescription = null
                        )
                    }

                }
            }
        }

    }

}

@Preview(showBackground = true)
@Composable
fun ProfilePreview() {
    ProfileScreen(rememberNavController())
}