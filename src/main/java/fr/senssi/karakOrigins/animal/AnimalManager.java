package fr.senssi.karakOrigins.animal;

public class AnimalManager {
    public static String genderToString(Gender gender) {
        switch (gender) {
            case MALE:
                return "m";
            case FEMALE:
                return "f";
            default:
                return "u";
        }
    }

    public static Gender stringToGender(String gender) {
        switch (gender) {
            case "f":
                return Gender.FEMALE;
            case "m":
                return Gender.MALE;
            default:
                return Gender.UNKNOWN;
        }
    }
}
