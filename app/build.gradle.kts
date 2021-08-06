plugins {
    id(Plugins.androidApp)
    kotlin(Plugins.kotlinAndroid)
    kotlin(Plugins.kotlinAndroidExt)
    id(Plugins.navigationSafeArgs)
    kotlin("kapt")
    id(Plugins.hiltAndroid)
    id("kotlin-android")
}

android {
    compileSdk = 30

    defaultConfig {
        applicationId = AppVersion.applicationId
        minSdk = Versions.minSdk
        targetSdk = Versions.targetSdk
        versionCode = AppVersion.versionCode()
        versionName = AppVersion.versionName()

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }

        val baseUrl: String by project
        val apiKey: String by project

        buildTypes.onEach {
            it.buildConfigField("String", "CRYPTOCOMPARE_BASE_URL", baseUrl)
            it.buildConfigField("String", "CRYPTOCOMPARE_API_KEY", apiKey)
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = JavaVersion.VERSION_1_8.toString()
    }
}

dependencies {

    implementation(Deps.coreKtx)
    implementation(Deps.appCompat)
    implementation(Deps.material)
    implementation(Deps.constraintLayout)
    implementation(Deps.livedata)
    implementation(Deps.viewmodel)
    implementation(Deps.navigationFragment)
    implementation(Deps.navigationUI)
    implementation(Deps.hiltAndroid)
    kapt(Deps.hiltCompiler)

    implementation(Deps.room)
    implementation(Deps.roomExt)
    kapt(Deps.roomCompiler)

    implementation(Deps.retrofit)
    implementation(Deps.retrofitGsonConverter)
    implementation(Deps.okhttp)
    implementation(Deps.okhttpLogging)

    testImplementation(Deps.junit)
    androidTestImplementation(Deps.junitExt)
    androidTestImplementation(Deps.espresso)
}