package com.amrabdelhamiddiab.bags.presentation.categories.women

import com.airbnb.epoxy.EpoxyAsyncUtil
import com.airbnb.epoxy.TypedEpoxyController
import com.amrabdelhamiddiab.bags.categoryCard
import com.amrabdelhamiddiab.bags.emptyCard
import com.amrabdelhamiddiab.core.domain.Bag

class CategoryWomenEpoxyController(private val viewModel: CategoryWomenViewModel) :
    TypedEpoxyController<List<Bag>>(
        EpoxyAsyncUtil.getAsyncBackgroundHandler(),
        EpoxyAsyncUtil.getAsyncBackgroundHandler()
    ) {
    override fun buildModels(data: List<Bag>?) {
        if (! data.isNullOrEmpty()){
            data.forEachIndexed { _, bag ->
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