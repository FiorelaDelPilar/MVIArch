package com.example.mviarch.promoModule.model

import com.example.mviarch.R
import com.example.mviarch.commonModule.utils.Constants

class PromoRepository(private val db: Database) {
    fun getPromos(): PromoState {
        val result = db.getPromos()
        return if (result.isNotEmpty()) {
            PromoState.RequestPromoSuccess(result)
        } else {
            PromoState.Fail(Constants.EC_REQUEST, R.string.promo_request_fail)
        }
    }
}