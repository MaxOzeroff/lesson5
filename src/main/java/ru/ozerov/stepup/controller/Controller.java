package ru.ozerov.stepup.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import ru.ozerov.stepup.dto.*;
import ru.ozerov.stepup.exceptions.*;
import ru.ozerov.stepup.services.InstanceService;
import ru.ozerov.stepup.services.RegisterService;

@RestController
@AllArgsConstructor
public class Controller {
    private final RegisterService registerService;
    private final InstanceService instanceService;

    @PostMapping(value = "/corporate-settlement-account/create",
            consumes = {MediaType.APPLICATION_JSON_VALUE},
            produces = {MediaType.APPLICATION_JSON_VALUE})
    @Operation(
            summary = "Создание продуктового регистра",
            description = "Метод создания продуктового регистра",
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE)),
            responses = {
                    @ApiResponse(responseCode = "200", description = "OK",
                            content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    schema = @Schema(implementation = AccountResponseDto.class))),
                    @ApiResponse(responseCode = "400", description = "Bad Request"),
                    @ApiResponse(responseCode = "404", description = "Not found"),
                    @ApiResponse(responseCode = "500", description = "Internal Server Error")}
    )
    ResponseEntity<AccountResponseDto> createRegister(@RequestBody @Valid AccountRequestDto request)
            throws RegistryTypeCodeAlreadyExistsException, RegistryTypeCodeNotExistsException {
        var result = registerService.create(request);
        return ResponseEntity.ok(result);
    }

    @PostMapping(value = "corporate-settlement-instance/create",
            consumes = {MediaType.APPLICATION_JSON_VALUE},
            produces = {MediaType.APPLICATION_JSON_VALUE})
    @Operation(
            summary = "Создание экземпляра продукта",
            description = "Метод создание экземпляра продукта",
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE)),
            responses = {
                    @ApiResponse(responseCode = "200", description = "OK",
                            content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    schema = @Schema(implementation = AccountResponseDto.class))),
                    @ApiResponse(responseCode = "400", description = "Bad Request"),
                    @ApiResponse(responseCode = "404", description = "Not found"),
                    @ApiResponse(responseCode = "500", description = "Internal Server Error")}
    )
    ResponseEntity<InstanceResponseDto> createInstance(@RequestBody @Valid InstanceRequestDto request)
            throws ContractNumberAlreadyExistsException, ProductCodeNotFoundException, AgreementAlreadyExistsException, ProductNotFoundException {
        var result = instanceService.create(request);
        return ResponseEntity.ok(result);
    }
}
