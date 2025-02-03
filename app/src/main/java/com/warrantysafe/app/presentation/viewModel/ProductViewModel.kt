package com.warrantysafe.app.presentation.viewModel

import android.annotation.SuppressLint
import android.content.Context
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.warrantysafe.app.domain.model.Product
import com.warrantysafe.app.domain.useCases.AddProductUseCase
import com.warrantysafe.app.domain.useCases.DeleteProductsUseCase
import com.warrantysafe.app.domain.useCases.GetActiveProductsUseCase
import com.warrantysafe.app.domain.useCases.GetAllProductsUseCase
import com.warrantysafe.app.domain.useCases.GetExpiredProductsUseCase
import com.warrantysafe.app.domain.useCases.GetProductDetailUseCase
import com.warrantysafe.app.domain.useCases.UpdateProductUseCase
import com.warrantysafe.app.domain.utils.Results
import com.warrantysafe.app.utils.checkValidNetworkConnection
import kotlinx.coroutines.launch

class ProductViewModel(
    private val getAllProductsUseCase: GetAllProductsUseCase,
    private val getActiveProductsUseCase: GetActiveProductsUseCase,
    private val getExpiredProductsUseCase: GetExpiredProductsUseCase,
    private val getProductDetailUseCase: GetProductDetailUseCase,
    private val addProductUseCase: AddProductUseCase,
    private val updateProductUseCase: UpdateProductUseCase,
    private val deleteProductsUseCase: DeleteProductsUseCase
) : ViewModel() {

    private val currentDate = getCurrentDate()

    private val _allProductsState = MutableLiveData<Results<List<Product>>>()
    val allProductsState: LiveData<Results<List<Product>>> get() = _allProductsState

    private val _productDetailState = MutableLiveData<Results<Product>>()
    val productDetailState: LiveData<Results<Product>> get() = _productDetailState

    private val _activeProductsState = MutableLiveData<Results<List<Product>>>()
    val activeProductsState: LiveData<Results<List<Product>>> get() = _activeProductsState

    private val _expiredProductsState = MutableLiveData<Results<List<Product>>>()
    val expiredProductsState: LiveData<Results<List<Product>>> get() = _expiredProductsState

    private val _addProductState = MutableLiveData<Results<Unit>>()
    val addProductState: LiveData<Results<Unit>> get() = _addProductState

    private val _updateProductState = MutableLiveData<Results<Product>>()
    val updateProductState: LiveData<Results<Product>> get() = _updateProductState

    private val _deleteProductsState = MutableLiveData<Results<Unit>>()
    val deleteProductsState: LiveData<Results<Unit>> get() = _deleteProductsState

    // Load Product Detail
    fun loadProductDetail(productId: String) {
        viewModelScope.launch {
            try {
                val result = getProductDetailUseCase(productId)
                _productDetailState.value = result
                Log.d("ProductDetail", "Product Detail Loaded: $result") // Debugging log
            } catch (e: Exception) {
                _productDetailState.value = Results.Failure(e)
                Log.e("ProductDetail", "Error Loading Product Detail: ${e.message}", e) // Debugging log
            }
        }
    }

    // Load all products
    fun loadAllProducts() {
        viewModelScope.launch {
            _allProductsState.value=Results.Loading
            try {
                val products = getAllProductsUseCase()
                _allProductsState.value = products
            } catch (e: Exception) {
                _allProductsState.value = Results.Failure(e)
            }
        }
    }

    // Load active products
    fun loadActiveProducts() {
        viewModelScope.launch {
            try {
                _activeProductsState.value = Results.Loading
                val products = getActiveProductsUseCase(currentDate)
                _activeProductsState.value = Results.Success(products)
               Log.d("ActiveProducts","Active Products Loaded: $products") // Debugging log
            } catch (e: Exception) {
                _activeProductsState.value = Results.Failure(e)
                Log.e("ActiveProducts","Error Loading Active Products: ${e.message}",e) // Debugging log
            }
        }
    }

    // Load expired products
    fun loadExpiredProducts() {
        viewModelScope.launch {
            _expiredProductsState.value = Results.Loading
            try {
                val products = getExpiredProductsUseCase(currentDate)
                _expiredProductsState.value = Results.Success(products)
            } catch (e: Exception) {
                _expiredProductsState.value = Results.Failure(e)
            }
        }
    }

    fun addProduct(context: Context,product: Product) {
        // Check for valid internet connection before proceeding
        if (!checkValidNetworkConnection(context)) {
            _addProductState.value = Results.Failure(Exception("No valid internet connection"))
            return
        }

        // Start the "loading" state
        _addProductState.value = Results.Loading

        viewModelScope.launch {
            try {
                // Simulate adding a product (this could be a network request or DB operation)
                val response = addProductUseCase(product)

                // If successful, update the state with a Success result (with no data in this case)
                _addProductState.value = Results.Success(response)
            } catch (e: Exception) {
                // If there's an error, update the state with a Failure result
                _addProductState.value = Results.Failure(e)
            }
        }
    }

    // Update an existing product
    fun updateProduct(product: Product) {
        viewModelScope.launch {
            _updateProductState.value= Results.Loading
            try {
                val result = updateProductUseCase.invoke(product)
                _updateProductState.value = result
                refreshAllProducts()
            } catch (e: Exception) {
                _updateProductState.value = Results.Failure(e)
            }
        }
    }

    // Delete products
    fun deleteProducts(products: List<Product>) {
        viewModelScope.launch {
            try {
                deleteProductsUseCase(products)
                _deleteProductsState.value = Results.Success(Unit)
                refreshAllProducts()
            } catch (e: Exception) {
                _deleteProductsState.value = Results.Failure(e)
            }
        }
    }

    // Refresh all product lists
    private fun refreshAllProducts() {
        loadAllProducts()
        loadActiveProducts()
        loadExpiredProducts()
    }

    // Helper function to get the current date in "dd/MM/yyyy" format
    @SuppressLint("NewApi")
    private fun getCurrentDate(): String {
        return java.time.LocalDate.now().format(java.time.format.DateTimeFormatter.ofPattern("dd/MM/yyyy"))
    }
}

