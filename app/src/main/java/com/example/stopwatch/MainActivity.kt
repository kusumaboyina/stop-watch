package com.example.stopwatch

 import android.annotation.SuppressLint
import android.os.Bundle
import android.os.CountDownTimer
import android.os.PersistableBundle
import android.widget.Button
import android.widget.TextView
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.stopwatch.ui.theme.StopwatchTheme
import java.util.concurrent.TimeUnit

class MainActivity : ComponentActivity() {
    private lateinit var countDownTimer: CountDownTimer
    private lateinit var textView: TextView
    private var START_TIME_IN_MILLIS: Long = 120000 // 2 minutes
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        textView = findViewById(R.id.textview)

        countDownTimer = object : CountDownTimer(START_TIME_IN_MILLIS, 1) {
            override fun onTick(p0: Long) {
                val minutes = TimeUnit.MILLISECONDS.toMinutes(p0)
                val seconds =
                    TimeUnit.MILLISECONDS.toSeconds(p0) - TimeUnit.MINUTES.toSeconds(minutes)
                val milliseconds = p0 % 1000 / 10
                val timeRemaining = String.format("%02d:%02d:%02d", minutes, seconds, milliseconds)
                textView.text = timeRemaining
            }

            override fun onFinish() {
                textView.text = "Times Up"
            }
        }
        val startButton = findViewById<Button>(R.id.startCountdown)
        startButton.setOnClickListener {
            countDownTimer.start()
        }
        val stopButton = findViewById<Button>(R.id.stopCountdown)
        stopButton.setOnClickListener {
            countDownTimer.cancel()
        }
    }

    }
