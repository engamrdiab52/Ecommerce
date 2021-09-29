package com.amrabdelhamiddiab.bags.framework

import com.amrabdelhamiddiab.core.useCases.*

data class Interactions(
    val resetUserPassword: ResetUserPassword,
    val sendEmailVerification: SendEmailVerification,
    val signUpUser: SignUpUser,
    val signOutUser: SignOutUser,
    val verifyUserEmail: VerifyUserEmail,
    val signInUser: SignInUser,
    val emailVerifiedState: EmailVerifiedState,
    val downloadFavorites: DownloadFavorites,
    val downloadCategoryWomen: DownloadCategoryWomen,
    val downloadCategories: DownloadCategories,
    val uploadFavoriteItem: UploadFavoriteItem,
    val removeFavoriteItem: RemoveFavoriteItem,
    val downloadCardItems: DownloadCardItems,
    val uploadCardItem: UploadCardItem,
    val removeCardItem: RemoveCardItem
)