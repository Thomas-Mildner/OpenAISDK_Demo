# OpenAI Connector (Java)

A Java-based connector for interacting with a custom OpenAI-compatible model endpoint hosted by [Artcodix](https://artcodix.com).  
This implementation is compatible with the official `openai-java` SDK and currently supports the `completions` endpoint only.

## Features

- Compatible with `openai-java` SDK (v1.5.1)
- Supports the `completions` endpoint
- Gradle-based Java project

## Setup

The API key is provided directly in the code (no environment variables required).

## Build

```bash
./gradlew build
```

## Dependencies

In `build.gradle.kts` or `build.gradle`:

```kotlin
dependencies {
    implementation("com.openai:openai-java:1.5.1")
}
```

## License

MIT License