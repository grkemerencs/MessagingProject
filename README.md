# Notification Application

## Proje Amacı
Mesaj şablonları ve parametreler yardımıyla farklı kanallar üzerinden otomatikleştirilmiş bildirim gönderimi sağlamak.

## Özellikler
- MongoDB ile veri yönetimi  
- SMTP ile e-posta gönderme  
- Telegram Bot API entegrasyonu  
- Firebase Push Notification desteği  

- Mobil uygulamalardan alınan Firebase token'ları ile veritabanına cihaz kaydı yapılabilir.  
- Telegram üzerinden "MessagingBot" adlı bota mesaj gönderildiğinde, backend düzenli olarak Telegram'ın `getUpdates` endpoint'inden gelen mesajları çeker. Yeni bir kullanıcı tespit edilirse, kullanıcının chat ID'si veritabanına kaydedilir.  
- Emails endpoint’i üzerinden veritabanındaki e-posta adresleri düzenlenebilir.  
- Uygulama, `/send`, `/send/all`, `/send/batch` endpoint’lerine gönderilen `NotificationRequestDTO` nesnesiyle yukarıdaki tüm kanallar üzerinden bildirim gönderir.

---

## Konfigürasyon
Proje klasörüne `.env` dosyası oluşturup aşağıdaki şablonu doldurabilirsiniz:

```env
# MongoDB bağlantı URI'si
# Format: mongodb+srv://<kullanıcı_adı>:<şifre>@<cluster_adresi>/<veritabanı_adı>?options
MONGODB_URI=mongodb+srv://<username>:<password>@<cluster-address>/<database-name>?retryWrites=true&w=majority&appName=<app-name>

# E-posta kullanıcı adı
MAIL_USERNAME=your-email@example.com

# E-posta uygulama şifresi (App Password)
# Google hesabınızda 2FA etkinse, uygulama şifresi kullanmalısınız
MAIL_PASSWORD=your-app-password

# Telegram Bot API URL'si
TELEGRAM_BOT_API_URL=https://api.telegram.org/bot<your-bot-token>

# Firebase servis hesabı kimlik dosyasının yolu
FIREBASE_CREDENTIALS_PATH=path/to/firebase-adminsdk.json
```

---

## API Endpoint Açıklamaları

### NotificationController

- **POST /notification/send**  
  - Gövde: `NotificationRequestDTO` (JSON)  
  - Açıklama: Tek cihaza bildirim gönderir.

- **POST /notification/send/batch**  
  - Gövde: `NotificationRequestDTO` (JSON)  
  - Açıklama: Birden fazla cihaza toplu bildirim gönderir.

- **POST /notification/send/all**  
  - Gövde: `NotificationRequestDTO` (JSON)  
  - Açıklama: Tüm kayıtlı cihazlara bildirim gönderir.

**Beklenen JSON:**
```json
{
  "deviceTokens": ["string"],
  "deviceIds": ["string"],
  "emails": ["string"],
  "emailIds": ["string"],
  "telegramChatIds": [123456789],
  "channel": "CHANNEL_ENUM_DEGERI",
  "templateName": "string",
  "parameters": { "key": "value" }
}
```
> `channel` ve `templateName` alanları zorunludur, diğerleri opsiyoneldir.

---

### UserDeviceController

- **GET /device**  
  - Açıklama: Tüm kullanıcı cihazlarını listeler.

- **POST /device/register**  
  - Gövde: `UserDeviceRegisterRequestDTO` (JSON)  
  - Açıklama: Yeni bir kullanıcı cihazı kaydeder.

- **GET /device/{id}**  
  - Açıklama: Belirli bir cihazı ID ile getirir.

- **DELETE /device/{id}**  
  - Açıklama: Belirli bir cihazı ID ile siler.

**Beklenen JSON:**
```json
{
  "ownerName": "string",
  "platform": "PLATFORM_ENUM_DEGERI",
  "fireBaseToken": "string"
}
```

---

### EmailAddressController

- **GET /email**  
  - Açıklama: Tüm e-posta adreslerini listeler.

- **POST /email/register**  
  - Gövde: `EmailAddressRegisterRequestDTO` (JSON)  
  - Açıklama: Yeni bir e-posta adresi kaydeder.

- **GET /email/{id}**  
  - Açıklama: Belirli bir e-posta adresini ID ile getirir.

- **DELETE /email/{id}**  
  - Açıklama: Belirli bir e-posta adresini ID ile siler.

**Beklenen JSON:**
```json
{
  "emailAddress": "string"
}
```

---

### MessageTemplateController

- **GET /templates**  
  - Açıklama: Tüm mesaj şablonlarını listeler.

- **POST /templates**  
  - Gövde: `MessageTemplateRegisterRequestDTO` (JSON)  
  - Açıklama: Yeni bir mesaj şablonu kaydeder.

- **GET /templates/{name}**  
  - Açıklama: Belirli bir şablonu adıyla getirir.

- **DELETE /templates/{name}**  
  - Açıklama: Belirli bir şablonu adıyla siler.

**Beklenen JSON:**
```json
{
  "name": "string",
  "title_template": "string",
  "body_template": "string"
}
```

---

### UserTelegramAccountController

- **GET /telegram**  
  - Açıklama: Tüm Telegram hesaplarını listeler.

- **POST /telegram/register**  
  - Gövde: `UserTelegramAccount` (JSON)  
  - Açıklama: Yeni bir Telegram hesabı kaydeder.

- **GET /telegram/{id}**  
  - Açıklama: Belirli bir Telegram hesabını ID ile getirir.

- **DELETE /telegram/{id}**  
  - Açıklama: Belirli bir Telegram hesabını ID ile siler.

**Beklenen JSON:**
```json
{
  "id": "string (opsiyonel, genellikle null)",
  "name": "string",
  "telegramId": 123456789
}
```

---

### LogController

- **GET /logs/{count}**  
  - Açıklama: Son N adet log kaydını getirir.

- **GET /logs/all**  
  - Açıklama: Tüm log kayıtlarını getirir.

- **DELETE /logs/all**  
  - Açıklama: Tüm log kayıtlarını siler.

---

> Tüm endpoint'ler için daha detaylı örnekler ve enum değerleri ilgili Java sınıflarında bulunabilir.
