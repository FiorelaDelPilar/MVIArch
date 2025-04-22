package com.example.mviarch.promoModule.model

import com.example.mviarch.commonModule.dataAccess.local.getAllPromos

class Database {
    fun getPromos() = getAllPromos()
}