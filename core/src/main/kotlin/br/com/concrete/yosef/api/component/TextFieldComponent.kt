package br.com.concrete.yosef.api.component

import android.text.InputType
import android.view.KeyEvent
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import android.widget.EditText
import android.widget.LinearLayout
import br.com.concrete.yosef.OnActionListener
import br.com.concrete.yosef.api.property.DynamicPropertyCommand
import br.com.concrete.yosef.api.property.id.IdCommand
import br.com.concrete.yosef.api.property.id.IdCommand.Companion.ID
import br.com.concrete.yosef.api.property.textfield.MaskPropertyCommand
import br.com.concrete.yosef.api.property.textfield.MaskPropertyCommand.Companion.MASK
import br.com.concrete.yosef.entity.DynamicProperty

/**
 * Command pattern for the TextFieldComponent component
 */
class TextFieldComponent : Component {

    companion object {
        const val TEXT_FIELD = "textField"
    }

    private val commands: Map<String, DynamicPropertyCommand> = mapOf(
            MASK to MaskPropertyCommand(),
            ID to IdCommand()
    )

    override fun applyProperties(view: View, dynamicProperties: List<DynamicProperty>, actionListener: OnActionListener?) {
        (view as EditText).setOnEditorActionListener { textView, actionId, event ->
            if (actionId == EditorInfo.IME_ACTION_SEARCH
                    || actionId == EditorInfo.IME_ACTION_DONE
                    || event.action == KeyEvent.ACTION_DOWN
                    && event.keyCode == KeyEvent.KEYCODE_ENTER) {

                actionListener?.callAction(textView.text.toString())
                return@setOnEditorActionListener true
            }
            return@setOnEditorActionListener false
        }
        dynamicProperties.forEach {
            commands[it.name]?.apply(view, it)
        }
    }

    override fun createView(parent: ViewGroup): View {
        return EditText(parent.context).apply {
            inputType = InputType.TYPE_CLASS_TEXT
            EditorInfo.IME_ACTION_DONE
            layoutParams = LinearLayout.LayoutParams(
                    ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT)
        }
    }
}