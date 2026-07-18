package fr.senssi.karakOrigins.utils.items;

public class SealedItemFormatter {
    public static String getSealedText(boolean isSealed, String sealText) {
        if (isSealed) return "[Sceau %s]".formatted(sealText);
        else return "[Sceau brisé %s]".formatted(sealText);
    }
}
