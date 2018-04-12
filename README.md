Yosef
===================
[![Build](https://circleci.com/gh/concretesolutions/yosef-android.svg?style=shield)](https://circleci.com/gh/concretesolutions/yosef-android)

Yosef is a multi-platform library that generates views from JSON specification.
It's useful for A/B testing and providing users with dynamic content without shipping updates to the Play Store.


![](art/artboard.png)

JSON Specification
-------------------------------

The JSON specification is very simple. We only have two entity models that describe how to represent a view (component) 
and how to style these components (properties). Our goal was to make the library as extensible as possible,
so you can create new components or properties as you grow your project (don't forget to contribute some of them 😊).

**DynamicComponent**

This entity represents what kind of component is going to be created by the `ViewCreator`:

* `type`: Type (name) used to find the component inside the `ViewCreator`
* `properties`: A list of `DynamicProperty` elements that should be applied to the component
* `children`: A list of `DynamicComponent` elements that will be added to this component as children (only supported by `ElementGroup` and `RadioGroupButton` as of now).

**DynamicProperty**

This entity represents a command that will be used to style and configure a `DynamicComponent` and has the following attributes:

* `name`: Name that will be associated to the command to be applied to the component
* `type`: Property type (e.g color, dimen, string, integer, float)
* `value`: The value associated to this property

You can see some example custom components we've built by taking a look at this project's modules,
specifically:

- [Picasso][1]
- [Glide][2]
- [Canarinho][3]
- [Lottie][4]


**Example usage**

The JSON

```json

  {
    "type": "text",
    "properties": [
      {
        "name": "text",
        "type": "string",
        "value": "Hello, Android!"
      },
      {
        "name": "textColor",
        "type": "color",
        "value": "#4A4A4A"
      },
      {
        "name": "textSize",
        "type": "dimen",
        "value": "30"
      }
    ],
    "children": null
  }
```

and Kotlin code

```kotlin

// a json string following the specification
val json = readJson("assets/example.json")

val creator = DynamicViewCreator.Builder()
    .build()

// an ActionListener callback
val actionListener : OnActionListener? = null
val view = creator.createViewFromJson(context, json, actionListener)

// setContentView or add to a parent
```

will produce

![](art/screenshot_example.png)


**Adding an action listener**

The action listener works as a callback for actions that can be performed in certain components.
Sort of like a click listener but it can be broader, depending on the component.

To add actions to your components, you must add a new property named "action" and if the component
has any actions registered, the `callAction` method is going to be called with the value associated 
to that property:

```json
{
    "type": "button",
    "properties": [
      {
        "name": "action",
        "type": "string",
        "value": "tappedButtonNo"
      },
      {
        "name": "text",
        "type": "string",
        "value": "Nope"
      }
    ],
    "children": null
}
```


**Using a custom component**

If you need extra components in your project, you can register this new component and its type (name) when building the `DynamicViewCreator`:

```kotlin

val creator = DynamicViewCreator.Builder()
    .addComponent("image", PicassoImageComponent())
    .build()

val view = creator.createViewFromJson(context, json, actionListener)
```

And now you are able to render things like:

```json

  {
    "type": "image",
    "properties": [
      {
        "name": "url",
        "type": "string",
        "value": "http://example.com/example.jpg"
      },
      {
        "name": "width",
        "type": "dimen",
        "value": "300"
      },
      {
        "name": "height",
        "type": "dimen",
        "value": "300"
      }
    ],
    "children": null
  }

```

Contributing your code
-------------------------------

See [CONTRIBUTING.md][contributing]


[1]: picasso
[2]: glide
[3]: canarinho
[4]: lottie
[contributing]: CONTRIBUTING.md
