package com.example.testapi

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.Dispatchers.Main
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.json.JSONArray
import java.net.URL
import java.nio.charset.Charset

class MainActivity : AppCompatActivity() {
   lateinit var etName :EditText
    lateinit var etLoc:EditText
    lateinit  var btSave:Button
    lateinit var tvText:TextView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        etName=findViewById(R.id.etName)
            etLoc=findViewById(R.id.etLoc)
         btSave=   findViewById(R.id.button)
           tvText= findViewById(R.id.tv1)

        requestAPI()


    }
   fun requestAPI(){
       CoroutineScope(IO).launch {
           var data=async {
               fechData()
           }.await()
           if(data.isNotEmpty())

           {
               var arrData=JSONArray(data)

withContext(Main){ tvText.text=arrData.getJSONObject(0).getString("name")
}

           }

       }
   }
    fun fechData():String{
       var response =""
            try{
                response =URL("https://dojo-recipes.herokuapp.com/test/").readText(Charsets.UTF_8)
    }catch (e : Exception ){
        //kkl

        }
        return response
    }
}