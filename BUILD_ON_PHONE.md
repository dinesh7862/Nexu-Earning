# Build the Android APK using only your phone

You do not need Android Studio on your phone. GitHub Actions can build the APK in the cloud.

1. Create/sign in to a GitHub account in your mobile browser.
2. Create a new repository named `nexus-ai-android`.
3. Upload the contents of this ZIP into the repository (upload the files inside the folder, not the outer ZIP).
4. Open the repository's **Actions** tab.
5. Select **Build NEXUS AI APK** and tap **Run workflow**. If it starts automatically after uploading to `main`, you can wait for that run instead.
6. Open the completed workflow run, scroll to **Artifacts**, and download `nexus-ai-debug-apk`.
7. Extract the downloaded artifact ZIP and install `app-debug.apk` on your Android phone. Android may ask you to allow installation from that browser/file manager.

## Current prototype limitations
- This build is a UI prototype. AI replies are placeholders.
- Wallet balance is a demo only. No real earnings, ads, referrals or withdrawals are active.
- Do not add API keys or ad-network secrets to the Android app. A secure backend is required.
- Sponsored rewards must be based on valid, provider-approved events; never pay users to click ads or generate invalid ad traffic.
- A production release needs authentication, a privacy policy, terms, account deletion, fraud controls, payout checks and ad-network approval.
