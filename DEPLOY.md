Despliegue de Aplicación FullStack (Angular + Spring Boot) en Google Cloud Platform (GCP)

Este documento describe paso a paso cómo desplegar una aplicación fullstack compuesta por un frontend en Angular y un backend en Spring Boot, dockerizados, en Google Cloud Platform utilizando Cloud Run.

📦 Requisitos Previos

Para el despliegue en GCP

Tener una cuenta de Google Cloud: https://console.cloud.google.com

Tener instalado:

Docker

Google Cloud SDK (gcloud)

Angular CLI y Maven en caso de querer hacer pruebas locales

Para pruebas locales en tu máquina

Docker Desktop en funcionamiento

Node.js y npm

Angular CLI: npm install -g @angular/cli

Maven (versión 3.8+)

JDK 17

🔁 Paso a Paso para pruebas locales (sin subir a la nube)

1. Clonar el repositorio y acceder al proyecto

git clone https://github.com/tu-usuario/tu-proyecto.git
cd tu-proyecto

2. Construir el backend

cd backend
mvn clean package

Esto genera target/productos.jar.

3. Construir el frontend

cd ../frontend
npm install
npm run build --prod

Esto genera los archivos estáticos en dist/frontend.

4. Levantar toda la aplicación con Docker Compose

Ubicate en la raíz donde está el docker-compose.yml y ejecutá:

docker-compose up --build

5. Acceder a la aplicación localmente

Frontend: http://localhost:4200

Backend (API REST): http://localhost:8090

Si configuraste CORS correctamente, el frontend puede comunicarse sin problema con el backend.

☁️ Paso a Paso para desplegar en GCP

Paso 1: Autenticarse en GCP y preparar el proyecto

gcloud auth login
gcloud config set project [ID_DEL_PROYECTO]
gcloud config set run/region us-central1

Paso 2: Crear el Artifact Registry (para subir las imágenes Docker)

gcloud artifacts repositories create fullstack-app-repo \
  --repository-format=docker \
  --location=us-central1 \
  --description="Repo para app fullstack"

Paso 3: Backend - Construir y subir la imagen

cd backend
mvn clean package

docker build -t us-central1-docker.pkg.dev/[ID_DEL_PROYECTO]/fullstack-app-repo/backend .

gcloud auth configure-docker us-central1-docker.pkg.dev
docker push us-central1-docker.pkg.dev/[ID_DEL_PROYECTO]/fullstack-app-repo/backend

Paso 4: Frontend - Construir y subir la imagen

cd ../frontend
npm install
npm run build --prod

docker build -t us-central1-docker.pkg.dev/[ID_DEL_PROYECTO]/fullstack-app-repo/frontend .
docker push us-central1-docker.pkg.dev/[ID_DEL_PROYECTO]/fullstack-app-repo/frontend

Paso 5: Desplegar en Cloud Run

Backend:

gcloud run deploy backend-service \
  --image=us-central1-docker.pkg.dev/[ID_DEL_PROYECTO]/fullstack-app-repo/backend \
  --platform=managed \
  --allow-unauthenticated \
  --port=8090

Frontend:

gcloud run deploy frontend-service \
  --image=us-central1-docker.pkg.dev/[ID_DEL_PROYECTO]/fullstack-app-repo/frontend \
  --platform=managed \
  --allow-unauthenticated \
  --port=80

Paso 6: Acceder a la aplicación

Una vez desplegados, GCP te dará una URL pública para cada servicio (backend y frontend).

Accedé al frontend usando el enlace provisto por Cloud Run

El frontend Angular ya debe estar configurado para apuntar al dominio del backend
(puede modificarse en environment.prod.ts y recompilar si es necesario)

