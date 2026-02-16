import React from 'react';
import './ProductCard.css';

function ProductCard({ product }) {
  const isOutOfStock = product.stockStatus === 'OUT_OF_STOCK';

  return (
    <div className={`product-card ${isOutOfStock ? 'out-of-stock' : ''}`}>
      <div className="product-image">
        <img
          src={product.imageUrl || 'https://via.placeholder.com/300x300?text=Product'}
          alt={product.name}
          onError={(e) => {
            e.target.src = 'https://via.placeholder.com/300x300?text=Product';
          }}
        />
        {isOutOfStock && <div className="stock-badge">Out of Stock</div>}
      </div>

      <div className="product-info">
        <div className="product-category">
          <span className={`category-tag ${product.category.toLowerCase()}`}>
            {product.category}
          </span>
        </div>

        <h3 className="product-name">{product.name}</h3>

        <div className="product-details">
          <p className="product-unit">
            {product.unit === 'LITRE' ? 'Per Litre' : 'Per Kg'}
          </p>
        </div>

        <div className="product-footer">
          <span className="product-price">₹{product.price}</span>
          <button
            className={`add-to-cart-btn ${isOutOfStock ? 'disabled' : ''}`}
            disabled={isOutOfStock}
          >
            {isOutOfStock ? 'Out of Stock' : 'Add to Cart'}
          </button>
        </div>
      </div>
    </div>
  );
}

export default ProductCard;

