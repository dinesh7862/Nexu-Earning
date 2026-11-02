# NEXUS AI Android prototype

This is a starter Android Studio project using Kotlin + Jetpack Compose.

## Build
1. Install Android Studio (latest stable).
2. Open this folder as a project.
3. Let Gradle sync.
4. Connect an Android phone with USB debugging enabled, or start an emulator.
5. Press Run.

## Current status
- Native Android UI with Home, AI Chat, Wallet and Profile tabs.
- AI replies are demo placeholders, not connected to a model.
- Wallet is a demo balance only; no real money, ads, referrals or payouts are enabled.

## Before public launch
Build a secure backend for authentication, AI requests, wallet ledger, advertiser offer verification,
fraud prevention, support and payout review. Keep AI provider keys and ad network secrets on the server,
never inside the APK. Integrate only an ad/reward provider whose current policies explicitly permit the
proposed user reward flow. Do not reward ad clicks or incentivize invalid impressions. Add privacy policy,
terms, account deletion, age/region checks and Play Store disclosures.


## Build using only an Android phone
See `BUILD_ON_PHONE.md`. A GitHub Actions workflow is included at `.github/workflows/android.yml` to build a debug APK in the cloud.
