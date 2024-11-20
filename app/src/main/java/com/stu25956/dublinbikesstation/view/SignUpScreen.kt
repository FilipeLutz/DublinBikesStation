package com.stu25956.dublinbikesstation.view

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.navigation.NavHostController
import com.google.firebase.auth.FirebaseAuth
import com.stu25956.dublinbikesstation.R
import com.stu25956.dublinbikesstation.auth.AuthHelper
import com.stu25956.dublinbikesstation.ui.theme.Black
import com.stu25956.dublinbikesstation.ui.theme.Transparent
import kotlinx.coroutines.launch

@Composable
fun SignUpScreen(navController: NavHostController) {
    // Local variables
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var confirmPassword by remember { mutableStateOf("") }
    var emailError by remember { mutableStateOf("") }
    var passwordError by remember { mutableStateOf("") }
    var confirmPasswordError by remember { mutableStateOf("") }
    var passwordVisible by remember { mutableStateOf(false) }
    var confirmPasswordVisible by remember { mutableStateOf(false) }
    var showPasswordGuidelines by remember { mutableStateOf(false) }
    var emailErrorDialogVisible by remember { mutableStateOf(false) }
    var passwordErrorDialogVisible by remember { mutableStateOf(false) }
    var confirmPasswordErrorDialogVisible by remember { mutableStateOf(false) }
    var successDialogVisible by remember { mutableStateOf(false) }
    val scope = rememberCoroutineScope()

    // Email validation regular expression
    val emailRegex = Regex("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$")
    // Password validation regular expression
    val passwordRegex = Regex(
        "^(?=.*[A-Z])(?=.*[0-9])(?=.*[!@#\$%^&*()_+\\-=<>?]).{8,}$")

    // Display the logo
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(330.dp)
            .padding(16.dp),
        contentAlignment = Alignment.Center
    ) {
        Image(
            painter = painterResource(
                id = R.drawable.bikeslogo),
            contentDescription = "App Logo",
            modifier = Modifier
                .size(150.dp)
                .padding(bottom = 16.dp)
        )
    }

    // Main Column
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Spacer(modifier = Modifier.height(20.dp))

        // Title
        Text(
            text = "Create Account",
            style = TextStyle(
                fontSize = 32.sp,
                fontWeight = FontWeight.Bold
            ),
            modifier = Modifier
                .padding(bottom = 16.dp)
        )

        // Box to contain sign-up components
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            // Column layout to arrange sign-up components vertically
            Column {

                // Create Email field
                OutlinedTextField(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp),
                    value = email,
                    onValueChange = { email = it; emailError = "" },
                    placeholder = {
                        Text(
                            "Email",
                            fontSize = 20.sp,
                            color = Color.Gray
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

                Spacer(modifier = Modifier.height(12.dp))

                // Password field with Show/Hide Icon
                OutlinedTextField(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp)
                        // Show password guidelines dialog when password field is focused
                        .onFocusChanged {
                            showPasswordGuidelines = it.isFocused
                        },
                    value = password,
                    onValueChange = { password = it; passwordError = "" },
                    placeholder = {
                        Text(
                            "Create Password",
                            fontSize = 20.sp,
                            color = Color.Gray
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
                    // Toggle visibility transformation based on passwordVisible state
                    visualTransformation =
                    if (passwordVisible) VisualTransformation.None
                    else PasswordVisualTransformation(),
                    // Show/Hide icon for the password field
                    trailingIcon = {
                        IconButton(onClick = { passwordVisible = !passwordVisible }) {
                            // Show/Hide icon
                            Icon(
                                painter = painterResource(
                                    id =
                                    if (passwordVisible) R.drawable.visible
                                    else R.drawable.invisible
                                ),
                                // Show/Hide icon content description
                                contentDescription =
                                if (passwordVisible) "Hide Password"
                                else "Show Password",
                                modifier = Modifier.size(27.dp)
                            )
                        }
                    }
                )

                Spacer(modifier = Modifier.height(12.dp))

                // Confirm Password field with Show/Hide Icon
                OutlinedTextField(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp),
                    value = confirmPassword,
                    // Reset confirm password error state
                    onValueChange = { confirmPassword = it; confirmPasswordError = "" },
                    // Placeholder text for confirm password field
                    placeholder = {
                        Text(
                            "Confirm Password",
                            fontSize = 20.sp,
                            color = Color.Gray
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
                    // Toggle visibility transformation based on confirmPasswordVisible state
                    visualTransformation =
                    if (confirmPasswordVisible) VisualTransformation.None
                    else PasswordVisualTransformation(),
                    // Show/Hide icon for the confirm password field
                    trailingIcon = {
                        IconButton(
                            onClick = { confirmPasswordVisible = !confirmPasswordVisible }) {
                            // Show/Hide icon
                            Icon(
                                painter = painterResource(
                                    id =
                                    if (confirmPasswordVisible) R.drawable.visible
                                    else R.drawable.invisible
                                ),
                                // Show/Hide icon content description
                                contentDescription =
                                if (confirmPasswordVisible) "Hide Password"
                                else "Show Password",
                                modifier = Modifier.size(27.dp)
                            )
                        }
                    }
                )
            }
        }

        Spacer(modifier = Modifier.height(15.dp))

        // Sign Up Button
        Button(
            modifier = Modifier
                .width(310.dp)
                .height(50.dp),
            onClick = {
                scope.launch {
                    // Reset all error states
                    emailError = if (!emailRegex.matches(email)) {
                        "Invalid email address."
                    } else {
                        ""
                    }

                    passwordError = if (!passwordRegex.matches(password)) {
                        "Password must be at least 8 characters, include an uppercase letter, a number, and a special character."
                    } else {
                        ""
                    }

                    confirmPasswordError = if (password != confirmPassword) {
                        "Passwords do not match."
                    } else {
                        ""
                    }

                    // Show dialogs based on errors
                    if (emailError.isNotEmpty()) {
                        emailErrorDialogVisible = true
                    } else if (passwordError.isNotEmpty()) {
                        passwordErrorDialogVisible = true
                    } else if (confirmPasswordError.isNotEmpty()) {
                        confirmPasswordErrorDialogVisible = true
                    } else {
                        // Proceed with signup if no errors
                        try {
                            val error = AuthHelper.signUp(email, password)
                            if (error == null) {
                                // Send email verification
                                val user = FirebaseAuth.getInstance().currentUser
                                user?.sendEmailVerification()?.addOnCompleteListener { task ->
                                    if (task.isSuccessful) {
                                        // Show success dialog
                                        successDialogVisible = true
                                    } else {
                                        // Handle email verification error
                                    }
                                }
                            } else {
                                // Handle sign-up exception
                            }
                        } catch (e: Exception) {
                            // Handle sign-up exception
                        }
                    }
                }
            }
        ) {
            // Sign Up Button Text
            Text(
                "SIGN UP",
                fontSize = 22.sp,
                fontWeight = FontWeight.Bold
            )
        }

        Spacer(modifier = Modifier.height(30.dp))

        // Already have an account? Log in link
        Row(
            horizontalArrangement = Arrangement.Center,
            modifier = Modifier.fillMaxWidth()
        ) {
            // Already have an account? text
            Text(
                text = "Already have an account? ",
                fontSize = 20.sp,
                fontWeight = FontWeight.SemiBold
            )
            // Log in link
            Text(
                text = "Login",
                fontSize = 20.sp,
                color = MaterialTheme.colorScheme.primary,
                fontWeight = FontWeight.Bold,
                modifier = Modifier
                    .clickable { navController.navigate("login") }
            )
        }

        // Success Dialog
        if (successDialogVisible) {
            AlertDialog(
                // Dismiss the dialog when clicked outside the dialog
                onDismissRequest = { successDialogVisible = false },
                title = {
                    // Success dialog title
                    Text(
                        text = "Sign Up Successful",
                        fontSize = 28.sp,
                        fontWeight = FontWeight.Bold
                    )
                },
                // Display the success message
                text = {
                    Text(
                        text = "A confirmation email has been sent to $email. Please verify your email to complete the registration process.",
                        fontSize = 19.sp
                    )
                },
                // OK button to dismiss the dialog
                confirmButton = {
                    TextButton(
                        onClick = {
                        successDialogVisible = false
                        navController.navigate("login")
                        }
                    ) {
                        Text(
                            text = "OK",
                            fontSize = 25.sp,
                            color = MaterialTheme.colorScheme.primary
                        )
                    }
                }
            )
        }

        // Error Dialogs

        // Email Error Dialog
        if (emailErrorDialogVisible) {
            AlertDialog(
                // Dismiss the dialog when clicked outside the dialog
                onDismissRequest = { emailErrorDialogVisible = false },
                title = { Text(
                    text = "Error",
                    fontSize = 25.sp,
                    fontWeight = FontWeight.Bold
                    )
                },
                // Display the error message
                text = {
                    Text(emailError,
                        fontSize = 22.sp,
                        color = Color.Red
                    )
                },
                // OK button to dismiss the dialog
                confirmButton = {
                    TextButton(
                        onClick = { emailErrorDialogVisible = false })
                    {
                        Text(
                            "OK",
                            color = MaterialTheme.colorScheme.primary,
                            fontSize = 22.sp,
                        )
                    }
                }
            )
        }

        // Password Error Dialog
        if (passwordErrorDialogVisible) {
            AlertDialog(
                // Dismiss the dialog when clicked outside the dialog
                onDismissRequest = { passwordErrorDialogVisible = false },
                title = { Text(
                    text = "Error",
                    fontSize = 25.sp,
                    fontWeight = FontWeight.Bold
                    )
                },
                // Display the error message
                text = {
                    Text(passwordError,
                    fontSize = 18.sp,
                    color = Color.Red
                    )
                },
                // OK button to dismiss the dialog
                confirmButton = {
                    TextButton(
                        onClick = { passwordErrorDialogVisible = false })
                    {
                        Text(
                            "OK",
                            color = MaterialTheme.colorScheme.primary,
                            fontSize = 22.sp
                        )
                    }
                }
            )
        }

        // Confirm Password Error Dialog
        if (confirmPasswordErrorDialogVisible) {
            AlertDialog(
                // Dismiss the dialog when clicked outside the dialog
                onDismissRequest = { confirmPasswordErrorDialogVisible = false },
                title = { Text(
                    text = "Error",
                    fontSize = 25.sp,
                    fontWeight = FontWeight.Bold
                    )
                },
                // Display the error message
                text = { Text(
                    confirmPasswordError,
                    fontSize = 22.sp,
                    color = Color.Red
                    )
                },
                // OK button to dismiss the dialog
                confirmButton = {
                    TextButton(
                        onClick = { confirmPasswordErrorDialogVisible = false })
                    {
                        Text(
                            "OK",
                            color = MaterialTheme.colorScheme.primary,
                            fontSize = 22.sp,
                        )
                    }
                }
            )
        }

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
    }
}