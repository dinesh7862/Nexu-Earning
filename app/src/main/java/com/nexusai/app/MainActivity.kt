package com.nexusai.app

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

private val Navy = Color(0xFF090D18)
private val Panel = Color(0xFF151D30)
private val Purple = Color(0xFF8B5CF6)
private val Mint = Color(0xFF5EEAD4)

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent { NexusApp() }
    }
}

@Composable
fun NexusApp() {
    var tab by remember { mutableStateOf(0) }
    var prompt by remember { mutableStateOf("") }
    var answer by remember { mutableStateOf("Hi! I'm NEXUS AI. Ask me anything to get started.") }
    var showReward by remember { mutableStateOf(false) }

    MaterialTheme(colorScheme = darkColorScheme(
        background = Navy, surface = Panel, primary = Purple, secondary = Mint
    )) {
        Scaffold(
            containerColor = Navy,
            bottomBar = {
                NavigationBar(containerColor = Color(0xFF101729), contentColor = Color.White) {
                    NavigationBarItem(selected = tab == 0, onClick = { tab = 0 },
                        icon = { Icon(Icons.Default.AutoAwesome, null) }, label = { Text("Home") })
                    NavigationBarItem(selected = tab == 1, onClick = { tab = 1 },
                        icon = { Icon(Icons.Default.Chat, null) }, label = { Text("AI Chat") })
                    NavigationBarItem(selected = tab == 2, onClick = { tab = 2 },
                        icon = { Icon(Icons.Default.AccountBalanceWallet, null) }, label = { Text("Wallet") })
                    NavigationBarItem(selected = tab == 3, onClick = { tab = 3 },
                        icon = { Icon(Icons.Default.Person, null) }, label = { Text("Profile") })
                }
            }
        ) { padding ->
            when (tab) {
                0 -> HomeScreen(padding, onChat = { tab = 1 }, onReward = { showReward = true })
                1 -> ChatScreen(padding, prompt, { prompt = it }, answer, {
                    answer = if (prompt.isBlank()) "Please type a question first." else
                        "Demo mode: connect a secure AI backend to generate a real answer. Your question was: “$prompt”"
                    prompt = ""
                })
                2 -> WalletScreen(padding)
                else -> ProfileScreen(padding)
            }
        }
        if (showReward) {
            AlertDialog(
                onDismissRequest = { showReward = false },
                title = { Text("Sponsored rewards") },
                text = { Text("Reward ads are not connected yet. In production, only verified, policy-compliant sponsored engagements can earn rewards. No guaranteed income.") },
                confirmButton = { TextButton(onClick = { showReward = false }) { Text("Got it") } },
                containerColor = Panel
            )
        }
    }
}

@Composable
fun HomeScreen(padding: PaddingValues, onChat: () -> Unit, onReward: () -> Unit) {
    LazyColumn(Modifier.fillMaxSize().padding(padding).padding(20.dp), verticalArrangement = Arrangement.spacedBy(18.dp)) {
        item {
            Text("NEXUS AI", color = Color.White, fontSize = 27.sp, fontWeight = FontWeight.ExtraBold)
            Text("Your AI companion. Your opportunities.", color = Color.LightGray, fontSize = 13.sp)
        }
        item {
            Column(Modifier.fillMaxWidth().background(Brush.linearGradient(listOf(Color(0xFF39256D), Color(0xFF142E46))), RoundedCornerShape(24.dp)).padding(22.dp)) {
                Text("SMARTER WITH AI", color = Mint, fontSize = 11.sp, fontWeight = FontWeight.Bold)
                Spacer(Modifier.height(8.dp))
                Text("What would you like to do today?", color = Color.White, fontSize = 23.sp, fontWeight = FontWeight.Bold)
                Spacer(Modifier.height(16.dp))
                Button(onClick = onChat, colors = ButtonDefaults.buttonColors(containerColor = Purple)) {
                    Icon(Icons.Default.AutoAwesome, null); Spacer(Modifier.width(8.dp)); Text("Start AI Chat")
                }
            }
        }
        item { Text("Explore tools", color = Color.White, fontSize = 18.sp, fontWeight = FontWeight.Bold) }
        item { ToolCard("AI Chat", "Ask questions and brainstorm ideas", Icons.Default.Chat, onChat) }
        item { ToolCard("AI Writer", "Draft captions, emails and scripts", Icons.Default.Edit, onChat) }
        item { ToolCard("AI Summarizer", "Turn long text into key points", Icons.Default.Article, onChat) }
        item { ToolCard("Sponsored rewards", "See eligible offers and reward rules", Icons.Default.CardGiftcard, onReward) }
        item { Text("Rewards depend on advertiser availability, eligibility and verified activity. Earnings are not guaranteed.", color = Color.Gray, fontSize = 11.sp) }
    }
}

@Composable
fun ToolCard(title: String, desc: String, icon: androidx.compose.ui.graphics.vector.ImageVector, click: () -> Unit) {
    Card(onClick = click, colors = CardDefaults.cardColors(containerColor = Panel), shape = RoundedCornerShape(18.dp), modifier = Modifier.fillMaxWidth()) {
        Row(Modifier.padding(16.dp), verticalAlignment = Alignment.CenterVertically) {
            Icon(icon, null, tint = Mint, modifier = Modifier.size(28.dp))
            Spacer(Modifier.width(14.dp))
            Column(Modifier.weight(1f)) {
                Text(title, color = Color.White, fontWeight = FontWeight.Bold)
                Text(desc, color = Color.LightGray, fontSize = 12.sp)
            }
            Icon(Icons.Default.ChevronRight, null, tint = Color.Gray)
        }
    }
}

@Composable
fun ChatScreen(padding: PaddingValues, prompt: String, onPrompt: (String) -> Unit, answer: String, send: () -> Unit) {
    Column(Modifier.fillMaxSize().padding(padding).padding(18.dp)) {
        Text("AI Assistant", color = Color.White, fontSize = 24.sp, fontWeight = FontWeight.Bold)
        Text("Your private AI workspace", color = Color.Gray, fontSize = 12.sp)
        Spacer(Modifier.height(20.dp))
        Card(colors = CardDefaults.cardColors(containerColor = Panel), shape = RoundedCornerShape(18.dp)) {
            Text(answer, Modifier.padding(18.dp), color = Color.White)
        }
        Spacer(Modifier.weight(1f))
        OutlinedTextField(value = prompt, onValueChange = onPrompt, modifier = Modifier.fillMaxWidth(),
            placeholder = { Text("Type your question...") }, shape = RoundedCornerShape(16.dp))
        Spacer(Modifier.height(10.dp))
        Button(onClick = send, modifier = Modifier.fillMaxWidth(), colors = ButtonDefaults.buttonColors(containerColor = Purple)) {
            Icon(Icons.Default.Send, null); Spacer(Modifier.width(8.dp)); Text("Send")
        }
    }
}

@Composable
fun WalletScreen(padding: PaddingValues) {
    Column(Modifier.fillMaxSize().padding(padding).padding(20.dp), verticalArrangement = Arrangement.spacedBy(16.dp)) {
        Text("My Wallet", color = Color.White, fontSize = 26.sp, fontWeight = FontWeight.Bold)
        Card(colors = CardDefaults.cardColors(containerColor = Panel), shape = RoundedCornerShape(22.dp), modifier = Modifier.fillMaxWidth()) {
            Column(Modifier.padding(22.dp)) {
                Text("Available rewards", color = Color.LightGray)
                Text("₹0.00", color = Mint, fontSize = 36.sp, fontWeight = FontWeight.ExtraBold)
                Text("Demo balance · no real payout connected", color = Color.Gray, fontSize = 11.sp)
            }
        }
        Text("Recent activity", color = Color.White, fontWeight = FontWeight.Bold)
        Text("No transactions yet.", color = Color.Gray)
        Text("Real payouts require verified users, a compliant rewards partner, minimum payout rules and fraud checks.", color = Color.Gray, fontSize = 12.sp)
    }
}

@Composable
fun ProfileScreen(padding: PaddingValues) {
    Column(Modifier.fillMaxSize().padding(padding).padding(20.dp), verticalArrangement = Arrangement.spacedBy(14.dp)) {
        Text("Profile & Settings", color = Color.White, fontSize = 25.sp, fontWeight = FontWeight.Bold)
        Text("Guest account", color = Mint)
        Text("Sign-in, privacy controls, terms, subscription and account deletion will be added with the backend.", color = Color.LightGray)
        Text("NEXUS AI · Prototype v0.1.0", color = Color.Gray, fontSize = 12.sp)
    }
}
