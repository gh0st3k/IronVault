package ghostek.ironvault.customer;

import ghostek.ironvault.common.exception.InvalidInputException;

import java.util.Locale;
import java.util.Set;
import java.util.regex.Pattern;

public final class DocumentValidator {

    // Official Spanish check letters: index = number % 23
    private static final String CHECK_LETTERS = "TRWAGMYFPDXBNJZSQVHLCKE";

    private static final Pattern DNI_PATTERN = Pattern.compile("\\d{8}[A-Z]");
    private static final Pattern NIE_PATTERN = Pattern.compile("[XYZ]\\d{7}[A-Z]");
    // Passport formats differ by country; we only allow letters and digits
    private static final Pattern PASSPORT_PATTERN = Pattern.compile("[A-Z0-9]{5,20}");

    // All ISO 3166-1 two-letter country codes the JDK knows, built once
    private static final Set<String> ISO_COUNTRIES =
            Locale.getISOCountries(Locale.IsoCountryCode.PART1_ALPHA2);

    private DocumentValidator() {
        // Utility class: only static methods, no instances
    }

    /* Uppercase, and remove spaces and hyphens: "12345678-z" -> "12345678Z". */
    public static String normalize(String documentNumber) {
        if (documentNumber == null) {
            return null;
        }
        return documentNumber.replaceAll("[\\s-]", "").toUpperCase(Locale.ROOT);
    }

    /* Throws InvalidInputException if the document is not valid. Expects normalized values. */
    public static void validate(DocumentType type, String country, String number) {
        if (type == null || country == null || number == null) {
            throw new InvalidInputException("Document type, country and number are required");
        }

        if (!ISO_COUNTRIES.contains(country)) {
            throw new InvalidInputException("Unknown country code: " + country);
        }

        if ((type == DocumentType.DNI || type == DocumentType.NIE) && !country.equals("ES")) {
            throw new InvalidInputException(type + " documents must be issued in ES");
        }

        boolean valid = switch (type) {
            case DNI -> isValidDni(number);
            case NIE -> isValidNie(number);
            case PASSPORT -> PASSPORT_PATTERN.matcher(number).matches();
        };

        if (!valid) {
            throw new InvalidInputException("Invalid " + type + " number: " + number);
        }
    }

    private static boolean isValidDni(String number) {
        if (!DNI_PATTERN.matcher(number).matches()) {
            return false;
        }
        return hasValidCheckLetter(number.substring(0, 8), number.charAt(8));
    }

    private static boolean isValidNie(String number) {
        if (!NIE_PATTERN.matcher(number).matches()) {
            return false;
        }
        // X -> 0, Y -> 1, Z -> 2, then the same algorithm as the DNI
        String prefix = switch (number.charAt(0)) {
            case 'X' -> "0";
            case 'Y' -> "1";
            default -> "2";
        };
        return hasValidCheckLetter(prefix + number.substring(1, 8), number.charAt(8));
    }

    private static boolean hasValidCheckLetter(String eightDigits, char letter) {
        int number = Integer.parseInt(eightDigits);
        return CHECK_LETTERS.charAt(number % 23) == letter;
    }
}