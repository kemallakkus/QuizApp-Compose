plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlin.compose)
    alias(libs.plugins.ksp)
    alias(libs.plugins.hilt.plugin)
    alias(libs.plugins.detekt)
    alias(libs.plugins.kotlin.serialization)
}

android {
    namespace = "com.kakkus.quizapp"
    compileSdk = 35

    defaultConfig {
        applicationId = "com.kakkus.quizapp"
        minSdk = 24
        targetSdk = 35
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        release {
            isMinifyEnabled = true
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
            buildConfigField("String", "BASE_URL", "\"https://api.canerture.com/quiz/\"")
            signingConfig = signingConfigs.getByName("debug")
        }

        debug {
            isMinifyEnabled = false
            buildConfigField("String", "BASE_URL", "\"https://api.canerture.com/quiz/\"")
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
        compose = true
    }
    buildFeatures {
        buildConfig = true
    }
}

dependencies {

    // AndroidX Kütüphaneleri
    implementation(libs.androidx.core.ktx) // Android KTX: Kotlin diline özel Android API'lerini kolaylaştırır.
    implementation(libs.androidx.lifecycle.runtime.ktx) // Lifecycle KTX: Android lifecycle yönetimi için Kotlin uzantıları.
    implementation(libs.androidx.activity.compose) // Compose Activity: Jetpack Compose için Activity entegrasyonu.
    implementation(platform(libs.androidx.compose.bom)) // Compose BOM: Tüm Compose bağımlılıkları için aynı versiyonu kullanır.
    implementation(libs.androidx.ui) // Compose UI: Jetpack Compose temel UI bileşenleri.
    implementation(libs.androidx.ui.graphics) // Compose Graphics: Grafiklerle ilgili yardımcı araçlar.
    implementation(libs.androidx.ui.tooling.preview) // Compose Preview: Tasarım ön izleme araçları.
    implementation(libs.androidx.material3) // Material3: Jetpack Compose için Material Design 3 bileşenleri.

    // Test Kütüphaneleri
    testImplementation(libs.junit) // JUnit: Birim testler için.
    androidTestImplementation(libs.androidx.junit) // AndroidX JUnit: Android testlerinde JUnit desteği.
    androidTestImplementation(libs.androidx.espresso.core) // Espresso: Android UI testleri için.
    androidTestImplementation(platform(libs.androidx.compose.bom)) // Compose BOM: Android testleri için Compose bağımlılıkları.
    androidTestImplementation(libs.androidx.ui.test.junit4) // Compose UI Test JUnit4: Jetpack Compose testleri için JUnit4 entegrasyonu.
    debugImplementation(libs.androidx.ui.tooling) // Compose Tooling: Debug sırasında tasarım ön izleme araçları.
    debugImplementation(libs.androidx.ui.test.manifest) // Compose Test Manifest: Test için manifest yapılandırma desteği.

    // Jetpack Compose Lifecycle
    implementation(libs.lifecycle.runtime.compose) // Compose Lifecycle: Compose'da lifecycle yönetimi.

    // Hilt (Dependency Injection)
    ksp(libs.hilt.compiler) // Hilt Compiler: Hilt için kod üretimi.
    implementation(libs.hilt.android) // Hilt Android: Android'de Dependency Injection (DI) için.
    implementation(libs.hilt.navigation.compose) // Hilt Navigation Compose: Compose ile Hilt'in entegrasyonu.
    implementation(libs.navigation.compose) // Compose Navigation: Jetpack Compose için navigation.

    // Coil (Görsel Yükleme Kütüphanesi)
    implementation(libs.coil.gif) // Coil GIF: Coil ile GIF desteği.
    implementation(libs.coil) // Coil: Görsel yükleme ve önbellekleme.

    // Detekt (Kod Kalitesi)
    detektPlugins(libs.detekt) // Detekt: Kotlin için statik kod analizi aracı.

    // Retrofit (API İstekleri)
    implementation(libs.retrofit) // Retrofit: API istemcisi.
    implementation(libs.converter.gson) // Gson Converter: Retrofit için JSON dönüştürücü.

    // Glide (Görsel Yükleme Kütüphanesi)
    implementation(libs.glide) // Glide: Görsel yükleme ve önbellekleme.
    implementation(libs.landscapist.glide) // Landscapist Glide: Glide'ın Compose ile kullanımı için yardımcı araçlar.

    // Serialization
    implementation(libs.serialzer) // Kotlinx Serialization: JSON serileştirme ve deserileştirme.

    // Preferences DataStore (Anahtar-değer depolama)
    //noinspection GradleDependency
    implementation(libs.androidx.datastore.preferences)

    implementation (libs.androidx.core.splashscreen)

    // Chucker Debug
    debugImplementation(libs.library)

    // Chucker Release (sadece crash raporları için)
    releaseImplementation(libs.library.no.op)

}

detekt {
    config.setFrom(file("$rootDir/detekt/detektConfig.yml"))
    source.from(files("src/main/kotlin"))
    parallel = true
    autoCorrect = true
    buildUponDefaultConfig = true
}
