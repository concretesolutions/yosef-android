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
import br.com.concrete.yosef.api.property.size.HeightCommand
import br.com.concrete.yosef.api.property.size.HeightCommand.Companion.HEIGHT_TYPE
import br.com.concrete.yosef.api.property.size.WidthCommand
import br.com.concrete.yosef.api.property.size.WidthCommand.Companion.WIDTH_TYPE
import br.com.concrete.yosef.entity.DynamicProperty

/**
 * Class that implements the [Component] interface and creates the component
 * TextField([EditText]), applying its properties
 */
class TextFieldComponent : Component {

    companion object {
        /**
         * This constant documents which type is associated with the component
         */
        const val TEXT_FIELD = "textField"
    }

    private val commands: Map<String, DynamicPropertyCommand> = mapOf(
        WIDTH_TYPE to WidthCommand(),
        HEIGHT_TYPE to HeightCommand(),
        ID to IdCommand()
    )

    override fun applyProperties(
        view: View,
        dynamicProperties: List<DynamicProperty>,
        actionListener: OnActionListener?
    ) {
        (view as EditText).setOnEditorActionListener { textView, actionId, event ->
            if (isEnterPressed(actionId, event)) {
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

    private fun isEnterPressed(actionId: Int, event: KeyEvent): Boolean {
        return actionId == EditorInfo.IME_ACTION_SEARCH
            || actionId == EditorInfo.IME_ACTION_DONE
            || event.action == KeyEvent.ACTION_DOWN
            && event.keyCode == KeyEvent.KEYCODE_ENTER
    }
}
