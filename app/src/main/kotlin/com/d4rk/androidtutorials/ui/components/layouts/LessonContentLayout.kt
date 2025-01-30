package com.d4rk.androidtutorials.ui.components.layouts

import android.content.Context
import android.os.Build.VERSION.SDK_INT
import android.widget.Toast
import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.text.selection.SelectionContainer
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.CopyAll
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.fromHtml
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import coil3.ImageLoader
import coil3.compose.AsyncImage
import coil3.gif.AnimatedImageDecoder
import coil3.gif.GifDecoder
import coil3.request.crossfade
import com.d4rk.android.libs.apptoolkit.ui.components.modifiers.bounceClick
import com.d4rk.android.libs.apptoolkit.ui.components.spacers.ButtonHorizontalSpacer
import com.d4rk.android.libs.apptoolkit.ui.components.spacers.LargeVerticalSpacer
import com.d4rk.android.libs.apptoolkit.ui.components.spacers.SmallVerticalSpacer
import com.d4rk.android.libs.apptoolkit.utils.helpers.ClipboardHelper
import com.d4rk.androidtutorials.data.model.ui.screens.UiLessonScreen
import com.d4rk.androidtutorials.ui.components.ads.AdBanner
import com.d4rk.androidtutorials.ui.screens.settings.display.theme.style.Colors
import com.d4rk.androidtutorials.ui.screens.settings.display.theme.style.TextStyles
import com.d4rk.androidtutorials.utils.constants.ui.lessons.LessonCodeConstants
import com.d4rk.androidtutorials.utils.constants.ui.lessons.LessonContentTypes
import com.google.android.gms.ads.AdSize
import com.wakaztahir.codeeditor.highlight.model.CodeLang
import com.wakaztahir.codeeditor.highlight.prettify.PrettifyParser
import com.wakaztahir.codeeditor.highlight.theme.CodeTheme
import com.wakaztahir.codeeditor.highlight.theme.CodeThemeType
import com.wakaztahir.codeeditor.highlight.utils.parseCodeAsAnnotatedString

@Composable
fun LessonContentLayout(
    paddingValues : PaddingValues ,
    scrollState : ScrollState ,
    lesson : UiLessonScreen ,
) {
    Column(
        modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues = paddingValues)
                .padding(horizontal = 16.dp)
                .verticalScroll(state = scrollState)
    ) {
        lesson.lessonContent.forEachIndexed { index , contentItem ->
            when (contentItem.contentType) {
                LessonContentTypes.HEADER -> {
                    StyledText(
                        text = contentItem.contentText , style = TextStyles.header() , color = Colors.primaryText()
                    )
                }

                LessonContentTypes.TEXT -> {
                    StyledText(
                        text = contentItem.contentText , style = TextStyles.body() , color = Colors.secondaryText()
                    )
                }

                LessonContentTypes.IMAGE -> {
                    StyledImage(
                        imageUrl = contentItem.contentImageUrl , contentDescription = "Lesson Image"
                    )
                }

                LessonContentTypes.CODE -> {
                    CodeBlock(
                        code = contentItem.contentCode , language = contentItem.programmingLanguage
                    )
                }

                LessonContentTypes.AD_BANNER -> {
                    AdBanner()
                }

                LessonContentTypes.AD_BANNER_FULL -> {
                    AdBanner(adSize = AdSize.FULL_BANNER)
                }

                LessonContentTypes.AD_LARGE_BANNER -> {
                    AdBanner(adSize = AdSize.LARGE_BANNER)
                }

                else -> {
                    Text(text = "Unsupported content type: ${contentItem.contentType}")
                }
            }
            if (index < lesson.lessonContent.lastIndex) {
                SmallVerticalSpacer()
            }
        }
    }
}

@Composable
fun StyledText(
    text : String ,
    style : TextStyle = TextStyles.body() ,
    color : Color = Colors.primaryText() ,
) {
    val annotatedString : AnnotatedString = AnnotatedString.fromHtml(htmlString = text)

    Text(
        text = annotatedString , style = style , color = color
    )
}

@Composable
fun StyledImage(
    imageUrl : String ,
    contentDescription : String? = null ,
    modifier : Modifier = Modifier ,
) {
    val context : Context = LocalContext.current
    val imageLoader : ImageLoader = ImageLoader.Builder(context = context).components {
        if (SDK_INT >= 28) {
            add(factory = AnimatedImageDecoder.Factory())
        }
        else {
            add(factory = GifDecoder.Factory())
        }
    }.crossfade(enable = true).build()
    Card(
        modifier = modifier.fillMaxWidth() ,
    ) {
        AsyncImage(
            model = imageUrl ,
            contentScale = ContentScale.FillWidth ,
            contentDescription = contentDescription ,
            modifier = Modifier.fillMaxWidth() ,
            imageLoader = imageLoader ,
        )
    }
}

@Composable
fun CodeBlock(code : String , language : String?) {
    val context = LocalContext.current
    val lang = when (language) {
        LessonCodeConstants.C -> {
            CodeLang.C
        }

        LessonCodeConstants.CPP -> {
            CodeLang.CPP
        }

        LessonCodeConstants.OBJECTIVEC -> {
            CodeLang.ObjectiveC
        }

        LessonCodeConstants.CSHARP -> {
            CodeLang.CSharp
        }

        LessonCodeConstants.JAVA -> {
            CodeLang.Java
        }

        LessonCodeConstants.BASH -> {
            CodeLang.Bash
        }

        LessonCodeConstants.PYTHON -> {
            CodeLang.Python
        }

        LessonCodeConstants.PERL -> {
            CodeLang.Perl
        }

        LessonCodeConstants.RUBY -> {
            CodeLang.Ruby
        }

        LessonCodeConstants.JAVASCRIPT -> {
            CodeLang.JavaScript
        }

        LessonCodeConstants.COFFEESCRIPT -> {
            CodeLang.CoffeeScript
        }

        LessonCodeConstants.RUST -> {
            CodeLang.Rust
        }

        LessonCodeConstants.BASIC -> {
            CodeLang.Basic
        }

        LessonCodeConstants.CLOJURE -> {
            CodeLang.Clojure
        }

        LessonCodeConstants.CSS -> {
            CodeLang.CSS
        }

        LessonCodeConstants.DART -> {
            CodeLang.Dart
        }

        LessonCodeConstants.ERLANG -> {
            CodeLang.Erlang
        }

        LessonCodeConstants.GO -> {
            CodeLang.Go
        }

        LessonCodeConstants.HASKELL -> {
            CodeLang.Haskell
        }

        LessonCodeConstants.LISP -> {
            CodeLang.Lisp
        }

        LessonCodeConstants.LUA -> {
            CodeLang.Lua
        }

        LessonCodeConstants.MATLAB -> {
            CodeLang.Matlab
        }

        LessonCodeConstants.ML -> {
            CodeLang.ML
        }

        LessonCodeConstants.SML -> {
            CodeLang.SML
        }

        LessonCodeConstants.MUMPS -> {
            CodeLang.Mumps
        }

        LessonCodeConstants.PASCAL -> {
            CodeLang.Pascal
        }

        LessonCodeConstants.SCALA -> {
            CodeLang.Scala
        }

        LessonCodeConstants.SQL -> {
            CodeLang.SQL
        }

        LessonCodeConstants.VHDL -> {
            CodeLang.VHDL
        }

        LessonCodeConstants.TCL -> {
            CodeLang.Tcl
        }

        LessonCodeConstants.WIKI -> {
            CodeLang.Wiki
        }

        LessonCodeConstants.XQUERY -> {
            CodeLang.XQuery
        }

        LessonCodeConstants.YAML -> {
            CodeLang.YAML
        }

        LessonCodeConstants.MARKDOWN -> {
            CodeLang.Markdown
        }

        LessonCodeConstants.JSON -> {
            CodeLang.JSON
        }

        LessonCodeConstants.XML -> {
            CodeLang.XML
        }

        else -> {
            CodeLang.Java
        }
    }

    val parser : PrettifyParser = remember { PrettifyParser() }
    val themeState : CodeThemeType by remember { mutableStateOf(value = CodeThemeType.Default) }
    val theme : CodeTheme = remember(key1 = themeState) { themeState.theme() }

    val textFieldValue : TextFieldValue by remember {
        mutableStateOf(
            value = TextFieldValue(
                annotatedString = parseCodeAsAnnotatedString(
                    parser = parser , theme = theme , lang = lang , code = code
                )
            )
        )
    }

    Card(
        modifier = Modifier.fillMaxWidth()
    ) {
        Column {
            Row(
                modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp) , horizontalArrangement = Arrangement.SpaceBetween , verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = language ?: "unknown" , style = MaterialTheme.typography.bodyMedium , modifier = Modifier.padding(end = 8.dp)
                )
                TextButton(modifier = Modifier.bounceClick() , onClick = {
                    ClipboardHelper.copyTextToClipboard(context = context , label = "Code" , text = code , onShowSnackbar = {
                        Toast.makeText(
                            context , "Code copied to clipboard" , Toast.LENGTH_SHORT
                        ).show()
                    })
                } , contentPadding = PaddingValues(horizontal = 8.dp)) {
                    Icon(
                        imageVector = Icons.Outlined.CopyAll , contentDescription = "Copy Code" , modifier = Modifier.size(size = ButtonDefaults.IconSize)
                    )
                    ButtonHorizontalSpacer()
                    Text(text = stringResource(id = android.R.string.copy))
                }
            }
            Spacer(modifier = Modifier.height(height = 2.dp))
            SelectionContainer {
                Text(
                    text = textFieldValue.annotatedString , modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 8.dp)
                )
            }
            LargeVerticalSpacer()
        }
    }
}