# StayCanvas

Full-stack stay-listing and reservation application. The source was reconstructed from the supplied project export; secrets and cloud credentials are intentionally not included.

## Run locally

1. Create a PostgreSQL/PostGIS database using `backend/compose.yaml` or your own database.
2. Provide `DATABASE_PASSWORD` and `GOOGLE_MAPS_API_KEY`; `backend/src/main/resources/application-local.example.yaml` shows the expected local override.
3. Place your own Google Cloud service-account JSON at `backend/src/main/resources/credentials.json` if you want image uploads to Cloud Storage.
4. Start the backend from `backend` with a Java 21-compatible Gradle installation: `gradle bootRun`.
5. In `frontend`, run `npm install` and `npm start`.

The frontend production build has been verified with `npm run build`.
