Telugu Calendar - Android app (Oct 2026 - Dec 2027)
====================================================

What it is
  A small offline Android app. It shows the calendar in the same layout as the
  printed page: weekdays down the left, weeks across, a festivals box, moon
  markers, and a day view with tithi, nakshatra, varjyam, rahukalam and
  durmuhurtham. Switches at the top turn colour and dark mode on or off.
  It needs no internet permission.
  Oct-Dec 2026 have full panchangam data. The 2027 months show dates and weekdays
  only, because the source PDF does not cover 2027.
  The calendar data was typed in from the printed Venkatrama & Co. 2026 calendar
  for personal use. Do not publish the app (Play Store etc.) without the
  publisher's permission.

The project has NOT been compiled yet (the workspace it was made in could not
reach the Android SDK). Use one of the two routes below.

Route A - Android Studio (easiest)
  1. Install Android Studio (free).
  2. File > Open > choose this TeluguCalendar folder. Let it sync (it downloads
     Gradle and the Android SDK on its own).
  3. Build > Build Bundle(s) / APK(s) > Build APK(s).
  4. Click "locate" in the pop-up. app-debug.apk is the file.
  5. Copy it to the phone and open it. Allow "install unknown apps" if asked.
  Or plug the phone in (USB debugging on) and press Run.

Route B - GitHub, no installs
  1. Create a new private repository on github.com and upload everything in
     this folder (keep the .github folder).
  2. Open the Actions tab > "Build APK" > it runs by itself (about 3 minutes).
  3. Open the finished run and download the "TeluguCalendar-apk" file. Unzip it to
     get TeluguCalendar.apk.

Editing the calendar
  All content is in app/src/main/assets/index.html (one file, plain HTML/JS).
  Day data is in the object M near the top of the script. To add 2027, add
  month entries to M and change mdata() so it looks up the year as well.
