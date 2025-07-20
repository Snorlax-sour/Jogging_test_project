### **超慢跑 App - 系統架構 V1.0 (定稿)**

#### **1. 整合開發環境 (IDE)**
*   **工具:** **Android Studio**

#### **2. 專案建置與配置 (Build & Configuration)**
*   **建置系統:** **Gradle**
    *   透過 **`build.gradle.kts` (內/外層)** 腳本進行專案配置與依賴管理。
    *   使用 **`libs.versions.toml` (版本目錄)** 統一管理所有函式庫版本。
*   **核心配置:** **`AndroidManifest.xml`**
    *   負責定義 App 的基礎屬性、元件（如 `MainActivity`）及入口點。

#### **3. UI 層 (User Interface)**
*   **框架:** **Jetpack Compose**
    *   使用 **Kotlin** 語言以宣告式方式構建使用者介面。
    *   UI 的主題與樣式由程式碼 (`/ui/theme/Theme.kt`) 進行定義與控制。

#### **4. 資料層 (Data Layer)**

##### **4.1 本地持久化 (Local Persistence)**
*   **資料庫:** **SQLite**
*   **抽象層 (ORM):** **Room** (Jetpack 元件)
    *   透過 **`@Entity`** 定義資料表結構。
    *   透過 **`@Dao`** 定義資料庫存取操作（CRUD）。
    *   **安全性:** 利用 Room 的參數化查詢機制，原生防禦 **SQL Injection** 攻擊。
    *   **程式碼生成:** 依賴 **KSP (Kotlin Symbol Processing)** 在編譯期自動產生必要的底層實作程式碼。

##### **4.2 雲端備份與同步 (Cloud Backup & Sync)**
*   **認證服務 (Authentication):** **Firebase Authentication**
    *   整合 **Google OAuth**，提供 `Google 帳號` 一鍵登入功能。
    *   負責使用者身份識別與管理。
*   **儲存服務 (Storage):** **Cloud Storage for Firebase**
    *   **備份策略:**
        1.  將本地 **Room 資料庫**的資料，完整匯出為一個**單一檔案** (例如 `JSON` 格式)。
        2.  將該備份檔，上傳至 Cloud Storage。
    *   **還原策略:**
        1.  從 Cloud Storage 下載備份檔。
        2.  解析檔案內容，並將資料寫回本地 Room 資料庫。
*   **風險管理:**
    *   **版本覆蓋:** 已知此架構存在版本覆蓋風險。在當前的「**單一使用者、單一裝置**」使用場景下，此風險被認為是**可接受**的。
    * APP開啟時警告說此APP只有單人裝置雲端同步的問題

