package models;

import lombok.Builder;
import lombok.Value;

@Builder
@Value
public class Account {
    long id;
    Type type;
}
