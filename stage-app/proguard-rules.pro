# Add project specific ProGuard rules here.
# You can control the set of applied configuration files using the
# proguardFiles setting in build.gradle.kts.kts.
#
# For more details, see
#   http://developer.android.com/guide/developing/tools/proguard.html

# If your project uses WebView with JS, uncomment the following
# and specify the fully qualified class name to the JavaScript interface
# class:
#-keepclassmembers class fqcn.of.javascript.interface.for.webview {
#   public *;
#}

# Uncomment this to preserve the line number information for
# debugging stack traces.
#-keepattributes SourceFile,LineNumberTable
#-keepattributes SourceFile,LineNumberTable        # Keep file names and line numbers.
#-keep public class * extends java.lang.Exception  # Optional: Keep custom exceptions.

# If you keep the line number information, uncomment this to
# hide the original source file name.
#-renamesourcefileattribute SourceFile
-dontwarn org.slf4j.impl.StaticLoggerBinder

#For okhttp3
-dontwarn okhttp3.internal.http.HttpDate
-dontwarn io.grpc.internal.DnsNameResolverProvider
-dontwarn io.grpc.internal.PickFirstLoadBalancerProvider

#### Gson ProGuard and R8 rules which are relevant for all users
#### This file is automatically recognized by ProGuard and R8, see https://developer.android.com/build/shrink-code#configuration-files
####
#### IMPORTANT:
#### - These rules are additive; don't include anything here which is not specific to Gson (such as completely
####   disabling obfuscation for all classes); the user would be unable to disable that then
#### - These rules are not complete; users will most likely have to add additional rules for their specific
####   classes, for example to disable obfuscation for certain fields or to keep no-args constructors
####
#
## Keep generic signatures; needed for correct type resolution
#-keepattributes Signature
#
## Keep Gson annotations
## Note: Cannot perform finer selection here to only cover Gson annotations, see also https://stackoverflow.com/q/47515093
#-keepattributes RuntimeVisibleAnnotations,AnnotationDefault
#
#### The following rules are needed for R8 in "full mode" which only adheres to `-keepattribtues` if
#### the corresponding class or field is matches by a `-keep` rule as well, see
#### https://r8.googlesource.com/r8/+/refs/heads/main/compatibility-faq.md#r8-full-mode
#
## Keep class TypeToken (respectively its generic signature) if present
#-if class com.google.gson.reflect.TypeToken
#-keep,allowobfuscation class com.google.gson.reflect.TypeToken
#
## Keep any (anonymous) classes extending TypeToken
#-keep,allowobfuscation class * extends com.google.gson.reflect.TypeToken
#
## Keep classes with @JsonAdapter annotation
#-keep,allowobfuscation,allowoptimization @com.google.gson.annotations.JsonAdapter class *
#
## Keep fields with any other Gson annotation
## Also allow obfuscation, assuming that users will additionally use @SerializedName or
## other means to preserve the field names
#-keepclassmembers,allowobfuscation class * {
#  @com.google.gson.annotations.Expose <fields>;
#  @com.google.gson.annotations.JsonAdapter <fields>;
#  @com.google.gson.annotations.Since <fields>;
#  @com.google.gson.annotations.Until <fields>;
#}
#
## Keep no-args constructor of classes which can be used with @JsonAdapter
## By default their no-args constructor is invoked to create an adapter instance
#-keepclassmembers class * extends com.google.gson.TypeAdapter {
#  <init>();
#}
#-keepclassmembers class * implements com.google.gson.TypeAdapterFactory {
#  <init>();
#}
#-keepclassmembers class * implements com.google.gson.JsonSerializer {
#  <init>();
#}
#-keepclassmembers class * implements com.google.gson.JsonDeserializer {
#  <init>();
#}
#
## Keep fields annotated with @SerializedName for classes which are referenced.
## If classes with fields annotated with @SerializedName have a no-args
## constructor keep that as well. Based on
## https://issuetracker.google.com/issues/150189783#comment11.
## See also https://github.com/google/gson/pull/2420#discussion_r1241813541
## for a more detailed explanation.
#-if class *
#-keepclasseswithmembers,allowobfuscation class <1> {
#  @com.google.gson.annotations.SerializedName <fields>;
#}
#-if class * {
#  @com.google.gson.annotations.SerializedName <fields>;
#}
#-keepclassmembers,allowobfuscation,allowoptimization class <1> {
#  <init>();
#}
#
#-keep class * implements com.google.gson.TypeAdapterFactory
#-keep class * implements com.google.gson.JsonSerializer
#-keep class * implements com.google.gson.JsonDeserializer
#
#
## Retrofit does reflection on generic parameters. InnerClasses is required to use Signature and
## EnclosingMethod is required to use InnerClasses.
#-keepattributes Signature, InnerClasses, EnclosingMethod
#
## Retrofit does reflection on method and parameter annotations.
#-keepattributes RuntimeVisibleAnnotations, RuntimeVisibleParameterAnnotations
#
## Keep annotation default values (e.g., retrofit2.http.Field.encoded).
#-keepattributes AnnotationDefault
#
## Retain service method parameters when optimizing.
#-keepclassmembers,allowshrinking,allowobfuscation interface * {
#    @retrofit2.http.* <methods>;
#}
#
## Ignore annotation used for build tooling.
#-dontwarn org.codehaus.mojo.animal_sniffer.IgnoreJRERequirement
#
## Ignore JSR 305 annotations for embedding nullability information.
#-dontwarn javax.annotation.**
#
## Guarded by a NoClassDefFoundError try/catch and only used when on the classpath.
#-dontwarn kotlin.Unit
#
## Top-level functions that can only be used by Kotlin.
#-dontwarn retrofit2.KotlinExtensions
#-dontwarn retrofit2.KotlinExtensions$*
#
## With R8 full mode, it sees no subtypes of Retrofit interfaces since they are created with a Proxy
## and replaces all potential values with null. Explicitly keeping the interfaces prevents this.
#-if interface * { @retrofit2.http.* <methods>; }
#-keep,allowobfuscation interface <1>
#
## Keep inherited services.
#-if interface * { @retrofit2.http.* <methods>; }
#-keep,allowobfuscation interface * extends <1>
#
## With R8 full mode generic signatures are stripped for classes that are not
## kept. Suspend functions are wrapped in continuations where the type argument
## is used.
#-keep,allowobfuscation,allowshrinking class kotlin.coroutines.Continuation
#
## R8 full mode strips generic signatures from return types if not kept.
#-if interface * { @retrofit2.http.* public *** *(...); }
#-keep,allowoptimization,allowshrinking,allowobfuscation class <3>
#
## With R8 full mode generic signatures are stripped for classes that are not kept.
#-keep,allowobfuscation,allowshrinking class retrofit2.Response
#


-dontwarn com.bumptech.glide.Glide
-dontwarn com.bumptech.glide.RequestBuilder
-dontwarn com.bumptech.glide.RequestManager
-dontwarn com.bumptech.glide.request.BaseRequestOptions
-dontwarn com.bumptech.glide.request.RequestOptions
-dontwarn com.bumptech.glide.request.target.ViewTarget
-dontwarn com.google.android.exoplayer2.ExoPlayer$Builder
-dontwarn com.google.android.exoplayer2.ExoPlayer
-dontwarn com.google.android.exoplayer2.MediaItem
-dontwarn com.google.android.exoplayer2.Player$Listener
-dontwarn com.google.android.exoplayer2.Player
-dontwarn com.google.android.exoplayer2.source.MediaSource
-dontwarn com.google.android.exoplayer2.source.hls.HlsMediaSource$Factory
-dontwarn com.google.android.exoplayer2.source.hls.HlsMediaSource
-dontwarn com.google.android.exoplayer2.trackselection.AdaptiveTrackSelection$Factory
-dontwarn com.google.android.exoplayer2.trackselection.DefaultTrackSelector
-dontwarn com.google.android.exoplayer2.trackselection.ExoTrackSelection$Factory
-dontwarn com.google.android.exoplayer2.trackselection.TrackSelector
-dontwarn com.google.android.exoplayer2.ui.StyledPlayerView
-dontwarn com.google.android.exoplayer2.upstream.BandwidthMeter
-dontwarn com.google.android.exoplayer2.upstream.DataSource$Factory
-dontwarn com.google.android.exoplayer2.upstream.DefaultBandwidthMeter$Builder
-dontwarn com.google.android.exoplayer2.upstream.DefaultBandwidthMeter
-dontwarn com.google.android.exoplayer2.upstream.DefaultDataSource$Factory
-dontwarn com.google.android.exoplayer2.upstream.DefaultHttpDataSource$Factory
-dontwarn com.google.android.exoplayer2.upstream.TransferListener
-dontwarn com.google.android.exoplayer2.util.Util

# Keep generic signatures; needed for correct type resolution
-keepattributes Signature

# Keep class TypeToken (respectively its generic signature)
-keep class com.google.gson.reflect.TypeToken { *; }

# Keep any (anonymous) classes extending TypeToken
-keep class * extends com.google.gson.reflect.TypeToken

-keep class com.network.core.dto.** { *; }
-keep class com.network.core.dbo.** { *; }
-keep class com.network.core.datastoreModel.** { *; }

-keep class com.core.coreDomain.model.** { *; }
-keep class com.stage.businesslogic.domain.** { *; }

-keep class com.epson.** { *; }
-dontwarn com.epson.**

-keepattributes SourceFile,LineNumberTable
# Keep Timber
-keep class timber.log.** { *; }

# Optional: Keep your custom Tree
-keep class com.core.coreDatasource.manager.FileLoggingTree { *; }