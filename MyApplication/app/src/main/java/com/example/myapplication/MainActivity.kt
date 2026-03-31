package com.example.myapplication

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val timeinput = findViewById<EditText>(R.id.timeInput)
        val suggestionbutton = findViewById<Button>(R.id.suggestButton)
        val resetbutton = findViewById<Button>(R.id.resetButton)
        val suggestionoutput = findViewById<TextView>(R.id.suggestionOutput)

        // error response 1 Ensures Input box isnt empty
        suggestionbutton.setOnClickListener {
            val Input = timeinput.text.toString().trim()
            if (Input.isEmpty())
            {
                suggestionoutput.text = "please enter a time in 24hr format with a : between the hours and minutes"
                return@setOnClickListener
            }

            //error response 2 Ensures correct format
            val time = Input.split(":")

            if (time.size != 2)
            {
                suggestionoutput.text = "Please use the format HH:MM (e.g. 14:30)"
                return@setOnClickListener
            }

            //error response 3 Ensures that numbers are entered in the text box and not letters
            val Hour = time[0].toIntOrNull()
            val Minute = time[1].toIntOrNull()
            if (Hour == null||Minute == null)
            {
                //this checks if they typed letters instead of numbers
                suggestionoutput.text = "only numbers please no letters"
                return@setOnClickListener
            }

            //error response 4
            if (Hour !in 0..23 || Minute !in 0..59)
            {
                suggestionoutput.text = "thats not a real time"
                return@setOnClickListener
            }

            // Social Suggestions for all 24 hours
            var suggestion = ""

            if (Hour in 5..8) { suggestion="Good Morning. Time for a motivational post. Get that bread" }
            else if (Hour in 9..11) { suggestion="Almost lunch time. Any one youve been needing to check up on?" }
            else if (Hour in 12..13) { suggestion="Lunch it is. Time to slide into some DMs, or post a lunch meal update." }
            else if (Hour in 14..16) { suggestion="Check in on your freinds socials. See whos posted maybe leave a reply or like" }
            else if (Hour in 17..18) { suggestion="Works wrapping up. Give someone a call during your commute or maybe share a gym post. Get in those gains!!!" }
            else if (Hour in 19..20) { suggestion="Alright! dinners done catch up with a freind over a message or a voice note, Hows there day been?" }
            else if (Hour in 21..22) { suggestion="Time to wind down post a good night or share a post on how you like to relax after work" }
            else if (Hour == 23) { suggestion="Still up? maybe send a message to a freind whos a night owl" }
            else if (Hour in 0..4) { suggestion="Woah its Late. No need for social media at this time" }
            else "Invalid time range"

            suggestionoutput.text = suggestion
        }

        resetbutton.setOnClickListener {
            //clears everything when reset is pressed
            timeinput.text.clear()
            suggestionoutput.text = ""
        }

    }

}