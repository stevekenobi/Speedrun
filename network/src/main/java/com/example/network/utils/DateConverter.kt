package com.example.network.utils

import com.google.gson.*
import java.lang.reflect.Type
import java.util.*

class DateConverter : JsonDeserializer<Date>, JsonSerializer<Date> {

//    throws JsonParseException
    override fun  deserialize(json: JsonElement,  typeOfT:Type,  context: JsonDeserializationContext):Date  {
        return  Date(json.asLong)
    }

    override fun  serialize( src: Date,  typeOfSrc:Type,  context: JsonSerializationContext):JsonElement {
        return  JsonPrimitive(src.time)
    }
}
