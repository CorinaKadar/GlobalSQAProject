package objectData;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AlertMessageObject extends GeneralObject {
    public String alertText;
    public String alertNumberValue;

    public AlertMessageObject(String alertText, String alertNumberValue) {
        this.alertText = alertText;
        this.alertNumberValue = alertNumberValue;
    }
}