package com.example.nav3recipes.basicsaveable

import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.remember
import androidx.core.os.bundleOf
import androidx.lifecycle.DEFAULT_ARGS_KEY
import androidx.lifecycle.HasDefaultViewModelProviderFactory
import androidx.lifecycle.ViewModelStoreOwner
import androidx.lifecycle.viewmodel.CreationExtras
import androidx.lifecycle.viewmodel.MutableCreationExtras
import androidx.lifecycle.viewmodel.compose.LocalViewModelStoreOwner
import androidx.navigation3.runtime.navEntryDecorator
import java.io.Serializable

val StoreKeyToSavedStateDecorator = navEntryDecorator<Any> { entry ->
    val viewModelStoreOwner = LocalViewModelStoreOwner.current
    val childViewModelStoreOwner = remember(viewModelStoreOwner) {
        if (viewModelStoreOwner !is HasDefaultViewModelProviderFactory) {
            viewModelStoreOwner
        } else {
            object :
                ViewModelStoreOwner by viewModelStoreOwner,
                HasDefaultViewModelProviderFactory by viewModelStoreOwner {
                override val defaultViewModelCreationExtras: CreationExtras
                    get() {
                        val key = entry.contentKey
                        return if (key is Serializable) {
                            MutableCreationExtras(viewModelStoreOwner.defaultViewModelCreationExtras).apply {
                                set(DEFAULT_ARGS_KEY, bundleOf("NavKey" to key))
                            }
                        } else {
                            viewModelStoreOwner.defaultViewModelCreationExtras
                        }
                    }
            }

        }
    }

    if (childViewModelStoreOwner != null) {
        CompositionLocalProvider(LocalViewModelStoreOwner provides childViewModelStoreOwner) {
            entry.Content()
        }
    } else {
        entry.Content()
    }
}