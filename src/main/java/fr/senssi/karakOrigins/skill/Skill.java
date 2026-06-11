package fr.senssi.karakOrigins.skill;

public enum Skill {
    // Alchimie
    ANATOMIE("anatomie"),
    APOTHICAIRERIE("apothicairerie"),
    DISTILLATION_ALCHIMIQUE("distillation_alchimique"),
    TOXICOLOGIE("toxicologie"),

    // Social
    RETHORIQUE("rethorique"),
    ETIQUETTE_COURS("etiquette_cours"),
    CRYPTOGRAPHIE("cryptographie"),
    RESEAU("reseau"),
    FAUX("faux"),

    // Forge
    EXTRACTION_FONTE("extraction_fonte"),
    FORGE("forge"),
    ORFEVRERIE("orfevrerie"),
    FRAPPE_MONNAIE("frappe_monnaie"),
    ARMURERIE("armurerie"),
    ARMURERIE_LOURDE("armurerie_lourde");

    private final String id;

    Skill(String id) {
        this.id = id;
    }

    public static Skill fromId(String id) {
        for (Skill c : values()) {
            if (c.id.equals(id)) return c;
        }
        throw new IllegalArgumentException("Compétence inconnue : " + id);
    }

    public String getId() {
        return id;
    }

}
