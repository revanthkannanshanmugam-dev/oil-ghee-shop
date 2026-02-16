# Somu Enterprises - React Frontend

A clean, responsive React frontend for Somu Enterprises edible oil and ghee e-commerce shop.

## Features

✅ **Home Page** - Landing page with shop information and features  
✅ **Products Page** - Display all products in a responsive grid  
✅ **Search Functionality** - Search products by name in real-time  
✅ **Category Filters** - Filter by Oil or Ghee category  
✅ **Stock Status** - Visual indication of out-of-stock products  
✅ **Responsive Design** - Works seamlessly on desktop, tablet, and mobile  
✅ **Clean UI** - Simple, professional styling without external UI libraries  
✅ **Axios Integration** - API calls to Spring Boot backend  
✅ **React Hooks** - Modern functional components with useState and useEffect  

## Technology Stack

- **React 18** - Frontend framework
- **Axios** - HTTP client for API requests
- **CSS3** - Clean, custom styling
- **JavaScript ES6+** - Modern JavaScript features

## Project Structure

```
somu-frontend/
├── public/
│   └── index.html
├── src/
│   ├── components/
│   │   ├── ProductCard.js
│   │   ├── ProductCard.css
│   │   ├── ProductList.js
│   │   └── ProductList.css
│   ├── pages/
│   │   ├── Home.js
│   │   ├── Home.css
│   │   ├── Products.js
│   │   └── Products.css
│   ├── App.js
│   ├── App.css
│   ├── index.js
│   └── index.css
├── package.json
└── .gitignore
```

## Installation & Setup

### 1. Install Dependencies

```bash
cd somu-frontend
npm install
```

### 2. Start the Development Server

```bash
npm start
```

The app will open at [http://localhost:3000](http://localhost:3000)

### 3. Build for Production

```bash
npm run build
```

This creates an optimized production build in the `build/` folder.

## API Configuration

The frontend connects to the Spring Boot backend at:

```
http://localhost:8080/api/products
```

If your backend is running on a different host/port, update the `API_BASE_URL` in `src/pages/Products.js`.

## Features Explained

### Home Page
- Welcome hero section
- Feature highlights
- Call-to-action button

### Products Page
- **Search Bar** - Real-time search by product name
- **Category Filters** - Radio buttons to filter by Oil, Ghee, or All
- **Product Grid** - Responsive grid layout (auto-adjusts columns)
- **Product Cards** - Display:
  - Product image
  - Category badge
  - Product name
  - Unit type (Litre/Kg)
  - Price
  - Stock status
  - Add to cart button (disabled for out-of-stock)
- **Refresh Button** - Refetch products from API

### Responsive Design

The frontend is fully responsive:
- **Desktop** (1024px+) - 4-5 products per row
- **Tablet** (768px-1023px) - 2-3 products per row
- **Mobile** (< 768px) - 1 product per row

### Error Handling

- Loading spinner while fetching products
- Error message if API call fails
- "No products found" message when filters return empty results

## Component Details

### ProductCard
- Displays individual product information
- Shows "Out of Stock" badge when applicable
- Hover effects for better UX
- Responsive image handling with fallback

### ProductList
- Maps through product array
- Handles loading, error, and empty states
- Responsive grid layout

### Products Page
- Manages state for:
  - All products
  - Filtered products
  - Loading state
  - Error state
  - Selected category
  - Search term
- Handles filtering logic
- Provides search and filter UI

## Available Scripts

### `npm start`
Runs the app in development mode on [http://localhost:3000](http://localhost:3000)

### `npm run build`
Builds the app for production to the `build` folder

### `npm test`
Launches the test runner (if tests are added)

## Styling

All styling is done with CSS files (no external UI libraries). The design follows:

- **Colors**:
  - Primary: #f39c12 (Orange)
  - Dark: #2c3e50 (Dark blue)
  - Light: #ecf0f1 (Light gray)
  
- **Spacing** - Consistent padding/margin throughout
- **Typography** - Clear hierarchy with font sizes
- **Responsive** - Mobile-first approach with media queries

## Browser Support

- Chrome (latest)
- Firefox (latest)
- Safari (latest)
- Edge (latest)

## Troubleshooting

### API Connection Issues
1. Verify the backend is running on `http://localhost:8080`
2. Check for CORS issues in browser console
3. Ensure the Spring Boot app is started with `mvn spring-boot:run`

### Styling Issues
1. Clear browser cache (Ctrl+Shift+Delete)
2. Restart the development server (`npm start`)

### Module Not Found Errors
1. Ensure all dependencies are installed: `npm install`
2. Check that all import paths are correct

## Future Enhancements

- [ ] Add pagination support
- [ ] Add sorting options (price, name)
- [ ] Implement shopping cart functionality
- [ ] Add product detail page
- [ ] User authentication
- [ ] Order history
- [ ] Wishlist feature
- [ ] Admin dashboard
- [ ] Payment integration

## Deployment

### Deploy to Vercel (Recommended)
1. Push code to GitHub
2. Connect repository to Vercel
3. Deploy with one click

### Deploy to Netlify
1. Push code to GitHub
2. Connect repository to Netlify
3. Set build command: `npm run build`
4. Set publish directory: `build/`

### Deploy to Traditional Hosting
1. Build: `npm run build`
2. Upload `build/` folder to web server
3. Configure web server for SPA routing

## Performance Tips

- Images are lazy-loaded (browser default)
- CSS is scoped to components
- Minimal re-renders with proper hook usage
- Efficient filtering (done on client-side with reasonable data sizes)

## Support

For issues or feature requests, please refer to the backend documentation or contact the development team.

## License

© 2026 Somu Enterprises. All rights reserved.

---

**Happy coding!** 🚀

