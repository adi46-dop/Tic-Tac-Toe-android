package com.example.tictactoe

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Message
import android.view.View
import android.widget.ImageView
import com.example.tictactoe.databinding.ActivityAddPlayerBinding
import com.example.tictactoe.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private val combinationList: MutableList<IntArray> = ArrayList()
    private var boxPositions = intArrayOf(0,0,0,0,0,0,0,0,0)
    private var playerTurn =1
    private var totalSelectedBoxes = 1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        combinationList.add(intArrayOf(0, 1, 2))
        combinationList.add(intArrayOf(3,4,5))
        combinationList.add(intArrayOf(6,7,8))
        combinationList.add(intArrayOf(0,3,6))
        combinationList.add(intArrayOf(1,4,7))
        combinationList.add(intArrayOf(2,5,8))
        combinationList.add(intArrayOf(2,4,6))
        combinationList.add(intArrayOf(0,4,8))

        val getPlayerOneName = intent.getStringExtra("playerOne")
        val getPlayerTwoName = intent.getStringExtra("playerTwo")

        binding.playerOneName.text = getPlayerOneName
        binding.playerTwoName.text = getPlayerTwoName

        setOnClickListeners()

    }

    private fun setOnClickListeners(){
        val clickListener: (View, Int) -> Unit = { view, selectedBoxPosition ->
            if (isBoxSelectable(selectedBoxPosition)) {
                performAction(view as ImageView, selectedBoxPosition)
            }
        }

        binding.image1.setOnClickListener { view -> clickListener(view, 0) }
        binding.image2.setOnClickListener { view -> clickListener(view, 1) }
        binding.image3.setOnClickListener { view -> clickListener(view, 2) }
        binding.image4.setOnClickListener { view -> clickListener(view, 3) }
        binding.image5.setOnClickListener { view -> clickListener(view, 4) }
        binding.image6.setOnClickListener { view -> clickListener(view, 5) }
        binding.image7.setOnClickListener { view -> clickListener(view, 6) }
        binding.image8.setOnClickListener { view -> clickListener(view, 7) }
        binding.image9.setOnClickListener { view -> clickListener(view, 8) }
    }

    private fun performAction(imageView: ImageView, selectedBoxPos: Int) {
        boxPositions[selectedBoxPos] = playerTurn

        if (playerTurn == 1) {
            imageView.setImageResource(R.drawable.ximage)
            if (checkResult()){
                showResultDialog("${binding.playerOneName.text} is a Winner!")
            }else if (totalSelectedBoxes == 9) {
                showResultDialog("Match Draw")
            } else {
                changePlayerTurn(2)
                totalSelectedBoxes++
            }
        } else {
            imageView.setImageResource(R.drawable.oimage)
            if (checkResult()){
                showResultDialog("${binding.playerTwoName.text} is a Winner!")
            } else if (totalSelectedBoxes == 0) {
                showResultDialog("Match Draw")
            } else {
                changePlayerTurn(1)
                totalSelectedBoxes++
            }
        }
    }

    private fun changePlayerTurn(currentPlayerTurn: Int){
        playerTurn = currentPlayerTurn
        if (playerTurn == 1){
            binding.playerOneLayout.setBackgroundResource(R.drawable.black_border)
            binding.playerTwoLayout.setBackgroundResource(R.drawable.white_box)
        } else {
            binding.playerTwoLayout.setBackgroundResource(R.drawable.black_border)
            binding.playerOneLayout.setBackgroundResource(R.drawable.white_box)
        }
    }

    private fun checkResult(): Boolean {
        var response = false

        for (combination in combinationList){

            if (boxPositions[combination[0]] == playerTurn &&
                boxPositions[combination[1]] == playerTurn &&
                boxPositions[combination[2]] == playerTurn
                ){
                response = true
            }
        }
        return response
    }

    private fun isBoxSelectable(boxPosition: Int): Boolean {
        return  boxPositions[boxPosition] == 0
    }

    private fun showResultDialog(message: String) {
        val resultDialog = ResultDialog(this, message,this)
        resultDialog.setCancelable(false)
        resultDialog.show()

    }

    fun restartMatch() {
        boxPositions = intArrayOf(0,0,0,0,0,0,0,0,0)
        playerTurn =1
        totalSelectedBoxes = 1

        with(binding){
            image1.setImageResource(R.drawable.white_box)
            image2.setImageResource(R.drawable.white_box)
            image3.setImageResource(R.drawable.white_box)
            image4.setImageResource(R.drawable.white_box)
            image5.setImageResource(R.drawable.white_box)
            image6.setImageResource(R.drawable.white_box)
            image7.setImageResource(R.drawable.white_box)
            image8.setImageResource(R.drawable.white_box)
            image9.setImageResource(R.drawable.white_box)
        }
    }
}