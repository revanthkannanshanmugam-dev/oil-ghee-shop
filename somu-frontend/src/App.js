import React, { useState } from 'react';
import './App.css';
import Home from './pages/Home';
import Products from './pages/Products';
import AdminPage from './pages/AdminPage';

function App() {
  const [currentPage, setCurrentPage] = useState('home');

  return (
    <div className="App">
      <nav className="navbar">
        <div className="navbar-content">
          <h1 className="logo">Somu Enterprises</h1>
          <ul className="nav-links">
            <li>
              <button
                className={`nav-btn ${currentPage === 'home' ? 'active' : ''}`}
                onClick={() => setCurrentPage('home')}
              >
                Home
              </button>
            </li>
            <li>
              <button
                className={`nav-btn ${currentPage === 'products' ? 'active' : ''}`}
                onClick={() => setCurrentPage('products')}
              >
                Products
              </button>
            </li>
            <li>
              <button
                className={`nav-btn ${currentPage === 'admin' ? 'active' : ''}`}
                onClick={() => setCurrentPage('admin')}
              >
                Admin
              </button>
            </li>
          </ul>
        </div>
      </nav>

      <main className="main-content">
        {currentPage === 'home' && <Home />}
        {currentPage === 'products' && <Products />}
        {currentPage === 'admin' && <AdminPage />}
      </main>

      <footer className="footer">
        <p>&copy; 2026 Somu Enterprises. All rights reserved.</p>
      </footer>
    </div>
  );
}

export default App;

