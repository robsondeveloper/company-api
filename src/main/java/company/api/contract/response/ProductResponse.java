package company.api.contract.response;

import java.math.BigDecimal;
import java.util.UUID;

import company.domain.model.Product;

public class ProductResponse {

	private UUID id;
	private String name;
	private String description;
	private BigDecimal price;
	private String photo;
	private Boolean active;

	public ProductResponse(Product product) {
		this.id = product.getId();
		this.name = product.getName();
		this.description = product.getDescription();
		this.price = product.getPrice();
		this.photo = product.getPhoto();
		this.active = product.getActive();
	}

	public UUID getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public String getDescription() {
		return description;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public String getPhoto() {
		return photo;
	}

	public Boolean getActive() {
		return active;
	}

}
