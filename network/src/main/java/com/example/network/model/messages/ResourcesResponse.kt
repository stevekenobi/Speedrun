package com.example.network.model.messages

import com.example.network.model.dto.ResourceDto

class ResourcesResponse(val data: List<ResourceDto>) : BaseResponse()