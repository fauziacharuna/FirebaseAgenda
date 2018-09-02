# FirebaseAgenda
For Learning Firebase Realtime Database

### Prerequisites

What things you need to install the software and how to install theme

```
Java Version 1.8 or Higher 
SDK 
Android Studio
```

### Firebase Realtime Database

A step by step series of examples that tell you how to get a development env running

```
New Project Firebase Console
```
Add SDK Android Project

```
Click android Icon in Firebase Console
```
Input Identitiy Apps
```
Input Package Name
SHA-1 by Signing Report Gradle
```
Google Services JSON
```
Download google-services.json on firebase console and copied to project/app
```
Setup depedencies buildscript on build.gradle (Project)
```
dependencies {
        classpath 'com.android.tools.build:gradle:3.0.0'
        classpath 'com.google.gms:google-services:4.0.1'
        classpath 'com.google.android.gms:play-services:11.4.0'
```
Setup dependecies on build.gradle(module:app)
```
dependencies {
    implementation fileTree(dir: 'libs', include: ['*.jar'])
    implementation 'com.android.support:appcompat-v7:27.1.1'
    testImplementation 'junit:junit:4.12'
    androidTestImplementation 'com.android.support.test:runner:1.0.2'
    androidTestImplementation 'com.android.support.test.espresso:espresso-core:3.0.2'
    compile 'com.android.support:support-annotations:27.1.0'
    implementation 'com.google.firebase:firebase-core:16.0.1'
    compile 'com.firebaseui:firebase-ui-database:3.3.1'


}
apply plugin: 'com.google.gms.google-services'
```

## References

* [MEDIUM](https://blog.javan.co.id/firebase-realtime-database-dengan-android-e8ac94dc18c8) - Firebase Realtime Database Concept
* [BLOG](https://erma.staf.akprind.ac.id/wp/index.php/2017/11/29/membuat-project-firebase-realtime-database-crud-untuk-android-app/) - Step-by-step instructional
* [FIREBASE](https://firebase.google.com/docs/database/web/structure-data?hl=ID) - Firebase Documentation


