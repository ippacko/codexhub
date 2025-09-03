.PHONY: up down build test seed

build:
	mvn -q -DskipTests package

test:
	mvn -q -DskipTests=false test

up:
	docker compose up -d --build

down:
	docker compose down -v

seed:
	./scripts/seed.sh
