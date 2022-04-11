package com.aaryav.bookhub.adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.aaryav.bookhub.R
import com.aaryav.bookhub.activity.DescriptionActivity
import com.aaryav.bookhub.model.Book
import com.squareup.picasso.Picasso
import org.w3c.dom.Text

class DashboardRecyclerAdapter(val context:Context, val itemlist: ArrayList<Book>): RecyclerView.Adapter<DashboardRecyclerAdapter.DashboardViewHolder> ()
{
     class DashboardViewHolder(view:View): RecyclerView.ViewHolder(view){

         val txtBookName: TextView = view.findViewById(R.id.txtBookName)
         val txtBookAuthor: TextView = view.findViewById(R.id.txtBookAuthor)
         val txtBookRating: TextView = view.findViewById(R.id.txtBookRating)
         val txtBookPrice: TextView = view.findViewById(R.id.txtBookPrice)
         val imgBookImage: ImageView = view.findViewById(R.id.imgBookImage)
         val llContent: LinearLayout = view.findViewById(R.id.llcontent)
     }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DashboardViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.recycler_dashboard_singlerow_file,parent,false)

        return DashboardViewHolder(view)
    }

    override fun onBindViewHolder(holder: DashboardViewHolder, position: Int) {

        val book = itemlist[position]

        holder.txtBookAuthor.text = book.bookAuthor
        holder.txtBookName.text = book.bookName
        holder.txtBookRating.text = book.bookRating
        holder.txtBookPrice.text = book.bookPrice
       // holder.imgBookImage.setImageResource(book.bookImage)
        Picasso.get().load(book.bookImage).error(R.drawable.default_book_cover).into(holder.imgBookImage)

        holder.llContent.setOnClickListener {
           val intent = Intent(context, DescriptionActivity::class.java)
            intent.putExtra("book_id",book.bookId)
            context.startActivity(intent)
        }




    }

    override fun getItemCount(): Int {
        return itemlist.size
    }

}