package com.example.bags.presentation.categories.categories

import com.airbnb.epoxy.EpoxyAsyncUtil
import com.airbnb.epoxy.TypedEpoxyController
import com.example.bags.favoriteCard
import com.example.core.domain.Category
import com.example.core.domain.FavoriteOrder

class CategoriesListEpoxyController : TypedEpoxyController<List<Category>>(
    EpoxyAsyncUtil.getAsyncBackgroundHandler(),
    EpoxyAsyncUtil.getAsyncBackgroundHandler()
) {
    override fun buildModels(data: List<Category>?) {
        data?.forEachIndexed { index, favoriteOrder ->
            favoriteCard {
                id(index)
              //  favoriteOrder(favoriteOrder)
            }
        }
    }
}