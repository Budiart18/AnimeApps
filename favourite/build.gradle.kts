plugins {
   alias(libs.plugins.android.dynamic.feature)
   alias(libs.plugins.kotlin.android)
   id("androidx.navigation.safeargs")
}
android {
   namespace = "com.aeryz.animeapps.favourite"
   compileSdk = 34

   defaultConfig {
      minSdk = 24
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
      jvmTarget = "1.8"
   }

   buildFeatures {
      viewBinding = true
   }
}

dependencies {
   implementation(project(":app"))
   implementation(project(":core"))
}