package br.com.concrete.yosef.lottie

import br.com.concrete.yosef.entity.DynamicProperty

fun checkMandatoryFields(received: List<DynamicProperty>, vararg mandatory: String) {
    mandatory.forEach { mandatoryField ->
        if (received.find { it.name == mandatoryField } == null)
            throw IllegalArgumentException("Mandatory field '$mandatoryField' " +
                "was not found in the received properties.")
    }
}
