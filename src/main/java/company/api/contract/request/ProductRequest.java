package company.api.contract.request;

import java.math.BigDecimal;
import java.util.Objects;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

import org.springframework.web.multipart.MultipartFile;

import company.domain.model.Product;

public class ProductRequest {

	@NotBlank
	private String name;

	private String description;

	@NotNull
	@Positive
	private BigDecimal price;

	private MultipartFile file;

	@NotNull
	private Boolean active;

	public void setName(String name) {
		this.name = name;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	public MultipartFile getFile() {
		return file;
	}

	public void setFile(MultipartFile file) {
		this.file = file;
	}

	public void setActive(Boolean active) {
		this.active = active;
	}

	public boolean hasFile() {
		return Objects.nonNull(file) && file.getSize() > 0;
	}

	public Product toModel() {
		return new Product(name, description, price, active);
	}

}
