package serviceTest;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.skypro.skyshop.model.article.Article;
import org.skypro.skyshop.model.product.Product;
import org.skypro.skyshop.model.product.SimpleProduct;
import org.skypro.skyshop.model.search.SearchResult;
import org.skypro.skyshop.service.SearchService;
import org.skypro.skyshop.service.StorageService;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

public class SearchServiceTest {

    @Mock
    private StorageService storageService;

    @InjectMocks
    private SearchService searchService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testSearchEmptyStorage() {
        when(storageService.getAllSearchables()).thenReturn(Collections.emptyList());
        assertTrue(searchService.search("test").isEmpty());
    }

    @Test
    void testSearchNoMatchingObjects() {
        Product product = new SimpleProduct("Apple", 50, UUID.randomUUID());
        Article article = new Article("How to choose apples", "Tips...", UUID.randomUUID());
        when(storageService.getAllSearchables()).thenReturn(List.of(product, article));
        assertTrue(searchService.search("Banana").isEmpty());
    }

    @Test
    void testSearchWithMatchingProduct() {
        Product product = new SimpleProduct("Apple", 50, UUID.randomUUID());
        when(storageService.getAllSearchables()).thenReturn(List.of(product));
        Collection<SearchResult> results = searchService.search("Apple");
        assertEquals(1, results.size());
        SearchResult result = results.iterator().next();
        assertEquals("Apple", result.getName());
        assertEquals("PRODUCT", result.getContentType());
    }

    @Test
    void testCaseInsensitiveSearch() {
        Product product = new SimpleProduct("Apple", 50, UUID.randomUUID());
        when(storageService.getAllSearchables()).thenReturn(List.of(product));
        assertFalse(searchService.search("apple").isEmpty());
    }
}
