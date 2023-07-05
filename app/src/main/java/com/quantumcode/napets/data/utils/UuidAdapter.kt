package com.quantumcode.napets.data.utils

import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.JsonReader
import com.squareup.moshi.JsonWriter
import java.util.UUID

class UuidAdapter: JsonAdapter<UUID>() {
    override fun fromJson(reader: JsonReader): UUID? {
        return UUID.fromString(reader.readJsonValue().toString())
    }

    override fun toJson(writer: JsonWriter, value: UUID?) {
        writer.jsonValue(value.toString())
    }
}
