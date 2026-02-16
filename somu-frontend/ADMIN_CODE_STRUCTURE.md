# Admin Panel - Code Structure Reference

## File Locations

```
C:\Users\revan\Downloads\SomuEnterprises\
└── somu-frontend\
    ├── src\
    │   ├── pages\
    │   │   ├── AdminPage.js (404 lines) ← NEW
    │   │   ├── AdminPage.css (650+ lines) ← NEW
    │   │   ├── ADMIN_PANEL_GUIDE.md ← NEW
    │   │   ├── Home.js
    │   │   ├── Home.css
    │   │   ├── Products.js
    │   │   └── Products.css
    │   ├── App.js ← UPDATED
    │   ├── App.css
    │   ├── index.js
    │   └── index.css
    ├── public\
    │   └── index.html
    ├── ADMIN_PANEL_IMPLEMENTATION.md ← NEW
    ├── ADMIN_QUICK_START.md ← NEW
    ├── ADMIN_PANEL_COMPLETE.md ← NEW
    ├── package.json
    └── ...
```

## AdminPage.js Structure

### Imports
```javascript
import React, { useState, useEffect } from 'react';
import './AdminPage.css';
```

### Component Declaration
```javascript
function AdminPage() {
  // ...
}
export default AdminPage;
```

### State Variables (8 total)
```javascript
const [products, setProducts] = useState([]);
const [isLoading, setIsLoading] = useState(false);
const [error, setError] = useState(null);
const [successMessage, setSuccessMessage] = useState('');
const [showAddForm, setShowAddForm] = useState(false);
const [showPriceModal, setShowPriceModal] = useState(false);
const [selectedProductForPrice, setSelectedProductForPrice] = useState(null);
const [newPrice, setNewPrice] = useState('');
```

### Constants
```javascript
const API_BASE_URL = 'http://localhost:8080/api/products';
```

### Functions
```javascript
1. fetchProducts() - async function to GET all products
2. handleAddProduct(e) - async function to POST new product
3. handleUpdatePrice() - async function to PATCH product price
4. handleToggleStock(product) - async function to PATCH stock status
5. handleDeleteProduct(productId, productName) - async function to DELETE product
```

### Effects (2 total)
```javascript
1. useEffect(() => { fetchProducts(); }, [])
   - Runs on component mount
   - Fetches all products
   
2. useEffect(() => { 
     if (successMessage) {
       const timer = setTimeout(() => setSuccessMessage(''), 3000);
       return () => clearTimeout(timer);
     }
   }, [successMessage])
   - Runs when successMessage changes
   - Auto-dismisses message after 3 seconds
```

### JSX Structure
```javascript
<div className="admin-page">
  ├── <div className="admin-header">
  │   ├── <h1>Admin Dashboard</h1>
  │   └── <p className="admin-subtitle">
  │
  ├── {error && <div className="alert alert-error">}
  ├── {successMessage && <div className="alert alert-success">}
  │
  └── <div className="admin-section">
      ├── <div className="section-header">
      │   ├── <h2>Products</h2>
      │   └── <button>+ Add New Product</button>
      │
      ├── {showAddForm && <form className="add-product-form">}
      │   ├── <div className="form-row">
      │   │   ├── <div className="form-group"> name
      │   │   ├── <div className="form-group"> category
      │   │   └── ...
      │   └── <div className="form-actions">
      │       ├── <button type="submit">Add Product</button>
      │       └── <button type="button">Cancel</button>
      │
      ├── <div className="products-table-wrapper">
      │   ├── {isLoading && <div className="loading">}
      │   ├── {products.length === 0 && <div className="empty-state">}
      │   └── <table className="products-table">
      │       ├── <thead>
      │       │   └── <tr>
      │       │       ├── <th>Name</th>
      │       │       ├── <th>Category</th>
      │       │       ├── <th>Price</th>
      │       │       ├── <th>Unit</th>
      │       │       ├── <th>Stock Status</th>
      │       │       └── <th>Actions</th>
      │       │
      │       └── <tbody>
      │           └── {products.map(product => <tr>)}
      │               ├── <td>{product.name}</td>
      │               ├── <td><span className="badge">{product.category}</span></td>
      │               ├── <td className="price">₹{product.price}</td>
      │               ├── <td>{product.unit}</td>
      │               ├── <td><span className="badge">{product.stockStatus}</span></td>
      │               └── <td className="actions">
      │                   ├── <button onClick={price}>💰 Price</button>
      │                   ├── <button onClick={toggleStock}>🔄 Stock</button>
      │                   └── <button onClick={delete}>🗑️ Delete</button>
      │
      └── {showPriceModal && <div className="modal-overlay">
          └── <div className="modal">
              ├── <div className="modal-header">
              │   ├── <h3>Update Price</h3>
              │   └── <button className="close-btn">×</button>
              │
              ├── <div className="modal-body">
              │   ├── <p>{product.name}</p>
              │   ├── <p>₹{product.price}</p>
              │   └── <input type="number" placeholder="New price">
              │
              └── <div className="modal-footer">
                  ├── <button>Cancel</button>
                  └── <button>Update Price</button>
```

## AdminPage.css Structure

### Main Sections
```css
1. .admin-page - Main container
2. .admin-header - Header section
3. .alert - Alert messages
4. .admin-section - Content section
5. .btn - Button styles
6. .add-product-form - Form styling
7. .products-table - Table styling
8. .badge - Status badges
9. .modal-overlay - Modal overlay
10. .modal - Modal styling
11. Media queries - Responsive design
```

### Key Classes
```css
/* Layout */
.admin-page { max-width: 1400px; margin: 0 auto; }

/* Buttons */
.btn { padding: 10px 20px; border: none; border-radius: 6px; }
.btn-primary { background-color: #007bff; color: white; }
.btn-success { background-color: #28a745; color: white; }
.btn-secondary { background-color: #6c757d; color: white; }
.btn-edit { background-color: #ffc107; color: #333; }
.btn-toggle { background-color: #17a2b8; color: white; }
.btn-delete { background-color: #dc3545; color: white; }

/* Forms */
.add-product-form { padding: 25px; border-radius: 8px; }
.form-row { display: grid; grid-template-columns: repeat(auto-fit, minmax(250px, 1fr)); }
.form-group { display: flex; flex-direction: column; }

/* Table */
.products-table { width: 100%; border-collapse: collapse; }
.products-table thead { background-color: #f8f9fa; }
.products-table th { padding: 15px; text-align: left; font-weight: 700; }
.products-table td { padding: 15px; border-bottom: 1px solid #dee2e6; }

/* Badges */
.badge { padding: 6px 12px; border-radius: 20px; font-weight: 600; }
.badge-success { background-color: #d4edda; color: #155724; }
.badge-danger { background-color: #f8d7da; color: #721c24; }

/* Modal */
.modal-overlay { position: fixed; background-color: rgba(0, 0, 0, 0.6); }
.modal { background: white; border-radius: 12px; max-width: 500px; }
```

## App.js Updated Structure

### Added Import
```javascript
import AdminPage from './pages/AdminPage';
```

### Updated Navigation
```javascript
<li>
  <button
    className={`nav-btn ${currentPage === 'admin' ? 'active' : ''}`}
    onClick={() => setCurrentPage('admin')}
  >
    Admin
  </button>
</li>
```

### Updated Main Content
```javascript
<main className="main-content">
  {currentPage === 'home' && <Home />}
  {currentPage === 'products' && <Products />}
  {currentPage === 'admin' && <AdminPage />}
</main>
```

## Component Flow

```
App.js
  ↓
currentPage state changes to 'admin'
  ↓
AdminPage component renders
  ↓
Component mounts
  ↓
useEffect hooks execute
  ↓
fetchProducts() called
  ↓
GET /api/products
  ↓
Response received
  ↓
setProducts() updates state
  ↓
Component re-renders
  ↓
Table displays products
```

## API Integration Points

### 1. Fetch Products (Line ~17)
```javascript
const response = await fetch(API_BASE_URL);
const data = await response.json();
setProducts(Array.isArray(productList) ? productList : []);
```

### 2. Add Product (Line ~46)
```javascript
const response = await fetch(API_BASE_URL, {
  method: 'POST',
  headers: { 'Content-Type': 'application/json' },
  body: JSON.stringify(newProduct),
});
```

### 3. Update Price (Line ~82)
```javascript
const url = `${API_BASE_URL}/${selectedProductForPrice.id}/price?price=${newPrice}`;
const response = await fetch(url, { method: 'PATCH' });
```

### 4. Toggle Stock (Line ~111)
```javascript
const url = `${API_BASE_URL}/${product.id}/stock-status?status=${newStatus}`;
const response = await fetch(url, { method: 'PATCH' });
```

### 5. Delete Product (Line ~132)
```javascript
const response = await fetch(`${API_BASE_URL}/${productId}`, {
  method: 'DELETE',
});
```

## State Management Pattern

```javascript
// Setting State
setState(value)

// Reading State
const [value, setValue] = useState(initialValue)

// Pattern Used
const [data, setData] = useState([]);
const [loading, setLoading] = useState(false);
const [error, setError] = useState(null);

// Async Pattern
try {
  setLoading(true);
  setError(null);
  const response = await fetch(url);
  const data = await response.json();
  setData(data);
  setMessage('Success!');
} catch (err) {
  setError(err.message);
} finally {
  setLoading(false);
}
```

## Error Handling Pattern

```javascript
// Check Response
if (!response.ok) {
  throw new Error('Failed to fetch');
}

// Catch Errors
catch (err) {
  setError(err.message || 'Default message');
  console.error('Error:', err);
}

// Display to User
{error && <div className="alert alert-error">{error}</div>}
```

## Event Handling Pattern

```javascript
// Click Events
onClick={() => handleFunction()}

// Form Submit
onSubmit={handleSubmit}
e.preventDefault();

// Input Change
onChange={(e) => setState(e.target.value)}

// Form Data
const formData = new FormData(e.target);
const value = formData.get('fieldName');
```

## Styling Pattern

```javascript
// Class Names
className={`css-class ${condition ? 'active' : ''}`}

// Conditional Rendering
{condition && <Component />}

// Lists
{items.map(item => <Element key={item.id} />)}
```

## Data Flow Example: Add Product

```
1. User clicks "+ Add New Product"
   └── setShowAddForm(true)

2. Form becomes visible
   └── Renders form with input fields

3. User fills form and clicks "Add Product"
   └── handleAddProduct(e) triggered

4. Function creates product object
   └── Collects form data with FormData API

5. POST request sent
   └── await fetch(url, { method: 'POST', body: JSON.stringify() })

6. Response received
   └── if (!response.ok) throw Error

7. Success message set
   └── setSuccessMessage('Product added successfully!')

8. Form cleared and hidden
   └── e.target.reset()
   └── setShowAddForm(false)

9. Products list refreshed
   └── fetchProducts()

10. Component re-renders
    └── Table shows new product
    └── Success message displays for 3 seconds

11. Auto-dismiss message
    └── useEffect timer completes
    └── setSuccessMessage('')
```

## Responsive Design Breakpoints

```css
/* Desktop */
@media (min-width: 1024px) {
  /* Full features, 2-column layout */
}

/* Tablet */
@media (max-width: 1024px) {
  /* Adjusted layout, 1-column forms */
}

/* Mobile */
@media (max-width: 768px) {
  /* Single column, stacked elements */
}

/* Small Mobile */
@media (max-width: 480px) {
  /* Extra padding, large buttons */
}
```

## File Size Summary

| File | Lines | Size (approx) | Purpose |
|------|-------|---------------|---------|
| AdminPage.js | 404 | 12 KB | Logic |
| AdminPage.css | 650+ | 18 KB | Styling |
| App.js | 59 | 2 KB | Navigation |
| Total JS | 475 | 14 KB | Combined |
| Total CSS | 650+ | 18 KB | Combined |
| **Total** | **1125+** | **~32 KB** | Production |

---

**Reference Complete**: All code structures documented and ready for development

