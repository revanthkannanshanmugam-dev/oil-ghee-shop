# API Integration Guide - Somu Enterprises Frontend

## Backend API Overview

The frontend communicates with the Spring Boot backend running on `http://localhost:8080`.

## Endpoint Used

### Get All Products
```
GET http://localhost:8080/api/products
```

**Response Format:**
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
    },
    {
      "id": 2,
      "name": "Desi Ghee",
      "category": "GHEE",
      "price": 850.0,
      "unit": "KG",
      "stockStatus": "IN_STOCK",
      "imageUrl": "https://example.com/images/desi-ghee.jpg",
      "lastUpdated": "2026-02-16T10:30:00"
    }
  ]
}
```

## API Integration in Frontend

### Location
File: `src/pages/Products.js`

### API Configuration
```javascript
const API_BASE_URL = 'http://localhost:8080/api/products';
```

### Fetch Function
```javascript
const fetchProducts = async () => {
  try {
    setIsLoading(true);
    setError(null);
    const response = await axios.get(API_BASE_URL);
    
    // Handle paginated response
    const productList = response.data.data || response.data;
    setProducts(Array.isArray(productList) ? productList : []);
  } catch (err) {
    setError(err.message || 'Failed to fetch products');
    console.error('Error fetching products:', err);
  } finally {
    setIsLoading(false);
  }
};
```

### Response Handling

The frontend handles the API response structure:
- Extracts `data` array from response
- Falls back to entire response if no `data` field
- Handles both paginated and flat responses
- Provides error feedback to user

### Error Handling

- Network errors → Display error message
- API errors → Display error message
- Empty response → Show "No products found"
- Loading state → Show spinner

## Product Data Model

Each product object contains:

| Field | Type | Description |
|-------|------|-------------|
| `id` | Long | Unique product identifier |
| `name` | String | Product name |
| `category` | String | "OIL" or "GHEE" |
| `price` | Double | Price in rupees |
| `unit` | String | "LITRE" or "KG" |
| `stockStatus` | String | "IN_STOCK" or "OUT_OF_STOCK" |
| `imageUrl` | String | URL to product image |
| `lastUpdated` | String | ISO 8601 timestamp |

## Frontend Filtering

All filtering is done on the client-side:

### Category Filter
```javascript
filtered = filtered.filter(
  (product) => product.category === selectedCategory
);
```

Supports: OIL, GHEE, ALL (no filter)

### Search Filter
```javascript
filtered = filtered.filter((product) =>
  product.name.toLowerCase().includes(searchTerm.toLowerCase())
);
```

Case-insensitive search by product name.

### Combined Filters
Both category and search can be applied simultaneously.

## Axios Configuration

### Installation
```bash
npm install axios
```

### Usage in Components
```javascript
import axios from 'axios';

// GET request
const response = await axios.get(url);
```

### Error Handling
```javascript
try {
  // API call
} catch (error) {
  if (error.response) {
    // Server responded with error
    console.error(error.response.data);
  } else if (error.request) {
    // Request made but no response
    console.error('No response from server');
  } else {
    // Error in request setup
    console.error('Error:', error.message);
  }
}
```

## Testing the API

### Using curl
```bash
curl http://localhost:8080/api/products
```

### Using Browser
Visit: `http://localhost:8080/api/products`

### Using Postman
1. Create GET request
2. URL: `http://localhost:8080/api/products`
3. Send request
4. View response

## Handling API Responses

### Success Response
```javascript
{
  status: "success",
  message: "Products retrieved successfully",
  data: [...products array...]
}
```

### Error Response
```javascript
{
  status: "error",
  message: "Error description"
}
```

## CORS Configuration

Frontend URL: `http://localhost:3000`  
Backend URL: `http://localhost:8080`

CORS is enabled in Spring Boot (`CorsConfig.java`):
- Allowed origins: * (all)
- Allowed methods: GET, POST, PUT, PATCH, OPTIONS
- Allowed headers: * (all)

## Performance Considerations

### API Calls
- Products fetched once on page load
- No pagination implemented (all products in one call)
- For large datasets, consider implementing pagination

### Filtering
- Client-side filtering (fast for reasonable data sizes)
- For 10K+ products, consider server-side filtering

### Caching
- Currently no client-side caching
- Consider adding React Query or similar for advanced caching

## Future API Enhancements

### Support for Pagination
```javascript
const response = await axios.get(`${API_BASE_URL}?page=0&size=10`);
```

### Support for Sorting
```javascript
const response = await axios.get(`${API_BASE_URL}?sort=price,desc`);
```

### Server-Side Filtering
```javascript
const response = await axios.get(`${API_BASE_URL}/category/OIL`);
```

### Search Endpoint
```javascript
const response = await axios.get(`${API_BASE_URL}/search?name=oil`);
```

## Debugging API Issues

### Check Backend Status
```bash
# Verify backend is running
curl http://localhost:8080/api/health
```

### Check Browser Console
- Press F12 → Console tab
- Look for error messages
- Check Network tab for failed requests

### Check Network Requests
1. Open DevTools (F12)
2. Go to Network tab
3. Reload page
4. Look for `/api/products` request
5. Check status code and response

## Common Issues & Solutions

### Issue: "Failed to fetch"
**Cause:** Backend not running or wrong URL  
**Solution:** 
- Start backend: `mvn spring-boot:run`
- Check URL in Products.js

### Issue: CORS error
**Cause:** CORS not configured in backend  
**Solution:**
- Ensure CorsConfig.js exists in backend
- Verify CORS mappings are correct

### Issue: Empty products list
**Cause:** Backend has no sample data  
**Solution:**
- Run backend with dev profile: `mvn spring-boot:run -Dspring-boot.run.arguments="--spring.profiles.active=dev"`

### Issue: Images not loading
**Cause:** Invalid image URL  
**Solution:**
- Frontend fallback: placeholder image used
- Add valid image URLs to products in backend

## API Response Optimization

### Current Response Size
- Example: 6 products ≈ 2-3 KB

### Data Transferred
- Request: ~1 KB
- Response: ~2-3 KB
- Total: ~4 KB per API call

### Optimization Tips
- Implement pagination (fetch 10-20 products per page)
- Lazy load product images
- Compress image URLs
- Cache responses locally

## Monitoring API Performance

### Measure Load Time
```javascript
const startTime = performance.now();
const response = await axios.get(API_BASE_URL);
const endTime = performance.now();
console.log(`API call took ${endTime - startTime}ms`);
```

### Expected Performance
- Local network: 10-50ms
- Remote server: 50-500ms
- Slow connection: 500ms-2s

## Production Deployment

### Change API URL for Production
1. Create `.env` file:
```
REACT_APP_API_URL=https://production-api.com
```

2. Update Products.js:
```javascript
const API_BASE_URL = process.env.REACT_APP_API_URL || 'http://localhost:8080/api/products';
```

3. Build: `npm run build`

### Environment Variables
- `.env` - development
- `.env.production` - production
- `.env.test` - testing

## Security Considerations

- ✅ No sensitive data exposed
- ✅ API calls over HTTP (upgrade to HTTPS in production)
- ✅ Error messages don't leak system info
- ✅ No API keys in frontend code
- ✅ CORS properly configured

## API Documentation

For complete backend API documentation, see:
- Backend README: `Somu-Enterprises/README.md`
- API Docs: `Somu-Enterprises/API_DOCUMENTATION.md`

---

**API Integration Status:** ✅ Complete and tested

