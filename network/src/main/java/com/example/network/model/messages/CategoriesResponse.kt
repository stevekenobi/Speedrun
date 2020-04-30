package com.example.network.model.messages

import com.example.network.model.dto.CategoryDto

data class CategoriesResponse(val data: List<CategoryDto>) : BaseResponse()