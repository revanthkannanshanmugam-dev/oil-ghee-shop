import React, { useState, useEffect } from 'react';
import axios from 'axios';
import ProductList from '../components/ProductList';
import './Products.css';

function Products() {
  const [products, setProducts] = useState([]);
  const [filteredProducts, setFilteredProducts] = useState([]);
  const [isLoading, setIsLoading] = useState(true);
  const [error, setError] = useState(null);
  const [selectedCategory, setSelectedCategory] = useState('ALL');
  const [searchTerm, setSearchTerm] = useState('');

  const API_BASE_URL = 'http://localhost:8080/api/products';

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

  // Fetch all products on component mount
  useEffect(() => {
    fetchProducts();
  }, []);

  // Filter products when products, category, or search term changes
  useEffect(() => {
    let filtered = products;

    // Filter by category
    if (selectedCategory !== 'ALL') {
      filtered = filtered.filter(
        (product) => product.category === selectedCategory
      );
    }

    // Filter by search term
    if (searchTerm.trim()) {
      filtered = filtered.filter((product) =>
        product.name.toLowerCase().includes(searchTerm.toLowerCase())
      );
    }

    setFilteredProducts(filtered);
  }, [products, selectedCategory, searchTerm]);


  const handleCategoryChange = (category) => {
    setSelectedCategory(category);
  };

  const handleSearchChange = (e) => {
    setSearchTerm(e.target.value);
  };

  const handleClearFilters = () => {
    setSelectedCategory('ALL');
    setSearchTerm('');
  };

  return (
    <div className="products-page">
      <h1>Our Products</h1>

      <div className="products-container">
        {/* Sidebar - Filters */}
        <aside className="filters-sidebar">
          <div className="filter-section">
            <h3>Search</h3>
            <input
              type="text"
              className="search-input"
              placeholder="Search products..."
              value={searchTerm}
              onChange={handleSearchChange}
            />
          </div>

          <div className="filter-section">
            <h3>Category</h3>
            <div className="category-filters">
              <label className="filter-label">
                <input
                  type="radio"
                  name="category"
                  value="ALL"
                  checked={selectedCategory === 'ALL'}
                  onChange={() => handleCategoryChange('ALL')}
                />
                <span>All Products</span>
              </label>
              <label className="filter-label">
                <input
                  type="radio"
                  name="category"
                  value="OIL"
                  checked={selectedCategory === 'OIL'}
                  onChange={() => handleCategoryChange('OIL')}
                />
                <span>🌾 Oils</span>
              </label>
              <label className="filter-label">
                <input
                  type="radio"
                  name="category"
                  value="GHEE"
                  checked={selectedCategory === 'GHEE'}
                  onChange={() => handleCategoryChange('GHEE')}
                />
                <span>🥛 Ghee</span>
              </label>
            </div>
          </div>

          <button className="clear-filters-btn" onClick={handleClearFilters}>
            Clear Filters
          </button>
        </aside>

        {/* Main Content - Products */}
        <div className="products-main">
          <div className="products-header">
            <p className="products-count">
              {filteredProducts.length} product{filteredProducts.length !== 1 ? 's' : ''} found
            </p>
            <button className="refresh-btn" onClick={fetchProducts} disabled={isLoading}>
              {isLoading ? 'Refreshing...' : 'Refresh'}
            </button>
          </div>

          <ProductList
            products={filteredProducts}
            isLoading={isLoading}
            error={error}
          />
        </div>
      </div>
    </div>
  );
}

export default Products;

