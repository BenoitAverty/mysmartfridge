import { getRecipes } from '../application';

test('getRecipes should return an array', () => {
  const recipes = getRecipes();

  expect(recipes).toEqual(jasmine.any(Array));
});
