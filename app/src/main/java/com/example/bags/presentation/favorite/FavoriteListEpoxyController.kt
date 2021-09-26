package com.example.bags.presentation.favorite

import com.airbnb.epoxy.EpoxyAsyncUtil
import com.airbnb.epoxy.TypedEpoxyController
import com.example.bags.cartCard
import com.example.bags.emptyCard
import com.example.bags.favoriteCard
import com.example.core.domain.Bag
import com.example.core.domain.FavoriteOrder

class FavoriteListEpoxyController(private val viewModel: FavoriteViewModel) : TypedEpoxyController<List<Bag>>(
    EpoxyAsyncUtil.getAsyncBackgroundHandler(),
    EpoxyAsyncUtil.getAsyncBackgroundHandler()
) {
    override fun buildModels(data: List<Bag>?) {
        if (! data.isNullOrEmpty()){
            data?.forEachIndexed { index, bag ->
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