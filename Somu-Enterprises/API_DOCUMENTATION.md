# API Documentation

## Somu Enterprises - Edible Oil & Ghee Shop REST API

Complete API reference for managing products in the edible oil and ghee e-commerce store.

### Base URL
```
http://localhost:8080
```

### Response Format

All API responses follow a consistent format:

```json
{
  "status": "success|error",
  "message": "Descriptive message",
  "data": {}
}
```

---

## Endpoints

### Home Endpoints

#### Get Welcome Message
```
GET /
```

**Response:** 200 OK
```json
{
  "status": "success",
  "message": "Welcome to Somu Enterprises Backend API",
  "data": null
}
```

#### Health Check
```
GET /api/health
```

**Response:** 200 OK
```json
{
  "status": "success",
  "message": "API is running",
  "data": null
}
```

---

## Product Endpoints

### Pagination & Sorting Parameters (applies to list endpoints)
- `page` (optional, default 0) - zero-based page index
- `size` (optional, default 10) - number of items per page
- `sort` (optional) - property[,asc|desc]. Example: `sort=price,desc` or `sort=name,asc`

These parameters can be passed as query parameters to the endpoints that return lists.

### Get All Products
```
GET /api/products?page=0&size=10&sort=price,desc
```

**Response:** 200 OK
```json
{
  "status": "success",
  "message": "Products retrieved successfully",
  "data": [
    {
      "id": 1,
      "name": "Sunflower Oil",
      "category": "OIL",
      "price": 450.0,
      "unit": "LITRE",
      "stockStatus": "IN_STOCK",
      "imageUrl": "https://example.com/images/sunflower-oil.jpg",
      "lastUpdated": "2026-02-16T10:30:00"
    }
  ],
  "page": 0,
  "size": 10,
  "totalElements": 6,
  "totalPages": 1
}
```

### Get Product by ID
```
GET /api/products/{id}
```

**Path Parameters:**
- `id` (required): Product ID

**Response:** 200 OK
```json
{
  "status": "success",
  "message": "Product retrieved successfully",
  "data": {
    "id": 1,
    "name": "Sunflower Oil",
    "category": "OIL",
    "price": 450.0,
    "unit": "LITRE",
    "stockStatus": "IN_STOCK",
    "imageUrl": "https://example.com/images/sunflower-oil.jpg",
    "lastUpdated": "2026-02-16T10:30:00"
  }
}
```

**Error Response:** 404 Not Found
```json
{
  "status": "error",
  "message": "Product not found with id: 999"
}
```

### Get Products by Category
```
GET /api/products/category/{category}?page=0&size=10&sort=name,asc
```

**Path Parameters:**
- `category` (required): OIL or GHEE

**Response:** 200 OK
```json
{
  "status": "success",
  "message": "Products retrieved successfully",
  "data": [
    {
      "id": 1,
      "name": "Sunflower Oil",
      "category": "OIL",
      "price": 450.0,
      "unit": "LITRE",
      "stockStatus": "IN_STOCK",
      "imageUrl": "https://example.com/images/sunflower-oil.jpg",
      "lastUpdated": "2026-02-16T10:30:00"
    }
  ],
  "page": 0,
  "size": 10,
  "totalElements": 3,
  "totalPages": 1
}
```

**Error Response:** 400 Bad Request
```json
{
  "status": "error",
  "message": "Invalid category. Valid categories are: OIL, GHEE"
}
```

### Get Products by Stock Status
```
GET /api/products/stock/{status}?page=0&size=10&sort=price,asc
```

**Path Parameters:**
- `status` (required): IN_STOCK or OUT_OF_STOCK

**Response:** 200 OK
```json
{
  "status": "success",
  "message": "Products retrieved successfully",
  "data": [...],
  "page": 0,
  "size": 10,
  "totalElements": 4,
  "totalPages": 1
}
```

**Error Response:** 400 Bad Request
```json
{
  "status": "error",
  "message": "Invalid stock status. Valid values are: IN_STOCK, OUT_OF_STOCK"
}
```

### Search Products by Name
```
GET /api/products/search?name={searchTerm}&page=0&size=10&sort=name,asc
```

**Query Parameters:**
- `name` (required): Search keyword

**Response:** 200 OK
```json
{
  "status": "success",
  "message": "Products retrieved successfully",
  "data": [...],
  "page": 0,
  "size": 10,
  "totalElements": 2,
  "totalPages": 1
}
```

### Create Product
```
POST /api/products
Content-Type: application/json
```

**Request Body:**
```json
{
  "name": "Olive Oil",
  "category": "OIL",
  "price": 750.0,
  "unit": "LITRE",
  "stockStatus": "IN_STOCK",
  "imageUrl": "https://example.com/images/olive-oil.jpg"
}
```

**Response:** 201 Created
```json
{
  "status": "success",
  "message": "Product created successfully",
  "data": {
    "id": 7,
    "name": "Olive Oil",
    "category": "OIL",
    "price": 750.0,
    "unit": "LITRE",
    "stockStatus": "IN_STOCK",
    "imageUrl": "https://example.com/images/olive-oil.jpg",
    "lastUpdated": "2026-02-16T11:00:00"
  }
}
```

**Error Response:** 400 Bad Request
```json
{
  "status": "error",
  "message": "Product with name 'Olive Oil' already exists"
}
```

### Update Product
```
PUT /api/products/{id}
Content-Type: application/json
```

**Path Parameters:**
- `id` (required): Product ID

**Request Body:**
```json
{
  "name": "Extra Virgin Olive Oil",
  "category": "OIL",
  "price": 850.0,
  "unit": "LITRE",
  "stockStatus": "IN_STOCK",
  "imageUrl": "https://example.com/images/ev-olive-oil.jpg"
}
```

**Response:** 200 OK
```json
{
  "status": "success",
  "message": "Product updated successfully",
  "data": {
    "id": 1,
    "name": "Extra Virgin Olive Oil",
    "category": "OIL",
    "price": 850.0,
    "unit": "LITRE",
    "stockStatus": "IN_STOCK",
    "imageUrl": "https://example.com/images/ev-olive-oil.jpg",
    "lastUpdated": "2026-02-16T11:15:00"
  }
}
```

### Update Stock Status
```
PATCH /api/products/{id}/stock-status?status={status}
```

**Path Parameters:**
- `id` (required): Product ID

**Query Parameters:**
- `status` (required): IN_STOCK or OUT_OF_STOCK

**Response:** 200 OK
```json
{
  "status": "success",
  "message": "Stock status updated successfully",
  "data": {
    "id": 1,
    "name": "Sunflower Oil",
    "category": "OIL",
    "price": 450.0,
    "unit": "LITRE",
    "stockStatus": "OUT_OF_STOCK",
    "imageUrl": "https://example.com/images/sunflower-oil.jpg",
    "lastUpdated": "2026-02-16T11:30:00"
  }
}
```

### Update Price
```
PATCH /api/products/{id}/price?price={newPrice}
```

**Path Parameters:**
- `id` (required): Product ID

**Query Parameters:**
- `price` (required): New price value

**Response:** 200 OK
```json
{
  "status": "success",
  "message": "Price updated successfully",
  "data": {
    "id": 1,
    "name": "Sunflower Oil",
    "category": "OIL",
    "price": 500.0,
    "unit": "LITRE",
    "stockStatus": "IN_STOCK",
    "imageUrl": "https://example.com/images/sunflower-oil.jpg",
    "lastUpdated": "2026-02-16T11:45:00"
  }
}
```

---

## Error Codes

| Code | Meaning |
|------|---------|
| 200 | OK - Request successful |
| 201 | Created - Resource successfully created |
| 400 | Bad Request - Invalid data or parameters |
| 404 | Not Found - Resource not found |
| 500 | Internal Server Error - Server-side error |

---

## Testing with cURL

### Create a Product
```bash
curl -X POST http://localhost:8080/api/products \
  -H "Content-Type: application/json" \
  -d '{
    "name": "Refined Oil",
    "category": "OIL",
    "price": 380.0,
    "unit": "LITRE",
    "stockStatus": "IN_STOCK",
    "imageUrl": "https://example.com/refined-oil.jpg"
  }'
```

### Get All Products
```bash
curl http://localhost:8080/api/products
```

### Get Product by ID
```bash
curl http://localhost:8080/api/products/1
```

### Search Products
```bash
curl "http://localhost:8080/api/products/search?name=Oil"
```

### Get Oils
```bash
curl http://localhost:8080/api/products/category/OIL
```

### Get In-Stock Products
```bash
curl http://localhost:8080/api/products/stock/IN_STOCK
```

### Update Product
```bash
curl -X PUT http://localhost:8080/api/products/1 \
  -H "Content-Type: application/json" \
  -d '{
    "name": "Premium Sunflower Oil",
    "category": "OIL",
    "price": 500.0,
    "unit": "LITRE",
    "stockStatus": "IN_STOCK",
    "imageUrl": "https://example.com/premium-oil.jpg"
  }'
```

### Update Price Only
```bash
curl -X PATCH "http://localhost:8080/api/products/1/price?price=550.0"
```

### Update Stock Status
```bash
curl -X PATCH "http://localhost:8080/api/products/1/stock-status?status=OUT_OF_STOCK"
```

---

## Valid Enum Values

### ProductCategory
- `OIL` - Edible oil products
- `GHEE` - Ghee products

### ProductUnit
- `LITRE` - Measured in liters
- `KG` - Measured in kilograms

### StockStatus
- `IN_STOCK` - Product is available
- `OUT_OF_STOCK` - Product is unavailable

---

## Future Enhancements

- [ ] Pagination and Filtering
- [ ] Advanced Search with Multiple Filters
- [ ] Bulk Operations
- [ ] Order Management
- [ ] Customer Reviews and Ratings
- [ ] Wishlist Management
- [ ] Payment Integration
- [ ] Discount and Coupon System
- [ ] API Documentation with Swagger/OpenAPI

---

For more information, see [README.md](README.md)

