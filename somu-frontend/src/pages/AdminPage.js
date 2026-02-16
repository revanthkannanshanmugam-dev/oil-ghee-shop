import React, { useState, useEffect } from 'react';
import './AdminPage.css';

function AdminPage() {
  const [products, setProducts] = useState([]);
  const [isLoading, setIsLoading] = useState(false);
  const [error, setError] = useState(null);
  const [successMessage, setSuccessMessage] = useState('');
  const [showAddForm, setShowAddForm] = useState(false);
  const [showPriceModal, setShowPriceModal] = useState(false);
  const [selectedProductForPrice, setSelectedProductForPrice] = useState(null);
  const [newPrice, setNewPrice] = useState('');

  const API_BASE_URL = 'http://localhost:8080/api/products';

  // Fetch all products
  const fetchProducts = async () => {
    try {
      setIsLoading(true);
      setError(null);
      const response = await fetch(API_BASE_URL);
      if (!response.ok) {
        throw new Error('Failed to fetch products');
      }
      const data = await response.json();
      const productList = data.data || data;
      setProducts(Array.isArray(productList) ? productList : []);
    } catch (err) {
      setError(err.message || 'Failed to fetch products');
      console.error('Error fetching products:', err);
    } finally {
      setIsLoading(false);
    }
  };

  // Fetch products on component mount
  useEffect(() => {
    fetchProducts();
  }, []);

  // Show success message for 3 seconds
  useEffect(() => {
    if (successMessage) {
      const timer = setTimeout(() => setSuccessMessage(''), 3000);
      return () => clearTimeout(timer);
    }
  }, [successMessage]);

  // Handle Add New Product
  const handleAddProduct = async (e) => {
    e.preventDefault();
    const formData = new FormData(e.target);
    const newProduct = {
      name: formData.get('name'),
      category: formData.get('category'),
      price: parseFloat(formData.get('price')),
      unit: formData.get('unit'),
      stockStatus: formData.get('stockStatus'),
      imageUrl: formData.get('imageUrl'),
    };

    try {
      setError(null);
      const response = await fetch(API_BASE_URL, {
        method: 'POST',
        headers: {
          'Content-Type': 'application/json',
        },
        body: JSON.stringify(newProduct),
      });

      if (!response.ok) {
        throw new Error('Failed to add product');
      }

      setSuccessMessage('Product added successfully!');
      setShowAddForm(false);
      e.target.reset();
      fetchProducts();
    } catch (err) {
      setError(err.message || 'Failed to add product');
      console.error('Error adding product:', err);
    }
  };

  // Handle Update Price
  const handleUpdatePrice = async () => {
    if (!selectedProductForPrice || !newPrice) {
      setError('Please enter a valid price');
      return;
    }

    try {
      setError(null);
      const url = `${API_BASE_URL}/${selectedProductForPrice.id}/price?price=${parseFloat(
        newPrice
      )}`;
      const response = await fetch(url, {
        method: 'PATCH',
      });

      if (!response.ok) {
        throw new Error('Failed to update price');
      }

      setSuccessMessage('Price updated successfully!');
      setShowPriceModal(false);
      setSelectedProductForPrice(null);
      setNewPrice('');
      fetchProducts();
    } catch (err) {
      setError(err.message || 'Failed to update price');
      console.error('Error updating price:', err);
    }
  };

  // Handle Toggle Stock Status
  const handleToggleStock = async (product) => {
    const newStatus =
      product.stockStatus === 'IN_STOCK' ? 'OUT_OF_STOCK' : 'IN_STOCK';

    try {
      setError(null);
      const url = `${API_BASE_URL}/${product.id}/stock-status?status=${newStatus}`;
      const response = await fetch(url, {
        method: 'PATCH',
      });

      if (!response.ok) {
        throw new Error('Failed to update stock status');
      }

      setSuccessMessage(`Stock status updated to ${newStatus}!`);
      fetchProducts();
    } catch (err) {
      setError(err.message || 'Failed to update stock status');
      console.error('Error updating stock status:', err);
    }
  };

  // Handle Delete Product
  const handleDeleteProduct = async (productId, productName) => {
    if (!window.confirm(`Are you sure you want to delete "${productName}"?`)) {
      return;
    }

    try {
      setError(null);
      const response = await fetch(`${API_BASE_URL}/${productId}`, {
        method: 'DELETE',
      });

      if (!response.ok) {
        throw new Error('Failed to delete product');
      }

      setSuccessMessage('Product deleted successfully!');
      fetchProducts();
    } catch (err) {
      setError(err.message || 'Failed to delete product');
      console.error('Error deleting product:', err);
    }
  };

  return (
    <div className="admin-page">
      <div className="admin-header">
        <h1>Admin Dashboard</h1>
        <p className="admin-subtitle">Manage your products</p>
      </div>

      {/* Messages */}
      {error && <div className="alert alert-error">{error}</div>}
      {successMessage && <div className="alert alert-success">{successMessage}</div>}

      {/* Add Product Section */}
      <div className="admin-section">
        <div className="section-header">
          <h2>Products</h2>
          <button
            className="btn btn-primary"
            onClick={() => setShowAddForm(!showAddForm)}
          >
            {showAddForm ? 'Cancel' : '+ Add New Product'}
          </button>
        </div>

        {/* Add Product Form */}
        {showAddForm && (
          <form className="add-product-form" onSubmit={handleAddProduct}>
            <div className="form-row">
              <div className="form-group">
                <label htmlFor="name">Product Name *</label>
                <input
                  type="text"
                  id="name"
                  name="name"
                  required
                  placeholder="Enter product name"
                />
              </div>
              <div className="form-group">
                <label htmlFor="category">Category *</label>
                <select id="category" name="category" required>
                  <option value="">Select Category</option>
                  <option value="OIL">Oil</option>
                  <option value="GHEE">Ghee</option>
                </select>
              </div>
            </div>

            <div className="form-row">
              <div className="form-group">
                <label htmlFor="price">Price *</label>
                <input
                  type="number"
                  id="price"
                  name="price"
                  step="0.01"
                  required
                  placeholder="Enter price"
                />
              </div>
              <div className="form-group">
                <label htmlFor="unit">Unit *</label>
                <input
                  type="text"
                  id="unit"
                  name="unit"
                  required
                  placeholder="e.g., litre, kg"
                />
              </div>
            </div>

            <div className="form-row">
              <div className="form-group">
                <label htmlFor="stockStatus">Stock Status *</label>
                <select id="stockStatus" name="stockStatus" required>
                  <option value="">Select Stock Status</option>
                  <option value="IN_STOCK">In Stock</option>
                  <option value="OUT_OF_STOCK">Out of Stock</option>
                </select>
              </div>
              <div className="form-group">
                <label htmlFor="imageUrl">Image URL</label>
                <input
                  type="url"
                  id="imageUrl"
                  name="imageUrl"
                  placeholder="Enter image URL (optional)"
                />
              </div>
            </div>

            <div className="form-actions">
              <button type="submit" className="btn btn-success">
                Add Product
              </button>
              <button
                type="button"
                className="btn btn-secondary"
                onClick={() => setShowAddForm(false)}
              >
                Cancel
              </button>
            </div>
          </form>
        )}

        {/* Products Table */}
        <div className="products-table-wrapper">
          {isLoading ? (
            <div className="loading">Loading products...</div>
          ) : products.length === 0 ? (
            <div className="empty-state">
              <p>No products found. Create your first product!</p>
            </div>
          ) : (
            <table className="products-table">
              <thead>
                <tr>
                  <th>Name</th>
                  <th>Category</th>
                  <th>Price</th>
                  <th>Unit</th>
                  <th>Stock Status</th>
                  <th>Actions</th>
                </tr>
              </thead>
              <tbody>
                {products.map((product) => (
                  <tr key={product.id} className="product-row">
                    <td className="product-name">{product.name}</td>
                    <td>
                      <span className="badge badge-category">
                        {product.category}
                      </span>
                    </td>
                    <td className="price">₹{product.price.toFixed(2)}</td>
                    <td>{product.unit}</td>
                    <td>
                      <span
                        className={`badge ${
                          product.stockStatus === 'IN_STOCK'
                            ? 'badge-success'
                            : 'badge-danger'
                        }`}
                      >
                        {product.stockStatus === 'IN_STOCK'
                          ? 'In Stock'
                          : 'Out of Stock'}
                      </span>
                    </td>
                    <td className="actions">
                      <button
                        className="action-btn btn-edit"
                        title="Update Price"
                        onClick={() => {
                          setSelectedProductForPrice(product);
                          setNewPrice(product.price.toString());
                          setShowPriceModal(true);
                        }}
                      >
                        💰 Price
                      </button>
                      <button
                        className="action-btn btn-toggle"
                        title="Toggle Stock Status"
                        onClick={() => handleToggleStock(product)}
                      >
                        🔄 Stock
                      </button>
                      <button
                        className="action-btn btn-delete"
                        title="Delete Product"
                        onClick={() => handleDeleteProduct(product.id, product.name)}
                      >
                        🗑️ Delete
                      </button>
                    </td>
                  </tr>
                ))}
              </tbody>
            </table>
          )}
        </div>
      </div>

      {/* Price Update Modal */}
      {showPriceModal && selectedProductForPrice && (
        <div className="modal-overlay" onClick={() => setShowPriceModal(false)}>
          <div className="modal" onClick={(e) => e.stopPropagation()}>
            <div className="modal-header">
              <h3>Update Price</h3>
              <button
                className="close-btn"
                onClick={() => setShowPriceModal(false)}
              >
                ×
              </button>
            </div>
            <div className="modal-body">
              <p className="modal-product-name">
                <strong>Product:</strong> {selectedProductForPrice.name}
              </p>
              <p className="modal-current-price">
                <strong>Current Price:</strong> ₹
                {selectedProductForPrice.price.toFixed(2)}
              </p>
              <div className="form-group">
                <label htmlFor="newPrice">New Price *</label>
                <input
                  type="number"
                  id="newPrice"
                  step="0.01"
                  value={newPrice}
                  onChange={(e) => setNewPrice(e.target.value)}
                  placeholder="Enter new price"
                  autoFocus
                />
              </div>
            </div>
            <div className="modal-footer">
              <button
                className="btn btn-secondary"
                onClick={() => setShowPriceModal(false)}
              >
                Cancel
              </button>
              <button className="btn btn-success" onClick={handleUpdatePrice}>
                Update Price
              </button>
            </div>
          </div>
        </div>
      )}
    </div>
  );
}

export default AdminPage;

