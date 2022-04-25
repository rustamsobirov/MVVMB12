package me.ruyeo.mvvmb12.ui.post

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import me.ruyeo.mvvmb12.model.Post
import me.ruyeo.mvvmb12.repository.PostRepository
import me.ruyeo.mvvmb12.utils.UiStateList

class PostViewModel(
    private val postRepository: PostRepository
) : ViewModel() {

    private val _postState = MutableStateFlow<UiStateList<Post>>(UiStateList.EMPTY)
    val postState = _postState

    fun getPosts() = viewModelScope.launch {
        _postState.value = UiStateList.LOADING
        try {
            val posts = postRepository.getPosts()
            _postState.value = UiStateList.SUCCESS(posts)
        } catch (e: Exception) {
            _postState.value = UiStateList.ERROR(e.localizedMessage ?: "No Connection")
        }
    }
}