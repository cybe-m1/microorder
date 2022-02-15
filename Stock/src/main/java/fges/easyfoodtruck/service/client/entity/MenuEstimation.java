package fges.easyfoodtruck.service.client.entity;

import javax.persistence.*;

@Entity
@Table(name = "menu_estimation")
public class MenuEstimation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @ManyToOne(optional = false)
    @JoinColumn(name = "id_menu", nullable = false)
    private Menu idMenu;

    @ManyToOne(optional = false)
    @JoinColumn(name = "id_estimation", nullable = false)
    private Estimation idEstimation;

    @Column(name = "quantity", nullable = false)
    private Integer quantity;

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Estimation getIdEstimation() {
        return idEstimation;
    }

    public void setIdEstimation(Estimation idEstimation) {
        this.idEstimation = idEstimation;
    }

    public Menu getIdMenu() {
        return idMenu;
    }

    public void setIdMenu(Menu idMenu) {
        this.idMenu = idMenu;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}
