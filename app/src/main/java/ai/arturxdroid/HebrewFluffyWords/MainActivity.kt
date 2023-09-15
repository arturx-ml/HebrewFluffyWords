package ai.arturxdroid.HebrewFluffyWords

import ai.arturxdroid.HebrewFluffyWords.ui.theme.HebrewFluffyWordsTheme
import android.annotation.SuppressLint
import android.graphics.Bitmap
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            HebrewFluffyWordsTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background,
                ) {
                    WordCard("So here we are")
                }
            }
        }
    }
}

@SuppressLint("PrivateResource")
@Composable
fun WordCard(word: String, image: Bitmap? = null) {
    Column(
        modifier = Modifier.padding(12.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        if (image == null) {
            Image(
                painter = painterResource(androidx.core.R.drawable.ic_call_answer),
                contentDescription = "Word representation image",
                modifier = Modifier.size(140.dp)
            )
        } else {
            Image(
                bitmap = image.asImageBitmap(),
                contentDescription = "Word representation image",
                modifier = Modifier.size(140.dp)
            )
        }


        Spacer(modifier = Modifier.height(12.dp))

        CurrentWord(word)
    }
}

@Composable
fun CurrentWord(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!", modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    HebrewFluffyWordsTheme {
        WordCard("so here we are")
    }
}