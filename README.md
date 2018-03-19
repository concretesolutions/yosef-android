Yosef
===================

Yosef é uma lib que converte JSON em componentes de tela android de forma dinâmica. 
Assim é possível a atualização de view no android sem que seja necessário subir uma nova versão de APK

Formato do JSON
-------------------------------
O json que foi definido para que a lib consiga fazer a conversão a partir de uma estrutura padrão

**DynamicComponent**

O DynamicComponent é referente ao componente que a lib irá criar

* `type`: Tipo do componente que a lib irá criar
* `properties`: É composto de uma lista de componentes que devem ser aplicados no componente
* `children`: Uma lista com os componentes que serão childs do tipo criado

**DynamicProperty**

O DynamicProperty é referente à propriedade que irá adicionada no componente

* `name`: Nome da propriedade a ser adicionada no componente
* `type`: Tipo da propriedade (color, dimen, string)
* `value`: O valor da propriedade

**Exemplos de JSON**

* Componente de Text (TextView) com texto "Teste componente text", textColor #333839 e textSize de 25 e sem childs
```
{
  "type": "text",
  "properties": [
    {
      "name": "text",
      "type": "string",
      "value": "Teste componente text"
    },
    {
      "name": "textColor",
      "type": "color",
      "value": "#333839"
    },
    {
      "name": "textSize",
      "type": "dimen",
      "value": "25"
    }
  ],
  "children": null
}
```

* Componente elementGroup(LinearLayout) com orientation horizontal e botões como children do elementGroup assim alinhando um botão do lado do outro
```
{
  "type": "elementGroup",
  "properties": [
    {
      "name": "orientation",
      "type": "string",
      "value": "horizontal"
    }
  ],
  "children": [
    {
      "type": "button",
      "properties": [
        {
          "name": "text",
          "type": "string",
          "value": "Não"
        }
      ],
      "children": null
    },
    {
      "type": "button",
      "properties": [
        {
          "name": "text",
          "type": "string",
          "value": "Sim"
        }
      ],
      "children": null
    }
  ]
}
```

***Exemplo do funcionamento***

**JSON**
```
[
  {
    "type": "elementGroup",
    "properties": [
      {
        "name": "orientation",
        "type": "string",
        "value": "vertical"
      }
    ],
    "children": [
      {
        "type": "text",
        "properties": [
          {
            "name": "text",
            "type": "string",
            "value": "Teste componente text"
          },
          {
            "name": "textColor",
            "type": "color",
            "value": "#333839"
          },
          {
            "name": "textSize",
            "type": "dimen",
            "value": "25"
          }
        ],
        
      },
      {
        "type": "elementGroup",
        "properties": [
          {
            "name": "orientation",
            "type": "string",
            "value": "horizontal"
          }
        ],
        "children": [
          {
            "type": "button",
            "properties": [
              {
                "name": "action",
                "type": "string",
                "value": "fechar"
              },
              {
                "name": "text",
                "type": "string",
                "value": "Não"
              },
              {
                "name": "backgroundColor",
                "type": "color",
                "value": "#a4a4a4"
              },
              {
                "name": "textColor",
                "type": "color",
                "value": "#515151"
              }
            ],
            "children": null
          },
          {
            "type": "button",
            "properties": [
              {
                "name": "action",
                "type": "string",
                "value": "Sim, com certeza"
              },
              {
                "name": "text",
                "type": "string",
                "value": "Sim"
              },
              {
                "name": "backgroundColor",
                "type": "color",
                "value": "#3F51B5"
              },
              {
                "name": "textColor",
                "type": "color",
                "value": "#ffffff"
              }
            ],
            "children": null
          }
        ]
      }
    ]
  }
]
```

Testes
-------------------------------
Todos os componentes de tela foram desenvolvidos e testados

Para os testes foram utilizados 2 frameworks

* Espresso
* Junit
