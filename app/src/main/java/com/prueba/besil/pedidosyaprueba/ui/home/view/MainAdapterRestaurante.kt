package com.prueba.besil.pedidosyaprueba.ui.home.view

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.prueba.besil.pedidosyaprueba.R
import com.prueba.besil.pedidosyaprueba.data.network.DTO.Restaurante

class MainAdapterRestaurante(var restauranteList: MutableList<Restaurante>) : RecyclerView.Adapter<MainAdapterRestaurante.ViewHolder>() {
    var offset = 0;
    var total = 0;
    var isLoading: Boolean = false

    override fun onBindViewHolder(holder: MainAdapterRestaurante.ViewHolder, position: Int) {
        holder.txtName?.text = restauranteList[position].name
        holder.txtCategories?.text = restauranteList[position].allCategories
        holder.txtScore?.text = restauranteList[position].ratingScore
        val maxMinMinutes = "${restauranteList[position].deliveryTimeMinMinutes} - ${restauranteList[position].deliveryTimeMaxMinutes} min"
        holder.txtMaxMinMinutes?.text = maxMinMinutes
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.item_layout, parent, false)
        return ViewHolder(v);
    }

    override fun getItemCount(): Int {
        return restauranteList.size
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val txtName = itemView.findViewById<TextView>(R.id.txtName)
        val txtCategories = itemView.findViewById<TextView>(R.id.txtCategories)
        val txtScore = itemView.findViewById<TextView>(R.id.txtScore)
        val txtMaxMinMinutes = itemView.findViewById<TextView>(R.id.txtMinMaxMinutes)
    }

}