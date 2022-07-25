package ai.ecma.library.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class RabbitMQMessageTest implements Serializable {
    private String username;
    private String message;
    private int age;
}
