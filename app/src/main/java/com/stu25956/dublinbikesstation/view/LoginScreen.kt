package com.stu25956.dublinbikesstation.view

import android.widget.Toast
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.stu25956.dublinbikesstation.R
import com.stu25956.dublinbikesstation.auth.AuthHelper
import com.stu25956.dublinbikesstation.ui.theme.Black
import com.stu25956.dublinbikesstation.ui.theme.Red
import com.stu25956.dublinbikesstation.ui.theme.Transparent
import com.stu25956.dublinbikesstation.ui.theme.White
import kotlinx.coroutines.launch

// Composable function to display the login screen
@Composable
fun LoginScreen(navController: NavHostController) {
    // State variables
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var showErrorDialog by remember { mutableStateOf(false) }
    var errorMessage by remember { mutableStateOf("") }
    var passwordVisible by remember { mutableStateOf(false) }
    //var showPasswordGuidelines by remember { mutableStateOf(false) }
    val scope = rememberCoroutineScope()
    val context = LocalContext.current

    // Logo Display
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(330.dp)
            .padding(16.dp),
        contentAlignment = Alignment.Center
    ) {
        Image(
            painter = painterResource(id = R.drawable.bikeslogo),
            contentDescription = "App Logo",
            modifier = Modifier
                .size(150.dp)
                .padding(bottom = 16.dp)
        )
    }

    // Login Form
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // Form Box
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            // Form Login Fields
            Column {
                // Email Input Field
                OutlinedTextField(
                    modifier = Modifier.fillMaxWidth(),
                    value = email,
                    onValueChange = { email = it },
                    label = {
                        Text(
                            "Email",
                            fontSize = 20.sp
                        )
                    },
                    textStyle = TextStyle(
                        fontSize = 20.sp
                    ),
                    colors = TextFieldDefaults
                        .colors(
                        focusedContainerColor = Transparent,
                        unfocusedContainerColor = Transparent,
                        focusedIndicatorColor = Black,
                        unfocusedIndicatorColor = Black,
                        focusedTextColor = Black,
                        unfocusedTextColor = Black
                    )
                )

                Spacer(modifier = Modifier.height(7.dp))

                // Password Input Field
                OutlinedTextField(
                    modifier = Modifier
                        .fillMaxWidth(),
                        /*// Show password guidelines dialog when password field is focused
                        .onFocusChanged {
                            showPasswordGuidelines = it.isFocused
                        }*/
                    value = password,
                    onValueChange = { password = it },
                    label = {
                        Text(
                            "Password",
                            fontSize = 20.sp
                        )
                    },
                    textStyle = TextStyle(
                        fontSize = 20.sp
                    ),
                    colors = TextFieldDefaults
                        .colors(
                        focusedContainerColor = Transparent,
                        unfocusedContainerColor = Transparent,
                        focusedIndicatorColor = Black,
                        unfocusedIndicatorColor = Black,
                        focusedTextColor = Black,
                        unfocusedTextColor = Black
                    ),
                    // Password visibility toggle
                    visualTransformation =
                    if (passwordVisible) VisualTransformation.None
                    else PasswordVisualTransformation(),
                    trailingIcon = {
                        IconButton(
                            onClick = { passwordVisible = !passwordVisible }) {
                            // Password visibility icon
                            Icon(
                                painter = painterResource(
                                    id =
                                    if (passwordVisible) R.drawable.visible
                                    else R.drawable.invisible
                                ),
                                // Icon content description
                                contentDescription =
                                if (passwordVisible) "Hide Password"
                                else "Show Password",
                                modifier = Modifier.size(27.dp),
                                tint = Color.Unspecified
                            )
                        }
                    }
                )

                Spacer(modifier = Modifier.height(12.dp))

                // Forgot Password Link
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.End
                ) {
                    Text(
                        text = "Forgot Password?",
                        color = MaterialTheme.colorScheme.primary,
                        fontSize = 18.sp,
                        fontWeight = FontWeight.SemiBold,
                        modifier = Modifier
                            .padding(end = 5.dp)
                            .clickable {
                            // launch coroutine to send password reset email
                            scope.launch {
                                if (email.isNotEmpty()) {
                                    val error = AuthHelper.sendPasswordResetEmail(email)
                                    if (error == null) {
                                        Toast.makeText(context,
                                            "Reset password sent to $email", Toast.LENGTH_LONG).show()
                                    } else {
                                        Toast.makeText(context,
                                            "Error: $error", Toast.LENGTH_LONG).show()
                                    }
                                } else {
                                    Toast.makeText(context,
                                        "Please enter your email address", Toast.LENGTH_LONG).show()
                                }
                            }
                        }
                    )
                }
            }
        }

        Spacer(modifier = Modifier.height(10.dp))

        // Column Login Button
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.width(310.dp)
        ) {
            // Login Button
            Button(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(50.dp),
                onClick = {
                    if (!android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
                        // Show error dialog if email is invalid
                        errorMessage = "Please enter a valid email address."
                        showErrorDialog = true
                        return@Button
                    }

                    scope.launch {
                        val error = AuthHelper.logIn(email, password)
                        if (error == null) {
                            navController.navigate("stationList")
                        } else {
                            // Show error dialog with login error
                            errorMessage = error
                            showErrorDialog = true
                        }
                    }
                }
            ) {
                Text(
                    "LOG IN",
                    fontSize = 22.sp
                )
            }

            Spacer(modifier = Modifier.height(20.dp))

            // Sign Up Button
            OutlinedButton(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(50.dp),
                onClick = { navController.navigate("signup") },
                border = BorderStroke(1.dp, Color.Black),
                colors = ButtonDefaults.outlinedButtonColors(
                    contentColor = Color.Black,
                    containerColor = White
                )
            ) {
                Text(
                    "CREATE ACCOUNT",
                    fontSize = 20.sp
                )
            }
        }
    }

    // Error Dialog
    if (showErrorDialog) {
        AlertDialog(
            onDismissRequest = { showErrorDialog = false },
            confirmButton = {
                Button(
                    onClick = { showErrorDialog = false }
                ) {
                    Text("OK")
                }
            },
            title = {
                Text(
                    "Error",
                    fontSize = 28.sp,
                    fontWeight = FontWeight.Bold
                )
            },
            text = {
                Text(
                    errorMessage,
                    fontSize = 16.sp,
                    color = Red) }
        )
    }
    /*
    // Password Guidelines Dialog
    if (showPasswordGuidelines) {
        Dialog(
            // Dismiss the dialog when clicked outside the dialog
            onDismissRequest = { showPasswordGuidelines = false }) {
            // Password guidelines box
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(
                        MaterialTheme.colorScheme.surface,
                        RoundedCornerShape(16.dp)
                    )
                    .padding(16.dp)
            ) {
                // Password guidelines column
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    // Password guidelines title
                    Text(
                        modifier = Modifier
                            .padding(7.dp),
                        text = "Password must contain :",
                        fontSize = 22.sp,
                        fontWeight = FontWeight.SemiBold,
                        color = MaterialTheme.colorScheme.primary
                    )

                    Spacer(modifier = Modifier.height(8.dp))
                    // Password guidelines text
                    Text(
                        text =
                        """
                            • At least 8 characters
                            • 1 uppercase letter
                            • 1 number
                            • 1 special symbol (#, $, *)
                            """
                            .trimIndent(),
                        fontSize = 21.sp,
                        fontWeight = FontWeight.SemiBold,
                        color = MaterialTheme.colorScheme.onSurface
                    )

                    Spacer(modifier = Modifier.height(8.dp))
                    // OK button to dismiss the dialog
                    Button(
                        onClick = { showPasswordGuidelines = false },
                        modifier = Modifier
                            .padding(8.dp)

                    ) {
                        Text(
                            "OK",
                            fontSize = 18.sp,)
                    }
                }
            }
        }
    }
    */
}