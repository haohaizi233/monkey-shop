package com.example.monkey.product.application.dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

/**
 * Creates a tenant-owned catalog item. {@code shopId} is a tenant-local commerce partition key used
 * for sub-order grouping and shop-scoped promotions; it is not a tenant identifier or evidence of
 * merchant ownership. Callers need tenant-wide {@code PRODUCT_MANAGE}. Customer cart requests must
 * still match the canonical value persisted on the SKU's SPU.
 */
public record CatalogCreateSpuRequestDto(
        @NotNull Long categoryId,
        @Positive Long shopId,
        @NotBlank String name,
        @NotBlank String title,
        @NotNull @DecimalMin("0.01") BigDecimal originalPrice,
        BigDecimal memberPrice,
        BigDecimal strikePrice,
        Map<String, BigDecimal> regionPrices,
        Map<String, Object> attributes,
        String detailJsonLd,
        String supplierPrivateRemark,
        String imageUrl,
        @NotEmpty List<@Valid CatalogSpecificationDimensionDto> specifications) {}
