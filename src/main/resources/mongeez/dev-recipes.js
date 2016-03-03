// mongeez formatted javascript

// changeset baverty:recipes-01
db.recipes.insert([
  {
    _id: 1,
    title: "Quiche Lorraine",
    nb_people: 4,
    prep_time: 15,
    cook_time: "20",
    ingredients: [
      {
        product: {$ref: "products", $id: 1 },
        quantity: {value: 2, unit: "NO_UNIT"}
      },
      {
        product: {$ref: "products", $id: 2 },
        quantity: {value: 0.5, unit: "LITER"}
      },
      {
        product: {$ref: "products", $id: 3 },
        quantity: {value: 200, unit: "GRAM"}
      },
      {
        product: {$ref: "products", $id: 4 },
        quantity: {value: 50, unit: "GRAM"}
      },
      {
        product: {$ref: "products", $id: 5 },
        quantity: {value: 1, unit: "KILOGRAM"}
      }
    ],
    steps: [
      { index: 1, text: "Préparer la quiche lorraine" },
      { index: 2, text: "Cuire la quiche lorraine dans une casserole" }
    ]
  },
  {
    _id: 2,
    title: "Poulet Roti",
    nb_people: 1,
    prep_time: 20,
    cook_time: "60",
    ingredients: [
      {
        product: {$ref: "products", $id: 6},
        quantity: {value: 1, unit: "NO_UNIT"}
      }
    ],
    steps: [
      { index: 1, text: "tuer le poulet (en lui tordant le cou)" },
      { index: 2, text: "plumer le poulet" },
      { index: 3, text: "cuire le poulet" }
    ]
  }
]);
