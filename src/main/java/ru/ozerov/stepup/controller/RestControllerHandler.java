package ru.ozerov.stepup.controller;

import jakarta.annotation.Nullable;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import ru.ozerov.stepup.exceptions.*;

@RestControllerAdvice
@RequiredArgsConstructor
public class RestControllerHandler extends ResponseEntityExceptionHandler {
    @Nullable
    protected ResponseEntity<Object> handleContractNumberAlreadyExistsException(ContractNumberAlreadyExistsException ex,
                                                                                HttpHeaders headers, HttpStatusCode status,
                                                                                WebRequest request) {
        return ResponseEntity.badRequest().body(
                String.format("Параметр ContractNumber № договора %s уже существует для ЭП с ИД %s",
                        ex.getContractNumber(), ex.getNumber()
                ));
    }
    @Nullable
    protected ResponseEntity<Object> handleAgreementAlreadyExistsException(AgreementAlreadyExistsException ex,
                                                                           HttpHeaders headers, HttpStatusCode status,
                                                                           WebRequest request) {
        return ResponseEntity.badRequest().body(
                String.format("Параметр № Дополнительного соглашения (сделки) Number %s уже существует для ДС с ИД %s",
                        ex.getContractNumber(), ex.getNumber()
                ));
    }
    @Nullable
    protected ResponseEntity<Object> handleProductCodeNotFoundException(ProductCodeNotFoundException ex,
                                                                        HttpHeaders headers, HttpStatusCode status,
                                                                        WebRequest request) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                String.format("КодПродукта %s не найден в Каталоге продуктов tpp_ref_product_class",
                        ex.getProductCode()
                ));
    }
    @Nullable
    protected ResponseEntity<Object> handleRegistryTypeCodeAlreadyExistsException(RegistryTypeCodeAlreadyExistsException ex,
                                                                                  HttpHeaders headers, HttpStatusCode status,
                                                                                  WebRequest request) {
        return ResponseEntity.badRequest().body(
                String.format("Параметр registryTypeCode тип регистра %s уже существует для ЭП с ИД %s",
                        ex.getRegistryTypeCode(), ex.getNumber()
                ));
    }
    @Nullable
    protected ResponseEntity<Object> handleRegistryTypeCodeNotExistsException(RegistryTypeCodeNotExistsException ex,
                                                                              HttpHeaders headers, HttpStatusCode status,
                                                                              WebRequest request) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                String.format("Код Продукта %s не найден в Каталоге продуктов (tpp_ref_product_class) для данного типа Регистра",
                        ex.getRegistryTypeCode()
                ));
    }
    @Nullable
    protected ResponseEntity<Object> handleProductNotFoundException(ProductNotFoundException ex,
                                                                            HttpHeaders headers, HttpStatusCode status,
                                                                            WebRequest request) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                String.format("Экземпляр продукта с параметром instanceId %s не найден",
                        ex.getInstanceId()
                ));
    }
}
