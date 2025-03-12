-dontwarn java.lang.invoke.StringConcatFactory

-keep class com.aeryz.core.di.CoreModuleKt{ *; }
-keepclassmembers class com.aeryz.core.di.CoreModuleKt { *; }

-keep class com.aeryz.core.domain.usecase.** { *; }
-keepclassmembers class com.domain.usecase.** { *; }

-keep class com.databinding.** { *; }
-keepclassmembers class com.databinding.** { *; }

-keep class com.aeryz.core.ui.** { *; }
-keepclassmembers class com.aeryz.core.ui.** { *; }

-keep class com.aeryz.core.data.** { *; }
-keepclassmembers class com.aeryz.core.data.** { *; }

-keep class com.aeryz.core.data.source.remote.response.** { *; }
-keepclassmembers class com.aeryz.core.data.source.remote.response.** { *; }

-keep class com.aeryz.core.domain.model.** { *; }
-keepclassmembers class com.aeryz.core.domain.model.** { *; }