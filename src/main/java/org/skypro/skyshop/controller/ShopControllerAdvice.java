package org.skypro.skyshop.controller;

import org.skypro.skyshop.exception.NoSuchProductException;
import org.skypro.skyshop.model.error.ShopError;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ShopControllerAdvice {

    @ExceptionHandler(NoSuchProductException.class)
    public ResponseEntity<ShopError> handleNoSuchProductException(NoSuchProductException ex) {
        // Код ошибки и сообщение
        String errorCode = "PRODUCT_NOT_FOUND";
        String errorMessage = ex.getMessage();

        // Создаем объект ошибки
        ShopError shopError = new ShopError(errorCode, errorMessage);

        // Возвращаем ResponseEntity с HTTP-кодом 404 и объектом ошибки
        return new ResponseEntity<>(shopError, HttpStatus.NOT_FOUND);
    }
}
