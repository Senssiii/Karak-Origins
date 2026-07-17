package fr.senssi.karakOrigins.utils.items;

public class SealedItemFormatter {
    public String getSealedText(boolean isSealed) {
        if (isSealed) return "L'objet est scellé.";
        else return "L'objet à été ouvert, le sceau semble avoir été brisé";
    }
}
