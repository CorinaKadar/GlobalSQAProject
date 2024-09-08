package objectData;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BankManagerObject extends GeneralObject {
    private String firstName;
    private String lastName;
    private String postCode;
    private String accountNumber;
    private String currency;

    public BankManagerObject(String firstName, String lastName, String postCode, String accountNumber) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.postCode = postCode;
        this.accountNumber = accountNumber;
    }

    public BankManagerObject(String filePath) {
        fromJsonToObject(filePath);
    }
}