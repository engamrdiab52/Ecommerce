package com.example.bags.presentation.categories.women

import com.airbnb.epoxy.EpoxyAsyncUtil
import com.airbnb.epoxy.TypedEpoxyController
import com.example.bags.categoryCard
import com.example.bags.emptyCard
import com.example.bags.favoriteCard
import com.example.core.domain.Bag

class CategoryWomenEpoxyController(private val viewModel: CategoryWomenViewModel) :
    TypedEpoxyController<List<Bag>>(
        EpoxyAsyncUtil.getAsyncBackgroundHandler(),
        EpoxyAsyncUtil.getAsyncBackgroundHandler()
    ) {
    override fun buildModels(data: List<Bag>?) {
        if (! data.isNullOrEmpty()){
            data?.forEachIndexed { _, bag ->
                categoryCard {
                    id(bag.id_product)
                    bag(bag)
                    onClickContent { _ ->
                        this@CategoryWomenEpoxyController.viewModel.addIdValue(bag)
                        this@CategoryWomenEpoxyController.viewModel.buttonGoToDetailsClicked()
                    }

                }
            }
        }else{
            emptyCard {
                id("EMPTY")
            }
        }

    }
}