// mongeez formatted javascript

// changeset baverty:recipes-01
db.recipes.insert([
  {
    _id: "82e3f389-2bcb-473b-bf8a-b599ede0a209",
    title: "Quiche Lorraine",
    nb_people: 4,
    prep_time: 15,
    cook_time: "20",
    ingredients: [
      {
        product: {$ref: "products", $id: "117ef373-279d-4cde-8beb-e31c379068c4" },
        quantity: {value: 2, unit: "NO_UNIT"}
      },
      {
        product: {$ref: "products", $id: "74714d9c-2ba9-42b2-9467-6ca068af86c5" },
        quantity: {value: 0.5, unit: "LITER"}
      },
      {
        product: {$ref: "products", $id: "7b7d5c8f-4f58-4443-8cc6-fdf0c92209cd" },
        quantity: {value: 200, unit: "GRAM"}
      },
      {
        product: {$ref: "products", $id: "85cc2cbf-453a-446a-9124-67f890d5dcd2" },
        quantity: {value: 50, unit: "GRAM"}
      },
      {
        product: {$ref: "products", $id: "5572d92f-5e7d-46e7-ae90-89d68cdec1d7" },
        quantity: {value: 1, unit: "KILOGRAM"}
      }
    ],
    steps: [
      { index: 1, text: "Préparer la quiche lorraine" },
      { index: 2, text: "Cuire la quiche lorraine dans une casserole" }
    ]
  },
  {
    _id: "8c66f217-6218-47eb-b247-d50c2ea25a9a",
    title: "Poulet Roti",
    nb_people: 1,
    prep_time: 20,
    cook_time: "60",
    ingredients: [
      {
        product: {$ref: "products", $id: "88971bf2-70a4-43f0-8410-3fb9b109248d"},
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
