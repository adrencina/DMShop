package com.example.dmshop.ui.viewmodel

import androidx.lifecycle.ViewModel
import com.example.dmshop.domain.model.CartForm
import com.example.dmshop.domain.model.CartItem
import com.example.dmshop.domain.model.CartState
import com.example.dmshop.domain.model.Product
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

enum class FormField {
    NAME, CONTACT, DATE_TIME, ADDRESS, NOTES
}

@HiltViewModel
class SharedCartViewModel @Inject constructor() : ViewModel() {

    private val _items = MutableStateFlow<List<CartItem>>(emptyList())
    val items: StateFlow<List<CartItem>> = _items

    private val _form = MutableStateFlow(CartForm())
    val form: StateFlow<CartForm> = _form

    fun addItem(product: Product, size: String = "M", quantity: Int = 1) {
        val newItem = CartItem(product, size, quantity)
        val currentItems = _items.value.toMutableList()
        
        val existingItem = currentItems.find { 
            it.product.id == product.id && it.size == size 
        }
        
        if (existingItem != null) {
            val index = currentItems.indexOf(existingItem)
            currentItems[index] = existingItem.copy(quantity = existingItem.quantity + quantity)
        } else {
            currentItems.add(newItem)
        }
        
        _items.value = currentItems
    }

    fun updateForm(field: FormField, value: String) {
        val currentForm = _form.value
        val updatedForm = when (field) {
            FormField.NAME -> currentForm.copy(customerName = value)
            FormField.CONTACT -> currentForm.copy(contact = value)
            FormField.DATE_TIME -> currentForm.copy(dateTime = value)
            FormField.ADDRESS -> currentForm.copy(address = value)
            FormField.NOTES -> currentForm.copy(notes = value)
        }
        _form.value = updatedForm
    }

    fun currentCart(): CartState {
        return CartState(
            items = _items.value,
            customerName = _form.value.customerName,
            contact = _form.value.contact,
            dateTime = _form.value.dateTime,
            address = _form.value.address,
            notes = _form.value.notes
        )
    }

    fun clearCart() {
        _items.value = emptyList()
        _form.value = CartForm()
    }
} 