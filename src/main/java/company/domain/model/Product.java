package company.domain.model;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.UUID;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;

@Entity
public class Product {

	@Id
	private UUID id;

	private String name;

	private String description;

	private BigDecimal price;

	private String photo;

	private Boolean active;

	private OffsetDateTime createdAt;

	private OffsetDateTime updatedAt;

	public Product() {
		this.id = UUID.randomUUID();
	}

	public Product(String name, String description, BigDecimal price, Boolean active) {
		this();
		this.name = name;
		this.description = description;
		this.price = price;
		this.active = active;
	}

	public UUID getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	public String getPhoto() {
		return photo;
	}

	public void setPhoto(String photo) {
		this.photo = photo;
	}

	public Boolean getActive() {
		return active;
	}

	public void setActive(Boolean active) {
		this.active = active;
	}

	public OffsetDateTime getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(OffsetDateTime createdAt) {
		this.createdAt = createdAt;
	}

	public OffsetDateTime getUpdatedAt() {
		return updatedAt;
	}

	public void setUpdatedAt(OffsetDateTime updatedAt) {
		this.updatedAt = updatedAt;
	}

	@PrePersist
	private void prePersist() {
		this.createdAt = OffsetDateTime.now();
	}

	@PreUpdate
	private void preUpdate() {
		this.updatedAt = OffsetDateTime.now();
	}

}
