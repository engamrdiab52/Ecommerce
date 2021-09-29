package com.amrabdelhamiddiab.bags.presentation.home

import com.airbnb.epoxy.EpoxyAsyncUtil
import com.airbnb.epoxy.TypedEpoxyController
import com.amrabdelhamiddiab.bags.categoriesCard
import com.amrabdelhamiddiab.core.domain.Category

class CategoriesEpoxyController(private val homeViewModel: HomeViewModel) : TypedEpoxyController<List<Category>>(
    EpoxyAsyncUtil.getAsyncBackgroundHandler(),
    EpoxyAsyncUtil.getAsyncBackgroundHandler()
) {

    override fun buildModels(data: List<Category>?) {
        data?.forEachIndexed { _, category ->
            categoriesCard {
                id(category.category_id)
                category(category)
                onClickContent{_ ->
                    when (category.category_id){
                        "Women_Bags" -> this@CategoriesEpoxyController.homeViewModel.buttonWomenCategoryClicked()
                        else ->this@CategoriesEpoxyController.homeViewModel.buttonClicked()
                    }

                }
            }
        }
    }
}