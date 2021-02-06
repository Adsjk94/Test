package com.lifehacker.test

import android.app.ProgressDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ListView
import okhttp3.*
import org.json.JSONArray
import org.json.JSONObject
import java.io.IOException

class MainActivity : AppCompatActivity() {

    lateinit var dialog:ProgressDialog
    lateinit var lv_details: ListView

    var al_details:ArrayList<Model> = ArrayList();
    val client = OkHttpClient()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        dialog= ProgressDialog.show(this@MainActivity,"Alert","Loading")
        lv_details = findViewById<ListView>(R.id.lv_details) as ListView
        run("https://api.androidhive.info/contacts/")


    }

    fun run(url: String) {
        dialog.show()
        val request = Request.Builder()
            .url(url)
            .build()

        client.newCall(request).enqueue(object : Callback {
            override fun onFailure(call: Call, e: IOException) {
                dialog.dismiss()

            }

            override fun onResponse(call: Call, response: Response) {
                var str_response = response.body!!.string()
                val json_contact:JSONObject = JSONObject(str_response)

                var jsonarray_contacts:JSONArray= json_contact.getJSONArray("contacts")

                var i:Int = 0
                var size:Int = jsonarray_contacts.length()

                al_details= ArrayList();

                for (i in 0.. size-1) {
                    var json_objectdetail:JSONObject=jsonarray_contacts.getJSONObject(i)


                    var model:Model= Model();
                    model.id=json_objectdetail.getString("id")
                    model.name=json_objectdetail.getString("name")
                    model.email=json_objectdetail.getString("email")
                    model.address=json_objectdetail.getString("address")
                    model.gender=json_objectdetail.getString("gender")

                    al_details.add(model)


                }

                runOnUiThread {
                    //stuff that updates ui
                    val obj_adapter : Adapter
                    obj_adapter = Adapter(applicationContext,al_details)
                    lv_details.adapter=obj_adapter
                }

                dialog.dismiss()

            }

        })

    }


}