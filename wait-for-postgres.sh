#!/bin/sh
echo "⏳ Waiting for PostgreSQL at db:5432..."

until pg_isready -h db -p 5432 -U myuser > /dev/null 2>&1; do
  echo "❌ Postgres is unavailable - sleeping"
  sleep 1
done

echo "✅ Postgres is up - starting app"
exec java -jar demo.jar