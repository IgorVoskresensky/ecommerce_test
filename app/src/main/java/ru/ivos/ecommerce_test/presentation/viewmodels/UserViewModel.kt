package ru.ivos.ecommerce_test.presentation.viewmodels

import android.net.Uri
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import ru.ivos.ecommerce_test.domain.models.local.User
import ru.ivos.ecommerce_test.domain.usecases.user_usecases.*
import ru.ivos.ecommerce_test.utils.Constants.CURRENT_USER_NAME
import ru.ivos.ecommerce_test.utils.Constants.IS_USER_SIGNED_IN_KEY
import javax.inject.Inject

@HiltViewModel
class UserViewModel @Inject constructor(
    private val deleteUserUseCase: DeleteUserUseCase,
    private val getUsersUseCase: GetUsersUseCase,
    private val getUserUseCase: GetUserUseCase,
    private val insertUserUseCase: InsertUserUseCase,
    private val getIsUserSignedInUseCase: GetIsUserSignedInUseCase,
    private val setIsUserSignedInUseCase: SetIsUserSignedInUseCase,
    private val getCurrentUserNameUseCase: GetCurrentUserNameUseCase,
    private val setCurrentUserNameUseCase: SetCurrentUserNameUseCase
) : ViewModel() {

    private var _users = MutableLiveData<List<User>>()
    val users: LiveData<List<User>> get() = _users

    private var _currentUser = MutableLiveData<User?>()
    val currentUser: LiveData<User?> get() = _currentUser

    fun getUsers() = viewModelScope.launch {
        _users.value = getUsersUseCase.invoke()
    }

    fun getUser(name: String) = viewModelScope.launch {
        _currentUser.value = getUserUseCase.invoke(name)
    }

    fun insertUser(user: User) = viewModelScope.launch {
        insertUserUseCase.invoke(user)
        _currentUser.value = user
    }

    fun deleteUser(id: Int) = viewModelScope.launch {
        deleteUserUseCase.invoke(id)
        _currentUser.value = null
    }

    fun updateUserPhoto(uri: Uri) = viewModelScope.launch {
        val oldUser = _currentUser.value!!
        val newUser = User(
            id = oldUser.id,
            firstName = oldUser.firstName,
            lastName = oldUser.lastName,
            email = oldUser.email
        )
        insertUserUseCase.invoke(newUser)
    }

    fun getIsUserSignedIn(): Boolean? = runBlocking {
        getIsUserSignedInUseCase.invoke(IS_USER_SIGNED_IN_KEY)
    }

    fun setIsUserSignedIn(value: Boolean) = runBlocking {
        setIsUserSignedInUseCase.invoke(IS_USER_SIGNED_IN_KEY, value)
    }

    fun getCurrentUserName(): String? = runBlocking {
        getCurrentUserNameUseCase.invoke(CURRENT_USER_NAME)
    }

    fun setCurrentUserName(value: String) = runBlocking {
        setCurrentUserNameUseCase.invoke(CURRENT_USER_NAME, value)
    }
}