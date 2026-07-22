# Email Organizer Android App - Quick Start

## 🚀 Başlangıç Kılavuzu

### Adım 1: Repository'yi Clone Et
```bash
git clone https://github.com/aayurtseven/email-organizer.git
cd email-organizer
```

### Adım 2: Android Studio'da Aç
- Android Studio açıp "Open Project" seç
- email-organizer klasörünü seç
- "Sync Now" butonuna tıkla

### Adım 3: API Keys Ayarla

#### Gemini API Key
1. https://makersuite.google.com/app/apikey adresine git
2. "Create API Key" tıkla
3. Key'i kopyala

#### Gmail App Password
1. Google hesabına gir
2. https://myaccount.google.com/apppasswords adresine git
3. "Select app" → "Mail" seç
4. "Select device" → "Android" seç
5. 16 karakterlik password'ı kopyala

#### Ethereal Email Hesabı
1. https://ethereal.email adresine git
2. "Create Ethereal Account" tıkla
3. Email ve password oluştur
4. Ekrana gelen SMTP credentials'ı kopyala

### Adım 4: Credentials'ı Dosyaya Ekle

`app/src/main/res/values/strings.xml` dosyasını aç ve şu bilgileri gir:

```xml
<?xml version="1.0" encoding="utf-8"?>
<resources>
    <string name="app_name">Email Organizer</string>
    
    <!-- Replace YOUR_* with actual values -->
    <string name="gemini_api_key">YOUR_GEMINI_API_KEY</string>
    <string name="gmail_email">your.email@gmail.com</string>
    <string name="gmail_app_password">xxxx xxxx xxxx xxxx</string>
    <string name="ethereal_email">your.ethereal@email.com</string>
    <string name="ethereal_password">your_ethereal_password</string>
</resources>
```

### Adım 5: Çalıştır
- Android Studio'da **Run** (⏯️) tuşuna bas
- Emülatör veya gerçek cihazı seç
- Uygulama başlayacak

---

## 📱 Uygulama Özellikleri

### ✅ Implemente Edilenler
- [x] Inbox Screen (Ana ekran)
- [x] Email Detail Screen (Email detayları)
- [x] Compose Screen (Mail yazma)
- [x] Room Database (Local storage)
- [x] Gemini AI Integration
- [x] Gmail IMAP Reader
- [x] Ethereal SMTP Sender
- [x] Material 3 Design

### 🔄 Sonraki Geliştirilecek
- [ ] Sesli Komutlar
- [ ] Push Notifications
- [ ] Settings Screen
- [ ] Spam Classifier
- [ ] Email Sync Service
- [ ] Dark Mode
- [ ] Search & Filter
- [ ] Attachment Download

---

## 🛠️ Troubleshooting

### "API Key Invalid" hatası
- Gemini key'ini kontrol et
- https://makersuite.google.com/app/apikey adresinde key var mı?

### "Gmail Connection Failed"
- App Password doğru mu?
- 2FA aktif mi?
- 16 karakterlik password'ı başında boşluk olmadan gir

### "Ethereal Send Failed"
- Email adresi doğru mu?
- Password'u yanlış girmedi mi?
- Internet bağlantısı var mı?

---

## 📝 Geliştirme Notları

- Proje `develop` branch'inde geliştiriliyor
- `main` branch production sürümü
- Her feature için yeni branch oluştur: `feature/feature-name`

---

## 🤝 Yardım
Sorun yaşarsan issue aç veya developer'a ulaş!

Happy Coding! 🚀
