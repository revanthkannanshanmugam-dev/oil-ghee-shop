import React from 'react';
import './Home.css';

function Home() {
  return (
    <div className="home">
      <div className="hero">
        <h1>Welcome to Somu Enterprises</h1>
        <p>Your trusted source for premium edible oils and ghee</p>
      </div>

      <div className="features">
        <div className="feature-card">
          <h3>🌾 Quality Products</h3>
          <p>We offer only the finest edible oils and ghee, sourced with care.</p>
        </div>
        <div className="feature-card">
          <h3>🚚 Fast Delivery</h3>
          <p>Quick and reliable delivery to your doorstep.</p>
        </div>
        <div className="feature-card">
          <h3>💯 Pure & Natural</h3>
          <p>100% pure and natural products, free from additives.</p>
        </div>
      </div>

      <div className="cta-section">
        <h2>Explore Our Products</h2>
        <p>Browse our collection of premium oils and ghee today!</p>
        <a href="#products" className="cta-button">Shop Now</a>
      </div>
    </div>
  );
}

export default Home;

