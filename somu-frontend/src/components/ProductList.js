import React from 'react';
import ProductCard from './ProductCard';
import './ProductList.css';

function ProductList({ products, isLoading, error }) {
  if (isLoading) {
    return (
      <div className="product-list-loading">
        <div className="spinner"></div>
        <p>Loading products...</p>
      </div>
    );
  }

  if (error) {
    return (
      <div className="product-list-error">
        <p>Error loading products: {error}</p>
      </div>
    );
  }

  if (!products || products.length === 0) {
    return (
      <div className="product-list-empty">
        <p>No products found. Try adjusting your filters.</p>
      </div>
    );
  }

  return (
    <div className="product-list">
      {products.map((product) => (
        <ProductCard key={product.id} product={product} />
      ))}
    </div>
  );
}

export default ProductList;

