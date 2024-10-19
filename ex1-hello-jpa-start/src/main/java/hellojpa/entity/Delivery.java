package hellojpa.entity;

import hellojpa.enums.DeliveryStatus;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import lombok.Getter;
import lombok.Setter;
@Entity
@Getter
@Setter
public class Delivery {
	@Id @GeneratedValue
	private Long id;
	
	private String city;
	private String street;
	private String zipcode;
	private DeliveryStatus deliveryStatus;
	@OneToOne(mappedBy = "ORDER_ID")
	private Order order;
}
