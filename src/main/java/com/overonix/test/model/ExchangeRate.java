package com.overonix.test.model;

import com.overonix.test.model.enums.DataSupplier;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Set;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ExchangeRate {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Enumerated(value = EnumType.STRING)
    private DataSupplier api;
    private LocalDateTime timestamp;
    private String currency;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "relatedRate")
    private Set<Rate> rates;

    @Override
    public String toString() {
        return "ExchangeRate{" +
                "id=" + id +
                ", api=" + api +
                ", timestamp=" + timestamp +
                ", currency='" + currency + '\'' +
                '}';
    }
}
