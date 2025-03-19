package serviceTest;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.skypro.skyshop.exception.NoSuchProductException;
import org.skypro.skyshop.model.basket.BasketItem;
import org.skypro.skyshop.model.basket.ProductBasket;
import org.skypro.skyshop.model.basket.UserBasket;
import org.skypro.skyshop.model.product.Product;
import org.skypro.skyshop.model.product.SimpleProduct;
import org.skypro.skyshop.service.BasketService;
import org.skypro.skyshop.service.StorageService;

import java.util.Collections;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class BasketServiceTest {

    @Mock
    private ProductBasket productBasket;

    @Mock
    private StorageService storageService;

    @InjectMocks
    private BasketService basketService;

    private final UUID existingProductId = UUID.randomUUID();
    private final UUID nonExistingProductId = UUID.randomUUID();

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        Product existingProduct = new SimpleProduct("Apple", 50, existingProductId);
        when(storageService.getProductById(existingProductId)).thenReturn(Optional.of(existingProduct));
        when(storageService.getProductById(nonExistingProductId)).thenReturn(Optional.empty());
    }

    @Test
    void testAddNonExistingProductThrowsException() {
        assertThrows(NoSuchProductException.class, () -> basketService.addProductToBasket(nonExistingProductId));
    }

    @Test
    void testAddExistingProductCallsAddMethod() {
        basketService.addProductToBasket(existingProductId);
        verify(productBasket).addProduct(existingProductId);
    }

    @Test
    void testGetUserBasketWhenEmpty() {
        when(productBasket.getPRODUCTSMAPUUID()).thenReturn(Collections.emptyMap());
        UserBasket userBasket = basketService.getUserBasket();
        assertTrue(userBasket.getItems().isEmpty());
        assertEquals(0.0, userBasket.getTotal());
    }

    @Test
    void testGetUserBasketWithItems() {
        when(productBasket.getPRODUCTSMAPUUID()).thenReturn(Map.of(existingProductId, 2));
        UserBasket userBasket = basketService.getUserBasket();
        assertEquals(1, userBasket.getItems().size());
        BasketItem item = userBasket.getItems().get(0);
        assertEquals(existingProductId, item.getProduct().getId());
        assertEquals(2, item.getQuantity());
    }
}
