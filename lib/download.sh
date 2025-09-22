#!/usr/bin/env bash
# Download JUnit 5 and required dependencies into ./lib
# Usage: ./download.sh [version]
# Default JUnit version: 5.10.0 (platform 1.10.0)
set -euo pipefail

JUPITER_VERSION="${1:-5.10.0}"
# Map 5.10.x to platform 1.10.x by default
PLATFORM_VERSION="${PLATFORM_VERSION:-1.${JUPITER_VERSION#5.}}"
LIB_DIR="$(cd "$(dirname "$0")/.." && pwd)/lib"

mkdir -p "$LIB_DIR"
cd "$LIB_DIR"

echo "Downloading JUnit Jupiter $JUPITER_VERSION and Platform $PLATFORM_VERSION to $LIB_DIR"

# Core JUnit 5 (Jupiter)
curl -L -o "junit-jupiter-api-${JUPITER_VERSION}.jar"     "https://repo1.maven.org/maven2/org/junit/jupiter/junit-jupiter-api/${JUPITER_VERSION}/junit-jupiter-api-${JUPITER_VERSION}.jar"
curl -L -o "junit-jupiter-engine-${JUPITER_VERSION}.jar"  "https://repo1.maven.org/maven2/org/junit/jupiter/junit-jupiter-engine/${JUPITER_VERSION}/junit-jupiter-engine-${JUPITER_VERSION}.jar"
curl -L -o "junit-jupiter-params-${JUPITER_VERSION}.jar"  "https://repo1.maven.org/maven2/org/junit/jupiter/junit-jupiter-params/${JUPITER_VERSION}/junit-jupiter-params-${JUPITER_VERSION}.jar"

# JUnit Platform
curl -L -o "junit-platform-launcher-${PLATFORM_VERSION}.jar"  "https://repo1.maven.org/maven2/org/junit/platform/junit-platform-launcher/${PLATFORM_VERSION}/junit-platform-launcher-${PLATFORM_VERSION}.jar"
curl -L -o "junit-platform-engine-${PLATFORM_VERSION}.jar"    "https://repo1.maven.org/maven2/org/junit/platform/junit-platform-engine/${PLATFORM_VERSION}/junit-platform-engine-${PLATFORM_VERSION}.jar"
curl -L -o "junit-platform-commons-${PLATFORM_VERSION}.jar"   "https://repo1.maven.org/maven2/org/junit/platform/junit-platform-commons/${PLATFORM_VERSION}/junit-platform-commons-${PLATFORM_VERSION}.jar"

# Additional dependency
curl -L -o "opentest4j-1.3.0.jar" "https://repo1.maven.org/maven2/org/opentest4j/opentest4j/1.3.0/opentest4j-1.3.0.jar"

# API Guardian (transitive dep of junit 5)
curl -L -o "apiguardian-api-1.1.2.jar" "https://repo1.maven.org/maven2/org/apiguardian/apiguardian-api/1.1.2/apiguardian-api-1.1.2.jar"

# Approval Tests
curl -L -o "approvaltests-25.4.3.jar" "https://repo1.maven.org/maven2/com/approvaltests/approvaltests/25.4.3/approvaltests-25.4.3.jar"
curl -L -o "approvaltests-util-25.4.3.jar" "https://repo1.maven.org/maven2/com/approvaltests/approvaltests-util/25.4.3/approvaltests-util-25.4.3.jar"
# For html normalisation
curl -L -o "jsoup-1.18.1.jar" "https://repo1.maven.org/maven2/org/jsoup/jsoup/1.18.1/jsoup-1.18.1.jar"

# Playwright for Java
PLAYWRIGHT_VERSION="1.47.0"
curl -L -o playwright-$PLAYWRIGHT_VERSION.jar       https://repo1.maven.org/maven2/com/microsoft/playwright/playwright/$PLAYWRIGHT_VERSION/playwright-$PLAYWRIGHT_VERSION.jar
curl -L -o driver-bundle-$PLAYWRIGHT_VERSION.jar    https://repo1.maven.org/maven2/com/microsoft/playwright/driver-bundle/$PLAYWRIGHT_VERSION/driver-bundle-$PLAYWRIGHT_VERSION.jar
curl -L -o driver-$PLAYWRIGHT_VERSION.jar           https://repo1.maven.org/maven2/com/microsoft/playwright/driver/$PLAYWRIGHT_VERSION/driver-$PLAYWRIGHT_VERSION.jar

# 👉 Add transitive deps Playwright expects:
curl -L -o gson-2.10.1.jar     https://repo1.maven.org/maven2/com/google/code/gson/gson/2.10.1/gson-2.10.1.jar
curl -L -o okhttp-4.12.0.jar   https://repo1.maven.org/maven2/com/squareup/okhttp3/okhttp/4.12.0/okhttp-4.12.0.jar
curl -L -o okio-3.9.0.jar      https://repo1.maven.org/maven2/com/squareup/okio/okio/3.9.0/okio-3.9.0.jar

echo "Done. Files in $LIB_DIR:"
ls -1 *.jar | sed 's/^/  - /'
