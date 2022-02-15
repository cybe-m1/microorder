package fges.easyfoodtruck.service.client.entity;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "estimations")
public class Estimation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_estimation", nullable = false)
    private Integer id;

    @Column(name = "date", nullable = false)
    private LocalDate date;

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}
