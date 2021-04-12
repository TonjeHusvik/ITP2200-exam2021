import java.util.ArrayList;

public abstract class Diet {
    private int daysDuration;
    private String purpose;
    private ArrayList<Food> allowedFood;
    private boolean isVegan;
    private String name;

    public Diet(String name, int daysDuration, String purpose, ArrayList<Food> allowedFood, boolean isVegan) {
        this.daysDuration = daysDuration;
        this.purpose = purpose;
        this.allowedFood = allowedFood;
        this.isVegan = isVegan;
        this.name = name;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getDaysDuration() {
        return daysDuration;
    }

    public void setDaysDuration(int daysDuration) {
        this.daysDuration = daysDuration;
    }

    public String getPurpose() {
        return purpose;
    }

    public void setPurpose(String purpose) {
        this.purpose = purpose;
    }

    public ArrayList<Food> getAllowedFood() {
        return allowedFood;
    }

    public void setAllowedFood(ArrayList<Food> allowedFood) {
        this.allowedFood = allowedFood;
    }

    public boolean isVegan() {
        return isVegan;
    }

    public void setVegan(boolean vegan) {
        isVegan = vegan;
    }

    public String writeDuration(Diet d) {
        String result = "";

        int days = daysDuration;
        int years = days / 365;
        int balance = days % 365;
        int months = balance / 30;
        days = balance % 30;
        result =  "This " + d.getName() + " lasts for " + years + " years, " + months + " months and " + days + " days";
        return result;
    }

    public String writeAllowedFood(Diet d) {
        String result = "";

        result = "The following food is allowed in this " + d.getName() +": "+ allowedFood;
        return result;
    }
    /* Rikke - Denne sjekker kun at dietten er vegan
    * Her må vi lage tre tester
    * d.isV = t & f.isV = t
    * d.isV = f & f.isV = t
    * d.isV = t & f.isV = f
    * d.isV = f & f.isV = f
    */
    public void dietRestriction1a(Diet d) {

        for (Food f:d.getAllowedFood()) {
            if (d.isVegan && f.isVegan()){
                System.out.println("True, this diet is vegan");
            }else{
                System.out.println("False, this diet is not vegan");
            }
        }

    }
    /*Tonje - If a diet contains only vegan food, it is considered vegan, even if it is not a
    VeganDiet (e.g., it could be a LowCarbDiet).

    Denne sjekker at dietten inneholder vegansk mat og skal sjekke at d.isVegan blir til true

    Teste en diett hvor alt er vegan
    Teste en diett hvor en eller fler ikke er vegan
    Kanskje fler tester?

     */
    public void dietRestriction1b(Diet d) {
        for (Food f : d.getAllowedFood()) {
            if (f.isVegan()) {
                d.isVegan = true;
            }
        }
    }

    /* Joachim - A VeganDiet cannot contain non-vegan food.
     *
     * Her tester vi at en diett ikke kan kalle seg vegansk, hvis den i sitt allowed food array inneholder et non vegan objekt
     * En test hvor alle f.isvegan food objekter er true
     * En test med en eller fler food objekter som ikke er isVegan
     */
    public boolean veganDietRestriction1c() {
        for (Food f : getAllowedFood()) {
            if (!f.isVegan()) {
                isVegan = false;
                System.out.println("This diet contains non-vegan food");
                return false;
            }
        }
        System.out.println("This diet is vegan");
        return true;
    }
    /* Rune - The preferred meat in a FlexitarianDiet MUST be non-vegan food of protein type.
     *
     * EN hvor begge prefferdmeat ikke er vegan OG foodType er protein
     * En hvor prefferdMeat er vegan OG foodType ikke er protein
     * En hvor PF er vegan Og FT er protein
     * En hvor PF ikke er vegan OG FT ikke er protein
     **
     */

    public static String flexDietRestriction1d(FlexitarianDiet d, Food f) {
        /*if (d.getPreferredMeat().isVegan() || d.getPreferredMeat().getType() != FoodType.Protein) {
            throw new IllegalArgumentException("The preferred meat can't be vegan, and must be a protein");*/

            if((d.getPreferredMeat().isVegan() && f.getType() != FoodType.Protein)) {
                throw new IllegalArgumentException("The preferred meat in FlexitarianDiet is not of FoodType PROTEIN and is VEGAN, don't eat!");
            } else if (f.getType() != FoodType.Protein) {
                throw new IllegalArgumentException("The preferred meat in FlexitarianDiet is NOT of FoodType Protein, DO NOT EAT");
            } else if (d.getPreferredMeat().isVegan()) {
                throw new IllegalArgumentException("The preferred meat in FlexitarianDiet is VEGAN! DO NOT EAT");
            } else
                return "The preferred meat in FlexitarianDiet is not vegan and is of FoodType Protein, ENJOY! =)";
    }


    /*Julie - The maximum carb-type foods that can be included in a LowCarbDiet is two.
     * Her sjekker vi at man ikke kan ha mer enn to food objekter med FoodType.Carb hvis man vil ha en lowcarb diet
     *
     * en test med mer enn 2 FT carb
     * en test med 2 eller mindre FT carb
<<<<<<< HEAD
     * ***/
    public boolean lowCarbRestriction1e() throws IllegalArgumentException {
=======
     */
    public int lowCarbRestriction1e() throws IllegalArgumentException {
>>>>>>> e4ed219d476ccfdba6e0b75c74708bf5ab01110d
        int i = 0;
        for (Food f : getAllowedFood()) {
            if (f.getType().equals(FoodType.Carb)) {
                i = i + 1;
                if (i >= 3) {
                    throw new IllegalArgumentException("You can not have more than two types of carb in a lowcarb diet");
                }
            }
        }
        return true;
    }
}

/*public int lowCarbRestriction1e() throws IllegalArgumentException {
        int i = 0;
        for (Food f : getAllowedFood()) {
            if (f.getType().equals(FoodType.Carb)) {
                i = i + 1;
                if (i >= 3) {
                    throw new IllegalArgumentException("You can not have more than two types of carb in a lowcarb diet");
                }
            }
        }
        return i;
    }*/


