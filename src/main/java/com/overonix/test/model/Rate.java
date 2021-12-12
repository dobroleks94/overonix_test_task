package com.overonix.test.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Rate {

    @Id
    @GeneratedValue
    private Long id;
    private String name;
    private double value;
    @ManyToOne
    @JsonIgnore
    private ExchangeRate relatedRate;

    @Override
    public String toString() {
        return "Rate{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", value=" + value +
                '}';
    }
}
