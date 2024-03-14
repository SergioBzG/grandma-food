package com.restaurant.grandmasfood.controller.utils;

import com.restaurant.grandmasfood.entity.CategoryProduct;
import com.restaurant.grandmasfood.entity.ProductEntity;
import com.restaurant.grandmasfood.model.ProductDto;
import com.restaurant.grandmasfood.repository.IProductRepository;
import com.restaurant.grandmasfood.service.IProductService;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.common.PDRectangle;
import org.apache.pdfbox.pdmodel.font.PDType1Font;
import org.apache.pdfbox.pdmodel.graphics.image.PDImageXObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/menu")
public class PdfController {

    @Autowired
    private IProductService productService;
    private IProductRepository productRepository;
    private ProductDto productDto;

    @GetMapping("/available")
    public  List<ProductDto> findByAvailableTrue() {
        return (this.productService.findByAvailableTrue()
                .stream()
                .filter(ProductDto::getAvailable)
                .collect(Collectors.toList()));
    }
    @GetMapping()
    public ResponseEntity<byte[]> generarPdf() {
        try {
            // Obtener la lista de productos desde la base de datos
            List<ProductDto> productsAvailables = this.productService.findByAvailableTrue();

            // Crear un nuevo documento PDF
            PDDocument document = new PDDocument();
            PDPage page = new PDPage();
            document.addPage(page);

            // Create a stream of content to write on the page
            PDPageContentStream contentStream = new PDPageContentStream(document, page);
            contentStream.beginText();
            float titleX = 200;
            float titleY = 750;
            // font and Size Title and position
            contentStream.setFont(PDType1Font.HELVETICA_BOLD, 16);
            contentStream.newLineAtOffset(titleX, titleY);

            //Title
            contentStream.showText("MENU GRANDMA'S FOOD");
            contentStream.endText();

            float categoryX = 20;
            float categoryY = titleY - 50;
            float productX = categoryX + 20;

            //Group products By Category
            Map<String, List<ProductDto>> productByCategory = new HashMap<>();

            for (ProductDto product : productsAvailables) {
                String category = product.getCategory();

                if (productByCategory.containsKey(category)) {
                    productByCategory.get(category).add(product);
                } else {
                    List<ProductDto> newCategoryList = new ArrayList<>();
                    newCategoryList.add(product);
                    productByCategory.put(category, newCategoryList);
                }
            }

            float productY = 0;
            for (Map.Entry<String, List<ProductDto>> entry : productByCategory.entrySet()) {
                String category = entry.getKey();
                List<ProductDto> productOfCategory = entry.getValue();

                contentStream.beginText();
                contentStream.setFont(PDType1Font.HELVETICA_BOLD, 14);
                contentStream.newLineAtOffset(categoryX, categoryY);
                contentStream.showText("-- " + category);
                contentStream.endText();

                productY = categoryY - 20;

                for (ProductDto product : productOfCategory) {
                    contentStream.beginText();
                    contentStream.setFont(PDType1Font.HELVETICA, 12);
                    contentStream.newLineAtOffset(productX, productY);
                    contentStream.showText("- " + product.getFantasyName() + " : $" + product.getPrice());
                    contentStream.endText();

                    productY -= 20;
                }

                categoryY = productY - 20;


            }

//            PDImageXObject logo = PDImageXObject.createFromFile("/Logo.png", document);

  //          contentStream.drawImage(logo, categoryY, productY, logo.getWidth(), logo.getHeight());


            //Visualize product by category


            // for (ProductDto product : products) {
            //    contentStream.setFont(PDType1Font.HELVETICA_BOLD, 14);
            //    contentStream.showText(product.getCategory().toString());
            //    contentStream.newLine(); // ADD  linea Break
            //    contentStream.setFont(PDType1Font.HELVETICA, 12);
            //    contentStream.newLineAtOffset(0, -50);
            //    contentStream.showText("- " + product.getFantasyName() + " : $" + product.getPrice());

            //}

            //contentStream.endText();
            contentStream.close();

            // Converter Pdf To ByteArray
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            document.save(byteArrayOutputStream);
            document.close();

            // Configure the Response
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_PDF);
            headers.setContentDispositionFormData("Menu GrandMa'sFoods Restaurant", "menu.pdf");

            return new ResponseEntity<>(byteArrayOutputStream.toByteArray(), headers, HttpStatus.OK);
        } catch (IOException e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
