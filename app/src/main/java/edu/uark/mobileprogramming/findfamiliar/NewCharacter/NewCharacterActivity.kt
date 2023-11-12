package edu.uark.mobileprogramming.findfamiliar.NewCharacter

import android.os.Bundle
import android.util.Log
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import edu.uark.mobileprogramming.findfamiliar.R
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.launch

class NewCharacterActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_new_character)
        val returnHome = findViewById<Button>(R.id.returnHome)
        returnHome.setOnClickListener {
            CoroutineScope(SupervisorJob()).launch {
                Log.d("New Character Activity", "Button Clicked")
            }
            setResult(RESULT_OK)
            finish()
        }
    }
}