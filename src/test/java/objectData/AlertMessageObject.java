package objectData;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AlertMessageObject extends GeneralObject {
    public String alertText;
    public String alertNumberValue;

    // Reference type object, it does not store but only redirects to the space allocated to the value
    public AlertMessageObject(String alertText, String alertNumberValue) {
        this.alertText = alertText;
        this.alertNumberValue = alertNumberValue;
    }
}