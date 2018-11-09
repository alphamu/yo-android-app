# Yeoman Generator for an Android app

This generates a Java or Kotlin android app that is preconfigured with:

- Dagger 2
- Retrofit
- Room
- Jetpack
- Glide

There is also a Dockerfile that allows you to build your code in clean
environment to see if there are going to be any issues.

## Requirements

- Node (almost any version will do)
- Yeoman
- JDK 1.8
- Android SDK
- Android P (API 28)
- Latest builds tools

## Usage

Make sure yeoman is installed, if it's not, install it:

    npm install -g yo

If Yeoman is installed, you can generate an app using:

    npm install generator-android-app
    yo android-app

This will result in something as follows:

    ? Name of your project: MyApp
    ? Package name for your project: com.ali.mu
    ? Java or Kotlin? (j/k): j
    app name:  MyApp
    app package:  com.ali.mu
    app language:  j
    /Users/muzaffar ali/Development/yo-android-app/generators/app/templates
    /Users/muzaffar ali/Development/test
    ./MyApp/app/src/main/java/com/ali/mu
    create MyApp/Dockerfile
    create MyApp/README.MD
    create MyApp/build-test-deploy.sh
    create MyApp/build.gradle
    create MyApp/gradle.properties
    create MyApp/gradlew
    create MyApp/gradlew.bat
    create MyApp/settings.gradle
    create MyApp/app/build.gradle
    create MyApp/app/proguard-rules.pro
    create MyApp/gradle/wrapper/gradle-wrapper.jar
    create MyApp/gradle/wrapper/gradle-wrapper.properties
    create MyApp/app/schemas/com.alimuzaffar.blank.database.TheDatabase/1.json
    create MyApp/app/src/main/AndroidManifest.xml
    create MyApp/app/src/main/res/drawable/ic_launcher_background.xml
    create MyApp/app/src/main/res/drawable-v24/ic_launcher_foreground.xml
    create MyApp/app/src/main/res/layout/main_activity.xml
    create MyApp/app/src/main/res/layout/main_fragment.xml
    create MyApp/app/src/main/res/menu/menu_main.xml
    create MyApp/app/src/main/res/mipmap-anydpi-v26/ic_launcher.xml
    create MyApp/app/src/main/res/mipmap-anydpi-v26/ic_launcher_round.xml
    create MyApp/app/src/main/res/mipmap-hdpi/ic_launcher.png
    create MyApp/app/src/main/res/mipmap-hdpi/ic_launcher_round.png
    create MyApp/app/src/main/res/mipmap-mdpi/ic_launcher.png
    create MyApp/app/src/main/res/mipmap-mdpi/ic_launcher_round.png
    create MyApp/app/src/main/res/mipmap-xhdpi/ic_launcher.png
    create MyApp/app/src/main/res/mipmap-xhdpi/ic_launcher_round.png
    create MyApp/app/src/main/res/mipmap-xxhdpi/ic_launcher.png
    create MyApp/app/src/main/res/mipmap-xxhdpi/ic_launcher_round.png
    create MyApp/app/src/main/res/mipmap-xxxhdpi/ic_launcher.png
    create MyApp/app/src/main/res/mipmap-xxxhdpi/ic_launcher_round.png
    create MyApp/app/src/main/res/values/colors.xml
    create MyApp/app/src/main/res/values/dimens.xml
    create MyApp/app/src/main/res/values/strings.xml
    create MyApp/app/src/main/res/values/styles.xml
    create MyApp/app/src/main/java/com/ali/mu/App.java
    create MyApp/app/src/main/java/com/ali/mu/database/TheDatabase.java
    create MyApp/app/src/main/java/com/ali/mu/net/ApiInterface.java
    create MyApp/app/src/main/java/com/ali/mu/repository/SampleRepository.java
    create MyApp/app/src/main/java/com/ali/mu/util/KeyStoreHelper.java
    create MyApp/app/src/main/java/com/ali/mu/util/Prefs.java
    create MyApp/app/src/main/java/com/ali/mu/database/converter/DateConverter.java
    create MyApp/app/src/main/java/com/ali/mu/database/converter/ListConverter.java
    create MyApp/app/src/main/java/com/ali/mu/database/dao/SampleDao.java
    create MyApp/app/src/main/java/com/ali/mu/database/entity/Sample.java
    create MyApp/app/src/main/java/com/ali/mu/di/component/AppComponent.java
    create MyApp/app/src/main/java/com/ali/mu/di/component/ObjComponent.java
    create MyApp/app/src/main/java/com/ali/mu/di/module/ActivityModule.java
    create MyApp/app/src/main/java/com/ali/mu/di/module/FragmentModule.java
    create MyApp/app/src/main/java/com/ali/mu/di/module/NetModule.java
    create MyApp/app/src/main/java/com/ali/mu/di/module/RepositoryModule.java
    create MyApp/app/src/main/java/com/ali/mu/di/module/ViewModelModule.java
    create MyApp/app/src/main/java/com/ali/mu/di/scope/UserScope.java
    create MyApp/app/src/main/java/com/ali/mu/di/scope/ViewModelScope.java
    create MyApp/app/src/main/java/com/ali/mu/net/mock/MockApiImpl.java
    create MyApp/app/src/main/java/com/ali/mu/net/mock/MockCall.java
    create MyApp/app/src/main/java/com/ali/mu/net/mock/MockHelper.java
    create MyApp/app/src/main/java/com/ali/mu/ui/main/FactoryViewModel.java
    create MyApp/app/src/main/java/com/ali/mu/ui/main/MainActivity.java
    create MyApp/app/src/main/java/com/ali/mu/ui/main/MainFragment.java
    create MyApp/app/src/main/java/com/ali/mu/ui/main/MainViewModel.java

The command will put the code in a directory with the same name as the app name with all non-word characters removed.
You can rename it to whatever you like before opening it in Android Studio.

Or you can download the source. Link to it to yo:

    git clone git@github.com:alphamu/yo-android-app.git
    cd yo-android-app
    npm link
    cd /some/dir
    yo android-app



