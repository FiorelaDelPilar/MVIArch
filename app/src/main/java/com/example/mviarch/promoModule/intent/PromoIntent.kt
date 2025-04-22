package com.example.mviarch.promoModule.intent

sealed class PromoIntent {
    data object RequestPromos : PromoIntent()
}