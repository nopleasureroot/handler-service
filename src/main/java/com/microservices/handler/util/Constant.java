package com.microservices.handler.util;

public class Constant {
    // base urls
    public static final String LAMODA_IMAGE_BASE_URL = "https://a.lmcdn.ru/img600x866";
    public static final String LAMODA_BASE_PRODUCT_URL = "https://www.lamoda.ru/p/";
    public static final String LAMODA_BASE_LOGO_URL =
        "https://logosklad.ru/UserFiles/image/lamoda/lamoda-logo-900x600.jpg";

    // scraped field names
    public static final String LAMODA_PRODUCT_NAME_FIELD_VALUE = "seo_title";
    public static final String LAMODA_PRICE_FIELD_VALUE = "price";
    public static final String LAMODA_IMAGE_FIELD_VALUE = "gallery";
    public static final String LAMODA_SKU_FIELD_VALUE = "sku";

    // default field values
    public static final String LAMODA_DEFAULT_PRODUCT_NAME = "NOT_FOUND";

    // utils constant
    public static final String EMPTY_STRING = "";
}
