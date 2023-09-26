package ai.arturxdroid.hebrewfluffywords

import ai.arturxdroid.HebrewFluffyWords.R
import ai.arturxdroid.hebrewfluffywords.di.CustomDiFactory
import ai.arturxdroid.hebrewfluffywords.domain.WordPairs
import ai.arturxdroid.hebrewfluffywords.ui.theme.HebrewFluffyWordsTheme
import ai.arturxdroid.hebrewfluffywords.ui.viewmodel.MainViewModel
import android.annotation.SuppressLint
import android.os.Bundle
import android.os.Handler
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
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage

class MainActivity : ComponentActivity() {

    private val viewModel: MainViewModel by lazy { CustomDiFactory.getMainViewModel() }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            HebrewFluffyWordsTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background,
                ) {
                    Column(
                        modifier = Modifier.fillMaxSize(),
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.SpaceEvenly
                    ) {
                        WordCard("So here we are")
                        WordButton(getString(R.string.refresh_word))
                    }
                }
            }
        }

        viewModel.screenState.observe(this) {
            if (it.images.isNotEmpty() && it.images[0].url.isNotEmpty()) {
                setContent {
                    HebrewFluffyWordsTheme {
                        // A surface container using the 'background' color from the theme
                        Surface(
                            modifier = Modifier.fillMaxSize(),
                            color = MaterialTheme.colorScheme.background,
                        ) {
                            Column(
                                modifier = Modifier.fillMaxSize(),
                                horizontalAlignment = Alignment.CenterHorizontally,
                                verticalArrangement = Arrangement.SpaceEvenly
                            ) {
                                WordCard(WordPairs.list[0].hebrew, it.images.get(0).url)
                                WordButton(getString(R.string.refresh_word))
                            }
                        }
                    }

                }
            }
        }

        Handler().postDelayed(
            {
            viewModel.fetchImages("image for an english textbook for a word \"to learn\"")
            }, 3000L
        )

    }
}

private fun getNewWord() {
    //todo hit main view model

}


@OptIn(ExperimentalGlideComposeApi::class)
@SuppressLint("PrivateResource")
@Composable
fun WordCard(word: String, imageUrl: String? = null) {
    Card(
        elevation = CardDefaults.cardElevation(defaultElevation = 6.dp),
        modifier = Modifier.size(320.dp, 320.dp)
    ) {
        Column(
            modifier = Modifier
                .padding(12.dp)
                .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            if (imageUrl.isNullOrEmpty()) {
                Image(
                    painter = painterResource(androidx.core.R.drawable.ic_call_answer),
                    contentDescription = "Word representation image",
                    modifier = Modifier.size(140.dp)
                )
            } else {
                GlideImage(
                    model = imageUrl,
                    contentDescription = stringResource(R.string.word_image_content_desc),
                    modifier = Modifier.size(140.dp)
                )
            }

            Spacer(modifier = Modifier.height(16.dp))

            Text(word)
        }
    }
}

@Composable
fun WordButton(text: String) {
    TextButton(
        modifier = Modifier.size(height = 36.dp, width = 140.dp),
        onClick = { getNewWord() },
        content = { Text(modifier = Modifier, text = text) },
        elevation = ButtonDefaults.buttonElevation(defaultElevation = 2.2.dp)
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    HebrewFluffyWordsTheme {
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.SpaceEvenly
        ) {
            WordCard("So here we are")
            WordButton("Refresh word")
        }
    }
}