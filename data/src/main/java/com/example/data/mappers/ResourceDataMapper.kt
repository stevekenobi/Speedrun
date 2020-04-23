package com.example.data.mappers

import com.example.network.model.dto.ResourceDto
import com.example.storage.model.ResourceEntity

object ResourceDataMapper {

    fun from(resource: ResourceDto): ResourceEntity {
        return ResourceEntity(resource.key, resource.value)
    }

    fun from(resource: ResourceEntity): ResourceDto {
        return ResourceDto(resource.key, resource.value)
    }
}