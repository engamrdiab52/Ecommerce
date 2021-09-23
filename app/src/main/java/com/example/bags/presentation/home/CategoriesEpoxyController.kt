package com.example.bags.presentation.home

import com.airbnb.epoxy.EpoxyAsyncUtil
import com.airbnb.epoxy.TypedEpoxyController
import com.example.bags.categoriesCard
import com.example.bags.favoriteCard
import com.example.core.domain.Category

class CategoriesEpoxyController : TypedEpoxyController<List<Category>>(
    EpoxyAsyncUtil.getAsyncBackgroundHandler(),
    EpoxyAsyncUtil.getAsyncBackgroundHandler()
) {
    override fun buildModels(data: List<Category>?) {
        data?.forEachIndexed { index, category ->
            categoriesCard {
                id(category.category_id)
                category(category)
            }
        }
    }
}