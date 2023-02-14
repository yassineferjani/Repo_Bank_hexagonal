package entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import models.Type;

import javax.persistence.*;
import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class Account {
    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private Type type;
    @OneToMany (mappedBy = "account", cascade = CascadeType.ALL)
    private List<Transaction> transactions;
}
