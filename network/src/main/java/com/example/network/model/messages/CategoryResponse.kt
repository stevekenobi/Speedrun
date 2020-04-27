package com.example.network.model.messages

import com.example.network.model.dto.CategoryDto

data class CategoryResponse(val data: CategoryDto) : BaseResponse()