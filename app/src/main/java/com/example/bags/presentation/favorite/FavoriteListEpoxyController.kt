package com.example.bags.presentation.favorite

import com.airbnb.epoxy.EpoxyAsyncUtil
import com.airbnb.epoxy.TypedEpoxyController
import com.example.bags.favoriteCard
import com.example.core.domain.FavoriteOrder

class FavoriteListEpoxyController : TypedEpoxyController<List<FavoriteOrder>>(
    EpoxyAsyncUtil.getAsyncBackgroundHandler(),
    EpoxyAsyncUtil.getAsyncBackgroundHandler()
) {
    override fun buildModels(data: List<FavoriteOrder>?) {
        data?.forEachIndexed { index, favoriteOrder ->
            favoriteCard {
                id(index)
                favoriteOrder(favoriteOrder)
            }
        }
    }
}