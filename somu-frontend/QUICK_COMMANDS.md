# Quick Commands & Troubleshooting - Somu Enterprises Frontend

## 🚀 Quick Start Commands

### First Time Setup
```bash
# 1. Navigate to frontend folder
cd C:\Users\revan\Downloads\SomuEnterprises\somu-frontend

# 2. Install dependencies (first time only)
npm install

# 3. Start development server
npm start
```

The app will automatically open at `http://localhost:3000`

---

## 📋 Common Commands

### Development
```bash
# Start development server
npm start

# Stop server (Ctrl + C in terminal)
npm stop
```

### Production
```bash
# Build optimized version
npm run build

# Build and deploy (if you have a deployment set up)
npm run deploy
```

### Cleaning
```bash
# Clear npm cache
npm cache clean --force

# Reinstall all dependencies
npm install --force
```

---

## 🔧 Troubleshooting

### Issue: "npm: command not found"
**Solution:**
1. Install Node.js from nodejs.org
2. Restart your terminal
3. Verify installation: `node --version`

### Issue: Port 3000 Already in Use
**Solution 1:** Kill the process using port 3000
```bash
# Windows
netstat -ano | findstr :3000
taskkill /PID <PID> /F
```

**Solution 2:** Use a different port
```bash
# Set PORT environment variable
set PORT=3001
npm start
```

### Issue: "Cannot find module 'axios'"
**Solution:**
```bash
npm install
# Then restart: npm start
```

### Issue: API Connection Error
**Checklist:**
1. Backend is running: `mvn spring-boot:run`
2. Backend URL in Products.js is correct
3. Check browser console (F12) for error messages
4. Verify CORS is enabled in Spring Boot

### Issue: Products Not Loading
**Steps to debug:**
1. Open browser DevTools (F12)
2. Go to Network tab
3. Click Products page
4. Look for `/api/products` request
5. Check status code (should be 200)
6. Check response data in Response tab

### Issue: Styling Not Working
**Solution:**
1. Clear browser cache (Ctrl+Shift+Delete)
2. Restart dev server (Ctrl+C, then npm start)
3. Hard refresh page (Ctrl+Shift+R)

### Issue: "react-scripts: command not found"
**Solution:**
```bash
npm install
npm start
```

---

## 🐛 Debugging Tips

### Check Backend Status
```bash
# Test if backend is running
curl http://localhost:8080/api/health
```

### Test API Endpoint
```bash
# Get all products
curl http://localhost:8080/api/products

# Pretty print JSON (Windows)
powershell -Command "(Invoke-WebRequest http://localhost:8080/api/products).Content | ConvertFrom-Json"
```

### Check Browser Console
1. Press F12 to open DevTools
2. Go to Console tab
3. Check for error messages
4. Look for warnings

### Check Network Activity
1. Press F12 to open DevTools
2. Go to Network tab
3. Reload page (F5)
4. Look for failed requests
5. Click on requests to see details

### Check Application State
1. Press F12 to open DevTools
2. Go to Sources tab
3. Set breakpoints in code
4. Watch state changes

---

## 📁 Project Navigation

### Open in Code Editor
```bash
# Open with VS Code
code .

# Open with your default editor
explorer .
```

### Terminal Commands
```bash
# List files
ls
dir (Windows)

# Navigate to folder
cd somu-frontend

# Go back one level
cd ..

# Show current path
pwd
```

---

## 🔄 Reset & Clean

### Clear node_modules and reinstall
```bash
# Delete node_modules folder
rm -r node_modules
# or (Windows)
rmdir /s /q node_modules

# Reinstall
npm install
```

### Clear npm cache
```bash
npm cache clean --force
```

### Reset to fresh state
```bash
# Remove node_modules
rm -r node_modules

# Clear cache
npm cache clean --force

# Remove package-lock.json
rm package-lock.json

# Reinstall everything
npm install

# Start fresh
npm start
```

---

## 📊 Performance Checks

### Check Bundle Size
```bash
npm run build
# Check size of build/ folder
```

### Monitor for Warnings
```bash
npm start 2>&1 | grep -i warning
```

### Performance Profile
1. Open DevTools (F12)
2. Go to Performance tab
3. Record interactions
4. Analyze results

---

## 🔗 API Testing Tools

### Using cURL
```bash
# Get all products
curl http://localhost:8080/api/products

# Pretty format (Windows PowerShell)
(Invoke-WebRequest http://localhost:8080/api/products).Content | ConvertFrom-Json | ConvertTo-Json
```

### Using Postman
1. Download from postman.com
2. Create GET request
3. URL: `http://localhost:8080/api/products`
4. Send request
5. View response

### Using Browser
Simply visit: `http://localhost:8080/api/products`

---

## 📋 Checklist Before Deployment

### Frontend
- [ ] Code is clean and tested
- [ ] No console errors
- [ ] All features working
- [ ] Responsive design verified
- [ ] API integration verified
- [ ] Build completes: `npm run build`

### Backend
- [ ] Backend is running
- [ ] Database has sample data
- [ ] API endpoints responding
- [ ] CORS is enabled

### Configuration
- [ ] API URL is correct for production
- [ ] Environment variables set
- [ ] .env file configured
- [ ] No hardcoded credentials

### Testing
- [ ] Desktop tested
- [ ] Tablet tested
- [ ] Mobile tested
- [ ] All browsers tested
- [ ] API error scenarios tested

---

## 🚀 Deployment Checklist

### Before Building
```bash
# Check for errors
npm run build

# Check for warnings
npm start (check console)
```

### Build for Production
```bash
npm run build
```

### Verify Build
```bash
# Navigate to build folder
cd build

# Serve locally to test
npx serve -s .
# or use Python
python -m http.server 8000
```

### Deploy to Vercel
```bash
npm install -g vercel
vercel
```

### Deploy to Netlify
1. Push code to GitHub
2. Connect repo to Netlify dashboard
3. Netlify auto-deploys on git push

---

## 💡 Pro Tips

### Speed Up npm install
```bash
npm install --legacy-peer-deps
```

### Update Dependencies
```bash
npm update
```

### Check Outdated Packages
```bash
npm outdated
```

### Security Audit
```bash
npm audit
npm audit fix
```

### Verbose Logging
```bash
npm start -- --verbose
```

---

## 📞 Still Having Issues?

### Check These Files First
- See `SETUP.md` for setup issues
- See `README.md` for general issues
- See `API_INTEGRATION.md` for API issues
- See `VISUAL_GUIDE.md` for layout issues

### Verify Setup
1. Node.js installed: `node --version`
2. npm installed: `npm --version`
3. Dependencies installed: `npm ls`
4. Backend running: `curl http://localhost:8080/api/products`

### Ask Yourself
- Is backend running?
- Is the API URL correct?
- Are all dependencies installed?
- Did you restart the server?
- Did you clear browser cache?

---

## 🔗 Useful Links

- Node.js: https://nodejs.org
- React Docs: https://react.dev
- Axios: https://axios-http.com
- Postman: https://postman.com
- Vercel: https://vercel.com
- Netlify: https://netlify.com

---

## ✨ Common Workflow

### Daily Development
```bash
# Start server
npm start

# Make changes in code
# Changes auto-reload

# Check console for errors
# Fix errors

# Test in browser
# Verify functionality

# Stop server (Ctrl+C)
```

### Before Committing
```bash
# Build to check for errors
npm run build

# Check console
npm start

# Test thoroughly
# Then commit
```

### Before Deploying
```bash
# Build final version
npm run build

# Test build locally
npx serve -s build

# Verify everything works
# Deploy
```

---

## 🎯 Quick Reference Table

| Task | Command |
|------|---------|
| Start development | `npm start` |
| Build for production | `npm run build` |
| Install dependencies | `npm install` |
| Check version | `npm --version` |
| Clear cache | `npm cache clean --force` |
| Test API | `curl http://localhost:8080/api/products` |
| Open editor | `code .` |
| Open explorer | `explorer .` |

---

## 🎓 Learning Resources

### React
- Official Docs: https://react.dev
- Hooks Guide: https://react.dev/reference/react

### Axios
- Documentation: https://axios-http.com
- Examples: https://github.com/axios/axios

### CSS
- MDN Web Docs: https://developer.mozilla.org
- CSS Tricks: https://css-tricks.com

---

**Everything you need to get started and troubleshoot issues!** 🚀

