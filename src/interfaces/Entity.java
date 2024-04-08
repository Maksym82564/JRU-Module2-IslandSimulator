package interfaces;

public interface Entity extends Drawable, Runnable {
    String getEntityName();
    int getNutritionValue();
    void setEaten();
}
