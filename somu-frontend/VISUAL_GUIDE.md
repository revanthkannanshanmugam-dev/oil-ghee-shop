# Visual Component Guide - Somu Enterprises Frontend

## Component Hierarchy

```
App.js (Root Component)
├── Navigation Bar
│   ├── Logo "Somu Enterprises"
│   └── Navigation Links (Home, Products)
├── Main Content (Dynamic)
│   ├── Home Page (when nav "Home" clicked)
│   │   ├── Hero Section
│   │   ├── Features (3 cards)
│   │   └── Call-to-Action
│   └── Products Page (when nav "Products" clicked)
│       ├── Sidebar Filters
│       │   ├── Search Input
│       │   ├── Category Filters (Oil, Ghee, All)
│       │   └── Clear Filters Button
│       └── Main Content Area
│           ├── Products Header (Count, Refresh)
│           └── Product Grid
│               ├── ProductCard #1
│               ├── ProductCard #2
│               ├── ProductCard #3
│               └── ... more cards
└── Footer

ProductCard Component (Repeating)
├── Image Container
│   ├── Product Image
│   └── Stock Badge (if out of stock)
├── Product Info
│   ├── Category Badge
│   ├── Product Name
│   ├── Unit Type
│   └── Product Footer
│       ├── Price
│       └── Add to Cart Button
```

## Page Layouts

### Home Page Layout
```
┌─────────────────────────────────────────┐
│         Navigation Bar                  │
│  Somu Enterprises  [Home] [Products]   │
├─────────────────────────────────────────┤
│                                         │
│              HERO SECTION               │
│      Welcome to Somu Enterprises       │
│   Premium edible oils and ghee         │
│                                         │
├─────────────────────────────────────────┤
│                                         │
│        Feature Cards (1 × 3 grid)       │
│  ┌──────────┬──────────┬──────────┐   │
│  │ Quality  │ Delivery │   Pure   │   │
│  │ Products │ & Fast   │ & Natural│   │
│  └──────────┴──────────┴──────────┘   │
│                                         │
├─────────────────────────────────────────┤
│                                         │
│     Call to Action Section              │
│    Explore Our Products                │
│         [Shop Now Button]               │
│                                         │
├─────────────────────────────────────────┤
│     © 2026 Somu Enterprises             │
└─────────────────────────────────────────┘
```

### Products Page Layout
```
┌─────────────────────────────────────────┐
│         Navigation Bar                  │
├─────────────────────────────────────────┤
│  Our Products                           │
├─────────────────────────────────────────┤
│                                         │
│ ┌─────────┬─────────────────────────┐  │
│ │ SIDEBAR │      MAIN CONTENT       │  │
│ │         │                         │  │
│ │ FILTERS │ Count: 6 products       │  │
│ │         │ [Refresh Button]        │  │
│ │ Search: │                         │  │
│ │ [input] │ ┌──────┬──────┬──────┐ │  │
│ │         │ │ Card │ Card │ Card │ │  │
│ │ Category│ ├──────┼──────┼──────┤ │  │
│ │ ⭕ All  │ │ Card │ Card │ Card │ │  │
│ │ 🌾 Oil  │ └──────┴──────┴──────┘ │  │
│ │ 🥛 Ghee │                         │  │
│ │         │ (Responsive: 1-5 per   │  │
│ │ [Clear] │  row depending on size) │  │
│ │         │                         │  │
│ └─────────┴─────────────────────────┘  │
│                                         │
├─────────────────────────────────────────┤
│     © 2026 Somu Enterprises             │
└─────────────────────────────────────────┘
```

## ProductCard Layout

```
┌───────────────────────┐
│                       │
│   Product Image       │
│   (250px height)      │
│                       │
│   [Out of Stock Badge]│ (if applicable)
├───────────────────────┤
│ [OIL] or [GHEE]      │ (category badge)
├───────────────────────┤
│ Sunflower Oil        │ (product name)
├───────────────────────┤
│ Per Litre            │ (unit)
├───────────────────────┤
│ ₹450  [Add to Cart]   │ (price + button)
└───────────────────────┘
```

## Responsive Behavior

### Desktop (1024px+)
```
┌─────────────────────────────────────────────────┐
│ [Sidebar]  [Card] [Card] [Card] [Card] [Card] │
│            [Card] [Card] [Card] [Card] [Card] │
└─────────────────────────────────────────────────┘
Grid: 5 columns (auto-fill based on 280px min width)
```

### Tablet (768px-1023px)
```
┌──────────────────────────────┐
│ [Sidebar]  [Card] [Card]    │
│            [Card] [Card]    │
│            [Card] [Card]    │
└──────────────────────────────┘
Grid: 2-3 columns
```

### Mobile (<768px)
```
┌──────────────────┐
│ [Filters]        │
├──────────────────┤
│ [Card]           │
├──────────────────┤
│ [Card]           │
├──────────────────┤
│ [Card]           │
└──────────────────┘
Grid: 1 column (full width)
Sidebar: Unsticky, placed above products
```

## Color Scheme

### Primary Colors
```
┌─────────────────────────────────────────┐
│ Background: #f5f5f5 (Light gray)       │
│ Primary: #f39c12 (Orange)              │
│ Dark: #2c3e50 (Dark blue)              │
│ Light: #ecf0f1 (Light gray)            │
│ White: #ffffff (White)                 │
└─────────────────────────────────────────┘
```

### Component Colors
```
Navigation Bar:
  Background: #2c3e50
  Text: White
  Active/Hover: #f39c12

Card Background: White
Card Hover: Lift effect + shadow

Buttons:
  Primary: #f39c12 (orange)
  Hover: #e67e22 (darker orange)
  Disabled: #bdc3c7 (gray)

Badges:
  Oil Category: Light yellow
  Ghee Category: Light green
  Out of Stock: Red
```

## State Flow Diagram

```
User Interaction
       ↓
State Update (useState)
       ↓
useEffect triggered
       ↓
Filtering logic applied
       ↓
Component Re-render
       ↓
DOM Updated
       ↓
Visual Feedback
```

### Specific Flow: Search
```
User types → setSearchTerm()
              ↓
        useEffect triggers
              ↓
        filterProducts() called
              ↓
        setFilteredProducts()
              ↓
        ProductList receives new props
              ↓
        Grid re-renders with filtered products
```

### Specific Flow: Category Filter
```
User clicks radio button → setSelectedCategory()
                            ↓
                      useEffect triggers
                            ↓
                      filterProducts() called
                            ↓
                      setFilteredProducts()
                            ↓
                      ProductList receives new props
                            ↓
                      Grid re-renders with filtered products
```

### Specific Flow: API Call
```
Component mounts → useEffect triggers
                        ↓
                   fetchProducts() called
                        ↓
                   setIsLoading(true)
                        ↓
                   axios.get() called
                        ↓
         Response received (success/error)
                        ↓
            setProducts() or setError()
                        ↓
            setIsLoading(false)
                        ↓
        ProductList receives products
                        ↓
        Grid renders with products
```

## User Interactions

### Home Page
```
1. User visits app
2. Home page loads (default)
3. User sees hero + features
4. User clicks "Shop Now" or "Products" nav
5. Navigates to Products page
```

### Products Page
```
1. Products page loads
2. API call fetches products
3. Loading spinner shown
4. Products displayed in grid

User Actions:
  └─ Click category filter → products filtered
  └─ Type in search → products filtered
  └─ Click Clear Filters → reset all filters
  └─ Click Refresh → refetch from API
  └─ Hover over card → card lifts up
  └─ Click Add to Cart → (currently disabled, placeholder)
  └─ Click out-of-stock item → button disabled
```

## Information Architecture

```
Somu Enterprises
├── Home
│   ├── Hero/Welcome
│   ├── About/Features
│   └── CTA (Shop Now)
├── Products
│   ├── Product Catalog
│   │   ├── Product List
│   │   │   └── Individual Products
│   │   └── Product Details
│   │       ├── Image
│   │       ├── Name
│   │       ├── Price
│   │       ├── Category
│   │       └── Stock Status
│   ├── Search Functionality
│   └── Category Filtering
└── Navigation (on all pages)
```

## Spacing & Layout Rules

```
Page Padding: 40px (desktop), 20px (tablet/mobile)
Component Gaps: 30px (large), 20px (medium), 15px (small)
Card Shadow: 0 2px 8px rgba(0,0,0,0.1)
Card Hover Shadow: 0 4px 16px rgba(0,0,0,0.15)
Border Radius: 8px (all components)
```

## Typography

```
Navigation Logo:
  Font-size: 24px
  Font-weight: bold
  Color: #f39c12

Page Heading (h1):
  Font-size: 36px
  Color: #2c3e50

Section Heading (h2):
  Font-size: 28px
  Color: #2c3e50

Component Heading (h3):
  Font-size: 18-20px
  Color: #2c3e50

Body Text:
  Font-size: 14-16px
  Color: #333

Price:
  Font-size: 24px
  Font-weight: bold
  Color: #f39c12
```

## Button States

```
Normal:
  Background: #f39c12
  Text: White
  Cursor: pointer

Hover:
  Background: #e67e22 (darker)
  Transform: slight scale up

Active:
  Background: Darker shade

Disabled:
  Background: #bdc3c7
  Cursor: not-allowed
  Opacity: 0.6
```

## Loading & Error States

### Loading State
```
┌──────────────────────┐
│   [Spinning wheel]   │
│  Loading products... │
└──────────────────────┘
```

### Error State
```
┌──────────────────────┐
│ Error loading        │
│ products: [message]  │
└──────────────────────┘
```

### Empty State
```
┌──────────────────────────┐
│ No products found.       │
│ Try adjusting your       │
│ filters.                 │
└──────────────────────────┘
```

## Navigation Flow

```
        ┌─ Home ─┐
        │         │
        └─ Products ─┐
                     │
                [Shop Now]
                     │
                  Products
```

---

This visual guide helps developers understand the layout, styling, and user flow of the Somu Enterprises frontend.

