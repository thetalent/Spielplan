package com.falkhiller.spielplan

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.transition.Visibility
import android.view.View
import android.view.View.VISIBLE
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    var teamCounts = 0
    lateinit var teams: MutableList<EditText>

    fun planRound(i:Int){
        Plan.text = Plan.text.toString() + ("Round " + i.toString() + ":\n")
        Plan.text = Plan.text.toString() +teams[0].text + " - " + teams[i].text + "\n"
        for(j in 1..Math.floorDiv(teamCounts,2)-1) {
            var player1 = (i-j+teamCounts -2) % (teamCounts -1) + 1
            var player2 = (i+j +teamCounts -2) % (teamCounts -1) +1
            Plan.text = Plan.text.toString() + teams[player1].text + " - " + teams[player2].text + "\n"
            Plan.text ="${Plan.text }\n"
        }


    }
    fun plan(){
        TeamsLayout.visibility = View.GONE
        for (i in 1 until( teamCounts ) ) planRound(i)
        Plan.visibility = VISIBLE
    }
    fun getInputFields(v:View){
        LfirstStep.visibility = View.GONE
        teamCounts = teamCount.text.toString().toInt()
        //TeamsLayout.visibility= View.VISIBLE
        teams = MutableList<EditText>(teamCounts,{it -> EditText(this)})
        teams.forEach { it->TeamsLayout.addView(it) }
        var planIt: Button = Button(this)
        planIt.text = "Show"
        TeamsLayout.addView(planIt)
        planIt.setOnClickListener { v-> plan() }
        TeamsLayout.visibility= View.VISIBLE
        }



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

    }
}
