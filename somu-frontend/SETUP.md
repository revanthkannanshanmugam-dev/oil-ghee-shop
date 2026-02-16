# Quick Setup Guide - Somu Enterprises Frontend

## Prerequisites

- Node.js 14+ and npm installed
- Spring Boot backend running on `http://localhost:8080`

## Quick Start (5 minutes)

### 1. Install Dependencies
```bash
cd somu-frontend
npm install
```

### 2. Start the Development Server
```bash
npm start
```

The app will automatically open in your browser at `http://localhost:3000`

## What You Get

- ✅ Home page with shop information
- ✅ Products page with all items
- ✅ Search functionality
- ✅ Category filters (Oil / Ghee)
- ✅ Out of stock badges
- ✅ Responsive design
- ✅ Clean, simple UI

## Project Structure

```
somu-frontend/
├── src/
│   ├── components/          # Reusable components
│   │   ├── ProductCard.js   # Individual product display
│   │   └── ProductList.js   # Product grid container
│   ├── pages/               # Page components
│   │   ├── Home.js          # Home/landing page
│   │   └── Products.js      # Products page with filters
│   ├── App.js               # Main app component
│   └── index.js             # Entry point
└── public/
    └── index.html           # HTML template
```

## Key Features Explained

### Home Page
- Welcome message
- Feature highlights
- Navigation to products

### Products Page
- **Search Input** - Type to search products by name
- **Category Filter** - Select Oil, Ghee, or All
- **Product Grid** - Responsive display of products
- **Stock Status** - Visual indication of availability

## API Integration

The frontend uses Axios to fetch products from:
```
GET http://localhost:8080/api/products
```

The backend handles:
- Fetching all products
- Filtering by category
- Searching by name
- Stock status tracking

## Customization

### Change API URL
Edit `src/pages/Products.js`:
```javascript
const API_BASE_URL = 'http://your-api-url:port/api/products';
```

### Change Colors
Edit any `.css` file and modify these colors:
- Primary color: `#f39c12` (orange)
- Dark color: `#2c3e50` (dark blue)
- Light color: `#ecf0f1` (light gray)

### Add New Pages
1. Create `src/pages/NewPage.js`
2. Import in `App.js`
3. Add navigation button in navbar

## Responsive Breakpoints

- **Desktop**: 1024px and above
- **Tablet**: 768px - 1023px
- **Mobile**: Below 768px

All layouts automatically adapt to screen size.

## Available Commands

```bash
# Start development server (port 3000)
npm start

# Build for production
npm run build

# Run tests (if added)
npm test

# Eject configuration (not reversible!)
npm eject
```

## Troubleshooting

### API connection errors
- Check backend is running: `http://localhost:8080`
- Verify CORS is enabled in Spring Boot
- Check browser console for error messages

### Styling not working
- Clear browser cache
- Restart dev server: `npm start`

### Products not loading
- Verify backend API is working: `curl http://localhost:8080/api/products`
- Check console for errors
- Ensure backend database has sample data

## Production Deployment

### Build for Production
```bash
npm run build
```

This creates optimized files in the `build/` folder.

### Deploy to Vercel
```bash
npm install -g vercel
vercel
```

### Deploy to Netlify
1. Push to GitHub
2. Connect repo to Netlify
3. Set build command: `npm run build`
4. Set publish directory: `build/`

## File Sizes (Approximate)

- React + dependencies: ~500KB
- App code: ~50KB
- Total (uncompressed): ~550KB
- Total (compressed): ~150KB

## Browser Support

- Chrome 90+
- Firefox 88+
- Safari 14+
- Edge 90+

## Next Steps

1. ✅ Start the server: `npm start`
2. ✅ View home page
3. ✅ Navigate to products
4. ✅ Try search and filters
5. ✅ Customize colors/content as needed

## Support

For issues or questions:
1. Check the backend is running
2. Check browser console for errors
3. Verify API responses with curl/Postman
4. Review README.md for detailed information

---

**Ready to go!** 🎉

