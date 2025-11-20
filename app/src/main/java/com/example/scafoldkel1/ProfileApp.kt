@file:OptIn(ExperimentalMaterial3Api::class)

package com.example.profileapp

import androidx.compose.material3.ExperimentalMaterial3Api

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.launch
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.sp
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign

// ---------- Sample Data ----------
private val studentData = mapOf(
    "name" to "KELOMPOK 1",
    "nim" to "123456789",
    "program" to "Sistem dan Teknologi Informasi",
    "email" to "kelompoksatujaya@example.com",
    "phone" to "+62 823-1622-1818"
)

private val skillsData: List<Pair<String, ImageVector>> = listOf(
    "Kotlin" to Icons.Default.Code,
    "Compose" to Icons.Default.PhoneAndroid,
    "Android" to Icons.Default.Android,
    "UI/UX" to Icons.Default.DesignServices,
    "Web" to Icons.Default.Language
)

// Optional ratings 0f 0..1
private val sampleRatings = listOf(0.9f, 0.75f, 0.8f, 0.6f, 0.7f)

// ---------- Biodata Kelompok Data ----------
data class Anggota(
    val nama: String,
    val nim: String,
    val latar: String
)

private val anggotaKelompok = listOf(
    Anggota("Atha", "220101001", "Fokus pada mobile programming dan UI Compose."),
    Anggota("Arya Ardy", "220101002", "Ahli dalam backend API dan integrasi server."),
    Anggota("Hikmawan", "220101003", "Spesialis testing, debugging, dan QA."),
    Anggota("Syakila", "220101004", "Mendesain UI/UX dan dokumentasi sistem.")
)

// ---------- Top-level App ----------
@Composable
fun ProfileApp() {
    var selectedScreen by remember { mutableStateOf(0) } // 0:About,1:Skills,2:Contact,3:Biodata
    var isDarkTheme by remember { mutableStateOf(false) }

    com.example.profileapp.ui.theme.ProfileAppTheme(darkTheme = isDarkTheme) {
        val themeColors = MaterialTheme.colorScheme

        Scaffold(
            topBar = {
                SmallTopAppBar(title = { Text("My Profile") })
            },
            bottomBar = {
                NavigationBar {
                    NavigationBarItem(
                        icon = { Icon(Icons.Default.Person, contentDescription = "About") },
                        label = { Text("About") },
                        selected = selectedScreen == 0,
                        onClick = { selectedScreen = 0 }
                    )
                    NavigationBarItem(
                        icon = { Icon(Icons.Default.Build, contentDescription = "Skills") },
                        label = { Text("Skills") },
                        selected = selectedScreen == 1,
                        onClick = { selectedScreen = 1 }
                    )
                    NavigationBarItem(
                        icon = { Icon(Icons.Default.MailOutline, contentDescription = "Contact") },
                        label = { Text("Contact") },
                        selected = selectedScreen == 2,
                        onClick = { selectedScreen = 2 }
                    )
                    NavigationBarItem(
                        icon = { Icon(Icons.Default.Group, contentDescription = "Biodata") },
                        label = { Text("Biodata") },
                        selected = selectedScreen == 3,
                        onClick = { selectedScreen = 3 }
                    )
                }
            },
            floatingActionButton = {
                if (selectedScreen == 0) {
                    FloatingActionButton(
                        onClick = { isDarkTheme = !isDarkTheme },
                        containerColor = themeColors.primary
                    ) {
                        Icon(
                            Icons.Default.Brightness4,
                            contentDescription = "Toggle Theme",
                            tint = themeColors.onPrimary
                        )
                    }
                }
            }
        ) { innerPadding ->
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(innerPadding)
                    .padding(16.dp)
            ) {
                when (selectedScreen) {
                    0 -> AboutScreen()
                    1 -> SkillsScreen()
                    2 -> ContactScreen()
                    3 -> BiodataKelompokScreen()
                }
            }
        }
    }
}

// ---------- About Screen ----------
@Composable
fun AboutScreen() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState()),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.height(8.dp))

        Box(
            modifier = Modifier
                .size(140.dp)
                .clip(CircleShape)
                .background(MaterialTheme.colorScheme.primary.copy(alpha = 0.15f)),
            contentAlignment = Alignment.Center
        ) {
            Icon(
                imageVector = Icons.Default.Person,
                contentDescription = "Profile placeholder",
                modifier = Modifier.size(80.dp),
                tint = MaterialTheme.colorScheme.primary
            )
        }

        Spacer(modifier = Modifier.height(12.dp))

        Text(
            text = studentData["name"] ?: "-",
            style = MaterialTheme.typography.titleLarge.copy(fontWeight = FontWeight.Bold),
            textAlign = TextAlign.Center
        )
        Text("NIM: ${studentData["nim"] ?: "-"}", style = MaterialTheme.typography.bodyMedium)
        Text(studentData["program"] ?: "-", style = MaterialTheme.typography.bodyMedium)

        Spacer(modifier = Modifier.height(16.dp))

        Card(
            modifier = Modifier.fillMaxWidth(),
            elevation = CardDefaults.cardElevation(6.dp)
        ) {
            Column(modifier = Modifier.padding(12.dp)) {
                Text("Biodata Singkat", style = MaterialTheme.typography.titleMedium)
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    "• Latar singkat: Kelompok terbaik dari mata kuliah Pengembangan Aplikas Mobile\n" +
                            "• Objective: Meratakan semua kelompok lain!!!"
                )
            }
        }

        Spacer(modifier = Modifier.height(12.dp))

        Column(modifier = Modifier.fillMaxWidth()) {
            InfoRow(label = "Email", value = studentData["email"] ?: "-")
            InfoRow(label = "Phone", value = studentData["phone"] ?: "-")
            InfoRow(label = "Location", value = "Indonesia")
        }

        Spacer(modifier = Modifier.height(24.dp))
    }
}

@Composable
private fun InfoRow(label: String, value: String) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 6.dp)
    ) {
        Text(
            text = "$label:",
            modifier = Modifier.width(80.dp),
            fontWeight = FontWeight.SemiBold
        )
        Text(text = value, maxLines = 2, overflow = TextOverflow.Ellipsis)
    }
}

// ---------- Skills Screen ----------
@Composable
fun SkillsScreen() {
    LazyColumn(
        modifier = Modifier.fillMaxSize(),
        contentPadding = PaddingValues(vertical = 8.dp)
    ) {
        itemsIndexed(skillsData) { index, pair ->
            val (name, icon) = pair
            val rating = sampleRatings.getOrNull(index) ?: 0.5f

            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp),
                elevation = CardDefaults.cardElevation(4.dp)
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(12.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(icon, contentDescription = name, modifier = Modifier.size(36.dp))

                    Spacer(modifier = Modifier.width(12.dp))

                    Column(modifier = Modifier.weight(1f)) {
                        Text(name, style = MaterialTheme.typography.titleMedium)
                        Spacer(modifier = Modifier.height(6.dp))
                        LinearProgressIndicator(
                            progress = rating,
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(8.dp)
                                .clip(MaterialTheme.shapes.small)
                        )
                        Spacer(modifier = Modifier.height(4.dp))
                        Text("${(rating * 100).toInt()}%", style = MaterialTheme.typography.bodySmall)
                    }
                }
            }
        }
    }
}

// ---------- Contact Screen ----------
@Composable
fun ContactScreen() {
    val snackbarHostState = remember { SnackbarHostState() }
    val scope = rememberCoroutineScope()

    Scaffold(snackbarHost = { SnackbarHost(snackbarHostState) }) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(12.dp)
        ) {
            Text("Contact", style = MaterialTheme.typography.titleLarge)
            Spacer(modifier = Modifier.height(12.dp))

            Card(modifier = Modifier.fillMaxWidth()) {
                Column(modifier = Modifier.padding(12.dp)) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Icon(Icons.Default.Email, contentDescription = "email")
                        Spacer(modifier = Modifier.width(8.dp))
                        Text(studentData["email"] ?: "-")
                    }
                    Spacer(modifier = Modifier.height(8.dp))
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Icon(Icons.Default.Phone, contentDescription = "phone")
                        Spacer(modifier = Modifier.width(8.dp))
                        Text(studentData["phone"] ?: "-")
                    }
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            Button(
                onClick = {
                    scope.launch { snackbarHostState.showSnackbar("Message sent!") }
                },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Send Message")
            }

            Spacer(modifier = Modifier.height(16.dp))

            Text("Social Media", style = MaterialTheme.typography.titleMedium)
            Spacer(modifier = Modifier.height(8.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                SocialIconButton(icon = Icons.Default.Facebook, label = "Facebook") {}
                SocialIconButton(icon = Icons.Default.ChatBubble, label = "Twitter") {}
            }
        }
    }
}

@Composable
private fun SocialIconButton(icon: ImageVector, label: String, onClick: () -> Unit) {
    Column(
        modifier = Modifier
            .size(84.dp)
            .clickable { onClick() }
            .background(MaterialTheme.colorScheme.primary.copy(alpha = 0.08f), shape = MaterialTheme.shapes.small),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Icon(icon, contentDescription = label, modifier = Modifier.size(32.dp))
        Spacer(modifier = Modifier.height(6.dp))
        Text(label, fontSize = 12.sp)
    }
}

// ---------- Biodata Kelompok Screen ----------
@Composable
fun BiodataKelompokScreen() {
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(12.dp)
    ) {
        item {
            Text("Biodata Kelompok", style = MaterialTheme.typography.titleLarge)
            Spacer(modifier = Modifier.height(12.dp))
        }

        items(anggotaKelompok) { anggota ->
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 6.dp),
                elevation = CardDefaults.cardElevation(4.dp)
            ) {
                Column(modifier = Modifier.padding(12.dp)) {
                    Text(anggota.nama, style = MaterialTheme.typography.titleMedium, fontWeight = FontWeight.Bold)
                    Spacer(modifier = Modifier.height(4.dp))
                    Text("NIM: ${anggota.nim}")
                    Spacer(modifier = Modifier.height(4.dp))
                    Text("Latar singkat: ${anggota.latar}")
                }
            }
        }
    }
}
