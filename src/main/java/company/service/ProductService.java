package company.service;

import java.io.IOException;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import company.api.contract.request.ProductRequest;
import company.api.contract.response.ProductResponse;
import company.domain.model.Product;
import company.domain.repository.ProductRepository;
import company.exception.FileException;
import company.exception.ResourceNotFoundException;
import company.service.storage.PhotoStorageService;

@Service
public class ProductService {

	private ProductRepository repository;
	private PhotoStorageService photoStorageService;

	public ProductService(ProductRepository repository, PhotoStorageService photoStorageService) {
		this.repository = repository;
		this.photoStorageService = photoStorageService;
	}

	public List<ProductResponse> findAll() {
		return repository.findAll().stream().map(this::toResponse).collect(Collectors.toList());
	}

	public ProductResponse findById(UUID id) {
		Product product = findBy(id);
		return toResponse(product);
	}

	@Transactional
	public ProductResponse create(ProductRequest request) {
		Product product = request.toModel();
		if (request.hasFile()) {
			byte[] file = getBytesFrom(request.getFile());
			product.setPhoto(photoStorageService.upload(file));
		}
		return toResponse(repository.save(product));
	}

	@Transactional
	public ProductResponse update(UUID id, ProductRequest request) {
		Product product = findBy(id);
		BeanUtils.copyProperties(request.toModel(), product, "id", "photo", "createdAt", "updatedAt");
		if (request.hasFile()) {
			if (StringUtils.isNotBlank(product.getPhoto())) {
				photoStorageService.delete(product.getPhoto());
			}
			byte[] file = getBytesFrom(request.getFile());
			product.setPhoto(photoStorageService.upload(file));
		}
		return toResponse(repository.save(product));
	}

	@Transactional
	public void delete(UUID id) {
		Product product = findBy(id);
		if (StringUtils.isNotBlank(product.getPhoto())) {
			photoStorageService.delete(product.getPhoto());
		}
		repository.delete(product);
	}

	private Product findBy(UUID id) {
		return repository.findById(id).orElseThrow(() -> new ResourceNotFoundException());
	}

	private ProductResponse toResponse(Product product) {
		return new ProductResponse(product);
	}

	private byte[] getBytesFrom(MultipartFile file) {
		try {
			return file.getBytes();
		} catch (IOException e) {
			throw new FileException();
		}
	}

}
