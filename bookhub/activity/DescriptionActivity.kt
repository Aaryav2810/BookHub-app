package com.aaryav.bookhub.activity

import android.content.Context
import android.media.Image
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.*
import androidx.appcompat.widget.Toolbar
import com.aaryav.bookhub.R
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import com.squareup.picasso.Picasso
import org.json.JSONException
import org.json.JSONObject

class DescriptionActivity : AppCompatActivity() {

    lateinit var txtBookName: TextView
    lateinit var txtBookAuthor: TextView
    lateinit var txtBookPrice:TextView
    lateinit var txtBookRating:TextView
    lateinit var txtbookdes:TextView
    lateinit var imgBookImage: ImageView
    lateinit var btnAddtoFav: Button
    lateinit var progressBar: ProgressBar
    lateinit var progressLayout: RelativeLayout
    lateinit var toolbar: Toolbar

    var bookId: String? = "100"


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_description)

        txtBookName = findViewById(R.id.txtbookname)
        txtBookAuthor = findViewById(R.id.txtauthorname)
        txtBookPrice = findViewById(R.id.txtprice)
        txtBookRating = findViewById(R.id.txtBookRating)
        txtbookdes = findViewById(R.id.textaboutbookdescription)
        imgBookImage = findViewById(R.id.impBookImage)
        btnAddtoFav = findViewById(R.id.btnaddtofav)
        progressBar  = findViewById(R.id.progressbar)
        progressLayout = findViewById(R.id.progressLayout)
        progressBar.visibility  = View.VISIBLE
        progressLayout.visibility = View.VISIBLE

        toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)
        supportActionBar?.title = "Book Details"

        if(intent!=null)
        {
            bookId = intent.getStringExtra("book_id")
        }
        else
        {
            finish()
            Toast.makeText(this@DescriptionActivity,"Some unexpected error occured",Toast.LENGTH_LONG).show()
        }

        if(bookId == "100")
        {
            finish()
            Toast.makeText(this@DescriptionActivity,"Some unexpected error occured",Toast.LENGTH_LONG).show()
        }

       val queue = Volley.newRequestQueue(this@DescriptionActivity)
       val url = "http://13.235.250.119/v1/book/get_book/"

        val jsonParams = JSONObject()
        jsonParams.put("book_id",bookId)

        val jsonRequest = object: JsonObjectRequest(Request.Method.POST,url,jsonParams, Response.Listener {

            try{

                val success = it.getBoolean("success")

                if(success)
                {
                    val bookJsonObject = it.getJSONObject("book_data")
                    progressLayout.visibility = View.GONE

                    Picasso.get().load(bookJsonObject.getString("image")).error(R.drawable.default_book_cover).into(imgBookImage)
                    txtBookName.text = bookJsonObject.getString("name")
                    txtBookAuthor.text = bookJsonObject.getString("author")
                    txtBookPrice.text = bookJsonObject.getString("price")
                    txtBookRating.text = bookJsonObject.getString("rating")
                    txtbookdes.text = bookJsonObject.getString("description")
                }
                else
                {
                    Toast.makeText(
                        this@DescriptionActivity,
                        "Error has Occured!!",
                        Toast.LENGTH_SHORT
                    ).show()
                }


            }
            catch(e:JSONException)
            {
                Toast.makeText(this@DescriptionActivity,"Some unexpected error occured",Toast.LENGTH_LONG).show()
            }
           //Code for response

        },Response.ErrorListener {

            //Code for error
            progressLayout.visibility = View.GONE


            Toast.makeText(this@DescriptionActivity,"Volley Error ${it}",Toast.LENGTH_LONG).show()
        })
        {
            override fun getHeaders(): MutableMap<String, String> {

                val headers = HashMap<String, String>()
                headers["Content-type"] = "application/json"
                headers["Token"] = "3bab198168b0a9"
                return headers
            }
        }


}}