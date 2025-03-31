package com.example.mviarch

import android.view.View
import androidx.recyclerview.widget.RecyclerView

/****
 * Project: Wines
 * From: com.cursosant.wines
 * Created by Alain Nicolás Tello on 06/02/24 at 20:23
 * All rights reserved 2024.
 *
 * All my Udemy Courses:
 * https://www.udemy.com/user/alain-nicolas-tello/
 * And Frogames formación:
 * https://cursos.frogamesformacion.com/pages/instructor-alain-nicolas
 *
 * Coupons on my Website:
 * www.alainnicolastello.com
 ***/
class WineFavListAdapter : WineListAdapter() {
    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        super.onBindViewHolder(holder, position)
        val wine = getItem(position)
        with((holder as ViewHolder).binding) {
            cbFavorite.apply {
                isChecked = wine.isFavorite
                visibility = View.VISIBLE
            }
        }
    }
}