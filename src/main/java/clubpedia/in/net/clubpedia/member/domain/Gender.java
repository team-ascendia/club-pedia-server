package clubpedia.in.net.clubpedia.member.domain;

public enum Gender {
    MALE, FEMALE, UNKNOWN;

    public static Gender fromString(String gender) {
        if (gender == null) {
            return UNKNOWN;
        }
        switch (gender.toUpperCase()) {
            case "M":
            case "MALE":
                return MALE;
            case "F":
            case "FEMALE":
                return FEMALE;
            default:
                return UNKNOWN;
        }
    }
}
