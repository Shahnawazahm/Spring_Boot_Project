package com.redbus.operator.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name="ticket_cost")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class TicketCost {
    @Id
    private String ticketId;

    @Column(name="cost")
    private double cost;

    @Column(name="code")
    private String code;

    @Column(name="discount_Amount",unique = true)
    private double discountAmount;

    @OneToOne
    @JsonIgnore
    @JoinColumn(name = "bus_id")
    private BusOperator busOperator;

    /*@OneToOne
    @JoinColumn(name = "bus_id", referencedColumnName = "busId", insertable = false, updatable = false)
    private BusOperator busOperator;*/
}
