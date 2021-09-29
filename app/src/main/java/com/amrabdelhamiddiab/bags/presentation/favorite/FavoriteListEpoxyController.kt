package com.amrabdelhamiddiab.bags.presentation.favorite

import com.airbnb.epoxy.EpoxyAsyncUtil
import com.airbnb.epoxy.TypedEpoxyController
import com.amrabdelhamiddiab.bags.emptyCard
import com.amrabdelhamiddiab.bags.favoriteCard
import com.amrabdelhamiddiab.core.domain.Bag

class FavoriteListEpoxyController(private val viewModel: FavoriteViewModel) : TypedEpoxyController<List<Bag>>(
    EpoxyAsyncUtil.getAsyncBackgroundHandler(),
    EpoxyAsyncUtil.getAsyncBackgroundHandler()
) {
    override fun buildModels(data: List<Bag>?) {
        if (! data.isNullOrEmpty()){
            data.forEachIndexed { index, bag ->
                favoriteCard {
                    id(index)
                    bag(bag)
                    onClickContent { _ ->
                        this@FavoriteListEpoxyController.viewModel.buttonGoToFavoritesDetailsClicked()
                        this@FavoriteListEpoxyController.viewModel.addIdValue(bag)
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