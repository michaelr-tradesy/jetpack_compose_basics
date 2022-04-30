package com.example.jetpackcomposebasics

import kotlinx.serialization.decodeFromString
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

interface AppSerializer {
}

class DefaultAppSerializer : AppSerializer {
    val json = Json {
        encodeDefaults = true
        isLenient = true
        allowSpecialFloatingPointValues = true
        allowStructuredMapKeys = true
        prettyPrint = true
        useArrayPolymorphism = false
        ignoreUnknownKeys = true
    }

    inline fun <reified T>String.toObject(): T? {
        return try {
            json.decodeFromString(this) as T
        } catch(t: Throwable) {
            return null
        }
    }

    inline fun <reified T>T.toJson(): String {
        return json.encodeToString(this)
    }
}