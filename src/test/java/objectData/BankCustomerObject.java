package objectData;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BankCustomerObject extends GeneralObject {
    private String dateTime;
    private String amount;
    private String transactionType;
    private String customerName;
    private String depositedAmount;
    private String withdrawnAmount;

    public BankCustomerObject(String dateTime, String amount, String transactionType) {
        this.dateTime = dateTime;
        this.amount = amount;
        this.transactionType = transactionType;
    }

    public BankCustomerObject(String filePath) {
        fromJsonToObject(filePath);
    }
}