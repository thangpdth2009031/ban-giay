package com.example.accountspringdatajpa.entity;

import com.example.accountspringdatajpa.entity.base.BaseEntity;
import com.example.accountspringdatajpa.entity.enums.ProductSimpleStatus;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Set;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "orders")
public class Order extends BaseEntity {

    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    private String id;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private Account account;


    @Column(name = "user_id", updatable = false, insertable = false)
    private Long userId;
    private LocalDateTime createdAt;
    private BigDecimal totalPrice;
    @Enumerated(EnumType.ORDINAL)
    private ProductSimpleStatus status;

    @OneToMany(mappedBy = "order",
            cascade = CascadeType.ALL,//Khi sửa, làm tren thằng này thì nó sẽ tương tác và thay đổi tất cả các thằng liên quan (detach trong cascade: save ,... thì cac thằng liên quan không ảnh hưởng, fetch: lazy, eager)
            fetch = FetchType.LAZY)
//    @OneToMany(mappedBy = "order")
    @JsonManagedReference
    private Set<OrderDetail> orderDetails;

    public void addTotalPrice(OrderDetail orderDetail) {
        if (this.totalPrice == null) {
            this.totalPrice = new BigDecimal(0);
        }
        BigDecimal quantityInBigDecimal = new BigDecimal(orderDetail.getQuantity());
        this.totalPrice = this.totalPrice.add(
                orderDetail.getUnitPrice().multiply(quantityInBigDecimal));
    }
}
