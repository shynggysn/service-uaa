package kz.ne.railways.tezcustoms.service.errors;

public interface Errors {
    String UNSUPPORTED_MEDIA_TYPE = "File is not supported. Please upload an excel file";
    String USER_NOT_FOUND = "User not found";
    String INVOICE_EXISTS = "Invoice already exists";
    String INVALID_PARAMETERS = "Invalid parameters supplied";
    String STATION_NOT_FOUND = "Station not found";
    String COUNTRY_NOT_FOUND = "Country not found";
    String CUSTOM_ORGAN_NOT_FOUND = "Custom organ not found";
    String TRANSIT_CODE_NOT_FOUND = "Transit code not found";
    String IIN_TAKEN = "IIN/BIN is already taken";
    String EMAIL_IN_USE = "Email is already in use";
    String ROLE_CANNOT_BE_EMPTY = "Role can not be empty";
    String ROLE_NOT_FOUND = "Role is not found";
    String INVALID_ROLE = "Role is invalid";
    String INVALID_USER = "NOT_VALID_USER";
    String IIN_NOT_MATCH = "IIN/BIN does not match";
}
