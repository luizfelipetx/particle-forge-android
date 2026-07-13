# Particle Forge

Aplicativo Android nativo em **Kotlin** que empacota uma versão offline do jogo HTML5 [particle_clicker](https://github.com/particle-clicker/particle-clicker) em um `WebView` local.

## Identidade Android

- Application ID: `com.consultanomesujo.particleforge`
- minSdk: 23
- targetSdk: 35
- versão: 1.0.0

## AdMob

O projeto usa apenas os **IDs oficiais de teste do Google**. Antes de publicar, substitua `admob_app_id`, `admob_banner_id` e `admob_interstitial_id` em `app/src/main/res/values/strings.xml`, implemente o fluxo de consentimento exigido para as regiões aplicáveis e teste segundo as políticas do Google Mobile Ads SDK.

## Build

```bash
./gradlew assembleDebug
./gradlew assembleRelease
```

## Licença e atribuição

O shell Android criado para este projeto está sob licença MIT. O jogo incorporado mantém sua licença original **MIT**. Consulte `THIRD_PARTY_LICENSE.txt`, `OPEN_SOURCE_NOTICES.md` e os arquivos preservados em `app/src/main/assets/www/`.

Não remova os créditos dos autores originais. A publicação na Google Play exige diferenciação real, política de privacidade, Data Safety preenchido e conformidade com as regras contra conteúdo repetitivo.
