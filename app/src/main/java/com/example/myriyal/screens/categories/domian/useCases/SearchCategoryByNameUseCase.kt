package com.example.myriyal.screens.categories.domian.useCases

import com.example.myriyal.screens.categories.data.local.CategoryEntity
import com.example.myriyal.screens.categories.domian.repository.CategoryRepository
import kotlinx.coroutines.flow.Flow

class SearchCategoryByNameUseCase (
    private val repository: CategoryRepository
){
    /**
     * Executes the search query.
     * @param query The search string to filter category names.
     * @return A flow of categories matching the query.
     */

    operator fun invoke(query:String): Flow<List<CategoryEntity>>{
        return repository.searchCategoryByName(query)
    }

}