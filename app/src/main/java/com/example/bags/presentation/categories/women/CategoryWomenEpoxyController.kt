package com.example.bags.presentation.categories.women

import com.airbnb.epoxy.EpoxyAsyncUtil
import com.airbnb.epoxy.TypedEpoxyController
import com.example.bags.categoryCard
import com.example.bags.favoriteCard
import com.example.core.domain.Bag

class CategoryWomenEpoxyController : TypedEpoxyController<List<Bag>>(
    EpoxyAsyncUtil.getAsyncBackgroundHandler(),
    EpoxyAsyncUtil.getAsyncBackgroundHandler()
) {
    override fun buildModels(data: List<Bag>?) {
        data?.forEachIndexed { _, bag ->
            categoryCard {
                id(bag.id_product)
                bag(bag)
            }
        }
    }
}