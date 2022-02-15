// Herman Lin
// HW1 - Recipe Class

class Ingredient {
    public String item;
    public double measurement;
    
    public Ingredient(String newItem, double newMeasurement) {
        this.item = newItem;
        this.measurement = newMeasurement;
    }
}

public class Recipe {
    
    Ingredient[] ingredients = new Ingredient[10];
    String[] steps = new String[9];
    
    @Override
    public String toString() {
        String output = "Ingredients:\r\n";
        for (Ingredient i : this.ingredients) {
            output += i.measurement + " " + i.item + "\r\n";
        }
        output += "\r\nSteps:\r\n";
        for (String s : this.steps) {
            output += s + "\r\n";
        }
        return output;
    }
    
    public static void main(String[] args) {
        Recipe cookies = new Recipe();
        
        cookies.ingredients[0] = new Ingredient("salted butter", 1);
        cookies.ingredients[1] = new Ingredient("white granulated sugar", 1);
        cookies.ingredients[2] = new Ingredient("light brown sugar", 1);
        cookies.ingredients[3] = new Ingredient("pure vanilla extract", 2);
        cookies.ingredients[4] = new Ingredient("large eggs", 2);
        cookies.ingredients[5] = new Ingredient("all-purpose flour", 3);
        cookies.ingredients[6] = new Ingredient("baking soda", 1);
        cookies.ingredients[7] = new Ingredient("baking powder", 0.5);
        cookies.ingredients[8] = new Ingredient("sea salt", 1);
        cookies.ingredients[9] = new Ingredient("chocolate chips", 2);
        
        cookies.steps[0] = "1. Preheat oven to 375 degrees F. Line a baking pan with parchement paper and set aside";
        cookies.steps[1] = "2. In a separate bowl mix flour, baking soda, salt, baking powder. Set aside.";
        cookies.steps[2] = "3. Cream together butter and sugars until combined.";
        cookies.steps[3] = "4. Beat in eggs and vanilla until fluffy.";
        cookies.steps[4] = "5. Mix in the dry ingredients until combined.";
        cookies.steps[5] = "6. Add 12 oz package of chocolate chips and mix well.";
        cookies.steps[6] = "7. Roll 2-3 TBS (depending on how large you like your cookies) of dough at a time into balls and place them evenly spaced on your prepared cookie sheets. (alternately, use a small cookie scoop to make your cookies).";
        cookies.steps[7] = "8. Bake in preheated oven for approximately 8-10 minutes. Take them out when they are just BARELY starting to turn brown.";
        cookies.steps[8] = "9. Let them sit on the baking pan for 2 minutes before removing to cooling rack.";
    
        System.out.println(cookies.toString());
    }
}