package com.example.calculator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.ViewTreeObserver
import android.widget.Toast
import com.example.calculator.databinding.ActivityMainBinding
import net.objecthunter.exp4j.ExpressionBuilder

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    private var operator:Char='+'
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        onNumberclicked()
        onOperatorclicked()

    }

    private fun onOperatorclicked() {
        binding.equal.setOnClickListener{
            try {
                val expression=ExpressionBuilder(binding.textasli.text.toString()).build()
                val result=expression.evaluate()
                val longresult=result.toLong()
                if(result==longresult.toDouble()){
                    binding.textfarei.text=longresult.toString()

                }else{
                    binding.textfarei.text=result.toString()
                }
            }catch (e:Exception){
                binding.textasli.text=""
                binding.textfarei.text=""
                Toast.makeText(this,"ارور رخ داد", Toast.LENGTH_LONG).show()
            }

        }
        binding.deleteone.setOnClickListener {
            val oldtext=binding.textasli.text.toString()
            if(oldtext.isNotEmpty()){
                //amir ->ami
                binding.textasli.text=oldtext.substring(0,oldtext.length-1)
            }
        }
        binding.deletall.setOnClickListener {
            binding.textasli.text=""
            binding.textfarei.text=""

        }
        binding.parantezbaz.setOnClickListener {
            appendText("(")

        }
        binding.parantezbaste.setOnClickListener {
            appendText(")")

        }
        binding.taghsim.setOnClickListener {
            if(binding.textasli.text.isNotEmpty()){
                val mychar=binding.textasli.text.last()
                if(mychar!='+'&& mychar!='-'&& mychar!='*'&& mychar!='/'){
                    appendText("/")


                }
            }

        }
        binding.zavrb.setOnClickListener {
            if(binding.textasli.text.isNotEmpty()){
                val mychar=binding.textasli.text.last()
                if(mychar!='+'&& mychar!='-'&& mychar!='*'&& mychar!='/'){
                    appendText("*")


                }
            }

        }
        binding.menha.setOnClickListener {
            if(binding.textasli.text.isNotEmpty()){
                val mychar=binding.textasli.text.last()
                if(mychar!='+'&& mychar!='-'&& mychar!='*'&& mychar!='/'){
                    appendText("-")


                }
            }

        }
        binding.plus.setOnClickListener {
            if(binding.textasli.text.isNotEmpty()){
                val mychar=binding.textasli.text.last()
                if(mychar!='+'&& mychar!='-'&& mychar!='*'&& mychar!='/'){
                    appendText("+")


                }
            }




        }


    }

    private fun onNumberclicked() {
        binding.dot.setOnClickListener {
            if (binding.textasli.text.isEmpty()||binding.textfarei.text.isNotEmpty()) {
                appendText("0.")
            } else if (!binding.textasli.text.contains(".")){
                appendText(".")
            }

        }
        binding.yek.setOnClickListener {
            appendText("1")

        }
        binding.two.setOnClickListener {
            appendText("2")

        }
        binding.three.setOnClickListener {
            appendText("3")

        }
        binding.chahar.setOnClickListener {
            appendText("4")

        }
        binding.panj.setOnClickListener {
            appendText("5")

        }
        binding.shish.setOnClickListener {
            appendText("6")

        }
        binding.seven.setOnClickListener {
            appendText("7")

        }
        binding.hasht.setOnClickListener {
            appendText("8")

        }
        binding.noh.setOnClickListener {
            appendText("9")

        }
        binding.zero.setOnClickListener {
            if(binding.textasli.text.isNotEmpty()){
                appendText("0")
            }

        }
    }
    private fun appendText(newtext:String){
        if(binding.textfarei.text.isNotEmpty()){
            binding.textasli.text=""
        }
        binding.textfarei.text=""
        binding.textasli.append(newtext)
        val viewTree: ViewTreeObserver = binding.horizontalScrollViewTxtExpression.viewTreeObserver
        viewTree.addOnGlobalLayoutListener(object : ViewTreeObserver.OnGlobalLayoutListener {
            override fun onGlobalLayout() {
                binding.horizontalScrollViewTxtExpression.viewTreeObserver.removeOnGlobalLayoutListener(
                    this
                )
                binding.horizontalScrollViewTxtExpression.scrollTo(binding.textasli.width, 0)

            }
        })

    }


}




