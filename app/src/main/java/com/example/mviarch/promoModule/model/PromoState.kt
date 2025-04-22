package com.example.mviarch.promoModule.model

import com.example.mviarch.commonModule.entities.Promo


sealed class PromoState {
    data object Init : PromoState()
    data class Fail(val code: Int, val msgRes: Int) : PromoState()
    data class RequestPromoSuccess(val list: List<Promo>) : PromoState()
}