# Quick Reference Guide - Product API

## 🚀 Quick Start

```bash
# 1. Compile
mvn clean compile

# 2. Run
mvn spring-boot:run

# 3. Access API
curl http://localhost:8080/api/products
```

## 📝 API Quick Reference

| Method | Endpoint | Description |
|--------|----------|-------------|
| GET | `/api/products` | Get all products |
| GET | `/api/products/{id}` | Get product by ID |
| GET | `/api/products/category/{cat}` | Get by category (OIL/GHEE) |
| GET | `/api/products/stock/{status}` | Get by stock (IN_STOCK/OUT_OF_STOCK) |
| GET | `/api/products/search?name=X` | Search by name |
| POST | `/api/products` | Create product |
| PUT | `/api/products/{id}` | Update product |
| PATCH | `/api/products/{id}/stock-status` | Update stock |
| PATCH | `/api/products/{id}/price` | Update price |

## 📦 Request/Response Examples

### Create Product
```json
POST /api/products
{
  "name": "Olive Oil",
  "category": "OIL",
  "price": 750.0,
  "unit": "LITRE",
  "stockStatus": "IN_STOCK",
  "imageUrl": "https://example.com/olive-oil.jpg"
}
```

### Product Response
```json
{
  "id": 1,
  "name": "Olive Oil",
  "category": "OIL",
  "price": 750.0,
  "unit": "LITRE",
  "stockStatus": "IN_STOCK",
  "imageUrl": "https://example.com/olive-oil.jpg",
  "lastUpdated": "2026-02-16T10:30:00"
}
```

## 🔧 Enum Values

**ProductCategory:** OIL, GHEE  
**ProductUnit:** LITRE, KG  
**StockStatus:** IN_STOCK, OUT_OF_STOCK  

## 🗄️ Database Access

**H2 Console:** `http://localhost:8080/h2-console`  
**Username:** sa  
**Password:** (blank)  

## 📂 Key Files

| File | Purpose |
|------|---------|
| `src/main/java/org/example/entity/Product.java` | JPA Entity |
| `src/main/java/org/example/controller/ProductController.java` | REST Endpoints |
| `src/main/java/org/example/service/ProductService.java` | Business Logic |
| `src/main/java/org/example/repository/ProductRepository.java` | Database Queries |
| `pom.xml` | Dependencies |
| `application.properties` | Configuration |

## 🧪 cURL Examples

```bash
# Get all
curl http://localhost:8080/api/products

# Get by ID
curl http://localhost:8080/api/products/1

# Get by category
curl http://localhost:8080/api/products/category/OIL

# Search
curl "http://localhost:8080/api/products/search?name=Oil"

# Create
curl -X POST http://localhost:8080/api/products \
  -H "Content-Type: application/json" \
  -d '{"name":"Olive Oil","category":"OIL","price":750,"unit":"LITRE","stockStatus":"IN_STOCK","imageUrl":"https://example.com/olive-oil.jpg"}'

# Update
curl -X PUT http://localhost:8080/api/products/1 \
  -H "Content-Type: application/json" \
  -d '{"name":"Premium Olive Oil","category":"OIL","price":850,"unit":"LITRE","stockStatus":"IN_STOCK"}'

# Update price
curl -X PATCH "http://localhost:8080/api/products/1/price?price=500"

# Update stock
curl -X PATCH "http://localhost:8080/api/products/1/stock-status?status=OUT_OF_STOCK"
```

## ✅ Status Codes

| Code | Meaning |
|------|---------|
| 200 | Success |
| 201 | Created |
| 400 | Bad Request |
| 404 | Not Found |
| 500 | Server Error |

## 💡 Tips

- Use H2 Console for database inspection
- Sample data loads automatically on startup
- lastUpdated is auto-managed (read-only)
- Use PATCH for partial updates
- Use PUT for full updates
- Category and unit values are case-insensitive in URLs

---

For detailed docs, see README.md and API_DOCUMENTATION.md
