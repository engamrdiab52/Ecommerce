package com.amrabdelhamiddiab.bags.presentation.cart

import com.airbnb.epoxy.EpoxyAsyncUtil
import com.airbnb.epoxy.TypedEpoxyController
import com.amrabdelhamiddiab.bags.cartCard
import com.amrabdelhamiddiab.bags.emptyCard
import com.amrabdelhamiddiab.core.domain.Bag

class CartListEpoxyController(private val viewModel: CartViewModel) : TypedEpoxyController<List<Bag>>(
    EpoxyAsyncUtil.getAsyncBackgroundHandler(),
    EpoxyAsyncUtil.getAsyncBackgroundHandler()
) {
    override fun buildModels(data: List<Bag>?) {
        if (! data.isNullOrEmpty()){
            data.forEachIndexed { index, bag ->
                cartCard {
                    id(index)
                    bag(bag)
                    onClickContent { _ ->
                        this@CartListEpoxyController.viewModel.buttonGoToCartDetailsClicked()
                        this@CartListEpoxyController.viewModel.addIdValue(bag)
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