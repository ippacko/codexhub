#!/usr/bin/env bash
set -e
echo "Seeding users via auth-service local port 8080 through gateway"
curl -s -X POST http://localhost:8080/auth/register -H "Content-Type: application/json" -d '{"email":"admin@codexhub.local","password":"admin123","role":"ADMIN"}' || true
echo
