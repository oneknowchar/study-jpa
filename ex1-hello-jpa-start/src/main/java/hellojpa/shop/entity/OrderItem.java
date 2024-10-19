package hellojpa.shop.entity;

import hellojpa.BaseEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity(name = "SHOP_ORDER_ITEM")
@Table(name = "SHOP_ORDER_ITEM")
@Data
public class OrderItem extends BaseEntity{

	@Id @GeneratedValue
	@Column(name = "ORDER_ITEM_ID")
	private Long id;

	@Column(name = "ORDER_ID")
	private Long orderId;

	@Column(name = "ITEM_ID")
	private Long itemId;

	private int orderPrice;

	private int count;
}
