package com.example.project2.dashboard

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material.icons.outlined.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

// --- Colors ---
val BgColor = Color(0xFF0F172A) // Dark navy background
val CardColor = Color(0xFF1E293B) // Slightly lighter navy for cards
val TextPrimary = Color(0xFFF8FAFC) // White-ish
val TextSecondary = Color(0xFF94A3B8) // Grey-ish
val BlueAccent = Color(0xFF3B82F6)
val GreenAccent = Color(0xFF10B981)
val OrangeAccent = Color(0xFFF59E0B)
val RedAccent = Color(0xFFEF4444)
val DividerColor = Color(0xFF334155)

// --- Data Models ---
data class TaskProgress(
    val title: String,
    val subTitle: String,
    val progress: Float,
    val status: String,
    val dueDate: String,
    val icon: ImageVector,
    val color: Color
)

data class Meeting(
    val subject: String,
    val assignee: String,
    val date: String
)

data class Member(
    val name: String,
    val color: Color
)

// --- Dummy Data ---
val tasks = listOf(
    TaskProgress("Literature Review", "Research Paper", 0.75f, "In Progress", "Due Oct 18", Icons.Outlined.MenuBook, BlueAccent),
    TaskProgress("Group Presentation", "Slide Deck", 0.40f, "Pending", "Due Oct 21", Icons.Outlined.Group, OrangeAccent),
    TaskProgress("Exam Prep", "Biology Quiz", 0.90f, "Almost Done", "Due Oct 25", Icons.Outlined.Assignment, GreenAccent),
    TaskProgress("Data Analysis", "Final Project", 0.15f, "Not Started", "Due Nov 2", Icons.Outlined.PieChart, RedAccent)
)

val meetings = listOf(
    Meeting("Data Science Workshop", "Prof. Aris", "Oct 16 - 2 PM"),
    Meeting("Project Sync", "Group A", "Oct 19 - 4 PM"),
    Meeting("Thesis Advisory", "Sarah J.", "Oct 22 - 11 AM"),
    Meeting("Weekly Check-in", "Dr. Chen", "Oct 24 - 10 AM")
)

val members = listOf(
    Member("Ben\nCarter", BlueAccent),
    Member("Sarah\nJohnson", OrangeAccent),
    Member("Michael\nLee", GreenAccent),
    Member("Aisha\nKhan", Color(0xFFE879F9)), // Pink
    Member("David\nChen", GreenAccent),
    Member("Maria\nGarcia", RedAccent)
)


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Dashboard() {
    Scaffold(
        containerColor = BgColor,
        bottomBar = { BottomNavigationBar() }
    ) { paddingValues ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(horizontal = 16.dp),
            verticalArrangement = Arrangement.spacedBy(24.dp)
        ) {
            item { TopHeader() }
            item { TaskProgressSection() }
            item { MeetingsSection() }
            item { MembersSection() }
            item { Spacer(modifier = Modifier.height(16.dp)) }
        }
    }
}

@Composable
fun TopHeader() {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(CardColor, RoundedCornerShape(bottomStart = 24.dp, bottomEnd = 24.dp))
            .padding(top = 48.dp, bottom = 24.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "EDU-GUIDE",
            color = TextPrimary,
            fontSize = 28.sp,
            fontWeight = FontWeight.Bold,
            letterSpacing = 1.sp
        )
        Spacer(modifier = Modifier.height(8.dp))
        Icon(
            imageVector = Icons.Default.KeyboardArrowDown,
            contentDescription = "Expand",
            tint = TextPrimary
        )
    }
}

@Composable
fun TaskProgressSection() {
    Column {
        Text(
            text = "Task Progress",
            color = TextPrimary,
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(bottom = 12.dp)
        )
        // 2x2 Grid
        Column(verticalArrangement = Arrangement.spacedBy(12.dp)) {
            Row(horizontalArrangement = Arrangement.spacedBy(12.dp)) {
                TaskCard(task = tasks[0], modifier = Modifier.weight(1f))
                TaskCard(task = tasks[1], modifier = Modifier.weight(1f))
            }
            Row(horizontalArrangement = Arrangement.spacedBy(12.dp)) {
                TaskCard(task = tasks[2], modifier = Modifier.weight(1f))
                TaskCard(task = tasks[3], modifier = Modifier.weight(1f))
            }
        }
    }
}

@Composable
fun TaskCard(task: TaskProgress, modifier: Modifier = Modifier) {
    Card(
        modifier = modifier,
        colors = CardDefaults.cardColors(containerColor = CardColor),
        shape = RoundedCornerShape(16.dp)
    ) {
        Column(modifier = Modifier.padding(12.dp)) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Icon(
                    imageVector = task.icon,
                    contentDescription = null,
                    tint = task.color,
                    modifier = Modifier.size(16.dp)
                )
                Spacer(modifier = Modifier.width(8.dp))
                Text(
                    text = task.title,
                    color = TextPrimary,
                    fontSize = 12.sp,
                    fontWeight = FontWeight.Medium,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )
            }
            Spacer(modifier = Modifier.height(12.dp))
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(text = task.subTitle, color = TextSecondary, fontSize = 11.sp, maxLines = 1, overflow = TextOverflow.Ellipsis)
                Text(
                    text = "${(task.progress * 100).toInt()}%",
                    color = task.color,
                    fontSize = 11.sp,
                    fontWeight = FontWeight.Bold
                )
            }
            Spacer(modifier = Modifier.height(6.dp))
            CustomProgressBar(progress = task.progress, color = task.color)
            Spacer(modifier = Modifier.height(12.dp))
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(text = task.status, color = task.color, fontSize = 10.sp)
                Text(text = task.dueDate, color = TextSecondary, fontSize = 10.sp)
            }
        }
    }
}

@Composable
fun CustomProgressBar(progress: Float, color: Color) {
    Canvas(
        modifier = Modifier
            .fillMaxWidth()
            .height(6.dp)
    ) {
        val cornerRadius = CornerRadius(3.dp.toPx(), 3.dp.toPx())
        // Background track
        drawRoundRect(
            color = Color(0xFF334155),
            size = size,
            cornerRadius = cornerRadius
        )
        // Progress fill
        drawRoundRect(
            color = color,
            size = Size(width = size.width * progress, height = size.height),
            cornerRadius = cornerRadius
        )
    }
}

@Composable
fun MeetingsSection() {
    Column {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "Meetings",
                color = TextPrimary,
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold
            )
            // Assuming "View All" belongs to Meetings header based on UI
            Button(
                onClick = { },
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF334155)),
                shape = RoundedCornerShape(8.dp),
                contentPadding = PaddingValues(horizontal = 12.dp, vertical = 4.dp),
                modifier = Modifier.height(32.dp)
            ) {
                Text(text = "View All", color = TextPrimary, fontSize = 12.sp)
            }
        }

        Spacer(modifier = Modifier.height(12.dp))

        Card(
            colors = CardDefaults.cardColors(containerColor = CardColor),
            shape = RoundedCornerShape(16.dp),
            modifier = Modifier.fillMaxWidth()
        ) {
            Column(modifier = Modifier.padding(16.dp)) {
                Text(
                    text = "Upcoming Meetings",
                    color = TextPrimary,
                    fontSize = 14.sp,
                    fontWeight = FontWeight.SemiBold
                )
                Spacer(modifier = Modifier.height(16.dp))

                // Table Header
                Row(modifier = Modifier.fillMaxWidth()) {
                    Text("SUBJECT", color = TextSecondary, fontSize = 10.sp, modifier = Modifier.weight(2f))
                    Text("ASSIGNEE", color = TextSecondary, fontSize = 10.sp, modifier = Modifier.weight(1.5f))
                    Text("DATE", color = TextSecondary, fontSize = 10.sp, modifier = Modifier.weight(1.5f))
                }
                Spacer(modifier = Modifier.height(8.dp))
                Divider(color = DividerColor, thickness = 1.dp)

                // Table Rows
                meetings.forEach { meeting ->
                    Spacer(modifier = Modifier.height(12.dp))
                    Row(modifier = Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically) {
                        Text(meeting.subject, color = TextPrimary, fontSize = 12.sp, modifier = Modifier.weight(2f), maxLines = 1, overflow = TextOverflow.Ellipsis)
                        Text(meeting.assignee, color = TextPrimary, fontSize = 12.sp, modifier = Modifier.weight(1.5f), maxLines = 1, overflow = TextOverflow.Ellipsis)
                        Text(meeting.date, color = TextPrimary, fontSize = 12.sp, modifier = Modifier.weight(1.5f), maxLines = 1, overflow = TextOverflow.Ellipsis)
                    }
                    Spacer(modifier = Modifier.height(12.dp))
                    Divider(color = DividerColor, thickness = 1.dp)
                }
            }
        }
    }
}

@Composable
fun MembersSection() {
    Column {
        Text(
            text = "Members",
            color = TextPrimary,
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(bottom = 16.dp)
        )
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            members.forEach { member ->
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier.width(52.dp)
                ) {
                    Box(
                        modifier = Modifier
                            .size(48.dp)
                            .clip(CircleShape)
                            .background(Color(0xFF334155))
                            .border(2.dp, member.color, CircleShape),
                        contentAlignment = Alignment.Center
                    ) {
                        Icon(
                            imageVector = Icons.Default.Person,
                            contentDescription = null,
                            tint = TextSecondary,
                            modifier = Modifier.size(28.dp)
                        )
                    }
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(
                        text = member.name,
                        color = TextPrimary,
                        fontSize = 10.sp,
                        textAlign = androidx.compose.ui.text.style.TextAlign.Center,
                        lineHeight = 12.sp
                    )
                }
            }
        }
    }
}

@Composable
fun BottomNavigationBar() {
    NavigationBar(
        containerColor = CardColor,
        contentColor = TextSecondary,
        tonalElevation = 0.dp
    ) {
        NavigationBarItem(
            icon = { Icon(Icons.Filled.Home, contentDescription = "Dashboard") },
            label = { Text("Dashboard", fontSize = 10.sp) },
            selected = true,
            colors = NavigationBarItemDefaults.colors(
                selectedIconColor = TextPrimary,
                selectedTextColor = TextPrimary,
                indicatorColor = CardColor
            ),onClick = {}
        )
        NavigationBarItem(
            icon = { Icon(Icons.Outlined.CheckCircle, contentDescription = "Tasks") },
            label = { Text("Tasks", fontSize = 10.sp) },
            selected = false,
            colors = NavigationBarItemDefaults.colors(unselectedIconColor = TextSecondary, unselectedTextColor = TextSecondary)
            ,onClick = {}
        )
        NavigationBarItem(
            icon = { Icon(Icons.Outlined.DateRange, contentDescription = "Meetings") },
            label = { Text("Meetings", fontSize = 10.sp) },
            selected = false,
            colors = NavigationBarItemDefaults.colors(unselectedIconColor = TextSecondary, unselectedTextColor = TextSecondary)
            ,onClick = {}
        )
        NavigationBarItem(
            icon = { Icon(Icons.Outlined.Person, contentDescription = "Members") },
            label = { Text("Members", fontSize = 10.sp) },
            selected = false,
            colors = NavigationBarItemDefaults.colors(unselectedIconColor = TextSecondary, unselectedTextColor = TextSecondary)
            ,onClick = {}
        )
        NavigationBarItem(
            icon = { Icon(Icons.Outlined.Settings, contentDescription = "Profile") },
            label = { Text("Profile", fontSize = 10.sp) },
            selected = false,
            colors = NavigationBarItemDefaults.colors(unselectedIconColor = TextSecondary, unselectedTextColor = TextSecondary)
            ,onClick = {}
        )
    }
}
