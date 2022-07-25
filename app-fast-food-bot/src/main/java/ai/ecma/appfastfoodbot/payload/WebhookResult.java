package ai.ecma.appfastfoodbot.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class WebhookResult {
    private boolean ok;
    private String description;
}
