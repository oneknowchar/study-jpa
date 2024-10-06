package hellojpa.shop.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity(name = "SHOP_ITEM")
@Table(name="SHOP_ITEM")
@Data
public class Item {
	@Id @GeneratedValue
	@Column(name = "ITEM_ID")
	private Long id;

	private String name;
	
	private int price;
	
	private int sotckQuantity;
}
