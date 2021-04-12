import java.util.ArrayList;

public class LowCarbDiet extends Diet{
    private float minWeightKg;

    public LowCarbDiet(String name, int daysDuration, String purpose,
                       ArrayList<Food> allowedFood,
                       boolean isVegan, float minWeightKg) {
        super(name, daysDuration, purpose, allowedFood, isVegan);
        this.minWeightKg = minWeightKg;
    }

    public float getMinWeightKg() {
        return minWeightKg;
    }

    public void setMinWeightKg(float minWeightKg) {
        this.minWeightKg = minWeightKg;
    }

}
