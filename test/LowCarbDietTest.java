
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;

import java.util.ArrayList;

import static org.junit.Assert.*;

public class LowCarbDietTest {
    ArrayList <Food> allowedLowCarbFood = new ArrayList<>();
    ArrayList <Food> allowedLowCarbFoodVegan = new ArrayList<>();


    @Before
    public void setUp() {
        Food lowCarbFood1 = new Food("Cauliflower rice", 23, true, FoodType.Protein);
        Food lowCarbFood2 = new Food("Salad", 20, true, FoodType.Fiber);
        Food lowCarbFood3 = new Food("Tofu", 200, true, FoodType.Protein);
        Food lowCarbFood4 = new Food("Fish", 130, true, FoodType.Protein);
        Food lowCarbFood5 = new Food("Broccoli", 20, true, FoodType.Fiber);
        allowedLowCarbFood.add(lowCarbFood1);
        allowedLowCarbFood.add(lowCarbFood2);
        allowedLowCarbFood.add(lowCarbFood3);
        allowedLowCarbFood.add(lowCarbFood4);
        allowedLowCarbFood.add(lowCarbFood5);

        Food[] jacksAllergies = new Food[2];
        jacksAllergies[0] = new Food("Apple", 35, true, FoodType.Fiber);
        jacksAllergies[1] = new Food("Chocolate", 500, false, FoodType.Recipe);

        Food lowCarbFoodVegan1 = new Food("Apple", 30, true, FoodType.Fiber);
        Food lowCarbFoodVegan2 = new Food("Orange", 50, true, FoodType.Carb);
        Food lowCarbFoodVegan3 = new Food("Seitan", 200, true, FoodType.Carb);
        Food lowCarbFoodVegan4 = new Food("Bread", 400, true, FoodType.Carb);
        Food lowCarbFoodVegan5 = new Food("Lentils", 250, true, FoodType.Protein);
        allowedLowCarbFoodVegan.add(lowCarbFoodVegan1);
        allowedLowCarbFoodVegan.add(lowCarbFoodVegan2);
        allowedLowCarbFoodVegan.add(lowCarbFoodVegan3);
        allowedLowCarbFoodVegan.add(lowCarbFoodVegan4);
        allowedLowCarbFoodVegan.add(lowCarbFoodVegan5);


        Person JACK = new Person(new Food("Chicken", 130, false, FoodType.Protein),
                jacksAllergies, new LowCarbDiet("LowcarbDiet", 30, "Health", allowedLowCarbFood, false, 50),
                               79);

        }


    //TODO a. If a diet contains any non-vegan food, it is considered not vegan (i.e., isVegan = false).
    @Test
    public void  requirement1_a(){

        LowCarbDiet lowCarbD1 = new LowCarbDiet("LowcarbDiet", 30, "Health", allowedLowCarbFood, false, 50);
        for (Food f: lowCarbD1.getAllowedFood()) {
            if(!f.isVegan()){
                assertFalse(f.isVegan());
                System.out.println("not vegan");
            }
        }
    }

    //TODO If a diet contains only vegan food, it is considered vegan, even if it is not a VeganDiet (e.g., it could be a LowCarbDiet).
    @Test
    public void  requirement1_b() throws Exception {
        LowCarbDiet lowCarbD1 = new LowCarbDiet("LowcarbDiet", 30, "Health", allowedLowCarbFoodVegan, false, 50);
        for (Food f : lowCarbD1.getAllowedFood()) {
            Assertions.assertAll("Sjekker om alt er vegansk, yo",
                    () -> assertTrue("Her var ikke alt vegansk, nei!", f.isVegan()));
        }
    }

    //TODO The maximum carb-type foods that can be included in a LowCarbDiet is two.
    @Test
    public void requirment1_e (){
        LowCarbDiet lowCarbD1 = new LowCarbDiet("LowcarbDiet", 90, "Loose 10 kg", allowedLowCarbFoodVegan, false, 50);
        int teller = 0;

        for ( Food f : lowCarbD1.getAllowedFood()) {
            if (f.getType().equals(FoodType.Carb)){
                teller = teller+1;
                System.out.println(teller);

                if(teller <= 2){
                    System.out.println("sec if");
                }


            }

        }



        
    }

    @Test
    public void requirement3_b() {
        LowCarbDiet lowCarbDiet1 = new LowCarbDiet("LowcarbDiet", 90, "Loose weight", allowedLowCarbFood, false, 50);

        String result = lowCarbDiet1.writeAllowedFood(lowCarbDiet1)
                .replace("[",  "")
                .replace("]", "");

        assertEquals("The following food is allowed in this LowcarbDiet: Cauliflower rice, Salad, Tofu, Fish, Broccoli", result);
        System.out.println(result);
    }

    @Test
    public void requirement3_a() {
        LowCarbDiet lowCarbDiet1 = new LowCarbDiet("LowcarbDiet", 730, "Loose weight", allowedLowCarbFood, true, 50);

        String result = lowCarbDiet1.writeDuration(lowCarbDiet1);
        assertEquals("This LowcarbDiet lasts for 2 years, 0 months and 0 days", result);
    }


  /*  @Test
    // Testing intentionally wrong
    public void requirement3_a1() {
        LowCarbDiet lowCarbDiet1 = new LowCarbDiet("LowcarbDiet", 30, "Loose weight", allowedLowCarbFood, true, 50);
        // returning 0 years, 1 months and 0 days
        String result = lowCarbDiet1.writeDuration(lowCarbDiet1);
        assertEquals("This LowCarbDiet lasts for 1 years, 1 months and 5 days", result);
    }*/


}
