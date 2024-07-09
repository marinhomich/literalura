package dev.marinhomich.literaula.model;


public enum Language {
    ENGLISH("en"),
    PORTUGUESE("pt");

    private final String apiLanguage;

    Language(String apiLanguage) {
        this.apiLanguage = apiLanguage;
    }

    public static Language fromString(String text) {
        for (Language language : Language.values()) {
            if (language.apiLanguage.equalsIgnoreCase(text)) {
                return language;
            }
        }
        throw new IllegalArgumentException("Nenhum idioma encontrado para a opção fornecida");
    }
}