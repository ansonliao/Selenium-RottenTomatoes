package example;

import io.github.sskorol.data.Source;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor(force = true)
@Source(path = "users.yml")
public class User {
    private final String name;
    private final int age;
}
