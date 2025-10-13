package com.achadinhosdavez.service;

import com.achadinhosdavez.model.Product;
import com.achadinhosdavez.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.math.BigDecimal;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.UUID;

@Service
public class ProductService {

    @Value("${file.upload-dir}")
    private String uploadDir;

    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public Product saveProduct(Product product, MultipartFile[] photos) throws IOException {
        // Garantir que a pasta uploads exista
        Path uploadPath = Paths.get(uploadDir);
        if (!Files.exists(uploadPath)) {
            Files.createDirectories(uploadPath);
        }

        // Salvar até 5 fotos válidas
        String[] savedPhotos = new String[5];
        int photoIndex = 0;

        if (photos != null) {
            for (MultipartFile file : photos) {
                if (file != null && !file.isEmpty() && photoIndex < 5) {
                    String contentType = file.getContentType();
                    if (contentType != null && contentType.startsWith("image/")) {
                        String originalFilename = file.getOriginalFilename();
                        String extension = "";

                        if (originalFilename != null && originalFilename.contains(".")) {
                            extension = originalFilename.substring(originalFilename.lastIndexOf('.'));
                        }

                        String generatedName = UUID.randomUUID().toString() + extension;
                        Path filePath = uploadPath.resolve(generatedName);
                        Files.copy(file.getInputStream(), filePath, StandardCopyOption.REPLACE_EXISTING);
                        savedPhotos[photoIndex] = "/uploads/" + generatedName;
                        photoIndex++;
                    }
                }
            }
        }

        // Atribuir fotos ao product
        product.setPhoto1(savedPhotos[0]);
        product.setPhoto2(savedPhotos[1]);
        product.setPhoto3(savedPhotos[2]);
        product.setPhoto4(savedPhotos[3]);
        product.setPhoto5(savedPhotos[4]);

        // Garantir que preço não seja nulo
        if (product.getPrice() == null) {
            product.setPrice(BigDecimal.ZERO);
        }

        return productRepository.save(product);
    }
}
