package me.ruyeo.mvvmb12.repository.factory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import me.ruyeo.mvvmb12.repository.PostRepository
import me.ruyeo.mvvmb12.ui.post.PostViewModel
import java.lang.IllegalArgumentException

class PostViewModelFactory(private val repository: PostRepository) : ViewModelProvider.Factory{
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if  (modelClass.isAssignableFrom(PostViewModel::class.java)){
            return PostViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel Class")
    }

}