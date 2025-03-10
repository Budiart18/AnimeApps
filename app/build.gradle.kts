plugins {
   alias(libs.plugins.android.application)
   alias(libs.plugins.kotlin.android)
   id("com.google.devtools.ksp")
   id("kotlin-parcelize")
   id("androidx.navigation.safeargs")
}

android {
   namespace = "com.aeryz.animeapps"
   compileSdk = 35

   defaultConfig {
      applicationId = "com.aeryz.animeapps"
      minSdk = 24
      //noinspection EditedTargetSdkVersion
      targetSdk = 35
      versionCode = 1
      versionName = "1.0"

      testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
   }

   buildTypes {
      release {
         isMinifyEnabled = false
         proguardFiles(
            getDefaultProguardFile("proguard-android-optimize.txt"),
            "proguard-rules.pro"
         )
      }
   }
   compileOptions {
      sourceCompatibility = JavaVersion.VERSION_17
      targetCompatibility = JavaVersion.VERSION_17
   }
   kotlinOptions {
      jvmTarget = "17"
   }
   buildFeatures {
      viewBinding = true
   }
   dynamicFeatures += setOf(":favourite")
}

dependencies {
   implementation(project(":core"))
}