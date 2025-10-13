package com.achadinhosdavez.controller;

import com.achadinhosdavez.model.Product;
import com.achadinhosdavez.repository.ProductRepository;
import com.achadinhosdavez.service.ProductService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/")
public class ProductController {

    private final ProductRepository productRepository;
    private final ProductService productService;

    public ProductController(ProductRepository productRepository, ProductService productService) {
        this.productRepository = productRepository;
        this.productService = productService;
    }

    @GetMapping
    public String index(Model model) {
        List<Product> products = productRepository.findAll();
        model.addAttribute("products", products);
        return "index";
    }

    @GetMapping("/products/new")
    public String newProductForm(Model model) {
        model.addAttribute("product", new Product());
        return "product_form";
    }

    @PostMapping("/products")
    public String createProduct(@ModelAttribute Product product,
                                @RequestParam(value = "photos", required = false) MultipartFile[] photos,
                                Model model) {
        try {
            int validPhotosCount = 0;
            if (photos != null) {
                for (MultipartFile file : photos) {
                    if (file != null && !file.isEmpty()) {
                        validPhotosCount++;
                    }
                }
            }

            if (validPhotosCount > 5) {
                model.addAttribute("error", "Você pode enviar no máximo 5 fotos.");
                model.addAttribute("product", product);
                return "product_form";
            }

            if (product.getPrice() == null || product.getPrice().compareTo(BigDecimal.ZERO) == 0) {
                model.addAttribute("error", "Preço é obrigatório.");
                model.addAttribute("product", product);
                return "product_form";
            }

            productService.saveProduct(product, photos);
            return "redirect:/";
        } catch (IOException e) {
            e.printStackTrace();
            model.addAttribute("error", "Erro ao salvar imagens: " + e.getMessage());
            model.addAttribute("product", product);
            return "product_form";
        }
    }

    @GetMapping("/products/{id}")
    public String productDetail(@PathVariable Long id, Model model) {
        Optional<Product> optionalProduct = productRepository.findById(id);
        if (optionalProduct.isEmpty()) {
            return "redirect:/";
        }
        Product product = optionalProduct.get();
        model.addAttribute("product", product);
        return "product_detail";
    }

    @GetMapping("/api/products")
    @ResponseBody
    public List<Product> apiProducts() {
        return productRepository.findAll();
    }

    // ✅ Método de exclusão atualizado com debug e validações
    @PostMapping("/products/{id}/delete")
    public String deleteProduct(@PathVariable Long id, RedirectAttributes redirectAttributes) {
        System.out.println("=== DEBUG: Tentando excluir produto com ID = " + id);

        if (id == null || id <= 0) {
            System.out.println("=== ERRO: ID inválido (" + id + ") - Não excluindo nada.");
            redirectAttributes.addFlashAttribute("error", "ID inválido. Tente novamente.");
            return "redirect:/";
        }

        Optional<Product> optionalProduct = productRepository.findById(id);
        if (optionalProduct.isPresent()) {
            System.out.println("=== DEBUG: Produto encontrado: " + optionalProduct.get().getTitle() + " (ID: " + id + ") - Excluindo...");
            productRepository.delete(optionalProduct.get());
            System.out.println("=== DEBUG: Produto excluído com sucesso!");
            redirectAttributes.addFlashAttribute("message", "Produto excluído com sucesso!");
        } else {
            System.out.println("=== ERRO: Produto com ID " + id + " não encontrado.");
            redirectAttributes.addFlashAttribute("error", "Produto não encontrado.");
        }

        return "redirect:/";
    }
}
