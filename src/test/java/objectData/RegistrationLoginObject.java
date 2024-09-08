package objectData;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RegistrationLoginObject extends GeneralObject {
    private String firstNameValue;
    private String lastNameValue;
    private String usernameValue;
    private String passwordValue;

    public RegistrationLoginObject(String filePath) {
        fromJsonToObject(filePath);
    }
}