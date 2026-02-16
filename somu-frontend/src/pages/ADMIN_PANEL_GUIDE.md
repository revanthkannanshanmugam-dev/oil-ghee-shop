# Admin Panel Documentation

## Overview
The Admin Panel is a comprehensive product management interface that allows administrators to perform CRUD operations on products with real-time updates.

## Features Implemented

### 1. **Product Management Table**
- Displays all products in a clean, responsive table
- Shows columns: Name, Category, Price, Unit, Stock Status, and Actions
- Real-time updates after any operation
- Mobile-responsive design

### 2. **Add New Product Form**
- Toggle-able form to add new products
- Fields:
  - Product Name (required)
  - Category dropdown (OIL / GHEE) (required)
  - Price (required)
  - Unit (required)
  - Stock Status dropdown (IN_STOCK / OUT_OF_STOCK) (required)
  - Image URL (optional)
- Form validation
- Success/error notifications

### 3. **Update Price Modal**
- Click "💰 Price" button on any product
- Modal displays current product details
- Enter new price
- PATCH request to `/api/products/{id}/price?price=NEW_PRICE`
- Auto-refresh product list after update

### 4. **Toggle Stock Status**
- Click "🔄 Stock" button on any product
- Automatically toggles between IN_STOCK and OUT_OF_STOCK
- PATCH request to `/api/products/{id}/stock-status?status=NEW_STATUS`
- Instant feedback with badge color change

### 5. **Delete Product**
- Click "🗑️ Delete" button on any product
- Confirmation dialog prevents accidental deletion
- DELETE request to `/api/products/{id}`
- Auto-refresh after deletion

### 6. **User Feedback**
- Success messages (green alert)
- Error messages (red alert)
- Loading states
- Empty state when no products exist

## API Integration

All API calls use the native Fetch API (not Axios):

### Endpoints Used:
1. **GET** `/api/products` - Fetch all products
2. **POST** `/api/products` - Create new product
3. **PATCH** `/api/products/{id}/price` - Update product price
4. **PATCH** `/api/products/{id}/stock-status` - Update stock status
5. **DELETE** `/api/products/{id}` - Delete product

## File Structure
```
src/pages/
├── AdminPage.js       (Component logic)
├── AdminPage.css      (Styling)
├── Home.js
├── Products.js
└── ...other files

src/
├── App.js            (Updated with AdminPage route)
├── App.css
└── ...
```

## How to Use

### 1. Navigate to Admin Panel
Click the "Admin" button in the navigation bar.

### 2. Add a New Product
1. Click "+ Add New Product" button
2. Fill in all required fields
3. Click "Add Product"
4. Success message appears, form resets

### 3. Update Product Price
1. Click "💰 Price" button on the product row
2. Modal opens showing current price
3. Enter new price
4. Click "Update Price"
5. Product list refreshes automatically

### 4. Toggle Stock Status
1. Click "🔄 Stock" button on the product row
2. Status changes immediately
3. Badge color updates (green for IN_STOCK, red for OUT_OF_STOCK)

### 5. Delete Product
1. Click "🗑️ Delete" button on the product row
2. Confirmation dialog appears
3. Click OK to confirm deletion
4. Product is removed from list

## Styling Features

### Responsive Design
- Desktop: Full featured layout
- Tablet: Adjusted spacing and form layout
- Mobile: Single column layout, touch-friendly buttons

### Visual Elements
- Color-coded status badges
- Hover effects on buttons
- Smooth animations and transitions
- Professional color scheme

### Accessibility
- Semantic HTML
- Clear button labels
- Focus states on inputs
- Proper contrast ratios

## Error Handling
- Network error messages
- Validation feedback
- Confirmation dialogs for destructive actions
- Try-catch blocks for all API calls
- User-friendly error messages

## State Management
Uses React Hooks:
- `useState` - for component state
- `useEffect` - for lifecycle management and message auto-dismiss

## Technical Specifications
- Framework: React 18
- State Management: React Hooks
- HTTP Client: Fetch API
- Styling: CSS3 with Grid and Flexbox
- Package: No external UI library (pure CSS)

## Performance Considerations
- Auto-dismissing success messages (3 seconds)
- Loading states prevent duplicate requests
- Efficient re-renders with proper dependency arrays
- Optimized CSS with minimal specificity

## Browser Support
- Chrome/Edge (latest)
- Firefox (latest)
- Safari (latest)
- Mobile browsers

## Future Enhancements
- Search/filter functionality in the table
- Pagination for large product lists
- Batch operations (select multiple products)
- Product edit functionality
- Image upload instead of URL
- Undo functionality
- Export to CSV
- Advanced filtering options

## Troubleshooting

### Products not loading?
- Check if backend API is running on `http://localhost:8080`
- Check network tab in browser DevTools
- Verify API returns data in expected format

### Modal not closing?
- Click outside the modal or click the X button
- Check browser console for errors

### Form not submitting?
- Ensure all required fields are filled
- Check browser console for validation errors
- Verify API endpoint is accessible

