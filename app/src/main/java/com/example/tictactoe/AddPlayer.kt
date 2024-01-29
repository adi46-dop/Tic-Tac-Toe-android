package com.example.tictactoe

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.tictactoe.databinding.ActivityAddPlayerBinding

class AddPlayer : AppCompatActivity() {
    private lateinit var binding: ActivityAddPlayerBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAddPlayerBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.startGameButton.setOnClickListener{

            val getPlayerOneName = binding.playerOne.text.toString()
            val getPlayerSecondName = binding.playerTwo.text.toString()

            if ( getPlayerOneName.isEmpty() || getPlayerSecondName.isEmpty()){
                Toast.makeText(this,"Please enter players names",Toast.LENGTH_SHORT).show()
            }else {
                val intent = Intent(this,MainActivity::class.java)
                intent.putExtra("playerOne", getPlayerOneName)
                intent.putExtra("playerTwo", getPlayerSecondName)
                startActivity(intent)
            }
        }
    }
}