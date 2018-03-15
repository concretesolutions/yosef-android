package br.com.concrete.yosef.entity

import com.google.gson.annotations.SerializedName
import org.jetbrains.annotations.Nullable

data class DynamicComponent(val type: String, @Nullable val children: List<DynamicComponent>?,
                            @SerializedName("properties") val dynamicProperties: List<DynamicProperty>)

data class DynamicProperty(val name: String, val type: String, val value: String)
