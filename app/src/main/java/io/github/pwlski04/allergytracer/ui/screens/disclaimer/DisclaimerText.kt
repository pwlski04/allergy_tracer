package io.github.pwlski04.allergytracer.ui.screens.disclaimer

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Checkbox
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.fromHtml
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import io.github.pwlski04.allergytracer.ui.BigButton
import io.github.pwlski04.allergytracer.ui.Content_Group_withTitle
import io.github.pwlski04.allergytracer.ui.Content_Surface
import io.github.pwlski04.allergytracer.ui.Text_Default
import io.github.pwlski04.allergytracer.ui.Text_Title

@Composable
fun Page_Disclaimer_Text(onSubmit: () -> Unit ){
    var disclaimerAccepted by remember { mutableStateOf(false) }

    val disclaimerText = AnnotatedString.fromHtml(
        "AllergyTracer is <b>not a medical application</b>. It does not diagnose allergies or tell you anything is safe to use. It only tracks patterns across products you log, and surfaces ingredients that look suspicious based on your own history.<br><br>" +
                "Always consult a <b>qualified medical professional</b> for any official diagnosis, testing, or treatment.<br><br>" +
                "<b>AllergyTracer and its creator take no liability</b> for decisions made using this app."
    )

    Content_Group_withTitle(groupContent = {
        Text_Title("Disclaimer")
        Text_Default(disclaimerText)
        Content_Surface(
            left = {
                Checkbox(modifier = Modifier.size(18.dp), checked = disclaimerAccepted, onCheckedChange = { disclaimerAccepted = it})
            },
            middle = {
                Text("I understand this is not a medical application and no official diagnosis or safety guarantee is being made.", fontSize = 14.sp)
            }
        )

        Spacer(modifier = Modifier.weight(1f))

        BigButton("Continue", enabled = disclaimerAccepted, onClick = { onSubmit() })
    })
}